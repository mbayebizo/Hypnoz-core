package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users_applications", indexes = {
        @Index(name = "idx_userapplications_unq", columnList = "applications_id, users_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserApplications implements Persistable<UserApplications.UserApplicationsPK> {
    @EmbeddedId
    private UserApplicationsPK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "mod_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("applicationsId")
    private Applications applications;

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
    public static class UserApplicationsPK implements Serializable{
        @Serial
        private static final long serialVersionUID = 4736343511565176307L;
        private  Long applicationsId;
        private Long usersId;
    }

}
