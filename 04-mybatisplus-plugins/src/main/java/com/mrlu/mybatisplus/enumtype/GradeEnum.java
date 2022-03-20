package com.mrlu.mybatisplus.enumtype;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 11:31
 *
 * 声明通用枚举类型的第一种方式
 */
public enum GradeEnum {

    PRIMARY("1", "小学"),  SECONDORY("2", "中学"),  HIGH("3", "高中");

    GradeEnum(String code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    private final String code;

    /**
     * 标记数据库存的值是descp
     */
    @EnumValue
    private final String descp;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
