package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "modules")
@Where(clause = "active <> 'N'")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Modules extends AbstractEntity<Long> {
    @Serial
    static final long serialVersionUID = -8557507909072700306L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String libCode;
    String libDesc;
    String version;
    LocalDate versionDate;
    String url;
    String iconClass;
    String active;
    int standart;
    int ordre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "str_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("structuresId")
    Structures structures;

    @Override
    public void beforePrePersit() {
        super.beforePrePersit();
        this.active = "Y";
    }
}
