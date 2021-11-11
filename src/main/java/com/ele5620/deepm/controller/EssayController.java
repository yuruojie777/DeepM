package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.Essay;
import com.ele5620.deepm.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/essay")
@RestController
public class EssayController {

    @Autowired
    private EssayService essayService;

    @GetMapping("/student")
    public Map<String, Object> viewStudentEssay(int sid){
        return essayService.findEssayBySid(sid);
    }

    @GetMapping("/teacher")
    public Map<String, Object> viewTeacherEssay(int tid){
        return essayService.findEssayByTid(tid);
    }

    @PostMapping
    public Map<String, Object> addEssay(Map<String, Object> form){
        Map<String, Object> map = new HashMap<>();
        Essay essay = new Essay();
        essay.setTitle((String)form.get("title"));
        essay.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        map.put("data", essay);
        return map;
    }

    @DeleteMapping
    public Map<String, Object> deleteEssay(@RequestBody int essayid) {
        return essayService.deleteEssay(essayid);
    }

    @PutMapping("/grade")
    public Map<String, Object> updateEssayGrade(@RequestBody int essayid, int grade) {
        return essayService.updateEssayGrade(essayid, grade);
    }

    @PutMapping("/comment")
    public Map<String, Object> updateEssayGrade(@RequestBody int essayid, String comment) {
        return essayService.updateEssayComment(essayid, comment);
    }

    @PutMapping("/status")
    public Map<String, Object> updateEssayStatus(@RequestBody int essayid, int status) {
        return essayService.updateEssayStatus(essayid, status);
    }
}
