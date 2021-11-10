package com.ele5620.deepm;

import com.ele5620.deepm.dao.EssayMapper;
import com.ele5620.deepm.entity.Essay;
import com.ele5620.deepm.service.EssayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DeepMApplication.class)
public class ApiTest {

    @Autowired
    private EssayService essayService;
    @Autowired
    private EssayMapper essayMapper;

    @Test
    public void testAiMarker(){
        for(Essay essay: essayMapper.selectBySid(1)){
            try{
                System.out.println(essayService.post(essay));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
