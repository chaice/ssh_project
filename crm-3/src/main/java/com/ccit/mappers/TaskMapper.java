package com.ccit.mappers;


import com.ccit.pojo.Task;

import java.util.List;

public interface TaskMapper {
    void add(Task task);

    List<Task> findAll();

    Task findById(Integer id);

    void deleteTask(Integer id);

    void finish(Task task);
}
