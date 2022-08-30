package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.GroupesModulesDto;
import net.hypnoz.core.models.GroupesModules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupesModulesMapper extends EntityMapper<GroupesModulesDto, GroupesModules> {
}