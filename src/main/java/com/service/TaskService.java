package com.service;

import com.model.Task;
import com.repository.TaskRepository;
import com.repository.UserRepository;
import com.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
     @Autowired
    private UserRepository userRepository;

    public List<Task> getTasksByUser(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        task.setUser(user);
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task updatedTask, String username) {
        Task task = taskRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Você não tem permissão para editar esta tarefa.");
        }
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }
    
    public void deleteTask(Long id, String username) {
        Task task = taskRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Você não tem permissão para excluir esta tarefa.");
        }
        taskRepository.delete(task);
    }
    

    // Outros métodos para criar, atualizar, excluir tarefas
}
