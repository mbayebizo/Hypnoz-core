package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UserFonctionsDto;
import net.hypnoz.core.models.UserFonctions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFonctionsMapper extends EntityMapper<UserFonctionsDto, UserFonctions> {
}