
    HttpMessagesConverter,报文信息转换器，将请求报文转化为java对象，或将Java对象转换为响应报文
    HttpMessagesConverter提供两个注解和两个类型
        @RequestBody,@ResponseBody,RequestEntity,ResponseEntity

## @RequestBody(POST请求才有请求体)
    
    @RequestBody可以获取请求体，需要在控制器方法设置一个形参，使用@RequestBody进行标识，当请求的请求体就会为当前注解所标识的形参赋值
```java
    @RequestMapping(value = "/requestBody",method = RequestMethod.POST)
    public void requestBody(@RequestBody String requestBody){
        System.out.println(requestBody);
    }
```

## RequestEntity
    RequestEntity封装请求报文的一种类型，需要在控制器方法的形参中设置该类型的形参，当前请求的请求报文就会赋值给该参数，
    可以通过getHeaders(）获取请求头信息
    通过getBody()获取请求体信息
```java
  @RequestMapping(value = "/requestEntity",method = RequestMethod.POST)
    public void requestEntity(RequestEntity<String>  requestEntity){
        System.out.println(requestEntity.getHeaders());
        System.out.println(requestEntity.getBody());
    }
```

## @ResponseBody处理Json
```java
   @RequestMapping(value = "/ResponseBody",method = RequestMethod.POST)
    @ResponseBody
    public String responseBody(){
        return "{ code:200 }";
    }
```
    
    直接将对象返回成Json字符串的处理方法

        1.添加依赖
```xml
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.12.4</version>
    </dependency>
```
    
        2.开启MVC注解驱动
```xml
    <!-- 开启mvc注解驱动-->
    <mvc:annotation-driven/>
```
    
        3.添加@ResponseBody注解
        4.将对象返回
```java
    @RequestMapping(value = "/ResponseBody",method = RequestMethod.POST)
    @ResponseBody
    public User responseBody(){
        return new User(1,"gy");
        }
```

## @RestController复合注解
        @RestController注解是SpringMVC提供的一个复合注解，标识在控制器的类上，相当于为类添加
            @Controller注解，并且为其中的每个方法添加@ResponseBody注解

## ResponseEntity（自定义相应报文或文件下载）
        用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文