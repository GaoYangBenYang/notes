## 一.什么是JDBCTemplate

        (1) Spring框架对JDBC进行封装，使用JDBCTemplate很方便对数据库进行操作

        (2) 准备工作

            第一步,引入依赖
```xml
    <!--   JDBCTemplate-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.9.RELEASE</version>
    </dependency>

    <!--    事务相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.2.9.RELEASE</version>
    </dependency>

    <!--    整合其他数据库框架需要-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
      <version>5.2.9.RELEASE</version>
    </dependency>

<!--    mysql驱动-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.38</version>
    </dependency>

<!--    德鲁伊连接池-->
    <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.10</version>
    </dependency>
```
            第二步,配置德鲁伊连接池:
```xml
<!--数据库连接池-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
    <property name="url" value="jdbc:mysql://localhost:3306/sql"></property>
    <property name="username" value="root"></property>
    <property name="password" value="123456"></property>
    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
</bean>

```
            第三步,创建jdbcTemplate对象,并且注入属性
```xml
<!--    创建jdbcTemplate对象-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
```

## 增 删 改 查询返回某个值 查询返回对象 查询返回集合
```java
public interface BookDao {
    //添加
    void add(Book book);
    //修改
    void update(Book book);
    //删除
    void delete(Book book);

    //查询(返回单一值)
    int selectCount();

    //查询(返回单一对象)
    Book selectObject(Integer id);

    //查询(返回集合对象)
    List<Book> selectAll();

    //批量添加
    void batchAdd(List<Object[]> batchArgs);

    //批量修改
    void batchUpdate(List<Object[]> batchArgs);

    //批量删除
    void batchDelete(List<Object[]> batchArgs);
}


```

        **增删改操作方法及参数

```java
@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加
    @Override
    public void add(Book book){
        //1.创建sql语句
        String sql = "insert into t_book values(?,?,?)";
        //2.调用参数实现
        //jdbcTemplate.update(sql语句,对应的可变参数)
        int add = jdbcTemplate.update(sql,book.getBookid(), book.getBookname(),book.getBookstatus());
        System.out.println(add);
    }

    //修改
    @Override
    public void update(Book book){
        //1.创建sql语句
        String sql = "update t_book set bookname = ? , bookstatus = ? where bookid = ?";
        //2.调用参数实现
        //jdbcTemplate.update(sql语句,对应的可变参数)
        int update = jdbcTemplate.update(sql,book.getBookname(), book.getBookstatus(),book.getBookid());
        System.out.println(update);
    }

    //删除
    @Override
    public void delete(Book book){
        //1.创建sql语句
        String sql = "delete from t_book where bookid = ?";
        //2.调用参数实现
        //jdbcTemplate.update(sql语句,对应的可变参数)
        int delete = jdbcTemplate.update(sql,book.getBookid());
        System.out.println(delete);
    }

    //查询返回某个值
    @Override
    public int selectCount() {
        //1.创建sql语句
        String sql = "select count(*) from t_book";
        //2.调用参数实现
        //jdbcTemplate.queryForObject(sql语句,返回类型Class)
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    //返回某一对象
    @Override
    public Book selectObject(Integer id) {
        //1.创建sql语句
        String sql = "select bookid,bookname,bookstatus from t_book where bookid = ?";
        //2.调用参数实现
        //jdbcTemplate.queryForObject(sql语句,RowMapper(是接口,返回不同类型数据,使用这个接口里面实现类完成数据封装),sql语句?中的值);
        Book book = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Book>(Book.class),id);
        return book;
    }

    //返回集合
    @Override
    public List<Book> selectAll() {
        //1.创建sql语句
        String sql = "select bookid,bookname,bookstatus from t_book";
        //2.调用参数实现
        //jdbcTemplate.query(sql语句,RowMapper(是接口,返回不同类型数据,使用这个接口里面实现类完成数据封装));
        List<Book> listBook = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Book>(Book.class));
        return listBook;
    }

    /**
     使用RowMapper接口的实现类时  实体类需创建无参构造器
     */

    //批量添加
    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        //1.创建sql语句
        String sql = "insert into t_book values(?,?,?)";
        //2.调用参数实现
        //jdbcTemplate.batchUpdate(sql语句,添加多条记录的集合)
        int[] inserts = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(inserts));
    }

    //批量修改
    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        //1.创建sql语句
        String sql = "update t_book set bookname = ? , bookstatus = ? where bookid = ?";
        //2.调用参数实现
        //jdbcTemplate.batchUpdate(sql语句,添加多条记录的集合)
        int[] inserts = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(inserts));
    }

    //批量删除
    @Override
    public void batchDelete(List<Object[]> batchArgs) {
        //1.创建sql语句
        String sql = "delete from t_book where bookid = ?";
        //2.调用参数实现
        //jdbcTemplate.batchUpdate(sql语句,添加多条记录的集合)
        int[] inserts = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(inserts));
    }
}
```
```java
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void add(Book book){
        bookDao.add(book);
    }

    public void update(Book book){
        bookDao.update(book);
    }

    public void delete(Book book){
        bookDao.delete(book);
    }

    public void selectCount(){
        System.out.println(bookDao.selectCount());
    }

    public Book selectObject(Integer bookid){
        return bookDao.selectObject(bookid);
    }

    public List<Book> selectAll(){
        return bookDao.selectAll();
    }

    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAdd(batchArgs);
    }
    public void batchUpdate(List<Object[]> batchArgs){
        bookDao.batchUpdate(batchArgs);
    }
    public void batchDelete(List<Object[]> batchArgs){
        bookDao.batchDelete(batchArgs);
    }
}
```

