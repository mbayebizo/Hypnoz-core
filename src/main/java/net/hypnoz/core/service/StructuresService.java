package net.hypnoz.core.service;

import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.dto.pojo.StructureInitPojo;
import net.hypnoz.core.emus.TypeEntreprise;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.mapper.StructuresMapper;
import net.hypnoz.core.models.Structures;
import net.hypnoz.core.repository.StructuresRepository;
import net.hypnoz.core.utils.FilesUtils;
import net.hypnoz.core.utils.HypnozCoreConstants;
import net.hypnoz.core.utils.OsUtils;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;
import net.hypnoz.core.utils.exceptions.ResponseException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class StructuresService {
    private final StructuresRepository repository;
    private final StructuresMapper structuresMapper;
    private final ModulesService modulesService;
    private final ApplicationsService applicationsService;
    private final ModulesMapper modulesMapper;
    private final ApplicationsMapper applicationsMapper;
    private final FonctionsService fonctionsService;
    private final GroupesService groupesService;

    public StructuresService(StructuresRepository repository, StructuresMapper structuresMapper,
                             ModulesService modulesService, ApplicationsService applicationsService, ModulesMapper modulesMapper, ApplicationsMapper applicationsMapper, FonctionsService fonctionsService, GroupesService groupesService) {
        this.repository = repository;
        this.structuresMapper = structuresMapper;
        this.modulesService = modulesService;
        this.applicationsService = applicationsService;
        this.modulesMapper = modulesMapper;
        this.applicationsMapper = applicationsMapper;
        this.fonctionsService = fonctionsService;
        this.groupesService = groupesService;
    }

    public ResponseEntity<StructuresDto> save(StructuresDto structuresDto) {
        validationSigleRaisonSocial(structuresDto);
        Structures entity = structuresMapper.toEntity(structuresDto);
        repository.saveAndFlush(entity);
        return ResponseEntity.ok(structuresMapper.toDto(entity));
    }

    private void validationSigleRaisonSocial(@NotNull StructuresDto structuresDto) {
        if (structuresDto.getSigle().length() < 2 || structuresDto.getSigle().length() > 50) {
            throw new ResponseException(RequestErrorEnum.ERROR_SIGLE);
        }
        if (structuresDto.getRaisonSocial().length() < 2 || structuresDto.getRaisonSocial().length() > 150) {
            throw new ResponseException(RequestErrorEnum.ERROR_RAISON_SOCIAL);
        }
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public StructuresDto findById(long id) {
        return structuresMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<StructuresDto> findByCondition(StructuresDto structuresDto, Pageable pageable) {
        Page<Structures> entityPage = repository.findAll(pageable);
        List<Structures> entities = entityPage.getContent();
        return new PageImpl<>(structuresMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public StructuresDto update(StructuresDto structuresDto, Long id) {
        StructuresDto data = findById(id);
        Structures entity = structuresMapper.toEntity(structuresDto);
        BeanUtils.copyProperties(data, entity);
        return save(structuresMapper.toDto(entity)).getBody();
    }

    public ResponseEntity<StructuresDto> initConfigStructure(StructureInitPojo structureInitPojo) {
        return createStruncture(structureInitPojo);
    }

    private ResponseEntity<StructuresDto> createStruncture(StructureInitPojo structureInitPojo) {
        StructuresDto structuresDto = StructuresDto.builder()
                .sigle(structureInitPojo.getSigle())
                .raisonSocial(structureInitPojo.getRaisonSocial())
                .typeEntreprise(TypeEntreprise.SA)
                .dateFiscale(LocalDate.now())
                .build();
        ResponseEntity<StructuresDto> structureResponse = null;
        Optional<Structures> structuresOptional = repository.findBySigleAndRaisonSocial(structuresDto.getSigle(), structuresDto.getRaisonSocial());

        if (structuresOptional.isPresent()) {
             modulesService.initializeOrAddtModule(structuresOptional.get())
                     .stream()
                     .filter(modulesDto -> Objects.equals(modulesDto.getStandart(), HypnozCoreConstants.STANDARD))
                     .forEach(modulesDto -> applicationsService.initApplication(modulesMapper.toEntity(modulesDto)).stream().toList()
                             .forEach(applicationsDto -> fonctionsService.initFonction(applicationsMapper.toEntity(applicationsDto))));
             structureResponse = ResponseEntity.ok(structuresMapper.toDto(structuresOptional.get()));
            groupesService.initGroupe(structuresMapper.toEntity(structureResponse.getBody()));
        } else {
             structureResponse = save(structuresDto);
            modulesService.initializeOrAddtModule(structuresMapper.toEntity(structureResponse.getBody()))
                    .stream()
                    .filter(modulesDto -> Objects.equals(modulesDto.getStandart(), HypnozCoreConstants.STANDARD))
                    .forEach(modulesDto -> applicationsService.initApplication(modulesMapper.toEntity(modulesDto))
                           .forEach(applicationsDto -> fonctionsService.initFonction(applicationsMapper.toEntity(applicationsDto))));
            groupesService.initGroupe(structuresMapper.toEntity(structureResponse.getBody()));
        }

        createFolderSysteme();
        return structureResponse;

    }

    private void createFolderSysteme() {
        String path = OsUtils.getOsHomeDir();
        if(!FilesUtils.existed(path)){
            FilesUtils.mkdir(path);
        }
        if (FilesUtils.existed(path)) {
            if (!FilesUtils.existed(path + "/" + HypnozCoreConstants.DIR_TEMP)) {
                FilesUtils.mkdir(path + "/" + HypnozCoreConstants.DIR_TEMP);
            }
            if (!FilesUtils.existed(path + "/" + HypnozCoreConstants.DIR_LOG)) {
                FilesUtils.mkdir(path + "/" + HypnozCoreConstants.DIR_LOG);
            }

        }
    }

    public String uploadLogoSociete(MultipartFile file, Long strid, String logo){
        String pathImage = OsUtils.getOsHomeDir();
        String message="";
        String urlImage="";
        if (FilesUtils.existed(pathImage)) {
            if (!FilesUtils.existed(pathImage + "/structure" + strid + "/image/logos/")) {
                FilesUtils.mkdir(pathImage + "/structure" + strid + "/image/logos/");
            }
        }

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Path rootLocation = Paths.get(pathImage + "/structure"+strid+"/image/logos/"+logo+'.'+extension);
        try{
            if(FilesUtils.existed(rootLocation.toFile().getPath())){
                FilesUtils.deleteFile(rootLocation.toFile().getPath());
            }
            Files.copy(file.getInputStream(),rootLocation);
            return  urlImage =rootLocation.toFile().getPath();
        }catch (Exception e){
            return  urlImage = "";
        }
    }
}