package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.models.Applications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationsMapper extends EntityMapper<ApplicationsDto, Applications> {
}