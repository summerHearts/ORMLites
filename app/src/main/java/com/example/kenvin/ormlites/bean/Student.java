package com.example.kenvin.ormlites.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kenvin on 2017/10/26.
 */

@DatabaseTable(tableName = "tb_student")

public class Student {

    @DatabaseField(generatedId = true)
    private  int id;

    @DatabaseField
    private  int age;

    @DatabaseField(columnName = "name",dataType = DataType.STRING,canBeNull = false,defaultValue = "AKyS")
    private  String name;

    @DatabaseField
    private  String phone;

    @DatabaseField(columnName = "school_id",foreign = true,foreignAutoRefresh = true)
    private School school;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Student(int age, String name, String phone, School school) {
        this.age = age;
        this.name = name;
        this.phone = phone;
        this.school = school;
    }

    public Student() {}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", school=" + school +
                '}';
    }
}
