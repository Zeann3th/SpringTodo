package dev.zeann3th.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zeann3th.todo.domain.Status;
import dev.zeann3th.todo.domain.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

  List<Task> findByStatus(Status status);

}
