package net.hypnoz.core.models;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "module_structure", indexes = {
        @Index(name = "idx_modulesstructure_unq", columnList = "modules_id, structures_id", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModulesStructure implements Persistable<ModulesStructure.ModulesStructurePK> {
    @EmbeddedId
    private ModulesStructurePK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "mod_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("modulesId")
    private Modules modules;

    @ManyToOne(fetch =FetchType.EAGER )
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "str_fk", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false)
    @MapsId("structuresId")
    private Structures structures;

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
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder

    public static class ModulesStructurePK implements Serializable {
        private Long modulesId;
        private Long structuresId;

    }
}
