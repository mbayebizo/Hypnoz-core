package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserModulesDto;
import net.hypnoz.core.models.UserModules;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModulesMapper extends EntityMapper<UserModulesDto, UserModules> {
}