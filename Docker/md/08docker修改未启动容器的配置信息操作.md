#docker修改未启动容器的配置信息操作
之前一开始用docker并没有使用dockerfile或者docker-compose编排服务，直接使用docker run启动的容器，结果一不小心配
置文件出错或者给停掉了那就导致服务再也启动不了了，这时候不要着急，使用以下办法既可以修改配置文件还可以修改启动
容器的端口信息和其他配置信息，这样容器中的文件和数据还是存在的。
1、修改容器内的配置信息
这里以nginx启动为例子，比如用docke run创建了一个nginx的容器，结果因为使用docker exec ** bash 修改了内部nginx的配
置文件，导致nginx容器再也无法启动了，那这种情况该怎么办呢
#首先进入以下目录
cd /var/lib/docker/overlay2
#查找配置文件
find ./ -name nginx.conf
./a45cd97113877fb480f66e0d982a594c7b18f2035c16e1f7eb687eef15552272/diff/etc/nginx/nginx.conf
./feff64c1f27a695e531c4654afa3b06e1ca84cc38d81cec76dc5ef52f6821c44/diff/etc/nginx/nginx.conf
./feff64c1f27a695e531c4654afa3b06e1ca84cc38d81cec76dc5ef52f6821c44/merged/etc/nginx/nginx.conf
#然后cd到nginx.conf的所在目录
cd ./feff64c1f27a695e531c4654afa3b06e1ca84cc38d81cec76dc5ef52f6821c44/diff/etc/nginx
#比如我的nginx的配置文件是在conf.d文件夹下
cd conf.d
default.conf
#观察里面的配置信息是不是自己nginx的配置信息，如果是的话那就修改它就行了，不是的话就看find查找出来的其他目录中用同样的方式去找就可以了
vim *.conf
#修改好配置文件后，直接启动比如我的nginx容器名是apg_nginx
docker start apg_nginx
2、修改docker run时的配置信息
这种情况比如我的容器被停掉了，然后有其他的服务想用我这个容器映射出来的端口号或者我的容器想重新修改当时run配置
的信息那怎么办呢？
#进入以下目录
cd /var/lib/docker/containers
096ed809576e948ada99be65faa181b7f926dd0e655c4c373169305a1954c115
d570bc34c3bb5955ec4c336ad3eeb6105ed49e33e87b8dfd35da3c907d074fdc
662d928aada58645e84fba34f1a1c54696061b767e3e262ccf6562db0498e733
e7237690488f15e40a6462df9cdcfeda83a6f7a74517662935c0a149dd36e057
92bb44f63186c3d80ab8557f84cb1ce907aedab714bb0109827dbfed5641daa8
f2849f199fd78b1636aaedf0bd890c12e7c6d8c6ad5849fff2670920e5e37c7d
d27bdc6286dd3314a1116115cab3a33233b9f4fba45ae4c88a6756d5c04a9aa9
#查看自己容器的hash值，hash值开头的文件夹就是容器的配置目录
docker ps -a
CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES
096ed809576e nginx "nginx -g 'daemon of…" 3 months ago Up 33 minutes 0.0.0.0:8081->80/tcp apg_nginx
#进入096开头的目录
cd 096ed809576e*
drwx------ 4 root root 4096 Jun 22 21:41 ./
drwx------ 9 root root 4096 Mar 8 18:07 ../
-rw-r----- 1 root root 2559664 Jun 22 21:47 096ed809576e948ada99be65faa181b7f926dd0e655c4c373169305a1954c115-json.log
drwx------ 2 root root 4096 Mar 8 18:07 checkpoints/
-rw------- 1 root root 3408 Jun 22 21:41 config.v2.json
-rw-r--r-- 1 root root 1519 Jun 22 21:41 hostconfig.json
-rw-r--r-- 1 root root 13 Jun 22 21:41 hostname
-rw-r--r-- 1 root root 174 Jun 22 21:41 hosts
drwx------ 3 root root 4096 Mar 8 18:07 mounts/
-rw-r--r-- 1 root root 259 Jun 22 21:41 resolv.conf
-rw-r--r-- 1 root root 71 Jun 22 21:41 resolv.conf.hash
#hostconfig.json就是存放run启动时的配置信息的文件
vim hostconfig.json
#在里面找到HostPort值就是映射到宿主机的端口号，可以自己修改，修改完成后要重启docker服务，不重启的话不会生效的
systemctl docker restart
通过以上两种情况的下的配置修改，对于docker run启动的容器出现问题都可以在此修改并正常启动，一般最好使用dockercompose的方式启动容器最佳。
补充知识：docker怎么查看容器启动日志
sudo docker logs -f -t –tail 行数 容器名
命令格式：
$ docker logs [OPTIONS] CONTAINER
Options:
--details 显示更多的信息
-f, --follow 跟踪实时日志
--since string 显示自某个timestamp之后的日志，或相对时间，如40m（即40分钟）
--tail string 从日志末尾显示多少行日志， 默认是all
-t, --timestamps 显示时间戳
--until string 显示自某个timestamp之前的日志，或相对时间，如40m（即40分钟）
查看最近30分钟的日志：
$ docker logs –since 30m CONTAINER_ID
查看某时间之后的日志：
$ docker logs -t –since=”2019-08-02T13:23:37″ CONTAINER_ID
查看某时间段日志：
$ docker logs -t –since=”2019-08-02T13:23:37″ –until “2019-08-03T12:23:37” CONTAINER_ID
以上这篇docker修改未启动容器的配置信息操作就是小编分享给大家的全部内容了，希望能给大家一个参考，也希望大家多多
支持软件开发网。
您可能感兴趣的文章:Docker 配置阿里云容器服务操作Docker容器网络端口配置过程详解docker中容器的网络配置常用命令详
解win7环境下Docker快速构建及阿里云容器加速配置详解Docker容器修改配置文件的实现Docker配置容器位置与小技巧总结
nginx在docker容器中自动生成配置文件