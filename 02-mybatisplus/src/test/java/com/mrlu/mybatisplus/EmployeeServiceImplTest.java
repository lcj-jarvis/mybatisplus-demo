package com.mrlu.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrlu.mybatisplus.dao.EmployeeMapper;
import com.mrlu.mybatisplus.domain.Employee;
import com.mrlu.mybatisplus.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-02 13:11
 *
 * 测试service通用的方法和条件构造器通用的方法
 * 更多的方法见官方手册
 * https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
 *
 *
 */
public class EmployeeServiceImplTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeService employeeService = context.getBean("employeeServiceImpl",EmployeeService.class);
    private EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);


    /**
     * 测试service通用的插入操作
     * 批量插入一些数据，编译后面的测试
     */
    @Test
    public void test01(){
        //插入一条记录
        //Employee employee = new Employee(null,"aabbpdd","aabbpdd@qq.com",1,25);
        //boolean isSuccess = employeeService.save(employee);
        //System.out.println(isSuccess);

        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (gender = ? AND last_name = ? AND email = ? AND age = ?)
        //Employee{id=543, lastName='aabbpdd', email='aabbpdd@qq.com', gender=1, age=25}
        //Map的key是数据库的字段名，value是要存入的查询数据
        //allEq(map); 将map里的查询条件用and拼接加到where后面
        Map<String, Object> map = new HashMap<>();
        map.put("last_name","aabbpdd");
        map.put("email","aabbpdd@qq.com");
        map.put("gender",1);
        map.put("age",25);
        Employee selectedEmp = employeeService.getOne(new QueryWrapper<Employee>()
                .allEq(map));
        System.out.println(selectedEmp);

        //INSERT INTO t_employee ( last_Name, email, gender, age ) VALUES ( ?, ?, ?, ? )
        /*List<Employee> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String name = UUID.randomUUID().toString().substring(0, 5);
            String email = name + i + "@qq.com";
            Integer age = random.nextInt(100) + 1;
            Integer gender = i % 2 == 0 ? 1:0;
            list.add(new Employee(null,name,email,gender,age));
        }
        //batchSize  插入批次数量
        boolean isSuccess = employeeService.saveBatch(list, 500);
        System.out.println(isSuccess);*/
    }


    /**
     * 测试service通用的删除操作
     */
    @Test
    public void test02(){

        // 根据 entity 条件，删除记录
        //boolean remove(Wrapper<T> queryWrapper);
        //QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        // DELETE FROM t_employee WHERE (last_name = ? AND age = ?)
        //boolean remove = employeeService.remove(queryWrapper.eq("last_name", "jack").eq("age", 38));
        //System.out.println(remove);

        // 根据 主键ID 删除
        //    boolean removeById(Serializable id);
        // DELETE FROM t_employee WHERE id=?
        //boolean isRemove = employeeService.removeById(42);
        //System.out.println(isRemove);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE id=?
        //System.out.println(employeeService.getById(42));

        // 根据 columnMap 条件，删除记录
        //    boolean removeByMap(Map<String, Object> columnMap);
        /*
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("last_name","aabbpdd");
        columnMap.put("email","aabbpdd@qq.com");
        columnMap.put("gender",1);

        //注意：null也会加入到条件中
        //DELETE FROM t_employee WHERE gender = ? AND last_name = ? AND email = ? AND age IS NULL
        //columnMap.put("age",null);
        //去掉age为null的情况
        //DELETE FROM t_employee WHERE gender = ? AND last_name = ? AND email = ?
        boolean remove = employeeService.removeByMap(columnMap);
        System.out.println(remove);
        */

        // 删除（根据主键ID列表 批量删除）
        //    boolean removeByIds(Collection<? extends Serializable> idList);
        //DELETE FROM t_employee WHERE id IN ( ? , ? , ? , ? )
        List<Integer> ids = Arrays.asList(47, 48, 49, 50);
        boolean remove = employeeService.removeByIds(ids);
        System.out.println(remove);

    }


    /**
     * 测试service通用的更新操作
     */
    @Test
    public void test03(){

        // 根据 UpdateWrapper 条件，更新记录 需要设置setSql
        //setSql可以用于有选择的更新哪些字段
        //boolean update(Wrapper<T> updateWrapper);
        //UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        //updateWrapper.setSql("last_name='老头子'");
        //updateWrapper.setSql("email='老头子@qq.com'");
        //updateWrapper.eq("last_name","jack").eq("age",25);
        //UPDATE t_employee SET last_name='老头子',email='老头子@qq.com' WHERE (last_name = ? AND age = ?)
        //employeeService.update(updateWrapper);

        // 根据 whereEntity 条件，更新记录
        //boolean update(T entity, Wrapper<T> updateWrapper);

        // 根据 主键ID 选择修改.会对要更新的字段进行非空判断
        //boolean updateById(T entity);
        //UPDATE t_employee SET last_Name=?, email=? WHERE id=?
        //boolean update = employeeService.updateById(new Employee(38, "lucy", "lucy@qq.com", null, null));
        //System.out.println(update);

        // 根据ID 批量更新
        //boolean updateBatchById(Collection<T> entityList);

        // 根据ID 批量更新
        //boolean updateBatchById(Collection<T> entityList, int batchSize);

        /**
         *
         * 根据主键ID 批量更新。会对要更新的字段进行非空判断。只有非空的才会判断
         *
         * @param entityList 实体对象集合
         * @param batchSize  更新批次数量
         *
         * boolean updateBatchById (Collection < T > entityList,int batchSize);
         */
         Employee employee1 = new Employee(51,null,"jack@qq.com",null,null);
         Employee employee2 = new Employee(52,null,"jack@qq.com",null,null);
         Employee employee3 = new Employee(53,null,"jack@qq.com",null,null);
         Employee employee4 = new Employee(54,null,"jack@qq.com",null,null);
         //使用Stream流把这几个对象收集到list集合中
         List<Employee> list = Stream.of(employee1, employee2, employee3, employee4).collect(Collectors.toList());
         //UPDATE t_employee SET email=? WHERE id=?
         boolean isSuccess = employeeService.updateBatchById(list,4);
         System.out.println(isSuccess);
    }

    /**
     * 测试service通用的查询单个的操作
     */
    @Test
    public void test04(){
        // 根据 主键ID 查询
        //T getById(Serializable id);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE id=?
        Employee byId = employeeService.getById(1);
        System.out.println(byId);

        // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，
        // 随机取一条加上限制条件 wrapper.last("LIMIT 1")
        //T getOne(Wrapper<T> queryWrapper);
        //QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("last_name", "jack");
        //Employee one = employeeService.getOne(queryWrapper);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name = ?)
        //System.out.println(one);

        // 根据 Wrapper，查询一条记录
        //T getOne(Wrapper<T> queryWrapper, boolean throwEx);
        /*QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name","a");
        //queryWrapper.last()
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name LIKE ?)
        //false表示有多个结果的时候不抛出异常，并且默认取第一个
        Employee one = employeeService.getOne(queryWrapper, false);
        System.out.println(one);*/

        // 根据 Wrapper，查询一条记录
        //Map<String, Object> getMap(Wrapper<T> queryWrapper);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("last_name","jack").eq("email","jack@qq.com");
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name = ? AND email = ?)
        //将查询的结果封装到Map中，map的key对应数据库的字段，value对应查询出来的结果
        Map<String, Object> map = employeeService.getMap(queryWrapper);
        //{gender=1, id=5, last_Name=jack, email=jack@qq.com, age=18}
        System.out.println(map);

        // 根据 Wrapper，查询一条记录
        //<V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
    }



    /**
     * 条件构造器，查询操作
     */
    @Test
    public void test05(){
        //查询年龄在18到50之间的，而且姓名为jack和gender=1的员工。

        //默认使用and连接条件。QueryWrapper的方法返回的是Wrapper类型，所以可以继续点。、
        //last_name、age、gender等都是数据库对应的列名
        // SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age BETWEEN ? AND ? AND last_name = ? AND gender = ?) LIMIT ?
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<>(1, 2),
                new QueryWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("last_name", "jack")
                        .eq("gender", 1));
        List<Employee> records = employeePage.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 条件构造器常用的方法
     */
    @Test
    public void test06(){
        // 查询所有
        //List<T> list();
        //List<Employee> list = employeeService.list();
        //list.forEach(System.out::println);

        // 查询列表
        //List<T> list(Wrapper<T> queryWrapper);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (email IS NULL)
        //queryWrapper.isNull("email");

        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (email IS NOT NULL)
        //queryWrapper.isNotNull("email");

        //ne:不等于
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name <> ?)
        //queryWrapper.ne("last_name","jack");

        //gt:大于
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age > ?)
        //queryWrapper.gt("age",40);

        //ge:大于等于
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age >= ?)
        //queryWrapper.ge("age",40);

        //lt ：小于
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age < ?)
        //queryWrapper.lt("age",40);

        //lt ：小于
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age <= ?)
        //queryWrapper.le("age",40);

        //between 大于等于val1，小于等于val2
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age BETWEEN ? AND ?)
        //queryWrapper.between("age",18,35);

        //notBetween:between的取反
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age NOT BETWEEN ? AND ?)
        //queryWrapper.notBetween("age",18,25);

        /*
        LIKE '%值%'
        例: like("name", "王")--->name like '%王%'

         SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name LIKE ?)
        */
        //queryWrapper.like("last_name","a");

        /*
         NOT LIKE '%值%'
         例: notLike("name", "王")--->name not like '%王%'
         SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name NOT LIKE ?)
         */
        //queryWrapper.notLike("last_name","a");

        /*
        LIKE '%值'
        例: likeLeft("name", "王")--->name like '%王'

          SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name LIKE ?)
           Parameters: %a_(String)
        */
        //last_name字段中倒数第二个字母是a的
        //queryWrapper.likeLeft("last_name","a_");

        /*
         LIKE '值%'
        例: likeRight("name", "王")--->name like '王%'
        SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name LIKE ?)
        Parameters: _a%(String)
         */
        //last_name字段中第二个字母是a的
        //queryWrapper.likeRight("last_name","_a");

        /*
          in(R column, Collection<?> value)
          in(R column, Object... values)
         */
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age IN (?,?,?))
        //queryWrapper.in("age",Arrays.asList(18,25,35));

        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name IN (?,?,?))
        // Parameters: jack(String), marry(String), Tom(String)
        //queryWrapper.in("last_name","jack","marry","Tom");

        /*

         notIn(R column, Collection<?> value)
         notIn(R column, Object... values)
         */
        // SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name NOT IN (?,?,?))
        //queryWrapper.notIn("last_name","jack","marry","Tom");

        /*
         inSql
         字段 IN ( sql语句 )
        例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
        例: inSql("id", "select id from table where id < 3")
              --->id in (select id from table where id < 3)
              实际上是进行sql语句的拼装
         */
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age IN (18,25,35))
        //queryWrapper.inSql("age","18,25,35");
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (id IN (select id from t_employee where id < 3))
        //queryWrapper.inSql("id","select id from t_employee where id < 3");


        /*
        notInSql  和 inSql的相反
        notInSql(R column, String inValue)
        notInSql(boolean condition, R column, String inValue)
        字段 NOT IN ( sql语句 )
        例: notInSql("age", "1,2,3,4,5,6")--->age not in (1,2,3,4,5,6)
        例: notInSql("id", "select id from table where id < 3")
        --->id not in (select id from table where id < 3)
         */
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age NOT IN (18,25,35))
        //queryWrapper.notInSql("age","18,25,35");

        /*
        groupBy(R... columns)
        groupBy("id", "name")--->group by id,name
         */
        //queryWrapper.notBetween("age",30,40);
        //SELECT id,last_Name,email,gender,age FROM t_employee GROUP BY id,last_name,email,gender,age
        //queryWrapper.groupBy("id","last_name","email","gender","age");


        /*
            orderByAsc
            orderByAsc(R... columns)
            orderByAsc(boolean condition, R... columns)
            排序：ORDER BY 字段, ... ASC
            例: orderByAsc("id", "name")--->order by id ASC,name ASC
        */
        //queryWrapper.orderByAsc("id","age");
        /*
            orderByDesc
            orderByDesc(R... columns)
            orderByDesc(boolean condition, R... columns)
            排序：ORDER BY 字段, ... DESC
            例: orderByDesc("id", "name")--->order by id DESC,name DESC

            orderBy
            orderBy(boolean condition, boolean isAsc, R... columns)
            排序：ORDER BY 字段, ...
            例: orderBy(true, true, "id", "name")--->order by id ASC,name ASC
         */
        //queryWrapper.orderByDesc("id","age");
        //SELECT id,last_Name,email,gender,age FROM t_employee ORDER BY id ASC,age ASC
        //queryWrapper.orderBy(true,true,"id","age");

        /*
        having
        having(String sqlHaving, Object... params)
        having(boolean condition, String sqlHaving, Object... params)
        HAVING ( sql语句 )
        例: having("sum(age) > 10")--->having sum(age) > 10
        例: having("sum(age) > {0}", 11)--->having sum(age) > 11
         */


        /*
        1、or

        or()
        or(boolean condition)
        拼接 OR

        【注意事项】
          主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)

        例: eq("id",1).or().eq("name","老王")--->id = 1 or name = '老王'

        泛型Param均为Wrapper的子类实例(均具有AbstractWrapper的所有方法)
        or(Consumer<Param> consumer)
        or(boolean condition, Consumer<Param> consumer)
        OR 嵌套
        例: or(i -> i.eq("name", "李白").ne("status", "活着"))
             --->or (name = '李白' and status <> '活着')

        2、and
        and(Consumer<Param> consumer)
        and(boolean condition, Consumer<Param> consumer)
        AND 嵌套
        例: and(i -> i.eq("name", "李白").ne("status", "活着"))
        --->and (name = '李白' and status <> '活着')

        3、nested
        nested(Consumer<Param> consumer)
        nested(boolean condition, Consumer<Param> consumer)
        正常嵌套 不带 AND 或者 OR
        例: nested(i -> i.eq("name", "李白").ne("status", "活着"))
        --->(name = '李白' and status <> '活着')
         */
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (last_name = ? OR (age BETWEEN ? AND ?))
        //queryWrapper.eq("last_name","jack").or(i -> i.between("age",15,20));

        /*
         last
        last(String lastSql)
        last(boolean condition, String lastSql)
        无视优化规则直接拼接到 sql 的最后
        注意事项:

        只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
         */
        //SELECT id,last_Name,email,gender,age FROM t_employee ORDER BY id ASC limit 0,5
        //queryWrapper.orderByAsc("id").last("limit 0,5");
        /*queryWrapper.orderByAsc("id").last("limit 0,5");
        List<Employee> list = employeeService.list(queryWrapper);
        list.forEach(System.out::println);*/

        // UPDATE t_employee SET gender=?,email=?,age=? WHERE (last_name = ?)
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("gender",1).set("email",null)
                .set("age",25).eq("last_name","Smith");
        boolean update = employeeService.update(updateWrapper);
        System.out.println(update);
    }

    /**
     * 测试Service通用的查询所有的方法
     */
    @Test
    public void test07(){
        // 查询所有
        //List<T> list();
        //SELECT id,last_Name,email,gender,age FROM t_employee
        //List<Employee> list = employeeService.list();
        //list.forEach(System.out::println);

        // 查询列表
        //List<T> list(Wrapper<T> queryWrapper);
        /*
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id",5);
        // SELECT id,last_Name,email,gender,age FROM t_employee WHERE (id < ?)
        List<Employee> list = employeeService.list(queryWrapper);
        list.forEach(System.out::println);
        */

        // 查询（根据ID 批量查询）
        //Collection<T> listByIds(Collection<? extends Serializable> idList);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE id IN ( ? , ? , ? , ? , ? )
        // Parameters: 1(Integer), 2(Integer), 3(Integer), 4(Integer), 5(Integer)
        /*List<Employee> list = employeeService.listByIds(Arrays.asList(1, 2, 3, 4, 5));
        list.forEach(System.out::println);*/

        // 查询（根据 columnMap 条件）
        //Collection<T> listByMap(Map<String, Object> columnMap);
        //Map<String, Object> map = new HashMap<>();
        //map.put("gender",1);
        //注意null也会加入条件中。
        //map.put("email",null);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE gender = ? AND email IS NULL
        //List<Employee> list = employeeService.listByMap(map);
        //list.forEach(System.out::println);

        // 查询列表
        //List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
        // 查询所有列表
        //List<Map<String, Object>> listMaps();
        /*QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id",5);
        List<Map<String, Object>> list = employeeService.listMaps(queryWrapper);*/
        /*
        把结果封装成键值对的形式。一个结果对应一个map
        {gender=1, id=1, last_Name=Tom, email=tom@atguigu.com, age=22}
        {gender=0, id=2, last_Name=Jerry, email=jerry@atguigu.com, age=25}
        {gender=1, id=3, last_Name=Black, email=black@atguigu.com, age=30}
        {gender=0, id=4, last_Name=White, email=white@atguigu.com, age=35}
         */
        //list.forEach(System.out::println);




        // 查询全部记录
        //List<Object> listObjs();
        /*List<Object> list = employeeService.listObjs();
        //输出所有的主键id
        list.forEach(System.out::println);*/

        // 查询全部记录
        //<V> List<V> listObjs(Function<? super Object, V> mapper);

        // 根据 Wrapper 条件，查询全部记录
        //List<Object> listObjs(Wrapper<T> queryWrapper);
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (id < ?)
        /*QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id",5);
        List<Object> list = employeeService.listObjs(queryWrapper);
        list.forEach(System.out::println);*/

        // 根据 Wrapper 条件，查询全部记录
        //<V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
    }

    /**
     * 测试service通用的分页方法
     */
    @Test
    public void test08(){
        // 无条件分页查询
        //IPage<T> page(IPage<T> page);

        // 条件分页查询
        //IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);

        /*
        分页构造函数
        @param current 当前页
        @param size    每页显示条数
        public Page(long current, long size) {
                this(current, size, 0);
        }
         */
        /*QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (age BETWEEN ? AND ?) LIMIT ?,?
        Page<Employee> page = employeeService.page(new Page<Employee>(2, 2),
                queryWrapper.between("age", 20, 50));
        List<Employee> records = page.getRecords();
        records.forEach(System.out::println);*/

        // 无条件分页查询
        //IPage<Map<String, Object>> pageMaps(IPage<T> page);
        // 条件分页查询
        //IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        //SELECT id,last_Name,email,gender,age FROM t_employee WHERE (id < ?) LIMIT ?,?
        Page<Map<String, Object>> page = employeeService.pageMaps(new Page<>(3, 2),
                queryWrapper.lt("id", 50));
        //以键值对的形式获取
        //{gender=1, id=5, last_Name=jack, email=jack@qq.com, age=18}
        //{gender=0, id=28, last_Name=marry, email=jack@qq.com, age=25}
        List<Map<String, Object>> maps = page.getRecords();
        maps.forEach(System.out::println);

    }

    /**
     * 测试service通用的统计方法
     */
    @Test
    public void test09(){
        // 查询总记录数
        //int count();
        int count = employeeService.count();
        //SELECT COUNT( * ) FROM t_employee
        System.out.println(count);
        // 根据 Wrapper 条件，查询总记录数
        //int count(Wrapper<T> queryWrapper);
        //SELECT COUNT( * ) FROM t_employee WHERE (age BETWEEN ? AND ?)
       /* QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        int count = employeeService.count(queryWrapper.between("age", 20, 50));
        System.out.println(count);*/
    }

}