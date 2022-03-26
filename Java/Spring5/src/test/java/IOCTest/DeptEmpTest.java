package IOCTest;

import IOC.Bean.Emp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeptEmpTest {
    @Test
    public void DeptEmpTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/IOC/DeptEmpMapper.xml");

        Emp emp = context.getBean("Emp",Emp.class);
        System.out.println(emp.toString());
        emp.add();
    }

}
