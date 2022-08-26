package net.hypnoz.core.dtos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UsersDto implements Serializable {
     @Serial
     static final long serialVersionUID=1L;
     Long usrId;
     String usrCivilite;
     String usrNom;
     String usrPrenom;
     String usrPatronyme;
     String usrCodeSecret;
     int usrConnexion;
     LocalDate usrDateNaissance;
     String usrLangue;
     String usrAdresse;
     String usrBp;
     String usrVille;
     String usrTelBureau;
     String usrTelDomicile;
     String usrCel;
     LocalDate usrLastLog;
     LocalDate usrFirstLog;
     String usrPhoto;
     String usrSignature;
     String usrLogin;
     String usrPwd;
     GroupesDto groupesDto;
}
