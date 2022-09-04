package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.models.UserModules;
import net.hypnoz.core.models.Users;

@ApiModel("User Module")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserModulesDto extends AbstractDto<UserModules.UserModulesPK> {
   UserModules.UserModulesPK id;
   Modules modules;
   Users users;
   boolean isNew;
    
}