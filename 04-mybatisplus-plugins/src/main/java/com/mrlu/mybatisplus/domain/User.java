package com.mrlu.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 21:09
 *
 *
 */
public class User {
    private Integer id;

    /**
     * FieldFill.INSERT_UPDATE：插入和更新时填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String name;

    /**
     * @TableLogic 表示该字段是一个逻辑删除属性值
     * FieldFill.INSERT 插入时填充字段
     */
    @TableField(fill = FieldFill.INSERT)

    @TableLogic
    private Integer logicFlag;

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



    public User() {
    }

    public User(Integer id, String name, Integer logicFlag) {
        this.id = id;
        this.name = name;
        this.logicFlag = logicFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logicFlag=" + logicFlag +
                '}';
    }

    public Integer getLogicFlag() {
        return logicFlag;
    }

    public void setLogicFlag(Integer logicFlag) {
        this.logicFlag = logicFlag;
    }
}
