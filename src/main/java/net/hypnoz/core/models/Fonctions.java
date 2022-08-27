package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
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
   String code;
   String libDesc;
   String type;
   String url;
   String iconClass;
   String actions;
   String application;
   String module;
   String active;
   int ordre;
   boolean used;

    @PrePersist
    public void beforePersist() {
        this.active = "Y";
    }

}
