package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serial;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Table(name = "fonctions")
@Where(clause = "active <> 'N'")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fonctions extends AbstractEntity<Long> {
    @Serial
   static final long serialVersionUID = 8828858818361258181L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String code;
    private String libCode;
    private String libDesc;
    private String type;
    private String url;
    private String iconClass;
    private String actions;
    private String application;
    private String module;
   String active;
   int ordre;
   boolean used;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "mod_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("applicationsId")
    private Applications applications;
    @Override
    public void beforePrePersit() {
        super.beforePrePersit();
        this.active = "Y";
        this.used=true;
    }
}
