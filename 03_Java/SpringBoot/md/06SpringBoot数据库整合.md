 ## 数据源的自动配置
        1.导入JDBC
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
```
        2.导入数据库驱动
```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.38</version>
    </dependency>
    想要修改版本
    1.直接依赖引用具体版本（maven的就近依赖原则）
    2.重新声明版本（maven的属性）
    <properties>
        <java.version>1.8</java.version>
        <mysql.version>5.1.38</mysql.version>
    </properties>
```
## 使用Druid数据源


## 整合Mybatis


## 整合Mybati-plus

    
## 整合redis