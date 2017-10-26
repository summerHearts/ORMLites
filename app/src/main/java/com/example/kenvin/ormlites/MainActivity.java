package com.example.kenvin.ormlites;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kenvin.ormlites.bean.School;
import com.example.kenvin.ormlites.bean.Student;
import com.example.kenvin.ormlites.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {


    public DatabaseHelper getDatabaseHelper(){
        return DatabaseHelper.getInstance(MainActivity.this);
    }

    public Dao<Student,Integer> getStudentDao() throws SQLException {
        return getDatabaseHelper().getDao(Student.class);
    }


    public Dao<School,Integer> getSchooltDao() throws SQLException {
        return getDatabaseHelper().getDao(School.class);
    }


    public void insertData() throws SQLException {
        Dao<Student,Integer> studentDao = getStudentDao();

        Dao<School,Integer> schoolDao = getSchooltDao();

        School school = new School("上海师范大学","上海市徐汇区桂林路.");

        Student stu1 = new Student(1 ,"AKyS","1323243435",school);
        Student stu2 = new Student(22 ,"BLANK","1323492002",school);
        Student stu3 = new Student(90 ,"DSKEN","1323673435",school);
        Student stu4 = new Student(80 ,"MENGXUE","1323353435",school);
        Student stu5 = new Student(58 ,"AUDSDKL","1323492002",school);
        Student stu6 = new Student(593 ,"SGG","1323673435",school);
        Student stu7 = new Student(344 ,"RNGVSSKT","1323353232",school);

        schoolDao.create(school);

        studentDao.create(stu1);
        studentDao.create(stu2);
        studentDao.create(stu3);
        studentDao.create(stu4);
        studentDao.create(stu5);
        studentDao.create(stu6);
        studentDao.create(stu7);
    }

    public void queryData() throws SQLException {
        Dao<Student,Integer> studentDao = getStudentDao();
        List<Student> students = studentDao.queryForAll();
        for (Student stu : students){
            Log.i("queryForAll",stu.toString()+ stu.getSchool());
        }

        Student student = studentDao.queryForId(3);
        Log.i("queryForId",student.toString());

        List<Student> queryForEq =  studentDao.queryForEq("name","AKyS");
        for (Student stu : queryForEq){
            Log.i("queryForEq",stu.toString());
        }

    }

    public void updateData() throws SQLException {
        Dao<Student,Integer> studentDao = getStudentDao();
        UpdateBuilder  update = studentDao.updateBuilder();
        update.setWhere(update.where().eq("name","AKyS").and().gt("age",19));
        update.updateColumnValue("name","DSKEN");
        update.updateColumnValue("phone","13285073577");
        update.update();
    }


    public void deleteData() throws SQLException{
        Dao<Student,Integer> studentDao = getStudentDao();
        studentDao.deleteById(8);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            queryData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void transiention() throws SQLException{
        TransactionManager.callInTransaction(getDatabaseHelper().getConnectionSource(), new Callable<Void>() {
            Dao<Student,Integer> studentDao = getStudentDao();
            School school = new School("上海师范大学","上海市徐汇区桂林路.");
            Student stu1 = new Student(1 ,"AKyS","1323243435",school);

            @Override
            public Void call() throws Exception {

               for (int i = 0;i< 100 ;i++){
                   studentDao.create(stu1);
                   if (i == 50){
                       throw new SQLException("test");
                   }
               }
                return null;
            }
        });
    }
}
