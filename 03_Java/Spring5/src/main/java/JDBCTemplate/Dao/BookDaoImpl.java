package JDBCTemplate.Dao;

import JDBCTemplate.Pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

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
