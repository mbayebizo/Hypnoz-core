package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.ModulesDto;
import net.hypnoz.core.dtos.UsersDto;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.models.Users;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ModulesMapper {
     ModulesDto entityToDTO(Modules modules);
    Modules dtoToEntity(ModulesDto modulesDto);
    Set<ModulesDto> ListEntityToListDTO(Set<Modules> modules);
}
