package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "modules")
@Where(clause = "active <> 'N'")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Modules implements Serializable {
    @Serial
    static final long serialVersionUID = -5256511258001979964L;
    @Id
    @Column(name = "code", nullable = false)
    String code;
    String libCode;
    String libDesc;
    String version;
    String versionDate;
    String url;
    String iconClass;
    String active;
    int ordre;

    @PrePersist
    public void beforePersist() {
        this.active = "Y";
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "str_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("structuresId")
    Structures structures;
}
