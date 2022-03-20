package com.mrlu.mybatisplus.dynamicTableName;


import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

import java.util.Random;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 1:21
 *
 * 这样比较麻烦，实际上可以直接在spring的配置类中直接写。见官方动态白表名的使用例子
 * 注意事项：
 * 原理为解析替换设定表名为处理器【MyTableNameHandler】的返回表名，
 *          表名建议可以定义复杂一些避免误替换
 *
 * https://gitee.com/baomidou/mybatis-plus-samples/blob/master/mybatis-plus-sample-dynamic-tablename/src/main/java
 * /com/baomidou/mybatisplus/samples/dytablename/config/MybatisPlusConfig.java
 */
public class MyTableNameHandler implements TableNameHandler {

    /**
     * 1、如果自定义规则的表名返回为空，则会按照实际的表名来处理
     * 2、如果配置了多租户sql解析器，过滤特定的sql，则也会按照实际表名来处理
     * @param sql 当前执行 SQL
     * @param tableName 表名
     * @return 返回按照指定规则指定的表名
     */
    @Override
    public String dynamicTableName(String sql, String tableName) {
        Random random = new Random();
        int num = random.nextInt(10);
        String str = num % 2 == 0?"_2020":"_2021";
        tableName += str;
        return tableName;
    }
}
