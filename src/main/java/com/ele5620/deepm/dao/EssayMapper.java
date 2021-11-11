package com.ele5620.deepm.dao;

import com.ele5620.deepm.entity.Essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EssayMapper {
    List<Essay> selectBySid(int sid);
    List<Essay> selectByTid(int tid);
    List<Essay> selectByTitle(String title);
    Essay selectByEssayId(int essayid);
    int insertEssay(Essay essay);

    int updateGrade(int essayid, float grade);
    int updateComment(int essayid, String comment);

    int deleteEssay(int essayid);
}
