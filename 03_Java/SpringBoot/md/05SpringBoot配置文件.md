## yaml
    1、配置文件
    SpringBoot使用一个全局的配置文件，配置文件名是固定的；
    •application.properties
    •application.yml
   
    配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；
    
    YAML（YAML Ain't Markup Language）
    YAML A Markup Language：是一个标记语言
    YAML isn't Markup Language：不是一个标记语言；
    
    标记语言：
    以前的配置文件；大多都使用的是 xxxx.xml文件；
    YAML：以数据为中心，比json、xml等更适合做配置文件；
    YAML：配置例子
```yaml
    server:
      port: 8081
```
    XML：
```xml
    <server>
    <port>8081</port>
    </server>
```

    2、YAML语法：
        1、基本语法
        k:(空格)v：表示一对键值对（空格必须有）；
            以空格的缩进来控制层级关系；只要是左对齐的一列数据，都是同一个层级的
```yaml
    erver:
         port: 8081
         path: /hello

```
        属性和值也是大小写敏感；

        2、值的写法
        字面量：普通的值（数字，字符串，布尔）
        k: v：字面直接来写；
        字符串默认不用加上单引号或者双引号；
        ""：双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
        name: "zhangsan \n lisi"：输出；zhangsan 换行 lisi
        ''：单引号；会转义特殊字符，特殊字符最终只是一个普通的字符串数据
        name: ‘zhangsan \n lisi’：输出；zhangsan \n lisi

        3、对象、Map（属性和值）（键值对）：
        k: v：在下一行来写对象的属性和值的关系；注意缩进
        对象还是k: v的方式
```yaml
    friends:
        lastName: zhangsan
        age: 20
```
        行内写法：
```yaml
    friends: {lastName: zhangsan,age: 18}
```
        
        4、数组（List、Set）：
        用- 值表示数组中的一个元素
```yaml
    pets:
        ‐ cat
        ‐ dog
        ‐ pig
```
        行内写法
```yaml
    pets: [cat,dog,pig]
```

    3、配置文件值注入
    配置文件
```yaml
person:
    lastName: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1,k2: 12}
    lists:
        ‐ lisi
        ‐ zhaoliu
    dog:
        name: 小狗
        age: 12
```
    javaBean：
```java
    /**
    * 将配置文件中配置的每一个属性的值，映射到这个组件中
    * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
    * prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
    *
    * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
    *
    */
    @Component
    @ConfigurationProperties(prefix = "person")
    public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```
    我们可以导入配置文件处理器，以后编写配置就有提示了
```xml
    <!‐‐导入配置文件处理器，配置文件进行绑定就会有提示‐‐>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring‐boot‐configuration‐processor</artifactId>
        <optional>true</optional>
    </dependency>
```
    properties配置文件在idea中默认utf-8可能会乱码
        Settting--->File Encodings（文件编码）----->属性文件改为UTF-8 + 勾选
