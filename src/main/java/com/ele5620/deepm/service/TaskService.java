package com.ele5620.deepm.service;

import com.ele5620.deepm.dao.TaskMapper;
import com.ele5620.deepm.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;


    public Map<String, Object> findTaskByTeacherId(int teacherid){
        Map<String, Object> map = new HashMap<>();
        List<Task> taskList = taskMapper.selectByTeacherId(teacherid);
        map.put("data", taskList);
        map.put("status", "success");
        return map;
    }

    public Map<String, Object> findTaskByStudentId(int sid, int status){
        Map<String, Object> map = new HashMap<>();
        List<Task> taskList = taskMapper.selectByStudentId(sid, status);
        map.put("data", taskList);
        map.put("status", "success");
        return map;
    }

    public Map<String, Object> findTaskByTitle(String title){
        Map<String, Object> map = new HashMap<>();
        List<Task> taskList = taskMapper.selectByTitle("%"+title+"%");
        map.put("data",taskList);
        map.put("status","success");
        return map;
    }

    public Map<String, Object> addTask(Task task){
        Map<String, Object> map = new HashMap<>();
        int result = taskMapper.insertTask(task);
        map.put("data",task);
        map.put("status", "success");
        return map;
    }

    public Map<String, Object> deleteTask(int tid) {
        Map<String, Object> map = new HashMap<>();
        Task task = taskMapper.selectByTaskId(tid);
        if(task != null){
            map.put("data", task);
            map.put("status","success");
            return map;
        }
        map.put("status", "no such task was found");
        return map;
    }
}
