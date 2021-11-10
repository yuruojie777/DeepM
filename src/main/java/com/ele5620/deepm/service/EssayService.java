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
import java.util.Map;

@Service
public class EssayService {

    @Autowired
    private EssayMapper essayMapper;

    public Map<String, Object> post(Essay essay) throws IOException {
        return TextcorrectwritingV3Demo.requestForAIMarking(essay.getContent());
    }
}