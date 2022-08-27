package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users_groupes", indexes = {
        @Index(name = "idx_usergroupes_unq", columnList = "groupes_id, users_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroupes implements Persistable<UserGroupes.UserGroupesPK> {
    @EmbeddedId
    private UserGroupesPK id;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "grp_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("groupesId")
    private Groupes groupes;

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
    public static class UserGroupesPK implements Serializable{
        @Serial
        private static final long serialVersionUID = 4736343511565176307L;
        private  Long groupes_id;
        private Long users_id;
    }

}
