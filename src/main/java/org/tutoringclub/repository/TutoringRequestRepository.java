package org.tutoringclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutoringclub.domain.TutoringRequest;
import java.util.UUID;

public interface TutoringRequestRepository extends JpaRepository<TutoringRequest, UUID> {
  
}