package com.mrlu.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrlu.mybatisplus.dao.EmployeeMapper;
import com.mrlu.mybatisplus.domain.Employee;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-02 13:06
 *
 * 通用的service 的 crud
 * 1、编写一个service接口（如EmployeeService）继承Service<T>，泛型T是要操作的实体类对象
 * 2、编写service接口的实现类，如EmployeeServiceImpl。然后实现ServiceImpl<M extends BaseMapper<T>, T>
 *     M extends BaseMapper<T>为实现BaseMapper接口的dao层的Mapper接口，泛型T是要操作的实体类对象。
 *     最后还要实现1、处的service接口
 *
 */
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{


}
