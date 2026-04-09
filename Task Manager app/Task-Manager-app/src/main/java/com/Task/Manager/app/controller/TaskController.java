package com.Task.Manager.app.controller;

import com.Task.Manager.app.model.Task;
import com.Task.Manager.app.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Map<String, String> body) {
        return service.createTask(body.get("title"));
    }

    @PatchMapping("/{id}")
    public Task toggleTask(@PathVariable Long id) {
        return service.toggleTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}