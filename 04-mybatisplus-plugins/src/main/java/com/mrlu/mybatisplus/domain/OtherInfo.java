package com.mrlu.mybatisplus.domain;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 14:20
 */
public class OtherInfo {
    /**
     * 性别
     */
    private String sex;

    public OtherInfo() {
    }

    public OtherInfo(String sex, String city) {
        this.sex = sex;
        this.city = city;
    }

    @Override
    public String toString() {
        return "OtherInfo{" +
                "sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 居住城市
     */
    private String city;
}
