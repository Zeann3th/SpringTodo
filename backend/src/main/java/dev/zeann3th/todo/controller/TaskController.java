package dev.zeann3th.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.zeann3th.todo.domain.Status;
import dev.zeann3th.todo.domain.Task;
import dev.zeann3th.todo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  TaskService taskService;

  @GetMapping
  public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) Status status) {
    if (status != null) {
      return taskService.getTasksByStatus(status);
    }
    return taskService.getAllTasks();
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    return taskService.createTask(task);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable String id) {
    return taskService.getTaskById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
    return taskService.updateTask(id, task);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable String id) {
    return taskService.deleteTask(id);
  }

}
