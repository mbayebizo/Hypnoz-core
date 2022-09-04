package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.FonctionsDto;
import net.hypnoz.core.mapper.FonctionsMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.models.Fonctions;
import net.hypnoz.core.repository.ApplicationsRepository;
import net.hypnoz.core.repository.FonctionsRepository;
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
public class FonctionsService {
    private final FonctionsRepository repository;
    private final FonctionsMapper fonctionsMapper;
    private final ApplicationsRepository applicationsRepository;
   /* @Value("${fonction-url}")
    private String fonctionUrl;*/

    public FonctionsService(FonctionsRepository repository, FonctionsMapper fonctionsMapper, ApplicationsRepository applicationsRepository) {
        this.repository = repository;
        this.fonctionsMapper = fonctionsMapper;
        this.applicationsRepository = applicationsRepository;
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

    public List<FonctionsDto> initFonction(Applications applications){
        Applications app = applicationsRepository.getReferenceById(applications.getId());
        try {
            Resource resource = new ClassPathResource("config/fonctions.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<FonctionsDto>> typeReference = new TypeReference<List<FonctionsDto>>(){};
            List<FonctionsDto> o = objectMapper.readValue(resource.getInputStream(),typeReference);
            return o.stream().filter(p-> Objects.equals(p.getModule(),applications.getModule())&& Objects.equals(p.getApplication(), applications.getCode())).map(_l->{
                Fonctions fonctions = fonctionsMapper.toEntity(_l);
                fonctions.setApplication(app.getCode());
                fonctions.setApplicationsId(app.getId());
                fonctions.setLibCode(FormatText.formatCode(_l.getLibCode()));
                fonctions.setOrdre(FormatText.getOrdre(_l.getCode()));
                repository.saveAndFlush(fonctions);
                return fonctionsMapper.toDto(fonctions);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResponseException(RequestErrorEnum.ERROR_INSERT_OR_UPDATE_IN_DATABASE,e);
        }
    }
}