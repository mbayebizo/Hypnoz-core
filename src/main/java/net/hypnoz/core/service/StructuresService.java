package net.hypnoz.core.service;

import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.mapper.StructuresMapper;
import net.hypnoz.core.models.Structures;
import net.hypnoz.core.repository.StructuresRepository;
import org.springframework.beans.BeanUtils;
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
public class StructuresService {
    private final StructuresRepository repository;
    private final StructuresMapper structuresMapper;

    public StructuresService(StructuresRepository repository, StructuresMapper structuresMapper) {
        this.repository = repository;
        this.structuresMapper = structuresMapper;
    }

    public StructuresDto save(StructuresDto structuresDto) {
        Structures entity = structuresMapper.toEntity(structuresDto);
        return structuresMapper.toDto(repository.save(entity));
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
        return save(structuresMapper.toDto(entity));
    }
}