## @RequestMapper请求地址唯一

## @RequestMapper注解的功能
    
    从注解名称上看，@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系
    SpringMVC接受到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。

## @RequestMapper注解的位置

    标识类时：设置映射请求请求路径的初始信息
    标识方法时：设置映射请求请求路径的具体信息

## @RequestMapper注解的value,method,headers,params属性

    value:value属性通过请求的请求地址匹配请求映射
          value属性是一个字符串类型的数组，表示改请求映射能够匹配多个请求地址所对应的请求
          value属性必须设置，至少设置一个匹配请求映射的地址
   
    method:method属性通过请求的请求方式（get或post）匹配请求映射
            method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多个请求方式
            **当满足value不满足method时报错405
            
            由于处理器指定请求方式的控制方法，SpringMVC提供了@RequestMapping派生注解
            处理get请求的映射-》@GetMapping
            处理post请求的映射-》@PostMapping
            处理put请求的映射-》@PutMapping
            处理delete请求的映射-》@DeleteMapping

    params:  params = {"username"}  必须携带username参数
             params = {"!username"} 不能携带username参数
             params = {"username=gy"} 必须携带username=gy参数
             params = {"username!=gy"}  不能携带username=gy参数，username可以等于其它值

    headers: headers = {"username"}  请求头必须携带含有username请求信息
             headers = {"!username"} 请求头不能携带username请求信息
             headers = {"username=gy"} 请求头必须携带username=gy请求信息
             headers = {"username!=gy"}  请求头不能携带username=gy参数，username可以等于其它值

## SpringMVC支持ant风格路径

    ？:表示任意的单个字符
    *:表示任意的0个或多个字符
    **:表示任意的一层或多层目录
    注意：在使用**时，只能使用/**/xxx的方式，**的前后不能添加字符

## SpringMVC支持路径中的占位符
    
    原始方法：/deleteUser?id=1&username=gy

    rest方法：/deleteUser/1/gy
            占位符参数获取：
```java
  @GetMapping(value = "/params/{id}/{username}")
    public void paramsTest(@PathVariable Interge id,@PathVariable String username){
        System.out.println("@GetMapping注解params参数");
        return ;
    }
```
    SpringMVC路径中的占位符常用于restful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，
    就可以在相应的@RequestMapping注解的value属性中通过占位符（xxx）表示传输的数据，在通过@PathVariable注解，
    将占位符表示的数据赋值给控制器的形参

       