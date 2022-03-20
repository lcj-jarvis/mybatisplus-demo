package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrlu.mybatisplus.domain.Employee;
import com.mrlu.mybatisplus.domain.Person;
import com.mrlu.mybatisplus.mapper.EmployeeMapper;
import com.mrlu.mybatisplus.mapper.PersonMapper;
import com.mrlu.mybatisplus.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-02 22:39
 */
public class PluginsTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);
    private PersonMapper personMapper = context.getBean("personMapper",PersonMapper.class);

    /**
     * 分页插件的测试.
     * MybatisPlusInterceptor【核心】、PaginationInnerInterceptor、MybatisSimpleExecutor
     */
    @Test
    public  void  test01(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id",1,20);
        Page<Employee> page = employeeMapper.selectPage(new Page<>(2, 3), queryWrapper);
        List<Employee> records = page.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 防止全表更新与删除插件的测试
     * BlockAttackInnerInterceptor
     */
    @Test
    public void  test02(){
        //抛出异常：
        //Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException:
        //      Prohibition of full table deletion 禁止全表删除
        //全表删除
        //personMapper.delete(null);
        Person person = new Person();
        person.setName("aabb");
        person.setAge(25);
        //抛出异常：
        //Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException:
        // Prohibition of table update operation 禁止全表的更新操作
        personMapper.update(person,null);
    }

    /**
     * 测试乐观锁插件
     */
    @Test
    public void test03(){
        Person person = new Person();
        person.setId(1);
        person.setName("jack");
        person.setAge(28);
        //这里的版本号是数据库记录没被改变之前的版本号
        /*
        Preparing: UPDATE t_person SET name=?, age=?, version=? WHERE id=? AND version=?
         Parameters: jack(String), 28(Integer), 2(Integer)【将传入的版本号加一作为新的版本号，用于更新】, 1(Integer), 1(Integer)【传入的版本号。用于查询，如果和数据库的版本号一致就更新】
         */
        person.setVersion(1);
        int update = personMapper.updateById(person);
        System.out.println(update);
        /*
         如果去数据库中，将id为1号的人的版本号修改了（不是1了）

         Preparing: UPDATE t_person SET name=?, age=?, version=? WHERE id=? AND version=?
          Parameters: jack(String), 28(Integer), 2(Integer), 1(Integer), 1(Integer)
          此时where条件不满足，直接找不到记录，所有更新失败。
         */
    }

    /**
     * 动态表名插件的测试
     */
    @Test
    public void test04(){
        // SELECT id,name,age,version FROM t_person_2020
        // SELECT id,name,age,version FROM t_person_2021
        List<Person> people = personMapper.selectList(null);
        System.out.println(people);
    }

    /**
     * 多租户插件测试
     */
}
