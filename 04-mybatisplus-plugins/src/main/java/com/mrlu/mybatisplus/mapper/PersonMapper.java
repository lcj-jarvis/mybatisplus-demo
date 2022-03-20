package com.mrlu.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrlu.mybatisplus.domain.Employee;
import com.mrlu.mybatisplus.domain.Person;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MrLu
 * @since 2021-03-02
 */
public interface PersonMapper extends BaseMapper<Person> {

    List<Person> selectAll();
}
