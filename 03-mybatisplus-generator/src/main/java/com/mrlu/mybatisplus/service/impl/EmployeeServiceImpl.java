package com.mrlu.mybatisplus.service.impl;

import com.mrlu.mybatisplus.domain.Employee;
import com.mrlu.mybatisplus.mapper.EmployeeMapper;
import com.mrlu.mybatisplus.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MrLu
 * @since 2021-03-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
