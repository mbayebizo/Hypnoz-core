package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.mapper.GroupesMapper;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.repository.GroupesRepository;
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

    public GroupesService(GroupesRepository repository, GroupesMapper groupesMapper) {
        this.repository = repository;
        this.groupesMapper = groupesMapper;
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
}