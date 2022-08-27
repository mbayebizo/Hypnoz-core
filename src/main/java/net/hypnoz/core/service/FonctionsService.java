package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.FonctionsDto;
import net.hypnoz.core.mapper.FonctionsMapper;
import net.hypnoz.core.models.Fonctions;
import net.hypnoz.core.repository.FonctionsRepository;
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
public class FonctionsService {
    private final FonctionsRepository repository;
    private final FonctionsMapper fonctionsMapper;

    public FonctionsService(FonctionsRepository repository, FonctionsMapper fonctionsMapper) {
        this.repository = repository;
        this.fonctionsMapper = fonctionsMapper;
    }

    public FonctionsDto save(FonctionsDto fonctionsDto) {
        Fonctions entity = fonctionsMapper.toEntity(fonctionsDto);
        return fonctionsMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public FonctionsDto findById(long id) {
        return fonctionsMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<FonctionsDto> findByCondition(FonctionsDto fonctionsDto, Pageable pageable) {
        Page<Fonctions> entityPage = repository.findAll(pageable);
        List<Fonctions> entities = entityPage.getContent();
        return new PageImpl<>(fonctionsMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FonctionsDto update(FonctionsDto fonctionsDto, Long id) {
        FonctionsDto data = findById(id);
        Fonctions entity = fonctionsMapper.toEntity(fonctionsDto);
        BeanUtil.copyProperties(data, entity);
        return save(fonctionsMapper.toDto(entity));
    }
}