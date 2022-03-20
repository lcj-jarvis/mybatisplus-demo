package com.mrlu.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * JavaBean属性在定义的时候推荐使用包装类
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-01 16:35
 *
 * MybatisPlus会默认使用实体类的类名到数据库中查找相应的表
 *  value属性：实体类对应的数据库的表名
 *   当然如果在spring的配置文件中，配置了全局配置对应的数据库关系。这些注解就可以省略
 */
//@TableName(value = "t_employee")
public class Employee {

    /**
     * @TableId: employeeMapper
     *    value属性：指定表中的主键列表名，如果实体类属性名和列名一致
     *    type属性：指定之间策略
     *             IdType.AUTO表示数据库ID自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 默认开启下滑线的驼峰命名
     * value:对应数据库的列名
     * exist:是否为数据库表字段。true表示是，false表示不是。默认是true
     */

    /**
     * 数据库字段值
     * <p>
     * 不需要配置该值的情况:
     * <li> 当 {@link com.baomidou.mybatisplus.core.MybatisConfiguration#mapUnderscoreToCamelCase} 为 true 时,
     * (mp下默认是true,mybatis默认是false), 数据库字段值.replace("_","").toUpperCase() == 实体属性名.toUpperCase() </li>
     * <li> 当 {@link com.baomidou.mybatisplus.core.MybatisConfiguration#mapUnderscoreToCamelCase} 为 false 时,
     * 数据库字段值.toUpperCase() == 实体属性名.toUpperCase() </li>
     *  String value() default "";
     */

    //@TableField(value = "last_Name",exist = true)
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

    @TableField(exist = false)
    private Integer salary;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, Integer gender, Integer age) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
