package com.mrlu.mybatisplus.mp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 13:16
 *
 *
 * 自定义全局操作：
 * 1、编写自定义的sql注入器【MysqlInjector】
 *    实现ISqlInjector接口或者继承AbstractSqlInjector
 * 2、注入自定义的sql注入器到spring容器中
 *    <bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
 *        <!--注入自定义全局操作-->
 *         <property name="sqlInjector" ref="mysqlInjector"></property>
 *     </bean>
 *     <!--定义自定义注入器-->
 *     <bean id="mysqlInjector" class="com.mrlu.mybatisplus.mp.MysqlInjector"></bean>
 * 3、编写一个表示方法的类【DeleteByLastName】，让该类去继承AbstractMethod
 *    重写injectMappedStatement方法，创建一个对应类型的MappedStatement
 * 4、在自定义的sql注入器的getMethodList方法中，把自定义的方法类添加到方法列表
 */
public class MysqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //添加自定义的方法类
        methodList.add(new SelectAll());
        return methodList;
    }
}
