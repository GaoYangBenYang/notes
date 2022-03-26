package JDBCTemplateTest;

import JDBCTemplate.Pojo.Book;
import JDBCTemplate.Service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class jdbcTest {

    @Test
    public void test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("JDBCTemplate/JDBCTemplate.xml");

        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book(1,"nodejs","yes");
        //添加
//        bookService.add(book);
        //修改
//        bookService.update(book);
        //删除
//        bookService.delete(book);

//        bookService.selectCount();
//        System.out.println(bookService.selectObject(book.getBookid()));

//        System.out.println(bookService.selectAll().toString());

        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {5};
        Object[] o2 = {6};
        Object[] o3 = {7};
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);

//        bookService.batchAdd(batchArgs);

//        bookService.batchUpdate(batchArgs);

        bookService.batchDelete(batchArgs);

    }
}
