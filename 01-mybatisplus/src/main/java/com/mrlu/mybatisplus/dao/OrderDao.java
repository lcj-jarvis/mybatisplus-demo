package com.mrlu.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrlu.mybatisplus.domain.Order;

/**
 * @Entity com.mrlu.mybatisplus.domain.Order
 */
public interface OrderDao extends BaseMapper<Order> {

    /**
     *
     * @mbg.generated 2021-03-04 15:57:48
     */
    int insertSelective(Order record);
}