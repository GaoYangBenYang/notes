## 使用注解前需开启注解功能
```java
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class SpringbootsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootsecurityApplication.class, args);
    }

}

```

## @Secured
    判断是否具有角色，另外注意这里匹配的字符串需要加ROLE_前缀
```java
    @GetMapping("/hello")
    @Secured({"ROLE_admin","ROLE_role"})
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }
```
## @ProAuthorize
    进入方法前进行权限验证，可以将用户的role/permissions参数传到方法中
```java
    @GetMapping("/hello")
    @ProAuthorize("hasAnyAuthority('select')")
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }
```
## @PostAuthorize
    在方法执行完后进行权限验证
```java
    @GetMapping("/hello")
    @PostAuthorize("hasAnyAuthority('select')")
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }
```
## @PostFilter
    权限验证之后对数据进行过滤，留下满足条件的数据
    filterObject用于的是list集合中的某一元素
```java
    @GetMapping("/hello")
    @PostAuthorize("hasAnyAuthority('select')")
    @PostFilter("filterObject.username == 'gy'")
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }
```
## @ProFilter
    进入方法之前进行数据过滤
```java
    @GetMapping("/hello")
    @PostAuthorize("hasAnyAuthority('select')")
    @ProFilter(value="filterObject.username == 'gy'")
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }
```