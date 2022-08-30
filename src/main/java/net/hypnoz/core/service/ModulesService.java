package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.repository.ModulesRepository;
import net.hypnoz.core.repository.StructuresRepository;
import net.hypnoz.core.utils.FormatText;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;
import net.hypnoz.core.utils.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ModulesService {
    private final ModulesRepository repository;
    private final ModulesMapper modulesMapper;
    private final StructuresRepository structuresRepository;

    @Value("${module-url}")
    private String moduleUrl;

    public ModulesService(ModulesRepository repository, ModulesMapper modulesMapper, StructuresRepository structuresRepository) {
        this.repository = repository;
        this.modulesMapper = modulesMapper;
        this.structuresRepository = structuresRepository;
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

    public List<ModulesDto> initializeOrAddtModule(long strId){
        try {
            var s = structuresRepository.findById(strId);
            if(s.isPresent()){
                Resource resource = new ClassPathResource(moduleUrl);
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<ModulesDto>> typeReference = new TypeReference<List<ModulesDto>>(){};
                List<ModulesDto> o = objectMapper.readValue(resource.getInputStream(),typeReference);
                return o.stream().map(_l->{
                    Modules mod = modulesMapper.toEntity(_l);
                    mod.setStructures(s.get());
                    mod.setLibCode(FormatText.formatCode(_l.getLibCode()));
                    mod.setOrdre(FormatText.getOrdre(_l.getCode()));
                    return modulesMapper.toDto(repository.saveAndFlush(mod));
                }).collect(Collectors.toList());
            }
           return null;
        } catch (IOException e) {
            throw new ResponseException(RequestErrorEnum.ERROR_INSERT_OR_UPDATE_IN_DATABASE);
        }
    }
}