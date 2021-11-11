package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.Task;
import com.ele5620.deepm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/task")
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public Map<String, Object> viewTask(@RequestParam int teacherid){
        return taskService.findTaskByTeacherId(teacherid);
    }

    @PostMapping
    public Map<String, Object> addTask(@RequestBody Map<String, Object> form){
        int teacherid = (int)form.get("teacherid");
        String title = (String)form.get("title");
        String content = (String)form.get("content");

        Task task = new Task();
        task.setTeacherid(teacherid);
        task.setTitle(title);
        task.setContent(content);
        task.setReleasetime(new Timestamp(System.currentTimeMillis()));

        return taskService.addTask(task);
    }

    @DeleteMapping
    public Map<String, Object> deleteTask(@RequestParam int tid){
        return taskService.deleteTask(tid);
    }
}
