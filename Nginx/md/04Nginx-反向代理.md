## 反向代理实现访问/0跳转3000端口,访问/1跳转3001端口
```xml
server{
        listen          80;
        server_name         127.0.0.1;

        location ~ /0/ {
            proxy_pass      http://localhost:3000;
        }

        location ~ /1/ {
            proxy_pass      http://localhost:3001;
        }
}
```

## location指令说明
    该指令用于匹配URL
    语法如下
```xml


     location [ = | ~ | ~* | ^~ ] uri {
     
          proxy_pass      http://localhost:3001;
     
     }
```
    = : 用于不含正则表达式的uri前，要求请求字符串与uri严格匹配,如果匹配成功，就停止继续向下搜索并立即处理该请求
    ~ : 用于表示uri包含正则表达式，并且区分大小写
    ~* : 用于表示uri包含正则表达式，并且不区分大小写
    ^~ : 用于不含正则表达式的uri前，要求Nginx服务器找到标识uri和请求字符匹配度最高的location后，
         立即使用此location处理请求，而不是使用location块中的正则uri和请求字符串做匹配。

    注意：如果uri包含正则表达式，则必须要有~或~*标识。