 ## 注册SpringMVC的前端控制器DispatcherServlet
 
    1.默认的配置方式
        此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，默认名称为<servlet-name>-servlet.xml,
        例如，以下配置所对应Springmvc的配置文件位于WEB-INF下，文件名为SpringMVC-servlt.xml
```xml
    <!--配置SpringMVC的前端控制器，对浏览器发送的请求进行统一管理-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
    <!--映射器-->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <!--设置springmvc的核心控制器所能处理的请求路径-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>

```
    2.扩展配置方式（资源文件集中管理）
```xml
    <!--配置SpringMVC的前端控制器，对浏览器发送的请求进行统一管理-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    
        <!--配置SpringMVC配置文件的位置和名称-->
        <init-param>
            <param-name></param-name>
            <param-value></param-value>
        </init-param>

        <!--将前端控制器DispatcherServlet的初始化时间提前到服务器启动时-->
        <load-on-startup>1</load-on-startup>
    
    
    
    </servlet>
    <!--映射器-->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <!--设置springmvc的核心控制器所能处理的请求路径-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```