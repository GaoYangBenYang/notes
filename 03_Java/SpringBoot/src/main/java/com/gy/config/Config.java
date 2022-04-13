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
        return new User("gy",18,"qe");
    }

    @Bean("cat") //自定义组件名,不写默认方法名
    public Pet cat01(){
        return new Pet("hahh");
    }

}
