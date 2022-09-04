package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.repository.ApplicationsRepository;
import net.hypnoz.core.repository.ModulesRepository;
import net.hypnoz.core.utils.FormatText;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;
import net.hypnoz.core.utils.exceptions.ResponseException;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ApplicationsService {
    private final ApplicationsRepository repository;
    private final ApplicationsMapper applicationsMapper;
    private final ModulesRepository modulesRepository;

    /*@Value("${application-url}")
    private String applicationUrl;*/
    public ApplicationsService(ApplicationsRepository repository, ApplicationsMapper applicationsMapper, ModulesRepository modulesRepository) {
        this.repository = repository;
        this.applicationsMapper = applicationsMapper;
        this.modulesRepository = modulesRepository;
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

    public List<ApplicationsDto> initApplication(Modules modules){
        Modules mod = modulesRepository.getReferenceById(modules.getId());
        try {
            Resource resource = new ClassPathResource("config/Applications.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<ApplicationsDto>> typeReference = new TypeReference<List<ApplicationsDto>>(){};
            List<ApplicationsDto> o = objectMapper.readValue(resource.getInputStream(),typeReference);
            return o.stream().filter(p-> Objects.equals(p.getModule(), modules.getCode())).map(_l->{
                Applications app = null;
                if(repository.findByCodeAndModule(_l.getCode(),_l.getModule()).isEmpty()){
                    app = applicationsMapper.toEntity(_l);
                    app.setModule(modules.getCode());
                    app.setModulesId(mod.getId());
                    app.setLibCode(FormatText.formatCode(_l.getLibCode()));
                    app.setOrdre(FormatText.getOrdre(_l.getCode()));
                    repository.saveAndFlush(app);
                }else{
                    app =repository.findByCodeAndModule(_l.getCode(),_l.getModule()).orElse(null);
                }
                return applicationsMapper.toDto(app);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResponseException(RequestErrorEnum.ERROR_INSERT_OR_UPDATE_IN_DATABASE);
        }
    }
}