package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.repository.ApplicationsRepository;
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
public class ApplicationsService {
    private final ApplicationsRepository repository;
    private final ApplicationsMapper applicationsMapper;

    public ApplicationsService(ApplicationsRepository repository, ApplicationsMapper applicationsMapper) {
        this.repository = repository;
        this.applicationsMapper = applicationsMapper;
    }

    public ApplicationsDto save(ApplicationsDto applicationsDto) {
        Applications entity = applicationsMapper.toEntity(applicationsDto);
        return applicationsMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public ApplicationsDto findById(long id) {
        return applicationsMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ApplicationsDto> findByCondition(ApplicationsDto applicationsDto, Pageable pageable) {
        Page<Applications> entityPage = repository.findAll(pageable);
        List<Applications> entities = entityPage.getContent();
        return new PageImpl<>(applicationsMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ApplicationsDto update(ApplicationsDto applicationsDto, Long id) {
        ApplicationsDto data = findById(id);
        Applications entity = applicationsMapper.toEntity(applicationsDto);
        BeanUtil.copyProperties(data, entity);
        return save(applicationsMapper.toDto(entity));
    }
}