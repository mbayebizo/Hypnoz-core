package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.models.ModulesStructure;
import net.hypnoz.core.models.Structures;

@ApiModel("module structure")
public class ModulesStructureDto extends AbstractDto<ModulesStructure.ModulesStructurePK> {
    private ModulesStructure.ModulesStructurePK id;
    private Modules modules;
    private Structures structures;
    private boolean isNew;

    public ModulesStructureDto() {
    }

    public void setId(net.hypnoz.core.models.ModulesStructure.ModulesStructurePK id) {
        this.id = id;
    }

    public net.hypnoz.core.models.ModulesStructure.ModulesStructurePK getId() {
        return this.id;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public Modules getModules() {
        return this.modules;
    }

    public void setStructures(Structures structures) {
        this.structures = structures;
    }

    public Structures getStructures() {
        return this.structures;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getIsNew() {
        return this.isNew;
    }
}