安装手册

本系统需要在linux系统下运行，可以在VMware中安装Ubuntu系统运行此程序。Windows也可以运行但官方安装手册说很难运行且教程不全。在安装linux系统时最好保证linux系统内存大于40g，并且路径需要更改到除c盘以外的其它盘。（内存不够再重新调整内存可能使linux系统奔溃）
命令行按顺序逐个执行即可，不可将几行命令同时复制入命令行窗口同时执行。
以防我步骤有所缺漏，或者表达不清，在本文最后附上同本文顺序一致的各步骤安装教程，如有纰漏可查看本文最后附录。

一、安装前准备（可选）
1.首先自带火狐浏览器检测是否可上网，不可的话在虚拟机设置里将网络适配器改为nat模式。
2.如果用不惯linux、虚拟机部分网页打不开、虚拟机下载速度太慢可以按如下步骤安装Xftp 7将主机的文件传输的虚拟机上。
Sudo apt-get install openssh-server openssh-client
安装完成后检查一下网口输入：
netstat -lnt

