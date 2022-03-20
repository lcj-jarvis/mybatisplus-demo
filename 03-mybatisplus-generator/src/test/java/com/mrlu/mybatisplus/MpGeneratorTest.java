package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-02 20:10
 */
public class MpGeneratorTest {

    public static void main(String[] args) {
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //是否支持AR模式
        globalConfig.setActiveRecord(true);
        //设置作者
        globalConfig.setAuthor("MrLu");
        //生成的路径：模块的绝对路径 + src下要生成的对应的目录
        globalConfig.setOutputDir("D:\\软件\\ideaproject\\mybatisplus\\03-mybatisplus-generator" +
                "\\src\\main\\java");//注意src前还有一个斜杆，不要漏掉
        //设置每次生成是否重新覆盖
        globalConfig.setFileOverride(true);
        //设置主键处理
        globalConfig.setIdType(IdType.AUTO);
        //设置生成的service接口的名字的首字母是否为大写字母I开头
        //按照下面的写法就设置成不是大写字母I开头
        globalConfig.setServiceName("%sService");
        //设置生成sql映射文件，应该对应javabean类属性的resultMap。因为我们有时候需要自定义方法
        globalConfig.setBaseResultMap(true);
        //生成基本的列，用于sql映射文件来写sql语句。
        globalConfig.setBaseColumnList(true);
        //设置是否开启二级缓存。默认是false。开不开启看自己
        globalConfig.setEnableCache(true);

        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //设置数据库的类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        //设置数据库的驱动
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        //设置连接数据库的url
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatisplus");
        //设置数据库用户名
        dataSourceConfig.setUsername("root");
        //设置数据库密码
        dataSourceConfig.setPassword("root");

        //3、配置策略。【查看源码都有说明】
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置全局大写
        strategyConfig.setCapitalMode(true);
        //数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //设置使用数据库表的前缀
        strategyConfig.setTablePrefix("t_");
        //代码生成器要使用到的表。可以写多个
        strategyConfig.setInclude("t_employee","t_person");
        //是否使用Lombok简化代码。前提是安装了Lombok插件
        //strategyConfig.setEntityLombokModel(true);
        //rest风格的Controller
        strategyConfig.setRestControllerStyle(true);

        //4、包名策略
        PackageConfig packageConfig = new PackageConfig();
        //先设置父表
        packageConfig.setParent("com.mrlu.mybatisplus");
        //此时mapper接口就在com.mrlu.mybatisplus.mpper目录下
        packageConfig.setMapper("mapper");
        //设置实体类所在的包
        packageConfig.setEntity("domain");
        //设置service类所在的包
        packageConfig.setService("service");
        //设置controller类所在的包
        packageConfig.setController("controller");
        //设置生成的sql映射文件所在的包.和mapper接口放在通过一个目录下
        packageConfig.setXml("mapper");

        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.execute();
    }

    /*
    Lombok能以简单的注解形式来简化java代码，提高开发人员的开发效率。
    例如开发中经常需要写的javabean，都需要花时间去添加相应的getter/setter，
    也许还要去写构造器、equals等方法，而且需要维护，当属性多时会出现大量的getter/setter方法，
    这些显得很冗长也没有太多技术含量，一旦修改属性，就容易出现忘记修改对应方法的失误。

     Lombok能通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString方法。
     出现的神奇就是在源码中没有getter和setter方法，但是在编译生成的字节码文件中有getter和setter方法。
     这样就省去了手动重建这些代码的麻烦，使代码看起来更简洁些。
    */
}
