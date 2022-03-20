package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrlu.mybatisplus.domain.Employee;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-02 18:02
 *
 * Active Record(活动记录)，是一种领域模型模式，特点是一个模型类对应关系型数据库中的
 * 一个表，而模型类的一个实例对应表中的一行记录。
 *
 *在domain的类继承Model<T>，泛型T为domain类的类型
 * 重写pkVal方法，返回主键对应的实体类字段。【新版的重不重写都行，最好重写一下】
 * 【注意】
 *   1、使用之前要打开spring的容器，不然会抛出异常
 *
 *  AR直接调用的方法，实际上是调用mybatis的sqlSession，然后直接调用底层的方法 。
 *
 */
public class ArTest {


    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    /**
     * Ar  插入操作
     */
    @Test
    public void test01(){
        Employee employee = new Employee();
        employee.setLastName("cccccc");
        employee.setAge(1);
        employee.setEmail("cccccc@qq.com");
        employee.setGender(1);
        boolean insert = employee.insert();
        System.out.println(insert);
    }


    /**
     * Ar  修改操作
     */
    @Test
    public void test02(){
        Employee employee = new Employee();
        employee.setId(28);
        employee.setEmail("marry@qq.com");
        employee.setGender(1);
        //根据主键进行更新
        //UPDATE t_employee SET email=?, gender=? WHERE id=?
        boolean update = employee.updateById();
        System.out.println(update);
    }

    /**
     * AR 查询单个操作
     */
    @Test
    public void test03(){
        Employee employee = new Employee();
        employee.setId(28);
        // SELECT id,last_Name,email,gender,age FROM t_employee WHERE id=?
        Employee result = employee.selectById();
        System.out.println(result);
    }

    /**
     * AR 查询全部操作
     */
    @Test
    public void test04(){
        Employee employee = new Employee();
        // SELECT id,last_Name,email,gender,age FROM t_employee
        List<Employee> list = employee.selectAll();
        list.forEach(System.out::println);
    }

    /**
     * AR 根据条件查询全部操作
     */
    @Test
    public void test05(){
        Employee employee = new Employee();
        // SELECT id,last_Name,email,gender,age FROM t_employee
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id",30);
        List<Employee> list = employee.selectList(queryWrapper);
        list.forEach(System.out::println);
        Integer integer = employee.selectCount(queryWrapper);
        System.out.println(integer);
    }

    /**
     * AR 删除操作
     *  删除成功，返回true，数据库中没有该条记录，就返回false
     */
    @Test
    public void test06(){
        Employee employee = new Employee();
        // DELETE FROM t_employee WHERE id=?
        //boolean delete = employee.deleteById(56);
        //employee.setId(56);
        //boolean delete = employee.deleteById();
        //System.out.println(delete);

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id",57,59);
        boolean delete = employee.delete(queryWrapper);
        System.out.println(delete);
    }


    /**
     * AR 分页操作
     */
    @Test
    public void test07(){
        Employee employee = new Employee();
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id",1,100);
        Page<Employee> page = employee.selectPage(new Page<>(2, 5), queryWrapper);
        List<Employee> records = page.getRecords();
        records.forEach(System.out::println);
    }
}
