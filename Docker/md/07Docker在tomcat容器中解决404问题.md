## 解决tomcat容器中访问404问题  
    确定防火墙关闭但访问Tomcat还是失败之后，使用如下命名进入Tomcat的目录
    docker exec -it c110e319cdd1(启动的Tomcat容器的容器id) bash
    
    使用命令查看当前文件夹内的所有文件
    ls -l
    
    进入webapps文件夹下
    cd webapps
    
    查看webapps下的文件
    ls -l
    显示total 0，问题就出在这里，webapps文件夹下没有东西

    返回上一级目录
    cd ..
    
    删除webapps文件夹（-r是级联删除,-f是强制删除，不然文件夹是删不掉的）
    rm -rf webapps
    
    将webapps.dist文件夹下的内容复制到给webapps文件夹
    cp -r webapps.dist webapps
