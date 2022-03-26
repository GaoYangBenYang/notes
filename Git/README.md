----------- git结构及基础命令 -------------

```
工作区（写代码）==》git add ==>暂存区（临时存储）==》git commit ==> 本地区（历史版本） 

==》git push ==> 远程库（代码托管、本地维护）

==》git clone ==> 克隆远程库到本地

==  git pull ==》拉取远程库到本地

------  跨团队协作 ---------

==  git fork ==》复制远程库

==  git pull request ==> 提交拉取请求 给 某远程库 ==》 git merge ==> 审核通过同意合并
```



----------- 代码托管中心(维护本地库) -----------

```
》局域网
》》GitLab服务器

》外网
》》GitHub
》》码云
```



--------------  本地库初始化 ----------

```
命令：git init
效果：生成.git目录
```



--------------  设置签名 --------------

```
用户名：gy
Email地址: 111111111@qq.com 
项目级别/仓库级别：仅仅在当前本地范围内有效
	git config user.name xxx
	git config user.email xxx@qq.com
系统用户级别： 登录当前操作系统的作用范围
    git config user.name --global xxx
	git config user.email --global xxx@qq.com
优先级别：就近原则：项目级别优先于系统级别（必须两者其一）
```



---------------查看工作区、暂存区状态------------------

```
命令：git status
```



---------------将工作区的“新建/修改”添加到暂存区------------

``
命令: git add <file>
```



---------------将提交至暂存区的代码回退到工作区-----------

```
命令：git rm --cached <file>
```



--------------暂存区的内容提交至本地库-----------

```
命令：git commit <file>
####填写提交信息#######
i
:wq
###########
命令：git commit -m "提交信息" <file>
```



---------------提交本地库后修改文件后进行撤销操作----------

```
命令：git restore <file>
```



----------------修改文件后的提交操作----------------

```
命令：git add  <file>  and/or  git commit <file>
```



---------------- 查看历史记录操作 ----------------

```
完整：
命令：git log 
	多屏显示：空格向下翻页 b向上翻页 q退出  

简洁：
命令：git log --pretty=oneline

命令：git log --oneline
	只显示当前版本后的记录操作
	
命令：git log reflog
```



----------------- 前进后退版本 -------------------

```
基于索引值操作
	git reset --hard [局部索引值]

使用^符号,只能后退（一个^符号表示退一步）
	后退一步： git reset --hard HEAD^ 
	后退二步： git reset --hard HEAD^^ 
	
使用~符号，只能后退（补上^符号的缺陷）
	后退N步： git reset --hard HEAD~N
```


------------------ reset命令的三个参数 -------------

```
	--soft 
	仅仅在本地库移动HEAD指针
	
	--mixed
	在本地库移动HEAD指针
	重置暂存区
	
	--hard
	在本地库中移动HEAD指针
	重置暂存区
	重置工作区
```


------------------- 删除文件并找回 --------

	前提：删除前，文件存在时的状态提交到本地库
	操作：git reset --hard [指针位置]
		删除操作已经提交到本地库：指针位置指向历史记录
		删除操作尚未提交到本地库：指针位置使用HEAD



------------------- 比较文件差异 -------------

	git diff HEAD [文件名]
		将工作区中的文件与暂存区进行比较
			
	git diff HEAD [本地库中的历史版本][文件名]
		将工作区的文件和本地库历史记录进行比较
	
	git diff HEAD
		比较多个文件 



------------------- 查看分支 --------------------

```
命令：git branch -v
```



------------------- 创建分支 -----------------

```
命令：git branch <分支名>
```



-------------------- 切换分支 --------------------

```
命令：git checkout <分支名>
```



-------------------- 合并分支 -----------------

```
命令：git merge hot_fix 
	**必须先切换到要合并的分支上面
```



--------------------- 仓库地址备忘录查看 --------------

```
	命令：git remote -v
	添加：git remote add origin xxxxxxxxxxxxxx
						《别名》《地址》
	删除：git remote remove origin xxxxxxxxxxxxxx
							《别名》《地址》
```



--------------------- 推送远程库 -------------

```
命令：git push origin master
  
        **报错：fatal: unable to access 'https://github.com/gaoyang0514/JavaBase.git/': OpenSSL SSL_read: Connection was aborted, errno 10053
        **原因：服务器的SSL证书没有经过第三方机构的签署
        **解决方式：全局使用 
            git config --global http.sslVerify "false" 
            git config --global https.sslVerify "false" 
            命令，然后在cd到对应目录push
```


--------------------- 远程库拉取 -----------

	   	pull = fetch + merge
				
	抓取：不更改本地，可以先进行查看，确定后再merge    
	命令：git fetch origin master
					<远程地址别名> <远程分支名>	
	
	合并：更改本地
	命令：git merge origin/master
					<远程地址别名>/<远程分支名>	



------------------ 解决冲突 ---------

```
	注意：如果不是基于GitHub远程库的最新版所做的修改，不能推送，必须先拉取。
		  如果拉取下来后如果进去冲突状态，则按照“分支冲突解决”，操作解决即可。
```



--------------------- 跨团队协作 -------------

	复制远程库：
	命令：git fork 
	
	申请合并远程库
	命令：git pull request

docker run -itd -v /home/nginxWebUI:/home/nginxWebUI -e BOOT_OPTIONS="--server.port=8081" --privileged=true --net=host  registry.cn-hangzhou.aliyuncs.com/cym1102/nginxwebui:2.1.1 /bin/bash