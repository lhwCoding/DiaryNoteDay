package com.dnd.diarynoteday.db.dao;

import com.dnd.diarynoteday.base.BaseDao;
import com.dnd.diarynoteday.db.UserData;

import java.nio.channels.Selector;

/**
 * Created by hongwu on 2015/12/6.
 * 用户数据操作类
 */
public class UserDataDao extends BaseDao{
    public UserDataDao(){}
    //TODO 用户数据作为云端 去处理
    private static final UserDataDao userdatadao=new UserDataDao();

    public static UserDataDao getInstance(){
        return  userdatadao;
    }

}
