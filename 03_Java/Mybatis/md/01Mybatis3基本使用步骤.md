    工具（洗衣机洗衣服）：JDBC->DBUtils(QueryRunner)->jdbcTemplate 
    
    功能简单：sql语句编写在java代码里面，硬编码高耦合的方式.
    
    框架（全自动洗衣机）：整体解决方案.
    
    Hibernate:全自动全映射orm(Object Relation Mapping)框架，旨在消除sql.
    
    Hibernate（全自动框架）黑箱操作流程：

              |                                                 |
    JavaBean--|--编写sql----预编译----设置参数----执行sql---封装结果--|--dbRecords
              |                                                 |

    我们希望sql语句交给开发人员编写，希望sql不是去灵活性

    Mybatis（半自动，轻量级持久化框架）操作流程：（除了编写sql其余步骤黑箱操作）

                        |                                       |
    JavaBean----编写sql--|--预编译----设置参数----执行sql---封装结果--|--dbRecords
                        |                                       |
## mybatis使用基本步骤

    1.在pom.xml文件中添加依赖
        mybatis jdbc连接数据库驱动  junit单元测试 lombok自动生成get和set
        为了Mapper映射文件和接口能够放在一起，配置<resources>
    
    2.在mysql数据库中创建数据库和表
    
    3.根据数据库中表，创建实体类 com.pojo.AB

    4.创建需要完成的方法，创建接口  com.mapper包中新建 ABMapper接口
    接口中定义抽象方法
    
    5.配置mybatis框架
     打开官网拷贝 https://mybatis.org/mybatis-3/zh/sqlmap-xml.html
     <5.1> 在resources下新建一个 mybatis-config.xml核心配置文件
           配置别名，连接数据的资源，映射文件的位置，具体看代码
     <5.2> 在src/main/java的 com.mapper包中，新建 ABMapper.xml文件
          <mapper name="接口的包名.接口名">
          <insert id="方法名" parameterType="参数的数据类型">sql语句</insert>  插入，修改，删除不用设置返回值类型，都知道返回int
          <update id="方法名" parameterType="参数的数据类型">sql语句</update>
          <delete id="方法名" parameterType="参数的数据类型">sql语句</delete>
          <select id="方法名" parameterType="参数的数据类型" resultType="返回值的数据类型">sql语句</select>
         </mapper>

    6.创建单元测试
         ① 设置核心配置文件的位置
            String resource="mybatis-config.xml";
         ② 使用InputStream指向核心配置文件，SqlSessionFactoryBuilder调用builder创建SqlSessionFactory
            InputStream inputStream=Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory  sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
         ③ 创建SqlSession
           SqlSession sqlSession=sqlSessionFactory.openSession();
         ④ 获取增删改查的接口
           ABMapper abMapper=sqlSession.getMapper(ABMapper.class);
         ⑤ 调用接口中的方法
           若，insert,update,delete 记得手动提交事务  sqlSession.commit();
         ⑥ 关闭sqlSession
          sqlSession.close();
