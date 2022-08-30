package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserModulesDto;
import net.hypnoz.core.models.UserModules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserModulesMapper extends EntityMapper<UserModulesDto, UserModules> {
}