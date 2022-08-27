package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.FonctionsDto;
import net.hypnoz.core.models.Fonctions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FonctionsMapper extends EntityMapper<FonctionsDto, Fonctions> {
}