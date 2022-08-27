package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.UsersDto;
import net.hypnoz.core.mapper.UsersMapper;
import net.hypnoz.core.models.Users;
import net.hypnoz.core.repository.UsersRepository;
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
public class UsersService {
    private final UsersRepository repository;
    private final UsersMapper usersMapper;

    public UsersService(UsersRepository repository, UsersMapper usersMapper) {
        this.repository = repository;
        this.usersMapper = usersMapper;
    }

    public UsersDto save(UsersDto usersDto) {
        Users entity = usersMapper.toEntity(usersDto);
        return usersMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public UsersDto findById(Long id) {
        return usersMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<UsersDto> findByCondition(UsersDto usersDto, Pageable pageable) {
        Page<Users> entityPage = repository.findAll(pageable);
        List<Users> entities = entityPage.getContent();
        return new PageImpl<>(usersMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public UsersDto update(UsersDto usersDto, Long id) {
        UsersDto data = findById(id);
        Users entity = usersMapper.toEntity(usersDto);
        BeanUtil.copyProperties(data, entity);
        return save(usersMapper.toDto(entity));
    }
}