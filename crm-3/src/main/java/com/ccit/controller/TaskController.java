package com.ccit.controller;

import com.ccit.pojo.JSONResult;
import com.ccit.pojo.Task;
import com.ccit.service.TaskService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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
    public JSONResult<Task> addtask(Task task, @RequestParam String hours,@RequestParam String mint){
        String remindtime = hours + ":" + mint;
        task.setRemindtime(remindtime);
        taskService.add(task);
        JSONResult<Task> result = new JSONResult<Task>(task);
        return result;
    }
    @ResponseBody
    @RequestMapping(value ="/{id}",method = RequestMethod.GET)
    public JSONResult<Task> findTask(@PathVariable Integer id){
        JSONResult<Task> result = new JSONResult<Task>(taskService.findById(id));
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "success";
    }
    @RequestMapping(value = "/dele/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/task";
    }
    @ResponseBody
    @RequestMapping(value = "/finish/{id}",method = RequestMethod.GET)
    public String finsh(@PathVariable Integer id){
        taskService.finish(id);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/finished",method = RequestMethod.GET)
    public Map<String, Object> finished(){
        List<Task> list = taskService.finished();
        Map<String,Object> result = Maps.newHashMap();
        result.put("message","success");
        result.put("list",list);
        return result;
    }
}
