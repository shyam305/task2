package com.centime.task2.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Component
public class TaskNode {
    public TaskNode(String name, List<TaskNode> subClasses) {
        this.name = name;
        this.subClasses = subClasses;
    }

    public TaskNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskNode> getSubClasses() {
        return subClasses;
    }

    public void setSubClasses(List<TaskNode> subClasses) {
        this.subClasses = subClasses;
    }

    @JsonProperty("Name")
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("Sub Classes")
    private List<TaskNode> subClasses;
}
