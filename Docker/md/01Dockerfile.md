FROM xxxxx   #制作基准镜像（基于某某镜像）
FROM scratch #不依赖任何基准镜像

MAINTAINER gy   #哪个人维护或者某机构维护
LABEL version = "1.0" #版本标签
LABEL description = "这是docker构建文件命令" #描述标签

WORKDIR /a/a/a/a   #自动创建文件

ADD hello/   #复制路径
ADD test.tar.gz / #添加根目录并解压
ADD 除了复制，还具备添加远程文件功能

# 设置环境变量
ENV JAVA_HOME /usr/local/openjdk8
RUN ${JAVA_HOME} /bin/java -jar test.jar


#在Build构建时执行命令
RUN yum install -y vim  #Shell命令模式
RUN ["yum","install","-y","vim"]    #Exec命令模式

#容器启动时执行的命令   Dockerfile中只有最后一个ENTRYPOINT会被执行
ENTRYPOINT ["ps"]

#容器启动后执行默认的命令或参数
#Dockerfile中只有最后一个ENTRYPOINT会被执行
#如果容器启动时附加指令，则忽视CMD
CMD ["ps","-ef"]

#运行DockerFile文件
docker build -t name:version（空格）. 
