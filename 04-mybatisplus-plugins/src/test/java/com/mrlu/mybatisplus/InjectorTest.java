package com.mrlu.mybatisplus;

import com.mrlu.mybatisplus.domain.Employee;
import com.mrlu.mybatisplus.domain.Person;
import com.mrlu.mybatisplus.mapper.EmployeeMapper;
import com.mrlu.mybatisplus.mapper.PersonMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 18:28
 *
 * 测试自定义的sql注入器
 */
public class InjectorTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private PersonMapper personMapper = context.getBean("personMapper",PersonMapper.class);
    @Test
    public void test(){
        List<Person> all = personMapper.selectAll();
        all.forEach(System.out::println);
    }
}
