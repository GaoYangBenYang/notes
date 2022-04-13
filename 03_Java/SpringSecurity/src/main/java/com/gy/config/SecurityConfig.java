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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    protected void configure(HttpSecurity http) throws Exception {




        //配置没有权限访问时跳转的页面
        http.exceptionHandling().accessDeniedPage("/mail.html");

        //配置登出后用户注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/mail.html").permitAll();

        http.formLogin()  //自定义编写的登录页面
                //.loginPage("/login.html")    //登录页面设置
                .loginProcessingUrl("/login")   //登录访问路径
                .defaultSuccessUrl("/mail.html").permitAll()    //登录成功之后，跳转路径
                .and().authorizeRequests().antMatchers("/","/login").permitAll()  //哪些路径不需要认证
                .antMatchers("/hello").hasAnyAuthority("select")
                .anyRequest().authenticated()
                //设置自动登录配置
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(userDetailsService)
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
        web.ignoring().antMatchers("/css/**","/fonts/**","/images/**","/js/**");
    }


    //配置数据源
    @Autowired
    private DataSource dataSource;

    //自动登录
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //启动是自动创建表格(鸡肋)
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
