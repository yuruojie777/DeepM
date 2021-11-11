package com.ele5620.deepm.entity;

import java.sql.Timestamp;

public class Task {
    private int tid;
    private int teacherid;
    private String title;
    private String content;
    private Timestamp releasetime;

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

    public Timestamp getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Timestamp releasetime) {
        this.releasetime = releasetime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", teacherid=" + teacherid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", releasetime=" + releasetime +
                '}';
    }
}
