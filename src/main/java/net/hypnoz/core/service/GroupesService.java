package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.mapper.GroupesMapper;
import net.hypnoz.core.models.*;
import net.hypnoz.core.repository.*;
import net.hypnoz.core.utils.HypnozCoreConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class GroupesService {
    private final GroupesRepository repository;
    private final GroupesMapper groupesMapper;
    private final StructuresRepository structuresRepository;
    private final ApplicationsRepository applicationsRepository;
    private final FonctionsRepository fonctionsRepository;
    private final ModulesStructureRepository modulesStructureRepository;
    private final GroupesModulesRepository groupesmodulesRepository;
    private final GroupesApplicationsRepository groupesApplicationsRepository;
    private final GroupesFonctionsRepository groupesFonctionsRepository;
    private final UsersService usersService;

    public GroupesService(GroupesRepository repository, GroupesMapper groupesMapper, StructuresRepository structuresRepository,
                          ApplicationsRepository applicationsRepository, FonctionsRepository fonctionsRepository,
                          ModulesStructureRepository modulesStructureRepository, GroupesModulesRepository groupesmodulesRepository, GroupesApplicationsRepository groupesApplicationsRepository, GroupesFonctionsRepository groupesFonctionsRepository, UsersService usersService) {
        this.repository = repository;
        this.groupesMapper = groupesMapper;
        this.structuresRepository = structuresRepository;
        this.applicationsRepository = applicationsRepository;
        this.fonctionsRepository = fonctionsRepository;
        this.modulesStructureRepository = modulesStructureRepository;
        this.groupesmodulesRepository = groupesmodulesRepository;
        this.groupesApplicationsRepository = groupesApplicationsRepository;
        this.groupesFonctionsRepository = groupesFonctionsRepository;
        this.usersService = usersService;
    }


    public GroupesDto save(GroupesDto groupesDto) {
        Groupes entity = groupesMapper.toEntity(groupesDto);
        return groupesMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public GroupesDto findById(long id) {
        return groupesMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<GroupesDto> findByCondition(GroupesDto groupesDto, Pageable pageable) {
        Page<Groupes> entityPage = repository.findAll(pageable);
        List<Groupes> entities = entityPage.getContent();
        return new PageImpl<>(groupesMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public GroupesDto update(GroupesDto groupesDto, Long id) {
        GroupesDto data = findById(id);
        Groupes entity = groupesMapper.toEntity(groupesDto);
        BeanUtil.copyProperties(data, entity);
        return save(groupesMapper.toDto(entity));
    }

    public GroupesDto initGroupe(Structures structures) {
        Groupes groupes = null;
        if (structuresRepository.findById(structures.getId()).isEmpty())
            return null;
        groupes = Groupes.builder()
                .code("ADM")
                .libelle("Administration")
                .structuresId(structures.getId())
                .build();
        repository.saveAndFlush(groupes);
        groupesModuleApplicationFonction(groupes);
        usersService.initUser(groupes);
        return groupesMapper.toDto(groupes);
    }


    private void groupesModuleApplicationFonction(Groupes groupes) {
        List<Modules> moduleList = modulesStructureRepository.findById_StructuresId(groupes.getStructuresId())
                .stream()
                .map(ModulesStructure::getModules)
                .filter(mod -> mod.getStandart() == HypnozCoreConstants.STANDARD).toList();




        moduleList.forEach(modules -> {
            groupesmodulesRepository.saveAndFlush(GroupesModules.builder()
                    .id(GroupesModules.GroupesModulesPK.builder()
                            .groupesId(groupes.getId())
                            .modulesId(modules.getId())
                            .build())
                    .modules(modules)
                    .groupes(groupes)
                    .build());
            applicationsRepository.findByModulesId(modules.getId()).forEach(applications ->{
                groupesApplicationsRepository.saveAndFlush(GroupesApplications.builder()
                        .id(GroupesApplications.GroupesApplicationsPK.builder()
                                .applicationsId(applications.getId())
                                .groupesId(groupes.getId())
                                .build())
                        .applications(applications)
                        .groupes(groupes)
                        .build());

                fonctionsRepository.findByApplicationsId(applications.getId()).forEach(fonctions -> {
                    groupesFonctionsRepository.saveAndFlush(GroupesFonctions.builder()
                            .id(GroupesFonctions.GroupesFonctionsPK.builder()
                                    .fonctionsId(fonctions.getId())
                                    .groupesId(groupes.getId())
                                    .build())
                            .fonctions(fonctions)
                            .groupes(groupes)
                            .build());
                });

            });
        });



    }
}