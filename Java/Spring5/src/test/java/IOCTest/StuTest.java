package IOCTest;

import IOC.Collect.Book;
import IOC.Collect.Stu;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StuTest {
    @Test
    public void testStu(){
        ApplicationContext context = new ClassPathXmlApplicationContext("IOC/StuMapper.xml");

        Stu stu = context.getBean("Stu",Stu.class);

        stu.test();
    }
    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("IOC/StuMapper2.xml");

        Book book = context.getBean("Book",Book.class);

        book.test();
    }
}
