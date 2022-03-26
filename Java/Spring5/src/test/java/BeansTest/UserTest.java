package BeansTest;

import Beans.User;
import IOC.Dao.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void testAdd(){
        //加载Spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/Beans/UserMapper.xml");

        //获取配置创建的对象   User.class 即将转换的类型
        User user = context.getBean("User",User.class);

        System.out.println(user);
        user.add();
    }

    @Test
    public void testBook(){
        //加载Spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/Beans/UserMapper.xml");

        //获取配置创建的对象   User.class 即将转换的类型
        Book book = context.getBean("book",Book.class);

        System.out.println(book);
        book.testDemo();
    }
}
