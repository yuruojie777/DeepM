package com.ele5620.deepm.controller;

import com.ele5620.deepm.entity.Feedback;
import com.ele5620.deepm.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Map<String, Object> addFeedback(@RequestBody Map<String, Object> form) {
        System.out.println(form);
        int toid = 0;
        int fromid = Integer.parseInt((String)form.get("fromid"));
        String content = (String)form.get("content");

        Feedback feedback = new Feedback();
        feedback.setToid(toid);
        feedback.setFromid(fromid);
        feedback.setContent(content);
        feedback.setCreatetime(new Timestamp(System.currentTimeMillis()));

        return feedbackService.addFeedback(feedback);
    }

    @GetMapping
    public Map<String, Object> viewFeedback() {
        return feedbackService.viewAllFeedback();
    }
}
