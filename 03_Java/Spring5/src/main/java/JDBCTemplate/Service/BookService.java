package JDBCTemplate.Service;

import JDBCTemplate.Dao.BookDao;
import JDBCTemplate.Pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
