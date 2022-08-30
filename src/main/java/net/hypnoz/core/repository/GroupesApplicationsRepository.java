package net.hypnoz.core.repository;

import net.hypnoz.core.models.GroupesApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupesApplicationsRepository extends JpaRepository<GroupesApplications, GroupesApplications.GroupesApplicationsPK>, JpaSpecificationExecutor<GroupesApplications> {
}