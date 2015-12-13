package com.dnd.diarynoteday.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by hongwu on 2015/12/6.
 * 用户信息表
 */
@Table(name = "userdata")
public class UserData {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "name")
    public String name;


    @Column(name = "pwd")
    private String pwd;


    @Column(name = "icon")
    public String icon;

    @Column(name = "text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + pwd + '\'' +
                ", icon=" + icon + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
