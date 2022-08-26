package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.StructuresDto;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.Structures;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface StructureMapper {
    StructuresDto entityToDTO(Structures structures);
    Structures dtoToEntity(StructuresDto structuresDto);
    Set<StructuresDto> ListEntityToListDTO(Set<Structures> structures);

}
