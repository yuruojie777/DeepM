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

    public Map<String, Object> findAllEssay(){
        Map<String, Object> map = new HashMap<>();
        map.put("data", essayMapper.selectAllEssay());
        map.put("status","success");
        return map;
    }


    public Essay findEssayByEssayid(int essayid){
        return essayMapper.selectByEssayId(essayid);
    }
    public Map<String, Object> findEssayBySid(int sid, int status){
        List<Essay> essayList =  essayMapper.selectBySid(sid, status);
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


    public Map<String, Object> findEssayByTitle(String title) {
        List<Essay> essayList = essayMapper.selectByTitle("%" + title + "%");
        Map<String, Object> map = new HashMap<>();
        map.put("data", essayList);
        map.put("status", "success");
        return map;
    }

    public Map<String, Object> addEssay( Map<String, Object> form){

        Essay essay = new Essay();
        String title = (String)form.get("title");
        String content = (String)form.get("content");
        System.out.println(title);
        System.out.println(content);
        int sid = Integer.parseInt((String)form.get("sid"));
        essay.setTitle(title);
        essay.setContent(content);
        essay.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        essay.setGrade(-1);
        essay.setSid(sid);
        essay.setStatus(0);
        essay.setLevel(0);
        essay.setComment("No comment yet");
        essay.setDeadline(new Timestamp(System.currentTimeMillis() + 3600*12));

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

    public void updateEssayComment(int essayid, String comment) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return;
        }
        essay.setComment(comment);
        essayMapper.updateComment(essayid, comment);
        map.put("status", "success");
        map.put("data", essay);
        return;
    }

    public void updateEssayGrade(int essayid, int grade) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return;
        }
        essay.setGrade(grade);
        System.out.println(grade);
        essayMapper.updateGrade(essayid, grade);
        map.put("status", "success");
        map.put("data", essay);
        return;
    }


    public void updateEssayStatus(int essayid, int status) {
        Map<String, Object> map = new HashMap<>();
        Essay essay = essayMapper.selectByEssayId(essayid);
        if(essay == null) {
            map.put("status", "essay doesn't exist");
            return;
        }
        essay.setStatus(status);
        essayMapper.updateGrade(essayid, status);
        map.put("status", "success");
        map.put("data", essay);
        return;
    }
}