package com.mrlu.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mrlu.mybatisplus.enumtype.AgeEnum;
import com.mrlu.mybatisplus.enumtype.GradeEnum;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 11:37
 */
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 名字
     * 数据库字段: name varchar(20)
     */
    private String name;

    /**
     * 年龄，IEnum接口的枚举处理
     * 数据库字段：age varchar(255)
     */
    private AgeEnum age;

    public Student() {
    }

    public Student(String name, AgeEnum age, GradeEnum grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeEnum getAge() {
        return age;
    }

    public void setAge(AgeEnum age) {
        this.age = age;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }

    /**
     * 年级，原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库字段：grade  varchar(255)
     */
    private GradeEnum grade;
}
