package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.models.Structures;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StructuresMapper extends EntityMapper<StructuresDto, Structures> {
}