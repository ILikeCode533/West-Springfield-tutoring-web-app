package org.tutoringclub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;

@Entity
public class User{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;
  
}