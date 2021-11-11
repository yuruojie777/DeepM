package com.ele5620.deepm.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private int tid;
    private int teacherid;
    private String title;
    private String content;
    private Date releasetime;
    private Date endtime;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", teacherid=" + teacherid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", releasetime=" + releasetime +
                ", endtime=" + endtime +
                '}';
    }
}
