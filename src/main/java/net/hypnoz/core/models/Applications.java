package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "active <> 'N'")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Applications implements Serializable {
    static final long serialVersionUID = 2657700055123057816L;

    @Id
    String code;
    @Column
    String libCode;
    String libDesc;
    String url;
    String iconClass;
    String module;
    String active;
    int ordre;

    @PrePersist
    public void beforePersist() {
        this.active = "Y";
    }

}
