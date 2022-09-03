package net.hypnoz.core.mapper;

import net.hypnoz.core.dto.GroupesApplicationsDto;
import net.hypnoz.core.models.GroupesApplications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupesApplicationsMapper extends EntityMapper<GroupesApplicationsDto, GroupesApplications> {
}