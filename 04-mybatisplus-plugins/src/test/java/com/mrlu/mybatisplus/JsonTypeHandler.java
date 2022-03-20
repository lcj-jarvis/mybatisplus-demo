package com.mrlu.mybatisplus;

import com.mrlu.mybatisplus.domain.Currency;
import com.mrlu.mybatisplus.domain.Manager;
import com.mrlu.mybatisplus.domain.OtherInfo;
import com.mrlu.mybatisplus.domain.Wallet;
import com.mrlu.mybatisplus.mapper.ManagerMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 14:32
 *
 * 字段类型处理器:JSON 字段类型
 * 1、加入json的依赖
 * 2、在对应的javabean上加注解@TableName(autoResultMap = true)
 * 3、在要以json字符串的形式的保存到数据库的字段使用注解@TableField(typeHandler = JacksonTypeHandler.class)
 * 4、进行测试
 *
 */
public class JsonTypeHandler {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private ManagerMapper managerMapper = context.getBean("managerMapper",ManagerMapper.class);

    /**
     * 去数据库查对应的记录，就会发现以json的形式插入了。
     */
    @Test
    public void test01(){
        Manager manager = new Manager();

        manager.setName("jack");
        OtherInfo otherInfo = new OtherInfo("男", "北京");
        manager.setOtherInfo(otherInfo);
        Wallet wallet = new Wallet();
        wallet.setName("钱包");
        List<Currency> list = new ArrayList<>();
        list.add(new Currency("RMB",8888.0));
        list.add(new Currency("RMB",6666.0));
        wallet.setCurrencyList(list);
        manager.setWallet(wallet);
        int insert = managerMapper.insert(manager);
        System.out.println(insert);
    }

    @Test
    public void test02(){
        Manager manager = managerMapper.selectById(1);
        System.out.println(manager);
    }
}
