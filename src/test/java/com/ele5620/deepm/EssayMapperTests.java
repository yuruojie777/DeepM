package com.ele5620.deepm;

import com.ele5620.deepm.dao.EssayMapper;
import com.ele5620.deepm.dao.UserMapper;
import com.ele5620.deepm.entity.Essay;
import com.ele5620.deepm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DeepMApplication.class)
public class EssayMapperTests {

    @Autowired
    private EssayMapper essayMapper;
    @Test
    public void testInsert(){
        Essay essay = new Essay();
        essay.setStatus(1);
        essay.setComment("good");
        essay.setDeadline(new Timestamp(System.currentTimeMillis()));
        essay.setGrade(-1);
        essay.setLevel(1);
        essay.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        essay.setTitle("a new story");
        essay.setSid(1);
        essay.setSid(1);
        essayMapper.insertEssay(essay);
    }

    @Test
    public void testSelect() {
        List<Essay> essays = essayMapper.selectBySid(1, 0);
        for(Essay essay : essays){
            System.out.println(essay);
        }
    }
}
