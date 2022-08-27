package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users_structure", indexes = {
        @Index(name = "idx_userstructures_unq", columnList = "structures_id, users_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStructures implements Persistable<UserStructures.UserStructuresPK> {
    @EmbeddedId
    private UserStructuresPK id;
    @ManyToOne(fetch =FetchType.EAGER )
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "str_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("structuresId")
    private Structures structures;

    @ManyToOne(fetch =FetchType.EAGER )
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "usr_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("usersId")
    private Users users;


    @Transient
    private boolean isNew=true;
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    protected void markNotNew() {
        this.isNew=false;
    }



    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserStructuresPK implements Serializable{
        @Serial
        private static final long serialVersionUID = 4736343511565176307L;
        private  Long structuresId;
        private Long usersId;
    }

}
