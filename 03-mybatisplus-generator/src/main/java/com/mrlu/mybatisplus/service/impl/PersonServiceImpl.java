package com.mrlu.mybatisplus.service.impl;

import com.mrlu.mybatisplus.domain.Person;
import com.mrlu.mybatisplus.mapper.PersonMapper;
import com.mrlu.mybatisplus.service.PersonService;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
