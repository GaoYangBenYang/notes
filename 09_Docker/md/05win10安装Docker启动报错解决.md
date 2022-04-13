    如果你的问题如下图，则可以继续往后面看
    System.InvalidOperationException
    
    Failed to set version to docker-desktop: exit code: -1
    
    
    开始我遇见这个问题的时候，简单百度一下，不过总是治标不治本，很烦恼
    
    cmd/shell下执行
    
    netsh winsock reset
    
    然后重启docker就好了（临时方案，管不了多久，基本上重启电脑就不行了），久而久之就渴望一种永久的方法，不过还好找着了
    
    *********************下面是永久方案**************************
    
    1.下载NoLsp(需要使用VPN等)
    
    http://www.proxifier.com/tmp/Test20200228/NoLsp.exe
    
    或者百度网盘
    
    链接：https://pan.baidu.com/s/14nxzeKvpjf5zSL8Mcu4r8g
    提取码：iq5s
    
    2.下载的文件建议放在C:\Windows\System32下，也可以随便放，位置你知道就行，如果不是NoLsp.exe建议重命名一下，当前也可以不重命名，使用方便就行了
    
    cmd下执行（管理员模式）
    
    NoLsp.exe c:\windows\system32\wsl.exe
    
    如果不对，肯定是路径不对。
    
    3.启动 wsl
    
    执行wsl
    
    4.效果图
    
    
    
    5.重启Restart Docker Desktop
    
    选中图标，重启即可
