package JDBCTemplate.Dao;

import JDBCTemplate.Pojo.Book;

import java.util.List;

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
