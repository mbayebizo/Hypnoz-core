package net.hypnoz.core.repositories;

import net.hypnoz.core.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}