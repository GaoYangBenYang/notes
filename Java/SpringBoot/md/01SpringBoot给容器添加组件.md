 ```java
package com.gy.config;
//使用注解进行组件配置

import com.gy.bean.Pet;
import com.gy.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 告诉SpringBoot这是一个配置类==配置文件
 * proxyBeanMethods = true 默认开启   代理bean的方法
 *  Full  （proxyBeanMethods = true）组件相互依赖
 *  Lite  （proxyBeanMethods = false）组件相互不依赖
 *
 */
@Configuration(proxyBeanMethods = false)
public class Config {

    /**
     * 外部无论对组件调用多少次都是获取的容器创建的单实例对象
     * @return
     */
    @Bean //给容器中添加组件，以方法名作为组件id,返回类型就是组件类型。返回值就是组件在容器中的实例
    public User user01(){
        return new User("gy",18);
    }

    @Bean("cat") //自定义组件名,不写默认方法名
    public Pet cat01(){
        return new Pet("hahh");
    }

}

```

## @Import    组件导入
        
    给容器中自动创建出对应类型的组件，默认组件的名字就是全类名

```java
 @Import({User.class})
```

## 条件装配 @Conditional

    满足Conditionalzhi指定的条件才进行组件注入

    @ConditionalOnBean(name="cat") 当容器中有cat组件时才进行相应的组件注入



## 导入Spring的xml配置文件  @ImportResources("classpath:xxxx.xml")



## 配置绑定方式一（如何是java读取Properties文件中的内容，并且把它封装进javaBean）
## 将组件放入容器中  @Component
## @Component + @ConfigurationProperties(prefix = "mysql")
```java
package com.gy.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有在容器中的组件才有SpringBoot强大的功能
 */
@Component 
@ConfigurationProperties(prefix = "mysql")
public class MySQL {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MySQL() {
    }

    public MySQL(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MySQL{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

```

## 配置绑定方式二 
## @EnableConfigurationProperties (一定要在配置类里面写 //开启配置绑定功能，并且自动注册到容器中) + @ConfigurationProperties(prefix = "mysql")
```java
package com.gy.config;
//使用注解进行组件配置

import com.gy.bean.Pet;
import com.gy.bean.User;
import com.gy.db.MySQL;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 告诉SpringBoot这是一个配置类==配置文件
 * proxyBeanMethods = true 默认开启   代理bean的方法
 *  Full  （proxyBeanMethods = true）组件相互依赖
 *  Lite  （proxyBeanMethods = false）组件相互不依赖
 *
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MySQL.class) //开启配置绑定功能，并且自动注册到容器中
public class Config {

    /**
     * 外部无论对组件调用多少次都是获取的容器创建的单实例对象
     * @return
     */
    @Bean //给容器中添加组件，以方法名作为组件id,返回类型就是组件类型。返回值就是组件在容器中的实例
    public User user01(){
        return new User("gy",18);
    }

    @Bean("cat") //自定义组件名,不写默认方法名
    public Pet cat01(){
        return new Pet("hahh");
    }

}

```

```java
package com.gy.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有在容器中的组件才有SpringBoot强大的功能
 */
@Component 
@ConfigurationProperties(prefix = "mysql")
public class MySQL {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MySQL() {
    }

    public MySQL(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MySQL{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

```