## SpringMVC的视图

    SpringMVC的试图是View接口，试图的作用就是渲染数据，将Model中的数据展示给用户
    
    若使用的试图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的试图解析器，由此视图解析器解析之后所得到的是ThymeleafView



## 转发视图
    SpringMVC中默认的转发视图是internalResourceView
    SpringMVC中创建转发视图情况：
        当控制器方法中所设置的视图名称以“forward”为前缀时，创建internalResourceView视图，
        此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀”forward“去掉，
        剩余部分作为最终路径通过转发的方式实现跳转。
```java
    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("转发,请求/testHello");
        return "forward:/testHello";
    }
```

## 重定向视图(实现页面跳转)
    SpringMVC默认的重定向视图是RedirectView
    当控制器方法中所设置的视图名称以”redirect:“为前缀时，创建RedirectView视图，此时的视图名称不会被
    SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀”redirect:“去掉，剩余部分作为最终路径通过
    重定向的方式实现跳转
```java
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("重定向,请求/testHello");
        return "redirect:/testHello";
    }
```

## 视图控制器view-controller
    当控制器方法中，仅仅来实现页面跳转时，即只需要设置视图名称时，可以将处理方法使用view-conntroller标签进行表示
```xml
   <!--
        path:设置处理的请求地址
        view-name:设置请求地址所对应的视图名称
   -->
   <mvc:view-controller path="/" view-name="index"></mvc:view-controller>
```
    特别注意：
        当SpringMVC中设置任何一个view-controller时，其它控制器中的请求映射将全部失效，
        此时需要在SpringMVC的核心配置文件中设置开启mvc注解驱动的标签
```xml
    <!-- 开启mvc注解驱动-->
    <mvc:annotation-driven/>
```