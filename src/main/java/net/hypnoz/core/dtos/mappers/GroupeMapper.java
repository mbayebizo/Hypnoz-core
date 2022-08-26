package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.GroupesDto;
import net.hypnoz.core.models.Groupes;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupeMapper {
    GroupesDto entityToDTO(Groupes groupes);
    Groupes dtoToEntity(GroupesDto groupesDte);
    Set<GroupesDto> ListEntityToListDTO(Set<Groupes> Groupes);

}
