## hasAuthority()
    如果当前主体具有指定的权限(例如修改)，则返回true,否则返回false
    
    1.设置相应路径对应的对象权限 .antMatchers("/hello").hasAnyAuthority("update")
```java
package com.gy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
                .loginPage("/login")    //登录页面设置
                .loginProcessingUrl("/login")   //登录访问路径
                .defaultSuccessUrl("/mail").permitAll()    //登录成功之后，跳转路径
                .and().authorizeRequests().antMatchers("/","/login").permitAll()  //哪些路径不需要认证
                .antMatchers("/hello").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and().csrf().disable();    //关闭csrf防护
    }

    /**
     * Security静态资源拦截处理
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**");
    }

}
```
    2.往返回User对象的参数中注入
           List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User(book.getUserName(),new BCryptPasswordEncoder().encode(book.getPassword()),auths);
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
        Book book = userDaoMapper.selectByName("gy");
        if (book==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("select");
        return new User(book.getUserName(),new BCryptPasswordEncoder().encode(book.getPassword()),auths);
    }
}

```
## hasAnyAuthority()
    如果当前主体具有指定的权限类型（例如修改，删除，查看），则返回true,否则返回false
       List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("select","update");

## hasRole
    基于角色进行访问控制
            注意：添加角色权限时，需加ROLE_前缀
     .antMatchers("/hello").hasAnyAuthority("admin")
    List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("select","ROLE_admin");

## hasAnyRole
     基于多角色进行访问控制