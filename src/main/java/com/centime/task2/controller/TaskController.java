package com.centime.task2.controller;

import com.centime.task2.aspect.LogMethodParam;
import com.centime.task2.entity.Task;
import com.centime.task2.service.TaskService;
import com.centime.task2.util.TaskNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/nested-json")
    public List<List<TaskNode>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @LogMethodParam
    public List<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/nested-json/parentid/{id}")
    @LogMethodParam
    public List<TaskNode> getAllTasks(@PathVariable Long id) {
        return taskService.getTaskByParentId(id);
    }
    @PostMapping
    public void saveCharacter(@RequestBody Task task) {
        taskService.saveTask(task);
    }
}
