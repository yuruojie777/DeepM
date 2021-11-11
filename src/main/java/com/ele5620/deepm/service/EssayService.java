package com.ele5620.deepm.service;

import com.ele5620.deepm.dao.EssayMapper;
import com.ele5620.deepm.entity.Essay;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.util.CommonUtil;
import com.ele5620.deepm.util.TextcorrectwritingV3Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EssayService {

    @Autowired
    private EssayMapper essayMapper;

    public Map<String, Object> post(Essay essay) throws IOException {
        return TextcorrectwritingV3Demo.requestForAIMarking(essay.getContent());
    }

    public Map<String, Object> findEssayBySid(int sid){
        List<Essay> essayList =  essayMapper.selectBySid(sid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", essayList);
        map.put("status","success");
        return map;
    }

    public Map<String, Object> findEssayByTid(int tid){
        List<Essay> essayList =  essayMapper.selectByTid(tid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", essayList);
        map.put("status","success");
        return map;
    }

    public Map<String, Object> addEssay(Essay essay){
        essayMapper.insertEssay(essay);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", essay);
        return map;
    }

    public Map<String, Object> deleteEssay(int essayid){
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return map;
        }
        essayMapper.deleteEssay(essayid);
        map.put("status", "success");
        map.put("data", essay);
        return map;
    }

    public Map<String, Object> updateEssayComment(int essayid, String comment) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return map;
        }
        essay.setComment(comment);
        essayMapper.updateComment(essayid, comment);
        map.put("status", "success");
        map.put("data", essay);
        return map;
    }

    public Map<String, Object> updateEssayGrade(int essayid, int grade) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return map;
        }
        essay.setGrade(grade);
        essayMapper.updateGrade(essayid, grade);
        map.put("status", "success");
        map.put("data", essay);
        return map;
    }


    public Map<String, Object> updateEssayStatus(int essayid, int status) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return map;
        }
        essay.setStatus(status);
        essayMapper.updateGrade(essayid, status);
        map.put("status", "success");
        map.put("data", essay);
        return map;
    }
}