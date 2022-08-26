package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

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
public class Fonctions implements Serializable {
    @Serial
   static final long serialVersionUID = 8828858818361258181L;
    @Id
   String code;
    @Column
   String libCode;
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
