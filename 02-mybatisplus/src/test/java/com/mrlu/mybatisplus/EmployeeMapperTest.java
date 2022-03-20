package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrlu.mybatisplus.dao.EmployeeMapper;
import com.mrlu.mybatisplus.domain.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-01 17:42
 */
public class EmployeeMapperTest {
   private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   private EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);

   /**
    * 测试通用的插入操作
    */
   @Test
   public void testCommonInsert(){
       //初始化Employee对象
      Employee employee = new Employee();
      employee.setLastName("jack");
      employee.setAge(18);
      employee.setGender(1);

      //没有设置email字段的sql语句
      //INSERT INTO t_employee ( last_Name, gender, age ) VALUES ( ?, ?, ? )
      //employee.setEmail("jack@qq.com");

      //使用@TableField(exist=false)表示不是插入数据库的字段
      //INSERT INTO t_employee ( last_Name, email, gender, age ) VALUES ( ?, ?, ?, ? ) (BaseJdbcLogger.java:137)
      employee.setSalary(8888);

      /**
       * MP的insert的方法在插入的时候，会根据实体类的每个属性进行非空判断
       * 只有非空的属性对应的字段才会出现到SQL语句中.
       * 但是不会插入自增属性的字段
       */
      int insert = employeeMapper.insert(employee);

      System.out.println("result:"+insert);

      //MP会自动将主键值回写到实体类中
      System.out.println("主键值:"+employee.getId());
   }

   /**
    * QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类
    * 用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件
    * 【注意】entity 生成的 where 条件与 使用各个 api 生成的 where 条件没有任何关联行为
    */


   /**
    * 测试通用的更新操作。
    */
   @Test
   public void testUpdate(){
      Employee employee = new Employee();
      employee.setId(29);
      employee.setLastName("marry");
      employee.setAge(25);
      employee.setGender(0);
      //employee.setEmail("marry@qq.com");
      //预编译时的sql语句UPDATE t_employee SET last_Name=?, gender=?, age=? WHERE id=?
      /**
       * updateById(实体类对象)：该方法会对字段进行非空的判断
       */
      Integer result = employeeMapper.updateById(employee);


      /**
       * UPDATE t_employee SET last_Name=?, gender=?, age=? WHERE id=? AND last_Name=?
       * Parameters: marry(String), 0(Integer), 25(Integer), 29(Integer), jack(String) (BaseJdbcLogger.java:137)
       *  update(实体类1，实体类2);
       *  实体类2的属性用于where条件的字段，用and连接，也会进行非空的判断
       *  实体类1的属性用于更新，也会进行非空的判断
       */
      //int result = employeeMapper.update(employee, new UpdateWrapper<>(new Employee(29,"jack",null,null,null)));
      System.out.println("result:"+result);
      System.out.println();
   }

   /**
    * 测试通用的查询操作01
    */
   @Test
   public void testSelect01(){
      //SELECT id,last_Name,email,gender,age FROM t_employee WHERE id=?
      /**
       * 通过主键ID进行查询
       */
      //Employee employee = employeeMapper.selectById(1);
      //System.out.println(employee);

      //2、通过多个列进行查询
      /**
       * selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
       *    传入的参数new UpdateWrapper<>(employee) 将employee的非空属性作为查询的条件，使用and连接这些属性
       *    如果查询到多个结果就会报错
       * SELECT id,last_Name,email,gender,age FROM t_employee WHERE id=? AND last_Name=? AND age=?
       *
       */
      Employee employee = new Employee();
      employee.setId(1);
      employee.setLastName("Tom");
      employee.setAge(22);
      Employee selectOne = employeeMapper.selectOne(new UpdateWrapper<>(employee));
      System.out.println(selectOne);

      System.out.println("======================");
      /**
       * selectBatchIds(包含多个id的list集合)
       *    根据ID 批量查询，返回包含多个查询结果的List集合
       *    方法的参数：主键ID列表(不能为 null 以及 empty)
       *
       * SELECT id,last_Name,email,gender,age FROM t_employee WHERE id IN ( ? , ? , ? , ? )
       *
       */
      List<Integer> list = Arrays.asList(1, 2, 3, 4);
      List<Employee> employees = employeeMapper.selectBatchIds(list);
      employees.forEach(System.out::println);
   }

   /**
    * 测试通用的查询操作02
    */
   @Test
   public void testSelect02(){
      /**
       * selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
       *   columnMap表示字段的map。
       *   map的key是数据库中对应的字段
       *   value是对应字段的属性值
       *
       *  使用and进行连接
       *  SELECT id,last_Name,email,gender,age FROM t_employee WHERE last_name = ? AND age = ?
       *
       */
       Map<String, Object>  columnMap = new HashMap<>();
       columnMap.put("last_name","jack");
       columnMap.put("age",18);
       List<Employee> employees = employeeMapper.selectByMap(columnMap);
       employees.forEach(System.out::println);
      System.out.println("==========================");
      /**
       * SELECT id,last_Name,email,gender,age FROM t_employee WHERE last_Name=? AND age=?
       * selectList(new QueryWrapper<>(employee))
       * QueryWrapper包装的实体类对象的非空字段用于做where的查询对象
       */
      Employee employee = new Employee();
      employee.setLastName("jack");
      employee.setAge(18);
      List<Employee> employees1 = employeeMapper.selectList(new QueryWrapper<>(employee));
      employees1.forEach(System.out::println);
      System.out.println("====================================");
      /**
       * selectCount(new QueryWrapper<>(employee))
       * QueryWrapper包装的实体类对象的非空字段用于做where的查询对象，查询总记录数
       */
      Integer integer = employeeMapper.selectCount(new QueryWrapper<>(employee));
      System.out.println("18岁jack个数："+integer);
      System.out.println("====================================");
      //分页查询
      //Page构造器的第一个参数为当前页码，第二个参数为每页显示的记录数
      //根据 entity 条件，查询全部记录（并翻页）
      Page<Employee> page = employeeMapper.selectPage(new Page<>(3, 2), null);
      //获取到分页查询的结果
      List<Employee> records = page.getRecords();
      records.forEach(System.out::println);

      /*
      使用前的两个步骤：
      <!--    ①构建paginationInterceptor   -->
      <bean id="interceptor" class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor"></bean>
      <!--   ②添加分页插件到MybatisSqlSessionFactoryBean-->
      <property name="plugins" ref="interceptor"></property>

      结果：
      Employee{id=5, lastName='jack', email='jack@qq.com', gender=1, age=18}
      Employee{id=28, lastName='marry', email='jack@qq.com', gender=0, age=25}
       */
   }

   /**
    * 通用的删除操作
    */
   @Test
    public void testDelete(){
       //1、根据主键ID删除
      //DELETE FROM t_employee WHERE id=?
       /*int delete = employeeMapper.deleteById(32);
       System.out.println(delete);*/

      /**
       * 2、 deleteByMap(columnMap)根据 columnMap 条件，删除记录
       *  map的key对应数据库对应表的字段。value对应属性值
       *  DELETE FROM t_employee WHERE last_name = ? AND age = ?
       */
     /* Map<String, Object> columnMap = new HashMap<>();
      columnMap.put("last_name","jack");
      columnMap.put("age",23);
      int i = employeeMapper.deleteByMap(columnMap);
      System.out.println(i);*/

      /**
       * 根据主键ID(不能为 null 以及 empty)列表批量删除
       * DELETE FROM t_employee WHERE id IN ( ? , ? , ? )
       */
     /* List<Integer> list = Arrays.asList(33, 34, 36);
      int i = employeeMapper.deleteBatchIds(list);
      System.out.println(i);*/

      /**
       * delete(new QueryWrapper<>(new Employee(29, "marry", null, null, null)));
       *        queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
       *        封装的对象的属性为条件字段。
       * DELETE FROM t_employee WHERE id=? AND last_Name=?
       */
      int marry = employeeMapper.delete(new QueryWrapper<>(new Employee(29, "marry", null, null, null)));
      System.out.println(marry);

   }
}