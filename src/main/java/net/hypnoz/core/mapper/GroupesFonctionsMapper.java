package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.GroupesFonctionsDto;
import net.hypnoz.core.models.GroupesFonctions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupesFonctionsMapper extends EntityMapper<GroupesFonctionsDto, GroupesFonctions> {
}