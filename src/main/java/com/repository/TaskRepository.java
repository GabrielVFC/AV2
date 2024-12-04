package com.repository;

import com.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserUsername(String username); // Busca tarefas pelo nome de usuário
}