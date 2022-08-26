package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "{id}")
@Builder
public class Users extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String civilite;
    String nom;
    String prenom;
    String patronyme;
    String codeSecret;
    int connexion;
    LocalDate dateNaissance;
    String langue;
    String adresse;
    String bp;
    String ville;
    String telBureau;
    String telDomicile;
    String cel;
    LocalDate lastLog;
    LocalDate firstLog;
    String photo;
    String signature;
    @Column
    String login;
    @Column
    String pwd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "grp_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("groupes_id")
    Groupes groupes;
}
