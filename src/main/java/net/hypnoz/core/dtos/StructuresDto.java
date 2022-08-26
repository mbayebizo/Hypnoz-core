package net.hypnoz.core.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class StructuresDto implements Serializable {
    @Serial
     static final long serialVersionUID=1L;
     Long strId;
     String strRaisonSocial;
     String strSigle;
     String strDescription;
     String strZoneFiscale;
     String strZoneFiscale2;
     LocalDate strDateFiscale;
     String strTypeEntreprise;
     String strBilanSocail;
     String strFormJuridique;
     String strAdresse;
     String strVille;
     String strDepartement;
     String strPays;
     String strTelephone;
     String strEmail;
     String strSiteweb;
     String strLogo;
     String strActiviteCommerciale;
     String strResponsable;
     String strQualiteResponsable;
     String strCapital;
}
