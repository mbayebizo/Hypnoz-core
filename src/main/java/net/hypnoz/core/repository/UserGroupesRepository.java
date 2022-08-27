package net.hypnoz.core.repository;

import net.hypnoz.core.models.UserGroupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupesRepository extends JpaRepository<UserGroupes, UserGroupes.UserGroupesPK>, JpaSpecificationExecutor<UserGroupes> {
}