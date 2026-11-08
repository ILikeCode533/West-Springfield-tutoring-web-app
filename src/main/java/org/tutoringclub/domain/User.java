package org.tutoringclub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_users")
public class User{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;
  
}