## 全局配置文件
```xml
    <!--mybatis可以使用来引用properties属性文件
            resource:引入类路径下的资源
    -->
    <properties resource=""></properties> 


    <!--设置项 -->
    <settings>
        <!--设置每一个设置项,name设置项的名称,value设置项的值
                last_name   ===>  lastName
        -->
        <setting name="mapUnderscoreToCameLCase" value="true" ></setting>
    </settings>

    <!--别名不区分大小写-->
    <typeAliases>
        <!-- 为某一类型起别名 
                type:指定要起别名的类型全类名;默认别名就是类名小写.
                alias:新名字
        -->
        <typeAlias type="com.gy.Pojo.Employee" alias="employee">
        <!-- 批量类型起别名 
                name:指定要起别名的包名;默认别名就是类名小写.
                alias:新名字
        -->
        <package type="com.gy.Pojo">
         <!-- 当多个相同类名时使用@Alias注解为类指定新的别名
                @Alias()
         -->       
    </typeAlias>
        
        
    <!--环境,mybatis可以配置多种环境,default指定配置环境-->
    <environments default="development">
        <!--environment:配置一个具体的环境信息,必须有两个标签,id代表当前的唯一标识

                transactionManager:事务管理器
                    type:事务管理器的类型

                dataSource:数据源
                    type:数据源类型;UNPOOLED|POOLED|JNDI

        -->
        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/sql"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
        
        <environment id="development">
           <transactionManager type="JDBC"/>
           <dataSource type="POOLED">
               <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/sql"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
           </dataSource>
        </environment>
    </environments>


    <!--多数据支持-->
    <databaseIdProvider type="DB_VENDOR">
        <!---给数据库厂商标识取简短别名-->
        <property name="MySQL" value="mysql"/>
        <property name="MongoDB" value="mongodb"/>
    </databaseIdProvider>

    <!--注册映射-->
    <mappers>
        <!--注册类路径下的sql映射文件-->
        <mapper resource="com/gy/Dao/Mapper/EmployeeMapper.xml"/>
        <!--注册网络或者磁盘路径下的sql映射文件-->
        <mapper url="com/gy/Dao/Mapper/EmployeeMapper.xml"/>
        <!--注册接口
            1.有sql映射文件,映射文件名必须和接口名相同,并且放在与接口同一目录下
            2.没有sql映射文件,所有的sql都是写在注解上面的-->
        <mapper class="com.gy.Dao.EmployeeMapper"/>
        <!--批量注册-->
        <mapper name="com.gy.Dao"></mapper>
    </mappers>
```
