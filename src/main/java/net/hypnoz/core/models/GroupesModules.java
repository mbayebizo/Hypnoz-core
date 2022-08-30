package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "groupes_modules", indexes = {
        @Index(name = "idx_groupesmodules_unq", columnList = "modules_id, groupes_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupesModules implements Persistable<GroupesModules.GroupesModulesPK> {
    @EmbeddedId
    private GroupesModulesPK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "mod_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("modulesId")
    private Modules modules;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "grp_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("groupesId")
    private Groupes groupes;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    protected void markNotNew() {
        this.isNew = false;
    }


    @Embeddable
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class GroupesModulesPK implements Serializable {
        private Long modulesId;
        private Long groupesId;

    }

}


