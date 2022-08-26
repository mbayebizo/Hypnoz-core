package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.ApplicationsDto;
import net.hypnoz.core.models.Applications;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationsDto entityToDto(Applications application);
    Applications dtoToEntity(ApplicationsDto applicationDto);
    Set<ApplicationsDto> listEntityToListDto(Set<Applications> applications);
}
