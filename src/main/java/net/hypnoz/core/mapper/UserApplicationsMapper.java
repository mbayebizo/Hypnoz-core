package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserApplicationsDto;
import net.hypnoz.core.models.UserApplications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserApplicationsMapper extends EntityMapper<UserApplicationsDto, UserApplications> {
}