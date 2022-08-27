package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.ModulesStructureDto;
import net.hypnoz.core.models.ModulesStructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModulesStructureMapper extends EntityMapper<ModulesStructureDto, ModulesStructure> {
}