获取镜像
从 Docker Registry 获取镜像的命令是 docker pull 。其命令格式为：

    docker pull [选项] [Docker Registry地址]<仓库名>:<标签>

运行

    docker run
    
    -it ：这是两个参数，一个是 -i ：交互式操作，一个是 -t 终端。我们这里打算进入 bash 执行一些命令    并查看返回结果，因此我们需要交互式终端。
    
    --rm ：这个参数是说容器退出后随之将其删除。默认情况下，为了排障需求，退出的容器并不会立即删除，除非    手动 docker rm 。我们这里只是随便执行个命令，看看结果，不需要排障和保留结果，因此使用 --rm     可以避免浪费空间。
    
    ubuntu:14.04 ：这是指用 ubuntu:14.04 镜像为基础来启动容器。
    
    bash ：放在镜像名后的是命令，这里我们希望有个交互式Shell，因此用的是bash。

列出镜像

    docker images
    
    //列表包含了仓库名、标签、镜像 ID、创建时间以及所占用的空间。

虚悬镜像

    docker images -f dangling=true

一般来说，虚悬镜像已经失去了存在的价值，是可以随意删除的

    docker rmi $(docker images -q -f dangling=true)

中间层镜像

    docker images -a
    
            默认的 docker images 列表中只 会显示顶层镜像，如果希望显示包括中间层镜像在内的所有镜像的话，需要加 - a 参数。 

列出部分镜像

    根据仓库名列出镜像
    
    docker images 镜像名
    
    列出特定的某个镜像，也就是说指定仓库名和标签
    
    docker images 镜像名:标签
    
    docker images 还支持强大的过滤器参数 --filter ，或者简写 - f
    
    docker images -f since=mongo:3.2

修改内容

    可以使用 docker exec 命令进入容器，修改其内容。
    
     docker exec -if [id] /bin/bash

可以通过 docker diff 命令看到具体的改动。

    docker diff ...

启动容器

    新建并启动
    
    docker run
    
    -t 选项让Docker分配一个伪终端（pseudo-tty）并绑定到容器的标准输入 上，
    
    -i 则让容器的标准输入保持打开。

启动已终止容器

    docker start

后台(background)运行

    -d

终止容器

    docker stop

重启

    docker restart

查看容器元数据

    docker inspect [id]

容器间通过link单向通信

    docker run -d --name [自定义容器名] --link [link的容器名称] [镜像]

查看docker底层网络服务容器

    docker network ls    

容器通过Bridge网桥双向通信

    docker network create -d bridge [自定义网桥名]

通过自定义网桥连接绑定容器

    docker network connect [自定义网桥] [容器名称1]
    docker network connect [自定义网桥] [容器名称2]

Volume容器间共享数据

    通过设置 -v 挂载宿主机目录
        docker run --name 容器名 -v 宿主机路径:容器内挂载路径 镜像名

    通过--volume-from共享容器内挂载点
        创建共享容器
            docker create --name webpage -v /webapps:/tomcat/webapps tomcat /bin/true
        共享容器挂载点
            docker run --volume-from webpage --name [] -d tomcat

进入容器

    docker attach

导出容器

    docker export

导入容器快照

    docker import

删除容器

    docker rm

清理所有处于终止状态的容器

    docker rm $(docker ps -a -q)  