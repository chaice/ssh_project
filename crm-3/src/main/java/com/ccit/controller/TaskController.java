package com.ccit.controller;

import com.ccit.pojo.Task;
import com.ccit.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Inject
    private TaskService taskService;
    @RequestMapping(method = RequestMethod.GET)
    public String tasklist(){
        return "task/task";
    }
    @ResponseBody
    @RequestMapping(value = "/event",method = RequestMethod.GET)
    public List<Task> task(){
        List<Task> data = taskService.findAll();
        return data;
    }
    @ResponseBody
    @RequestMapping(value = "/add_event",method = RequestMethod.POST)
    public Task addtask(Task task){
        taskService.add(task);
        return task;
    }
    @ResponseBody
    @RequestMapping(value ="/{id}",method = RequestMethod.GET)
    public Task findTask(@PathVariable Integer id){
        return taskService.findById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/finish/{id}",method = RequestMethod.GET)
    public String finsh(@PathVariable Integer id){
        taskService.finish(id);
        return "success";
    }
}
