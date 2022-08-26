package net.hypnoz.core.repositories;

import net.hypnoz.core.models.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationsRepository extends JpaRepository<Applications, String>{
}