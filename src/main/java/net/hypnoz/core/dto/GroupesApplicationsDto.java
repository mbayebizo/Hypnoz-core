package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.GroupesApplications;

@ApiModel("Groupe Application DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GroupesApplicationsDto extends AbstractDto<GroupesApplications.GroupesApplicationsPK> {
   GroupesApplications.GroupesApplicationsPK id;
   Applications applications;
   Groupes groupes;
   boolean isNew;
}