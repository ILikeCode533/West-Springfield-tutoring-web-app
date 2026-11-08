package org.tutoringclub.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Subject{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID subjectId;
  
  @Column(nullable = false, unique = true)
  private String name; 
}