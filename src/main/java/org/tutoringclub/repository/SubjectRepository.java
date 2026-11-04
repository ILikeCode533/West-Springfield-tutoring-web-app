package org.tutoringclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutoringclub.domain.Subject;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
  
}