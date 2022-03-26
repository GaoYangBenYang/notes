package AOP;

import AOP.AspectJAnnotation.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AOP/AspectJ.xml");
        User user = context.getBean("user",User.class);
        user.add();
    }
}
