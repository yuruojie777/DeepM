package com.ele5620.deepm.entity;

import java.sql.Timestamp;

public class Feedback {
    private int fid;
    private int toid;
    private int fromid;
    private String content;
    private Timestamp createtime;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fid=" + fid +
                ", toid=" + toid +
                ", fromid=" + fromid +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
