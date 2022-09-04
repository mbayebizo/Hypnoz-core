package net.hypnoz.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.UsersDto;
import net.hypnoz.core.mapper.UsersMapper;
import net.hypnoz.core.models.*;
import net.hypnoz.core.repository.*;
import net.hypnoz.core.utils.HypnozCoreConstants;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UsersService {
    private final UsersRepository repository;
    private final UsersMapper usersMapper;
    private final UserGroupesRepository userGroupesRepository;
    private final ModulesStructureRepository modulesStructureRepository;
    private final UserModulesRepository userModulesRepository;
    private final UserApplicationsRepository userApplicationsRepository;
    private final UserFonctionsRepository userFonctionsRepository;
    private final ApplicationsRepository applicationsRepository;
    private final FonctionsRepository fonctionsRepository;

    public UsersService(UsersRepository repository, UsersMapper usersMapper, UserGroupesRepository userGroupesRepository, ModulesStructureRepository modulesStructureRepository, UserModulesRepository userModulesRepository, UserApplicationsRepository userApplicationsRepository, UserFonctionsRepository userFonctionsRepository, ApplicationsRepository applicationsRepository, FonctionsRepository fonctionsRepository) {
        this.repository = repository;
        this.usersMapper = usersMapper;
        this.userGroupesRepository = userGroupesRepository;
        this.modulesStructureRepository = modulesStructureRepository;
        this.userModulesRepository = userModulesRepository;
        this.userApplicationsRepository = userApplicationsRepository;
        this.userFonctionsRepository = userFonctionsRepository;
        this.applicationsRepository = applicationsRepository;
        this.fonctionsRepository = fonctionsRepository;
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



    public UsersDto update(UsersDto usersDto, Long id) {
        UsersDto data = findById(id);
        Users entity = usersMapper.toEntity(usersDto);
        BeanUtil.copyProperties(data, entity);
        return save(usersMapper.toDto(entity));
    }

    public void initUser(Groupes groupes) {
        Users users = Users.builder()
                .patronyme("Admin")
                .login("admin")
                .build();
       Optional<Users> users1=  repository.findByLogin(users.getLogin());
       if(users1.isPresent()) {
           initModuleapplicationFonction(users1.get(),groupes);
           return;
       }
       repository.saveAndFlush(users);

       userGroupesRepository.saveAndFlush(UserGroupes.builder()
                       .id(UserGroupes.UserGroupesPK.builder()
                               .usersId(users.getId())
                               .groupesId(groupes.getId())
                               .build())
                       .groupes(groupes)
                       .users(users)
               .build());
        initModuleapplicationFonction(users
                ,groupes);
    }

    private void initModuleapplicationFonction(Users users, Groupes groupes) {
        List<Modules> moduleList = modulesStructureRepository.findById_StructuresId(groupes.getStructuresId())
                .stream()
                .map(ModulesStructure::getModules)
                .filter(mod -> mod.getStandart() == HypnozCoreConstants.STANDARD).toList();




        moduleList.forEach(modules -> {
            userModulesRepository.saveAndFlush(UserModules.builder()
                    .id(UserModules.UserModulesPK.builder()
                            .usersId(users.getId())
                            .modulesId(modules.getId())
                            .build())
                    .modules(modules)
                    .users(users)
                    .build());

            applicationsRepository.findByModulesId(modules.getId()).forEach(applications ->{
                userApplicationsRepository.saveAndFlush(UserApplications.builder()
                        .id(UserApplications.UserApplicationsPK.builder()
                                .applicationsId(applications.getId())
                                .usersId(users.getId())
                                .build())
                        .applications(applications)
                        .users(users)
                        .build());

                fonctionsRepository.findByApplicationsId(applications.getId()).forEach(fonctions -> userFonctionsRepository.saveAndFlush(UserFonctions.builder()
                        .id(UserFonctions.UserFonctionsPK.builder()
                                .fonctionsId(fonctions.getId())
                                .usersId(users.getId())
                                .build())
                        .fonctions(fonctions)
                        .users(users)
                        .build()));

            });
        });



    }
}