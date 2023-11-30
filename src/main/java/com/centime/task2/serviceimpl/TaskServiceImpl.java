package com.centime.task2.serviceimpl;

import com.centime.task2.aspect.LogMethodParam;
import com.centime.task2.entity.Task;
import com.centime.task2.repository.TaskRepository;
import com.centime.task2.service.TaskService;
import com.centime.task2.util.TaskNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public  List<List<TaskNode>> getAllTasks() {

       List<Task> tasks=taskRepository.findAll();
     return   buildTaskTree(tasks);
    }

    @Override
    @LogMethodParam
    public List<Task> getTaskById(Long id) {
        return taskRepository.findById(id).map(List::of).orElse(List.of());
    }
    @Override
    @LogMethodParam
    public List<TaskNode> getTaskByParentId(Long id) {
        List<Task> tasks=taskRepository.findAll();
        return recursive(tasks,id);
    }
    @Override
    @LogMethodParam
    public Task saveTask(Task task) {
    return taskRepository.save(task);
    }
    @Override
    @LogMethodParam
    public List<List<TaskNode>> buildTaskTree(List<Task> tasks) {
        // implement logic to build the nested structure

        List<List<TaskNode>> list=new ArrayList<>();
        Set<Long> set=new HashSet<>();
        for(Task task:tasks){
            set.add(task.getParentId());
        }
        for(Long parentId:set){
            list.add(recursive(tasks,parentId));
        }
        return list;
    }
    @LogMethodParam
    List<TaskNode> recursive(List<Task> tasks,Long id){
        List<TaskNode> listTaskNode=new ArrayList<>();


        Long parentId=null;
        for(int i=0;i<tasks.size();i++){
            TaskNode taskNode=new TaskNode();
            if(tasks.get(i).getParentId().longValue()==id.longValue()){
                parentId=tasks.get(i).getId();
                taskNode.setName(tasks.get(i).getName());
                taskNode.setSubClasses(recursive(tasks,parentId));

                listTaskNode.add(taskNode);
            }


        }
        return listTaskNode;

    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }
}
