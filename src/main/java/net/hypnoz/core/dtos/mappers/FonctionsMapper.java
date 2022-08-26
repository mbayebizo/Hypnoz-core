package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.FonctionsDto;
import net.hypnoz.core.models.Fonctions;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FonctionsMapper {
    FonctionsDto entityToDto(Fonctions fonctions);
    Fonctions dtoToEntity(FonctionsDto fonctionsDto);
    Set<FonctionsDto> listEntityToListDto(Set<Fonctions> fonctions);
}
