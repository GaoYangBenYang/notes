## 前提条件，进入nginx目录才能使用命令
    /usr/local/nginx/sbin
    
## 查看nginx版本号
    nginx -v

## 启动nginx
    nginx

## 关闭nginx
    nginx -s stop

## 重新加载nginx
    nginx -s reload

## nginx配置文件
    1.docker中nginx配置文件位置：
        docker上启动nginx,并配置修改nginx的配置文件
        1.使用docker 下载nginx 镜像  docker pull nginx
        2.启动nginx
        docker run --name nginx -p 80:80 -d nginx
        这样就简单的把nginx启动了，但是我们想要改变配置文件nginx.conf ，进入容器,命令：
        docker exec -it nginx bash
        nginx.conf配置文件在 /etc/nginx/  下面，但是你使用vim nginx.conf 或者vi nginx.conf
        会发现vi或者vim命令没有用，解决办法：apt-get  update  完成之后 apt-get install vim
        此时你就可以自己定制nginx.con文件了，改好配置文件之后重启容器，步骤，先把容器停了   
        docker stop nginx  然后重启 docker start nginx
        这样不是很方便，还有第二种方式，挂载配置文件，就是把装有docker宿主机上面的nginx.conf配置文件映射到启动的nginx容器里面，
        这需要你首先准备好nginx.con配置文件,如果你应经准备好了，下一步是启动nginx
        命令：docker run --name nginx -p 80:80 -v /home/docker-nginx/nginx.conf:/etc/nginx/nginx.conf -v /home/docker-nginx/log:/var/log/nginx -v /home/docker-nginx/conf.d/default.conf:/etc/nginx/conf.d/default.conf -d nginx
        解释下上面的命令：
        --name  给你启动的容器起个名字，以后可以使用这个名字启动或者停止容器
        -p 映射端口，将docker宿主机的80端口和容器的80端口进行绑定
        -v 挂载文件用的，第一个-v 表示将你本地的nginx.conf覆盖你要起启动的容器的nginx.conf文件，第二个表示将日志文件进行挂载，就是把nginx服务器的日志写到你docker宿主机的/home/docker-nginx/log/下面
        第三个-v 表示的和第一个-v意思一样的。
        -d 表示启动的是哪个镜像

    2.nginx配置文件由三部分组成
        第一部分:全局快
                从配置文件开始到events块，主要配置一些影响nginx服务器整体运行的配置命令
```xml
    user  nginx;

    worker_processes  auto; //nginx处理并发的关键配置，值越大，可以支持的并发处理量也越多，但是受硬件，软件等设备的制约
    
    error_log  /var/log/nginx/error.log notice;
    pid        /var/run/nginx.pid;

```
        第二部分:events块
                主要影响nginx服务器与用户的网络连接
```xml
events {
    worker_connections  1024; //主持的最大连接数
}

```
        第二部分:http块
                包含http块和server块：
                    http全局快配置的指令包括文件引入、MIME-TYPE定义、日志自定义、连接超时时间、但链接请求数上限等
                    server块和虚拟主机有密切关系，虚拟主机从用户角度看和一台独立的硬件主机是完全一样的，该技术的产生是为了节省互联网服务器硬件的成本
                每个http块可以包含多个server块，而每个server块就相当于一个虚拟主机
                而每个server块也分为全局server块以及同时包含多个location块
            1.全局server块
                常见的配置就是本虚拟机主机的监听配置和本虚拟机主机的名称或ip配置
            2.location块
                一个server块可以配置多个location块
                基于nginx服务器接受的请求字符串（server_name、uri-string）,对虚拟主机名称（也可以是IP别名）之外的字符串（例如前面的、uri-string）进行匹配，
                对特定的请求进行处理，地址定向，数据缓存和应答控制等功能，还有许多第三方模块的配置也在这里进行。
        
```xml
http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';
 
    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}

```

