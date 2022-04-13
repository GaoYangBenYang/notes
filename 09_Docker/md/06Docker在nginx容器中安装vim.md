## docker nginx容器中安装vim
    #复制原文件备份
    mv /etc/apt/sources.list /etc/apt/sources.list.bak
    
    #修改sources.list
    cat <<EOF >/etc/apt/sources.list
    deb http://mirrors.ustc.edu.cn/debian stable main contrib non-free
    deb http://mirrors.ustc.edu.cn/debian stable-updates main contrib non-free
    EOF
    
    #更新apt
    apt update
    
    #安装vim命令
    apt install vim
    
    #还原sources.list文件
    mv /etc/apt/sources.list.bak /etc/apt/sources.list