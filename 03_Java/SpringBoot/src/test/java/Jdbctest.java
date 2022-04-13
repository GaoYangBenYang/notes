import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
@Slf4j
@SpringBootTest
public class Jdbctest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws NullPointerException{
        int a = jdbcTemplate.queryForObject("select count(*) from t_book",Integer.class);
        log.info("数据源{}",dataSource.getClass());
        log.info("数据源{}",a);

    }



}
