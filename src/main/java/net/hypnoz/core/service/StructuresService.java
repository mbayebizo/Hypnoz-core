package net.hypnoz.core.service;

import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.dto.pojo.StructureInitPojo;
import net.hypnoz.core.emus.TypeEntreprise;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.mapper.StructuresMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.models.Structures;
import net.hypnoz.core.repository.StructuresRepository;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;
import net.hypnoz.core.utils.exceptions.ResponseException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    public StructuresService(StructuresRepository repository, StructuresMapper structuresMapper, ModulesService modulesService, ApplicationsService applicationsService, ModulesMapper modulesMapper, ApplicationsMapper applicationsMapper, FonctionsService fonctionsService) {
        this.repository = repository;
        this.structuresMapper = structuresMapper;
        this.modulesService = modulesService;
        this.applicationsService = applicationsService;
        this.modulesMapper = modulesMapper;
        this.applicationsMapper = applicationsMapper;
        this.fonctionsService = fonctionsService;
    }

    public ResponseEntity<StructuresDto> save(StructuresDto structuresDto) {
        validationSigleRaisonSocial(structuresDto);
        Structures entity = structuresMapper.toEntity(structuresDto);
        return ResponseEntity.ok( structuresMapper.toDto(repository.save(entity)));
    }

    private void validationSigleRaisonSocial(@NotNull StructuresDto structuresDto) {
        if (structuresDto.getSigle().length() < 2 || structuresDto.getSigle().length() > 50) {
            throw new ResponseException(RequestErrorEnum.ERROR_SIGLE);
        }
        if(structuresDto.getRaisonSocial().length()<2 || structuresDto.getRaisonSocial().length()>150){
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

    public ResponseEntity<Void> initConfigStructure(StructureInitPojo structureInitPojo){
        createStruncture(structureInitPojo);

        return ResponseEntity.ok().build();
    }

    private void createStruncture(StructureInitPojo structureInitPojo) {
        StructuresDto structuresDto = StructuresDto.builder()
                .sigle(structureInitPojo.getSigle())
                .raisonSocial(structureInitPojo.getRaisonSocial())
                .typeEntreprise(TypeEntreprise.valueOf(structureInitPojo.getTypeEntreprise()))
                .dateFiscale(structureInitPojo.getDateFiscale())
                .build();

        var structureResponse=save(structuresDto);

        var moduleDTO = modulesService.initializeOrAddtModule(structuresMapper.toEntity(structureResponse.getBody()));

       var applicationDTOs= moduleDTO.stream().filter(modulesDto -> modulesDto.getStandart()==0).map(mod-> applicationsService.initApplication(modulesMapper.toEntity(mod)));

       applicationDTOs.map(app-> fonctionsService.initFonction((Applications) applicationsMapper.toEntity(app)));

    }
}