package com.mrlu.mybatisplus;

import com.mrlu.mybatisplus.domain.User;
import com.mrlu.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 0:24
 * 自动填充功能的实现步骤
 * 1、在对应的Javabean的属性加上使用注解，指明自动填充的策略
 *  如：
 *   @TableField(fill = FieldFill.INSERT_UPDATE)
 *   插入和更新时填充字段
 * 2、编写一个类去实现MetaObjectHandler接口，重写insertFill和updateFill方法。
 *   指定插入和填充的规则。
 * 3、在spring的配置文件中配置
 * <!--注入公共字段填充处理器-->
 *     <bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
 *         <!--配置公共字段填充处理器-->
 *         <property name="metaObjectHandler" ref="myMetaObjectHandler"></property>
 *     </bean>
 *     <bean id="myMetaObjectHandler" class="com.mrlu.mybatisplus.autofill.MyMetaObjectHandler"></bean>
 * 4、编写测试
 */
public class AutoFillField {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserMapper userMapper = context.getBean("userMapper",UserMapper.class);

    /*
    测试公共字段填充
     */
    @Test
    public void testAutoFill(){
        User user = new User();
        user.setName("bciqbcq");
        //指定填充了逻辑删除字段
        //INSERT INTO t_user ( name, logic_flag ) VALUES ( 'bciqbcq', 1 )
        int insert = userMapper.insert(user);
        //System.out.println(insert);
        System.out.println(user.getId());
        //UPDATE t_user SET name='aabb' WHERE id=9 AND logic_flag=1
        user.setName(null);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
}
