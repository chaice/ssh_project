package com.ccit.service;

import com.ccit.mappers.TaskMapper;
import com.ccit.pojo.Task;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class TaskService {
    @Inject
    private TaskMapper taskMapper;
    public void add(Task task) {
        taskMapper.add(task);
    }

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public Task findById(Integer id) {
        return taskMapper.findById(id);
    }

    public void deleteTask(Integer id) {
        taskMapper.deleteTask(id);
    }

    public void finish(Integer id) {
        Task task = taskMapper.findById(id);
        String title = task.getTitle()+"(已完成)";
        task.setDone(true);
        task.setColor("#cccccc");
        task.setTitle(title);
        taskMapper.finish(task);
    }
}
