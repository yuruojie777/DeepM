package com.ele5620.deepm.dao;

import com.ele5620.deepm.entity.Essay;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface EssayMapper {

    Essay selectEssayById(int id);
    List<Essay> selectEssayByTeacherId(int tid);
    List<Essay> selectEssayByStudentId(int sid);

    int insertEssay(int sid, int tid, int essayId, int title, int content, int grade, int level, int status, String comment, Timestamp submitTime, Timestamp deadline);

    int updateEssayStatus(int essayId, int status);
}
