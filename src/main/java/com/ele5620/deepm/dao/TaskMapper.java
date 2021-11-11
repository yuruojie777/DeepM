package com.ele5620.deepm.dao;

import com.ele5620.deepm.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectByTeacherId(int teacherid);
    List<Task> selectByStudentId(int sid, int status);
    List<Task> selectByTitle(String title);
    Task selectByTaskId(int tid);
    int insertTask(Task task);
    int deleteTask(int tid);
}
