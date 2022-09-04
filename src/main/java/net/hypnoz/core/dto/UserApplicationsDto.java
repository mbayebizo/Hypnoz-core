package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.models.UserApplications;
import net.hypnoz.core.models.Users;

@ApiModel("User Applications DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserApplicationsDto extends AbstractDto<UserApplications.UserApplicationsPK> {
   UserApplications.UserApplicationsPK id;
   Applications applications;
   Users users;
   boolean isNew;
    
}