## 通过Servlet API获取  (一般不要)
```java
    @RequestMapping("/testServletAPI")
    // 形参位置的request表示当前请求
    public string testservletAPI (HttpservletRequest request) {
            string username =request.getParameter( name: "username");
            string password = request.getParameter( name: "password");
            string[] hobby = request.getParameterValues( name: "hobby");
            system.out.println("username: "+username+" ,password:"+password);
            return "success";
    }
```

## SpringMVC获取请求参数

```java
   //根据控制器形参获取请求参数
    @RequestMapping("/testParams")
    public void testParams(Integer id,String username){
        System.out.println("di:"+id+",username:"+username);
        }

    //根据控制器形参获取请求参数（包含多个同名的请求参数）hobby
    @RequestMapping("/testParams")
    public void testParams(Integer id,String username,String hobby){
        //使用字符串类型的参数，使用逗号拼接，String hobby    a,b,b
        //使用字符串数组类型的参数 String[] hobby        [a,b,c]
        System.out.println("di:"+id+",username:"+username);
        }

    //请求参数注解 @RequestParam(value  name   required默认true必须传值    defaultValue设置默认值) 处理请求参数不一致
    @RequestMapping("/testParams")
    public void testParams(Integer id,@RequestParam("user_name") String username){
        System.out.println("di:"+id+",username:"+username);
        }
        
        
    //请求头处理器注解 @RequestHeader(value  name   required默认true必须传值    defaultValue设置默认值) 处理请求参数不一致
    @RequestMapping("/testParams")
    public void testParams(Integer id,@RequestParam("user_name") String username,@RequestHeader("Host") String host){
        System.out.println("di:"+id+",username:"+username+",Host"+host);
        }

    //cookie映射注解 @CookieValue(value  name   required默认true必须传值    defaultValue设置默认值) 处理请求参数不一致
    @RequestMapping("/testParams")
    public void testParams(Integer id,@RequestParam("user_name") String username,@CookieValue("JSESSIONID") String jsessionid){
        System.out.println("di:"+id+",username:"+username+",JSESSIONID"+jsessionid);
    }
    
    //实体类形参
    @RequestMapping("/pojo")
    public void testPojo(User user){
        System.out.println("di:"+user.getId()+",username:"+user.getUsername());
    }
```
 