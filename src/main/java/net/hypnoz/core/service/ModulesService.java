package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.models.ModulesStructure;
import net.hypnoz.core.models.Structures;
import net.hypnoz.core.repository.ModulesRepository;
import net.hypnoz.core.repository.ModulesStructureRepository;
import net.hypnoz.core.utils.FormatText;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;
import net.hypnoz.core.utils.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ModulesService {
    private final ModulesRepository repository;
    private final ModulesMapper modulesMapper;
    private final ModulesStructureRepository modulesStructureRepository;

   @Value("${module-url}")
    private String moduleUrl;

    public ModulesService(ModulesRepository repository, ModulesMapper modulesMapper,
                          ModulesStructureRepository modulesStructureRepository) {
        this.repository = repository;
        this.modulesMapper = modulesMapper;
        this.modulesStructureRepository = modulesStructureRepository;
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


    public ModulesDto update(ModulesDto modulesDto, Long id) {
        ModulesDto data = findById(id);
        Modules entity = modulesMapper.toEntity(modulesDto);
        BeanUtil.copyProperties(data, entity);
        return save(modulesMapper.toDto(entity));
    }

    public List<ModulesDto> initializeOrAddtModule(Structures structures){
        try {
                Resource resource = new ClassPathResource("config/modules.json");
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<ModulesDto>> typeReference = new TypeReference<List<ModulesDto>>(){};
                List<ModulesDto> o = objectMapper.readValue(resource.getInputStream(),typeReference);
                return o.stream().map(modulesDto->{
                    Modules mod = null;
                    if (repository.findByCode(modulesDto.getCode()).isEmpty()){
                        mod = modulesMapper.toEntity(modulesDto);
                        mod.setLibCode(FormatText.formatCode(modulesDto.getLibCode()));
                        mod.setOrdre(FormatText.getOrdre(modulesDto.getCode()));
                        repository.saveAndFlush(mod);
                        ModulesStructure modulesStructure = ModulesStructure.builder()
                                .id(ModulesStructure.ModulesStructurePK.builder()
                                        .structuresId(structures.getId())
                                        .modulesId(mod.getId())
                                        .build())
                                .modules(mod)
                                .structures(structures)
                                .build();
                        modulesStructureRepository.saveAndFlush(modulesStructure);
                    }else {
                       mod = repository.findByCode(modulesDto.getCode()).orElse(null);
                    }
                    return modulesMapper.toDto(mod);
                }).toList();
        } catch (IOException e) {
            throw new ResponseException(RequestErrorEnum.ERROR_INSERT_OR_UPDATE_IN_DATABASE,e);
        }
    }
}