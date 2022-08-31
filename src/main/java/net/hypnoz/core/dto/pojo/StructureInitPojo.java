package net.hypnoz.core.dto.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.annotation.CheckDate;

import java.time.LocalDate;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StructureInitPojo {
    String raisonSocial;
    String sigle;
    @CheckDate
    LocalDate dateFiscale;
    String typeEntreprise;
}
