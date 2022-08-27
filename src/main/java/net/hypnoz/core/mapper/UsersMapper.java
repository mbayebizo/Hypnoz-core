package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.UsersDto;
import net.hypnoz.core.models.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper extends EntityMapper<UsersDto, Users> {
}