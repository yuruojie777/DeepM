package com.ele5620.deepm.dao;

import com.ele5620.deepm.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {

    List<Feedback> selectAllFeedback();
    Feedback selectFeedbackByFid(int fid);
    List<Feedback> selectFeedbackByToid(int toid);
    List<Feedback> selectFeedbackByFromid(int fromid);

    int insertFeedback(Feedback feedback);

    int deleteFeedback(int fid);
}
