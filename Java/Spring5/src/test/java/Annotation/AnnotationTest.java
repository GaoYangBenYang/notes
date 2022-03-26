package Annotation;

import Annotation.service.UserService;
import AutoWrite.Emp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void test(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationDev.class);
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }
}
