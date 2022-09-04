package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Fonctions;
import net.hypnoz.core.models.UserFonctions;
import net.hypnoz.core.models.Users;

@ApiModel("User Fonctions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UserFonctionsDto extends AbstractDto<UserFonctions.UserFonctionsPK> {
  UserFonctions.UserFonctionsPK id;
  Fonctions fonctions;
  Users users;
  boolean isNew;
    
}