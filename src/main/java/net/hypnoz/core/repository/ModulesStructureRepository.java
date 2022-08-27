package net.hypnoz.core.repository;

import net.hypnoz.core.models.ModulesStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulesStructureRepository extends JpaRepository<ModulesStructure, ModulesStructure.ModulesStructurePK>, JpaSpecificationExecutor<ModulesStructure> {
}