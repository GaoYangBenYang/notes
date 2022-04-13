    <面试题1> Mybatis原理
    （1）Mybatis是一个持久层框架。能够对数据库进行增删改查操作。
    （2）Mybatis的核心理念（宗旨）是，用少量的代码就能实现对数据库的操作。
    （3）Mybatis是通过Mapper层(DAO层)零实现来解决对数据库的操作。所谓的零实现，
    就是通过扫描Mapper接口和XML映射文件，生成动态代理对象。
    这就要求映射文件中通过<select>、<insert>、<delete>、<update>等标签来实现定义sql语句。
    （4）为了提高sql的重用性，Mybatis实现了动态sql编程，即在XML映射文件中，
    使用<if>、<where>、<foreach>等一系列标签，来封装同一条sql语句。
    
    
    <面试题2> Mybatis的执行流程是怎样的?
    (1) 添加mybatis-config.xml文件
    (2) XMLConfigBuilder读配置文件，创建一个Configuration对象
    (3) SqlSessionFactoryBuilder加载这个Configuration对象，单例模式创建一个SqlSessionFactory
    (4) SqlSessionFactory对象调用openSession,返回一个SqlSession对象
    (5) SqlSession是当前程序和mysql数据库一次会话，只有连接才能执行sql语句
    (6) Executor接口才是真正执行sql语句的对象
    它会调用StatementHandler将sql语句发送给数据库
    执行数据库，若是查询语句，则会通知ResultSetHandler处理结果集，转换成实体对象
    
    <面试题3> 简述ORM?
    (1)对象关系映射（Object Relational Mapping，简称ORM）是通过使用描述对象和数据库之间映射的元数据，
    (2)常见的ORM框架有：Hibernate、MyBatis、Spring Data等。
    (3) 原理：要实现实体类的属性 和   数据库表的字段的映射，
    任何ORM框架不外乎是读某个配置文件把实体类的属性和
    数据库表的字段自动关联起来，当从数据库Query时，
    自动把字段的值塞进JavaBean的对应属性里，当做INSERT或UPDATE时，
    自动把 JavaBean的属性值绑定到SQL语句中。
    
    <面试题4>:  #{}和${}的区别是什么？
    #{}是预编译处理，${}是字符串替换。
    Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；
    Mybatis在处理${}时，就是把${}替换成变量的值。 字符串拼接
    使用#{}可以有效的防止SQL注入，提高系统安全性。