package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserFonctionsDto;
import net.hypnoz.core.models.UserFonctions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFonctionsMapper extends EntityMapper<UserFonctionsDto, UserFonctions> {
}