package AutoWrite;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWriteTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AutoWrite/AutoWriteMapper.xml");
        Emp emp = context.getBean("Emp",Emp.class);
        System.out.println(emp);
    }
}
