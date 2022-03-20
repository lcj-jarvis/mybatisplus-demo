package com.mrlu.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrlu.mybatisplus.domain.Employee;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-01 17:25
 *
 * Mapper接口：
 * 基于Mybatis的方式：在Mapper接口中编写CRUD相关的方法，
 *                  提供Mapper接口所对应的SQL映射文件以及方法对应的SQL语句
 *
 * 基于MP（MybatisPlus的方式）：让XxxMapper接口继承BaseMapper接口即可
 *                           BaseMapper<T>:泛型指定的就是当前Mapper接口所操作的实体类类型
 *
 *
 *
 *
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {


}
