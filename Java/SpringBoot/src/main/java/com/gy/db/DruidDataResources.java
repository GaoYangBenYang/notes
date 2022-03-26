package com.gy.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidDataResources {

    //与配置文件进行绑定
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource(){
       DruidDataSource druidDataSource  = new DruidDataSource();
       //不进行配置文件绑定需要手动set属性
//       druidDataSource.setUrl("jdbc:mysql://localhost:3306/sql");
//       druidDataSource.setUsername("root");
//       druidDataSource.setPassword("123456");
        return druidDataSource;
    }


    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletServletRegistrationBean =
                new ServletRegistrationBean<>(statViewServlet, "/druid");
        return servletServletRegistrationBean;
    }
}
