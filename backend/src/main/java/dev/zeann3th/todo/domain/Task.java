package dev.zeann3th.todo.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String title;
  private String description;
  private Integer priority;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(updatable = false)
  private Date createdAt;
  private Date updatedAt;
  private Date completedAt;
  private Date dueDate;

  @PrePersist
  protected void onCreate() {
    if (status == null) {
      status = Status.PENDING;
    }
    createdAt = new Date();
    updatedAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();

    // Handle task completion logic
    if (status == Status.COMPLETED && completedAt == null) {
      completedAt = new Date();

      // Check if the task was completed late
      if (dueDate != null && completedAt.after(dueDate)) {
        status = Status.LATE;
      }
    }
  }
}
