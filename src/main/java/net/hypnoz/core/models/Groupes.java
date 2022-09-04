package net.hypnoz.core.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serial;

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
public class Groupes extends AbstractEntity<Long> {
    @Serial
    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String libelle;
    @Column(name = "structures_id")
    Long structuresId;

}
