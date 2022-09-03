package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.annotation.CheckDate;
import net.hypnoz.core.annotation.CheckEmail;
import net.hypnoz.core.annotation.CheckMobile;
import net.hypnoz.core.emus.TypeEntreprise;

import java.io.Serial;
import java.time.LocalDate;

@ApiModel("Structure Dto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class StructuresDto extends AbstractDto<Long> {
    @Serial
    long serialVersionUID = 5570157676629231277L;
    Long id;
    String raisonSocial;
    String sigle;
    String description;
    String zoneFiscale;
    String zoneFiscale2;
    @CheckDate
    LocalDate dateFiscale;
    TypeEntreprise typeEntreprise;
    String bilanSocail;
    String formJuridique;
    String adresse;
    String ville;
    String departement;
    String pays;
    String telephone;
    @CheckEmail
    String email;
    String siteweb;
    String logo;
    String activiteCommerciale;
    String responsable;
    String qualiteResponsable;
    String capital;
    
}