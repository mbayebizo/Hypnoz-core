package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.emus.TypeEntreprise;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "groupes")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "{id}")
@Builder
@SQLDelete(sql = "UPDATE groupes SET flag_etat = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Groupes extends AbstractEntity {
    @Serial
    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String libelle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "str_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("structuresId")
    Structures structures;

}
