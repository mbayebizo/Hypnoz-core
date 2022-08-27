package net.hypnoz.core.repository;

import net.hypnoz.core.models.Structures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StructuresRepository extends JpaRepository<Structures, Long>, JpaSpecificationExecutor<Structures> {
}