package br.com.lucassacerdote.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
/**
   * ID 
   * Usuário (ID_Usuario)
   * Descrição
   * Titulo
   * Data de inicio
   * Data de Término
   * 
   */
  @Data
  @Entity(name = "tb_tasks")
public class TaskModel {
  
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String description;
 
  @Column(length = 50)
  private String title;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;

  private UUID IdUser;
  @CreationTimestamp
  private LocalDateTime createdTime;

}
