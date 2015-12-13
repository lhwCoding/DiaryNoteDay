package com.dnd.diarynoteday.base;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by hongwu on 2015/12/6.
 */
public class BaseDao {


    public final DbManager db;


    public BaseDao() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("DiaryDB")
                .setDbDir(new File("/sdcard"))
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });
        db = x.getDb(daoConfig);

    }

    private static final BaseDao baseDao = new BaseDao();

    public static BaseDao getInstance() {
        return baseDao;
    }


}
