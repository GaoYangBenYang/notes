## 在配置类中实现相关配置
```java
package com.gy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //需要创建PasswordEncoder
    @Bean  
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()  //自定义编写的登录页面
                .loginPage("/login.html")    //登录页面设置
                .loginProcessingUrl("/login")   //登录访问路径
                .defaultSuccessUrl("/main.html").permitAll()    //登录成功之后，跳转路径
                .and().authorizeRequests().antMatchers("/","/login","/hello").permitAll()  //哪些路径不需要认证
                .anyRequest().authenticated()
                .and().csrf().disable();    //关闭csrf防护
    }

}

```