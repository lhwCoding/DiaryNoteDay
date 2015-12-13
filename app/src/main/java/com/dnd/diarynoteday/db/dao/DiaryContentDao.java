package com.dnd.diarynoteday.db.dao;

import com.dnd.diarynoteday.base.BaseDao;
import com.dnd.diarynoteday.db.DiaryContent;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by hongwu on 2015/12/6.
 */
public class DiaryContentDao extends BaseDao {

    public  DiaryContentDao(){}

    public static final DiaryContentDao diarycontentdao=new DiaryContentDao();

    public static DiaryContentDao getInstance(){
        return  diarycontentdao;
    }

    /**
     * 查询所有的日记内容
     * @return
     */
    public List<DiaryContent> getDiaryAll(){
        List<DiaryContent> diaryContents=null;
        try {
            diaryContents=db.findAll(DiaryContent.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return diaryContents;
    }

    public void updateDiary(int id,String content){
        try {
            db.update(DiaryContent.class,"content",content);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
