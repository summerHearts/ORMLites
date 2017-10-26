package com.example.kenvin.ormlites.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kenvin.ormlites.bean.School;
import com.example.kenvin.ormlites.bean.Student;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Kenvin on 2017/10/26.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    // 如何实现单例

    private static  DatabaseHelper  databaseHelper = null;

    public static DatabaseHelper getInstance(Context context){
        if (databaseHelper == null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    private DatabaseHelper(Context context) {
        super(context, "test.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Student.class);
            TableUtils.createTable(connectionSource, School.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {
            TableUtils.dropTable(connectionSource, Student.class,true);
            TableUtils.dropTable(connectionSource, School.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
