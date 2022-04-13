package BeansTest;

import Beans.Orders;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrdersTest {

    @Test
    public void TestBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans/OrdersMapper.xml");
        Orders orders = context.getBean("Orders",Orders.class);
        System.out.println(orders);

        ((ClassPathXmlApplicationContext)context).close();
    }
}
