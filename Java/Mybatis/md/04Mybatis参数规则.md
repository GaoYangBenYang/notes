## 单个参数
    mybatis不会做特殊处理
        #{参数名}:取出参数.

## 多个参数
    mybatis会特殊处理
        多个参数会被封装成一个map,
            key:param1.....paramN,或者参数的索引也许.
            value:传入的参数值
        #{param1}就是从map中获取指定的key的值

## 多个参数取值处理优化  
    明确指定封装参数时map的key:@Param("id")
        多个参数会被封装成一个map,
            key:使用@Param注解指定的值.
            value:传入的参数值
        #{指定的key}就是从map中获取指定的key的值

## 传入POJO
    如果多个参数正好是我们业务逻辑的数据模型,我们就可以直接传入pojo;
    #{属性名}:取出传入的pojo的属性值

## 传入Map
    如果多个参数不是是我们业务逻辑的数据模型,我们就可以直接传入map;
    #{key}:取出map中对应的属性值
   
## 传入TO(Transfer Object)
    如果多个参数不是是我们业务逻辑的数据模型,但是经常使用,我们可以编写一个TO(Transfer Object)数据传输对象;
    例如分页
        Page{
            int index;
            int size;
        }

## 特别注意:如果是Collection (List.Set)类型或者是数组,也会特殊处理。
    也是把传入的List或者数组封装在map中。
        key: collection (collection) ,
        如果是List还可以使用这个key (list)
        数组(array)
    public Employee getEmpById(List<Integer> ids);
        取值:取出第一个id的值: #{list[0]} 