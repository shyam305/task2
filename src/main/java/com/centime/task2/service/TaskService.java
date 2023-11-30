package com.centime.task2.service;

import com.centime.task2.entity.Task;
import com.centime.task2.util.TaskNode;

import java.util.List;

public interface TaskService {
    List<List<TaskNode>> getAllTasks();
    List<Task> getTaskById(Long id);
    Task saveTask(Task task);
    public List<List<TaskNode>> buildTaskTree(List<Task> tasks);
    public List<TaskNode> getTaskByParentId(Long id);
}
