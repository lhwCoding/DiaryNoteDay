package com.dnd.diarynoteday.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by hongwu on 2015/12/6.
 * 日记信息表
 */
@Table(name = "DiaryContent")
public class DiaryContent {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "content")
    private String content;
    @Column(name = "days")
    private String days;
    @Column(name = "winder")
    private String winder;
    @Column(name = "image")
    private String image;
    @Column(name = "data")
    private String data;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWinder() {
        return winder;
    }

    public void setWinder(String winder) {
        this.winder = winder;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DiaryContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", days='" + days + '\'' +
                ", winder='" + winder + '\'' +
                ", image='" + image + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
