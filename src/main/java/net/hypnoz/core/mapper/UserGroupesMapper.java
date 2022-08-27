package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserGroupesDto;
import net.hypnoz.core.models.UserGroupes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserGroupesMapper extends EntityMapper<UserGroupesDto, UserGroupes> {
}