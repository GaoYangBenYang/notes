## 配置文件(不实用)
```yaml
spring:
  security:
    user:
      name: gy
      password: 123
```
## 配置类(不实用)
```java

@Configuration
public class Application extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("gy");

        auth.inMemoryAuthentication().withUser("gy").password(password).roles("admin");
    }
    
    //需要创建PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
```
## 自定义编写实现类(重点)
    1.创建配置类，设置使用哪个userDetailsService实现类
```java
package com.gy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//飘红是因为接口没有实现类

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //需要创建PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

```
    2.编写接口实现类，返回User对象，User对象有用户名，密码以及权限
```java
package com.gy.services;

import com.gy.bean.Book;
import com.gy.dao.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoMapper userDaoMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //定义用户bean为book是因为自己定义的User bean会和该框架返回的User起冲突
        Book book = userDaoMapper.selectByName("gy");
        if (book==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(book.getUserName(),new BCryptPasswordEncoder().encode(book.getPassword()),auths);
    }
}


```
