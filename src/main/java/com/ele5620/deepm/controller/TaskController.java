package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.Task;
import com.ele5620.deepm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    @GetMapping("/student")
    public Map<String, Object> viewStudentTask(@RequestParam int sid, int status) {
        return taskService.findTaskByStudentId(sid, status);
    }

    @PostMapping
    public Map<String, Object> addTask(@RequestBody Map<String, Object> form){

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        int teacherid = (int)form.get("teacherid");
        String title = (String)form.get("title");
        String content = (String)form.get("content");
        try {
            Date releasetime = sdf.parse((String)form.get("releasetime"));
            Date endtime = sdf.parse((String)form.get("endtime"));

            Task task = new Task();
            task.setTeacherid(teacherid);
            task.setTitle(title);
            task.setContent(content);
            task.setReleasetime(releasetime);
            task.setEndtime(endtime);
            return taskService.addTask(task);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status","error");
        return map;
    }

    @DeleteMapping
    public Map<String, Object> deleteTask(@RequestParam int tid){
        return taskService.deleteTask(tid);
    }
}
