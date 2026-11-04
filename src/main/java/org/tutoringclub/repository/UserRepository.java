package org.tutoringclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutoringclub.domain.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  
}