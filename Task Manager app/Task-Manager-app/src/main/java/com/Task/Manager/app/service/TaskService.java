package com.Task.Manager.app.service;

import com.Task.Manager.app.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task createTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }

        Task task = new Task(idCounter++, title);
        tasks.add(task);
        return task;
    }

    public Task toggleTask(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(task -> {
                    task.setCompleted(!task.isCompleted());
                    return task;
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (!removed) {
            throw new RuntimeException("Task not found");
        }
    }
}