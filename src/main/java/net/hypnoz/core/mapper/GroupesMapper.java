package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.models.Groupes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupesMapper extends EntityMapper<GroupesDto, Groupes> {
}