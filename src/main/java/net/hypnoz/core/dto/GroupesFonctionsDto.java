package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Fonctions;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.GroupesFonctions;

@ApiModel("Groupe Functions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GroupesFonctionsDto extends AbstractDto<GroupesFonctions.GroupesFonctionsPK> {
     GroupesFonctions.GroupesFonctionsPK id;
     Fonctions fonctions;
     Groupes groupes;
     boolean isNew;

}