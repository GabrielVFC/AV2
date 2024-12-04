package com.controller;

import com.dto.TaskDTO;
import com.model.Task;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{username}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable String username) {
        return ResponseEntity.ok(taskService.getTasksByUser(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @PathVariable String username) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        return ResponseEntity.ok(taskService.createTask(task, username));
    }

    @PutMapping("/{id}/{username}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO, @PathVariable String username) {
        Task updatedTask = new Task();
        updatedTask.setTitle(taskDTO.getTitle());
        updatedTask.setDescription(taskDTO.getDescription());
        updatedTask.setCompleted(taskDTO.isCompleted());
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask, username));
    }

    @DeleteMapping("/{id}/{username}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @PathVariable String username) {
        taskService.deleteTask(id, username);
        return ResponseEntity.ok("Tarefa excluída com sucesso.");
    }
}
