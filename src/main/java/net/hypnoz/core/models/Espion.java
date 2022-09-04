package net.hypnoz.core.models;

import lombok.*;
import net.hypnoz.core.emus.TypeAction;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "espions")
@Where(clause = "flag_etat <> 'DELETED'")
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE espions SET flag_etat = 'DELETED' WHERE esp_id = ?", check = ResultCheckStyle.COUNT)
public class Espion extends AbstractEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreat;
    private TypeAction espAction;
    private int espType;
    private Long espIdTiers;
    private Long usrId;
    private String username;
    private Long idDoc;
    private String nameDoc;
}
