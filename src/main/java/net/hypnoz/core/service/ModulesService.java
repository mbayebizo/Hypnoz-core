package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.repository.ModulesRepository;
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
public class ModulesService {
    private final ModulesRepository repository;
    private final ModulesMapper modulesMapper;

    public ModulesService(ModulesRepository repository, ModulesMapper modulesMapper) {
        this.repository = repository;
        this.modulesMapper = modulesMapper;
    }

    public ModulesDto save(ModulesDto modulesDto) {
        Modules entity = modulesMapper.toEntity(modulesDto);
        return modulesMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public ModulesDto findById(long id) {
        return modulesMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ModulesDto> findByCondition(ModulesDto modulesDto, Pageable pageable) {
        Page<Modules> entityPage = repository.findAll(pageable);
        List<Modules> entities = entityPage.getContent();
        return new PageImpl<>(modulesMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ModulesDto update(ModulesDto modulesDto, Long id) {
        ModulesDto data = findById(id);
        Modules entity = modulesMapper.toEntity(modulesDto);
        BeanUtil.copyProperties(data, entity);
        return save(modulesMapper.toDto(entity));
    }
}