package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.models.Modules;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModulesMapper extends EntityMapper<ModulesDto, Modules> {
}