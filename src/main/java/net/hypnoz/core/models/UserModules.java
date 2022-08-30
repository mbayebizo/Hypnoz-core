package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users_modules", indexes = {
        @Index(name = "idx_usermodules_unq", columnList = "modules_id, users_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModules implements Persistable<UserModules.UserModulesPK> {
    @EmbeddedId
    private UserModulesPK id;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "mod_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("modulesId")
    private Modules modules;

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
    public static class UserModulesPK implements Serializable{
        @Serial
        private static final long serialVersionUID = -6704195049868174266L;
        private  Long modulesId;
        private Long usersId;
    }

}
