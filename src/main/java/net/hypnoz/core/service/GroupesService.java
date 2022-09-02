package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.mapper.GroupesMapper;
import net.hypnoz.core.mapper.GroupesModulesMapper;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.GroupesModules;
import net.hypnoz.core.models.ModulesStructure;
import net.hypnoz.core.models.Structures;
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
    private final GroupesModulesMapper groupesApplications;
    private final StructuresRepository structuresRepository;
    private final ModulesRepository modulesRepository;
    private final ApplicationsRepository applicationsRepository;
    private final FonctionsRepository fonctionsRepository;
    private final ModulesStructureRepository modulesStructureRepository;
    private final GroupesModulesRepository groupesmodulesRepository;


    public GroupesService(GroupesRepository repository, GroupesMapper groupesMapper, GroupesModulesMapper groupesApplications, StructuresRepository structuresRepository, ModulesRepository modulesRepository, ApplicationsRepository applicationsRepository, FonctionsRepository fonctionsRepository, ModulesStructureRepository modulesStructureRepository, GroupesModulesRepository groupesmodulesRepository) {
        this.repository = repository;
        this.groupesMapper = groupesMapper;
        this.groupesApplications = groupesApplications;
        this.structuresRepository = structuresRepository;
        this.modulesRepository = modulesRepository;
        this.applicationsRepository = applicationsRepository;
        this.fonctionsRepository = fonctionsRepository;
        this.modulesStructureRepository = modulesStructureRepository;
        this.groupesmodulesRepository = groupesmodulesRepository;
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
        Structures str = structuresRepository.getReferenceById(structures.getId());
        Groupes groupes = Groupes.builder()
                .code("ADM")
                .libelle("Administration")
                .structures(str)
                .build();
        repository.saveAndFlush(groupes);
        groupesModuleApplicationFonction(groupes);
        return groupesMapper.toDto(groupes);
    }


    private void groupesModuleApplicationFonction(Groupes groupes) {
        modulesStructureRepository.findById_StructuresId(groupes.getStructures().getId()).stream()
                .map(ModulesStructure::getModules)
                .filter(mod -> mod.getStandart() == HypnozCoreConstants.STANDARD)
                .peek(m -> {
                    GroupesModules groupesModules = GroupesModules.builder()
                            .id(GroupesModules.GroupesModulesPK.builder()
                                    .groupesId(groupes.getId())
                                    .modulesId(m.getId())
                                    .build())
                            .modules(m)
                            .groupes(groupes)
                            .build();
                    groupesmodulesRepository.saveAndFlush(groupesModules);
                });

    }
}