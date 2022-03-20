package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.mrlu.mybatisplus.domain.User;
import com.mrlu.mybatisplus.mapper.UserMapper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 21:14
 * 逻辑删除的配置步骤
 * 1、在spring的配置文件中加入以下配置
 * <bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
 *         <!--配置数据库全局默认的映射关系-->
 *         <property name="dbConfig">
 *             <bean class="com.baomidou.mybatisplus.core.config.GlobalConfig$DbConfig">
 *                 <!--声明全局默认类名的对应的表的前缀。-->
 *                 <property name="tablePrefix" value="t_"></property>
 *                 <!--配置全局主键自增-->
 *                 <property name="idType" value="AUTO"></property>
 *
 *                 <!--配置逻辑删除-->
 *                 <!-- # 全局逻辑删除的实体字段名.数据库表的逻辑删除字段-->
 *                 <property name="logicDeleteField" value="logic_flag"></property>
 *                 <!--逻辑已删除值。即删除之后数据库对应的字段logic_flag的值置为-1，但是记录还是没有删除-->
 *                 <property name="logicDeleteValue" value="-1"></property>
 *                 <!--逻辑未删除值。即未删除之前数据库对应的字段logic_flag的值置为1-->
 *                 <property name="logicNotDeleteValue" value="1"></property>
 *             </bean>
 *         </property>
 *
 *     </bean>
 *
 * 2、在对应的JavaBean的逻辑删除属性上，加上注解@TableLogic
 */
public class LogicFieldTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserMapper userMapper = context.getBean("userMapper",UserMapper.class);

    /**
     * 测试逻辑删除
     */
    @Test
    public void test01(){
        //UPDATE t_user SET logic_flag = -1 WHERE id=? AND logic_flag=1
        int i = userMapper.deleteById(1);
        System.out.println(i);
        //SELECT id,name,logic_flag FROM t_user WHERE id=? AND logic_flag=1
        User user = userMapper.selectById(1);
        System.out.println(user);
    }


    /*

    public class DeleteById extends AbstractMethod {

        @Override
        public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
            String sql;
            //逻辑删除的方法
            SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BY_ID;
            if (tableInfo.isWithLogicDelete()) { //是否是逻辑删除
                //构建一个逻辑删除的sql语句
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlLogicSet(tableInfo),
                        tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                        tableInfo.getLogicDeleteSql(true, true));
                SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
                //添加update的MappedStatement。所以删除的操作就会变成更新的操作
                return addUpdateMappedStatement(mapperClass, modelClass, getMethod(sqlMethod), sqlSource);
            } else {
                sqlMethod = SqlMethod.DELETE_BY_ID;
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                        tableInfo.getKeyProperty());
                SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
                return this.addDeleteMappedStatement(mapperClass, getMethod(sqlMethod), sqlSource);
            }
        }
    }
     */
}
