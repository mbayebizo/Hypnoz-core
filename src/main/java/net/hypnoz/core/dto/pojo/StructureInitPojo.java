package net.hypnoz.core.dto.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StructureInitPojo {
    String raisonSocial;
    String sigle;
}
