package dev.zeann3th.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.zeann3th.todo.domain.Status;
import dev.zeann3th.todo.domain.Task;
import dev.zeann3th.todo.exception.AppException;
import dev.zeann3th.todo.repository.TaskRepository;

@Service
public class TaskService {

  @Autowired
  TaskRepository taskRepository;

  public ResponseEntity<List<Task>> getAllTasks() {
    try {
      return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      throw new AppException(500, "Error fetching tasks");
    }
  }

  public ResponseEntity<Task> createTask(Task task) {
    try {
      return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new AppException(500, "Error creating task");
    }
  }

  public ResponseEntity<Task> getTaskById(String id) {
    try {
      Optional<Task> task = taskRepository.findById(id);
      if (task.isPresent()) {
        return new ResponseEntity<>(task.get(), HttpStatus.CREATED);
      } else {
        throw new AppException(404, "Task not found");
      }
    } catch (Exception e) {
      throw new AppException(500, "Error fetching task");
    }
  }

  public ResponseEntity<List<Task>> getTasksByStatus(Status status) {
    try {
      return new ResponseEntity<>(taskRepository.findByStatus(status), HttpStatus.OK);
    } catch (Exception e) {
      throw new AppException(400, "Error fetching tasks by status");
    }
  }

  public ResponseEntity<Task> updateTask(String id, Task task) {
    try {
      Task existingTask = taskRepository.findById(id).orElse(null);
      if (existingTask == null) {
        throw new AppException(404, "Task not found");
      }
      existingTask.setTitle(task.getTitle());
      existingTask.setDescription(task.getDescription());
      existingTask.setStatus(task.getStatus());
      return new ResponseEntity<>(taskRepository.save(existingTask), HttpStatus.OK);
    } catch (Exception e) {
      throw new AppException(500, "Error updating task");
    }
  }

  public ResponseEntity<Void> deleteTask(String id) {
    try {
      Task existingTask = taskRepository.findById(id).orElse(null);
      if (existingTask == null) {
        throw new AppException(404, "Task not found");
      }
      taskRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      throw new AppException(500, "Error deleting task");
    }
  }
}
