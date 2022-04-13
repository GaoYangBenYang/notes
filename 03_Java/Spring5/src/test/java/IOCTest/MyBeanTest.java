package IOCTest;

import IOC.Collect.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBeanTest {
    @Test
    public void myBeanTest () throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("IOC/MyBeanMapper.xml");
        Book myBean1 = context.getBean("MyBean", Book.class);
        Book myBean2 = context.getBean("MyBean", Book.class);
        System.out.printf(String.valueOf(myBean1));
        System.out.printf(String.valueOf(myBean2));

    }
}
