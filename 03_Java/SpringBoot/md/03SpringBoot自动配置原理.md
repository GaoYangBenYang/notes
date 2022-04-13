## 1.引导加载自动配置类
```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {}
```
    1.@SpringBootConfiguration
        @Configuration 代表当前是一个配置类
 
    2.@ComponentScan
        指定扫描哪些，Spring注解

    3.@EnableAutoConfiguration
```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```
        @AutoConfigurationPackage 自动配置包，利用@Register给容器中批量注册组件
                                    将Application主程序所在的包下面的所有组件导入容器 


## 2.按需开启自动配置类

## 3.定制化修改自动配置