package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.Essay;
import com.ele5620.deepm.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping
    public Map<String, Object> searchByKeyWord(@RequestParam String search) {
        return essayService.findEssayByTitle(search);
    }

    @PostMapping
    public Map<String, Object> addEssay(@RequestBody Map<String, Object> form){
        Map<String, Object> map = new HashMap<>();

        map.put("data", essayService.addEssay(form));
        return map;
    }

    @GetMapping("/aimark")
    public Map<String, Object> aiMark(int essayid) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayService.findEssayByEssayid(essayid);
        if(essay == null) {
            map.put("status", "No essay was found");
            return map;
        }
        try {
            return essayService.post(essay);
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("status", "error");
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
