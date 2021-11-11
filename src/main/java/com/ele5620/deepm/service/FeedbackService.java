package com.ele5620.deepm.service;

import com.ele5620.deepm.dao.FeedbackMapper;
import com.ele5620.deepm.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;
    public Map<String, Object> addFeedback(Feedback feedback){
        Map<String, Object> map = new HashMap<>();
        feedbackMapper.insertFeedback(feedback);
        map.put("data", feedback);
        map.put("status", "success");
        return map;
    }

    public Map<String, Object> viewAllFeedback() {
        Map<String, Object> map = new HashMap<>();
        List<Feedback> feedbackList = feedbackMapper.selectAllFeedback();
        map.put("data", feedbackList);
        map.put("status", "success");
        return map;
    }
}
