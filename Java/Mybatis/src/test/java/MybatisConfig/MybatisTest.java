package MybatisConfig;

import com.gy.Dao.EmployeeMapper;
import com.gy.Pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    @Test
    public void test() throws IOException {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();

        Employee employee = openSession.selectOne("selectEmp",1);

        System.out.println(employee);

        openSession.close();
    }

    @Test
    public void test2() throws IOException {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);
        //获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        //获取接口的实现类对象
        //mybatis自动为接口创建一个代理对象
        EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);

        System.out.println(employeeMapper.selectEmpById(1));
        System.out.println(employeeMapper.selectEmpById(1));

        openSession.close();
    }
}
