package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.GroupesModules;
import net.hypnoz.core.models.Modules;

@ApiModel("Groupe Module DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GroupesModulesDto extends AbstractDto<GroupesModules.GroupesModulesPK> {
  GroupesModules.GroupesModulesPK id;
  Modules modules;
  Groupes groupes;
  boolean isNew;
}