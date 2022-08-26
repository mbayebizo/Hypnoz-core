package net.hypnoz.core.dtos.mappers;

import net.hypnoz.core.dtos.GroupesDto;
import net.hypnoz.core.dtos.UsersDto;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.Users;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UsersDto entityToDTO(Users users);
    Users dtoToEntity(UsersDto usersDto);
    Set<UsersDto> ListEntityToListDTO(Set<Users> users);
}
