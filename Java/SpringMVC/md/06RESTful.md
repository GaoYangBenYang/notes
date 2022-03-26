```java
@Controller
public class UserCtr {
    //利用RESTful模拟增删改查
    // /user   GET 查询所有用户信息
    // /user/1  GET 根据id查询用户信息
    // /user   POST  添加用户信息
    // /user/1   DELETE  删除用户信息
    // /user   PUT 修改用户信息
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public void selectUser(){
        System.out.println("查询所有用户信息");
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public void selectUserById(){
        System.out.println("根据id"+"查询所有用户信息");
    }


    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void addUser(User user){
        System.out.println("添加用户信息"+user);
    }


    //SpringMVC不识别DELETE和PUT请求
    //需要使用HiddenHttpMethodFilter处理器处理
    @RequestMapping(value = "/user/1",method = RequestMethod.DELETE)
    public void deleteUser(){
        System.out.println("删除用户信息");
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public void updateUser(User user){
        System.out.println("修改用户信息"+user);
    }
}
```

## web.xml配置
```xml
    <!--配置HiddenHttpMethodFilter-->
    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```