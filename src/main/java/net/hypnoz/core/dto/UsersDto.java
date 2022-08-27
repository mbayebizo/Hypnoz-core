package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.annotation.CheckDate;
import net.hypnoz.core.models.Groupes;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.time.LocalDate;

@ApiModel("User Dto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class UsersDto extends AbstractDto<Long> {
    @Serial
    static final long serialVersionUID = -2251460200601550695L;
    Long id;
    String civilite;
    String nom;
    String prenom;
    String patronyme;
    String codeSecret;
    int connexion;
    @CheckDate
    LocalDate dateNaissance;
    String langue;
    String adresse;
    String bp;
    String ville;
    String telBureau;
    String telDomicile;
    String cel;
    @CheckDate
    LocalDate lastLog;
    @CheckDate
    LocalDate firstLog;
    String photo;
    String signature;
    @Size(max = 255)
    String login;
    @Size(max = 255)
    String pwd;
    Groupes groupes;
    
}