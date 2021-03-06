package com.mrlu.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * null
 * @TableName t_order
 */
public class Order implements Serializable {
    /**
     *
     * @mbg.generated 2021-03-04 15:57:48
     * 对应数据库的主键(uuid,自增id，雪花算法，雪花算法加uuid，Redis，zookeeper)
     * ASSIGN_ID为MP分配ID (主键类型为number或string）。随机分配一个唯一的id
     * ASSIGN_UUID(4),分配UUID (主键类型为 string)
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 
     *
     * @mbg.generated 2021-03-04 15:57:48
     */
    private String name;

    /**
     * 
     *
     * @mbg.generated 2021-03-04 15:57:48
     */
    private Integer count;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order
     *
     * @mbg.generated 2021-03-04 15:57:48
     */
    private static final long serialVersionUID = 1L;
}