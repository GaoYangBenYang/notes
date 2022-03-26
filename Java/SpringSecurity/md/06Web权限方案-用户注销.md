## 在配置类中配置
```java
        //配置登出后用户注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/mail.html").permitAll();
```