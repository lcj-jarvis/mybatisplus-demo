package com.mrlu.mybatisplus.enumtype;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 11:33
 *
 * 声明通用枚举类型的第二种方式
 */
public enum AgeEnum implements IEnum<String> {
    ONE("1", "一岁"),
    TWO("2", "二岁"),
    THREE("3", "三岁");

    AgeEnum(String value, String desc) {
        this.code = value;
        this.desc = desc;
    }

    private String code;

    public String getCode() {
        return code;
    }

    private String desc;

    @Override
    public String toString() {
        return this.code;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public String getValue() {
        return this.desc;
    }
}