package com.mrlu.mybatisplus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mrlu.mybatisplus.domain.Student;
import com.mrlu.mybatisplus.enumtype.AgeEnum;
import com.mrlu.mybatisplus.enumtype.GradeEnum;
import com.mrlu.mybatisplus.mapper.StudentMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 11:54
 *
 * 通用枚举类型的使用
 * 1、加入jackson的maven依赖
 *  <dependency>
 *       <groupId>com.fasterxml.jackson.core</groupId>
 *       <artifactId>jackson-databind</artifactId>
 *       <version>2.12.1</version>
 *     </dependency>
 * 2、  <bean id="sqlSessionFactoryBean" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
 *         <!--配置扫描枚举类型的序列化的注解所在的包-->
 *         <property name="typeEnumsPackage" value="com.mrlu.mybatisplus.enumtype"></property>
 *     </bean>
 *      <!--配置枚举类型的序列化-->
 *     <bean id="objectMapper" class="com.fasterxml.jackson.databind.ObjectMapper"></bean>
 *
 * 3、在对应的枚举中要保存到数据库的值，使用 @EnumValue注解
 *
 * 4、重写枚举类型的toString方法。当从数据库查询的时候，就是使用的toString方法，返回枚举的类型。
 *
 */
public class EnumHandlerTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private StudentMapper studentMapper = context.getBean("studentMapper",StudentMapper.class);
    private ObjectMapper objectMapper = context.getBean("objectMapper",ObjectMapper.class);

    @Test
    public void test01(){
        //INSERT INTO t_student ( name, age, grade ) VALUES ( 'marry', '二岁', '高中' )
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        Student student = new Student("marry", AgeEnum.TWO, GradeEnum.HIGH);
        int insert = studentMapper.insert(student);
        System.out.println(insert);
    }

    @Test
    public void test02(){
        Student student = studentMapper.selectById(2);
        System.out.println(student);
    }
}
