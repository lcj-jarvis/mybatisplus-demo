package com.mrlu.mybatisplus.mp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-03 17:59
 */
public class SelectAll extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //方法名.一定要和mapper接口中对应的方法名一致
        String sqlMethod = "selectAll";
        //sql语句
        String sql = "select * from  " + tableInfo.getTableName();
        //构造SqlSource对象
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        //添加一个对应sql语句类型的MappedStatement
        return addSelectMappedStatementForTable(mapperClass,sqlMethod,sqlSource,tableInfo);
    }
}
