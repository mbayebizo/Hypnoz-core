package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.GroupesApplicationsDto;
import net.hypnoz.core.models.GroupesApplications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupesApplicationsMapper extends EntityMapper<GroupesApplicationsDto, GroupesApplications> {
}