# **舆情系统安装手册**

本系统需要在linux系统下运行，可以在VMware中安装Ubuntu系统运行此程序。Windows也可以运行但官方安装手册说很难运行且教程不全。在安装linux系统时最好保证linux系统内存大于40g，并且路径需要更改到除c盘以外的其它盘。（内存不够再重新调整内存可能使linux系统奔溃）。

命令行按顺序逐个执行即可，不可将几行命令同时复制入命令行窗口同时执行。

以防我步骤有所缺漏，或者表达不清，在本文最后附上同本文顺序一致的各步骤安装教程，如有纰漏可查看本文最后附录。

### 一、**安装前准备（可选）**

1.首先自带火狐浏览器检测是否可上网，不可的话在虚拟机设置里将网络适配器改为nat模式。

2.如果用不惯linux、虚拟机部分网页打不开、虚拟机下载速度太慢可以按如下步骤安装Xftp 7将主机的文件传输的虚拟机上。
Sudo apt-get install openssh-server openssh-client
安装完成后检查一下网口输入：
netstat -lnt

![[yuqing-master/ProIMG/图片1.png at main · mikewang68/yuqing-master (github.com)](https://github.com/mikewang68/yuqing-master/blob/main/ProIMG/图片1.png))

出现这个22端口即可，然后输入ifconfig查看网络端口号

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps1.jpg) 

然后回到自己主机网页搜索Xftp 7下载安装然后左上角新建链接：名称随意，主机填写上面的网络端口号。在虚拟机上输入：

sudo ufw allow 22

打开防火墙运行22端口链接，在主机也打开命令行（win+r  cmd）输入：

Ping 192.168就还是上面那个端口号

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps2.jpg) 

回到Xftp连接即可

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps3.jpg) 

 

### 二、**安装jdk1.8****（必须是jdk1.8否则可能idea不兼容）

1.命令行安装jdk1.8

sudo apt-get update

sudo apt-get install openjdk-8-jdk

 

2、设置环境变量

作用于所有用户：vim /etc/profile

在最下方加上以下代码

export JAVA_HOME=/usr/local/java/jdk1.8

export JRE_HOME=${JAVA_HOME}/jre

export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib

export PATH=.:${JAVA_HOME}/bin:$PATH

 

（vim可能提示找不到命令可：

sudo apt-get update

sudo apt-get install vim

即可使用vim命令）

 

（或者不熟悉linux可以输入

sudo gedit /etc/profile

这样打开的就不是命令行而是文本编辑器，可以方便复制粘贴和保存）

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps4.jpg) 

3、输入下面代码行使修改的配置立刻生效 

source /etc/profile

 

4、检查是否安装成功

java -version

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps5.jpg) 

如图所示jdk1.8就算安装完成。

 

### 三、**安装MySQL**

sudo apt-get update

sudo apt-get install mysql-server

sudo mysql_secure_installation

然后会跳出一个配置页面有很多选项按照以下配置即可：

\#1

VALIDATE PASSWORD PLUGIN can be used to test passwords...

Press y|Y for Yes, any other key for No: N (我的选项)

 

\#2

Please set the password for root here...

New password: (输入密码)

Re-enter new password: (重复输入)

 

\#3

By default, a MySQL installation has an anonymous user,

allowing anyone to log into MySQL without having to have

a user account created for them...

Remove anonymous users? (Press y|Y for Yes, any other key for No) : N (我的选项)

 

\#4

Normally, root should only be allowed to connect from

'localhost'. This ensures that someone cannot guess at

the root password from the network...

Disallow root login remotely? (Press y|Y for Yes, any other key for No) : Y (我的选项)

 

\#5

By default, MySQL comes with a database named 'test' that

anyone can access...

Remove test database and access to it? (Press y|Y for Yes, any other key for No) : N (我的选项)

 

\#6

Reloading the privilege tables will ensure that all changes

made so far will take effect immediately.

Reload privilege tables now? (Press y|Y for Yes, any other key for No) : Y (我的选项)

 

配置完成后检查MySQL是否可以正常工作：

systemctl status mysql.service

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps6.jpg) 

如上现实即为配置成功

 

配置远程访问：

sudo mysql -uroot -p

use mysql

select  User,authentication_string,Host from user;

update user set host='%' where user='root';

FLUSH PRIVILEGES;

select  User,authentication_string,Host from user;

当,root已经多了一条记录,且Host记录值为%,代表已经开启了root的远程访问权限,后续就可以通过root用户远程访问该MySQL了

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps7.jpg) 

 

### 四、**安装redis**

sudo apt-get update 

sudo apt-get install redis-server

启动 Redis

redis-server

查看 redis 是否启动？

redis-cli

以上命令将打开以下终端：

redis 127.0.0.1:6379>

127.0.0.1 是本机 IP ，6379 是 redis 服务端口。输入 PING 命令

redis 127.0.0.1:6379> ping PONG

以上说明已经成功安装了redis

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps8.jpg) 

Redis不需要安装远程连接，到上面这步即可。

 

### 五、**安装idea（个人喜好安装运行程序）**

Idea官网linux下载网址：选择tar.gz格式的压缩包，一定不带是arm的压缩包https://www.jetbrains.com/idea/download/?section=linux#section=linux

解压后在idea目录的bin目录下打开命令行

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps9.jpg) 

./idea.sh

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps10.jpg) 

输入后idea就可以运行了，后面都是可视化界面按照自己喜好安装即可。

 

### 六、**安装docker**

更新 Ubuntu首先，在安装 Docker 之前，您需要确保您的 Ubuntu 系统已更新。使用以下命令更新您的系统：

sudo apt-get update

sudo apt-get upgrade

安装 Docker在更新系统之后，使用以下命令安装 Docker：

sudo apt-get install docker.io

检查docker版本（注意docker要切换到root用户下）

docker -v

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps11.jpg) 

 

拉取镜像文件

docker pull maydays/yuqing:latest

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps12.jpg) 

 

### 七、**安装apache-maven**

去官网https://archive.apache.org/dist/maven/maven-3/

我下载的是3.8.1版本

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps13.jpg) 

之后到下载目录下打开终端进行解压tar.gz

tar -zxvf apache-maven-3.8.1-bin.tar.gz

在用户根目录下，编辑环境

export M2_HOME=/home/downloads/apache-maven-3.8.1

将maven的bin目录添加到path路径

PATH=$M2_HOME/bin:$PATH

之后在idea中配置maven，打开Files，找到setting，选择build execution下打开build tools，选择maven进行配置

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps14.jpg) 

（注意要勾选override，然后变成自己的路径）之后apply再ok就配置好maven文件了。

p.s我之前之所以没数据还是因为我redis配置的问题，首先在终端输入redis -cli看是否出现127.0.0.1：6379>就算连上了redis，还是需要去yml文件下查看host是不是127.0.0.1，且下面端口号也为6379。之后试着运行代码，看是否有数据。

 

### 八、**idea里配置系统**

首先配置mysql，新建数据库链接输入内容如下：输入完后点击下方test connection，如有报错，可以尝试在命令行窗口先进入MySQL创建database名称如截图所示。

![img](file:///C:\Users\34720\AppData\Local\Temp\ksohtml11576\wps15.jpg) 

第二步安装官方yml配置手册配置yml文件下面附上我的举例：

 

\# 1.设置舆情系统web访问端口，默认端口号8084

server:

 port: 8084

 servlet:

   session:

​    cookie:

​     name: local-portal

​     max-age: 3600

​    timeout: 3600

​     

spring:

 thymeleaf:

  prefix: classpath:/templates/

  cache: false

  mode: LEGACYHTML5 # 用非严格的 HTML

  encoding: UTF-8

  servlet:

   content-type: text/html

 http:

 \# 设置编码

  encoding:

   force: true

   charset: UTF-8

   enabled: true

 devtools:

   restart:

​    enabled: true #热部署生效

 application:

  name: stonedt-portal

 

 \# 2.修改设置MySQL服务器地址和用户名及密码

 datasource:

  druid:

   driver-class-name: com.mysql.cj.jdbc.Driver

   url: jdbc:mysql://localhost:3306/stonedt_portal?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useSSL=false

   username: root

   password: 123456

 

 

 \# 3.修改设置Redis服务器地址

 redis:

  database: 0

  host: 127.0.0.1

  port: 6379

  max-active: 10000

  max-idle: 10

  max-wait: 100000

  timeout: 100000

 

 \# flyway 自动建库建表设置

 flyway:

  enabled: true

  locations: classpath:db/migration

  baseline-on-migrate: true

 

​     

mybatis:

  type-aliases-package: com.stonedt.intelligence.entity

  mapper-locations: classpath:mapper/*.xml

 

logging:

 level:

   com.stonedt.intelligence.dao : debug

 

最后找到sitong-yuqing.jar运行即可。

如果在已经配置了jdk但idea里仍旧提示无法运行，没有可用的jdk看本文附录有网页介绍如何配置。



# **附录**

思通舆情分析系统安装官网：https://gitee.com/stonedtx/yuqing/blob/master/install_guide.md#https://gitee.com/stonedtx/yuqing/attach_files

 

Jdk的安装以及fxtp 7 b站视频：https://www.bilibili.com/video/BV1cX4y1y7u2?vd_source=59e6c705f5d373894e4fe93e15c3fb31

 

在ubuntu 中装jdk1.8：https://blog.csdn.net/qq_39871579/article/details/109472475?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522169140730816800227451291%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=169140730816800227451291&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-109472475-null-null.142^v92^koosearch_v1&utm_term=ubuntu%E5%AE%89%E8%A3%85jdk1.8&spm=1018.2226.3001.4187

 

Ubuntu18.04 安装MySQL：https://blog.csdn.net/weixx3/article/details/80782479

 

MySQL开启远程访问权限：https://developer.aliyun.com/article/801237#:~:text=MySQL%E5%BC%80%E5%90%AF%E8%BF%9C%E7%A8%8B%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%201%201.%E8%BF%9E%E6%8E%A5MySQL%E7%8E%AF%E5%A2%83%20%E9%80%9A%E8%BF%87mysql%E5%91%BD%E4%BB%A4%E8%BF%9E%E6%8E%A5MySQL%202%202.%E6%9F%A5%E7%9C%8BMySQL%E5%BD%93%E5%89%8D%E8%BF%9C%E7%A8%8B%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%E9%85%8D%E7%BD%AE%20use%20mysql,4%204.%E5%86%8D%E6%AC%A1%E6%9F%A5%E7%9C%8BMySQL%E8%BF%9C%E7%A8%8B%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%E9%85%8D%E7%BD%AE%20select%20User%2Cauthentication_string%2CHost%20from%20user%3B%20%E6%AD%A4%E6%97%B6%2Croot%E5%B7%B2%E7%BB%8F%E5%A4%9A%E4%BA%86%E4%B8%80%E6%9D%A1%E8%AE%B0%E5%BD%95%2C%E4%B8%94Host%E8%AE%B0%E5%BD%95%E5%80%BC%E4%B8%BA%25%2C%E4%BB%A3%E8%A1%A8%E5%B7%B2%E7%BB%8F%E5%BC%80%E5%90%AF%E4%BA%86root%E7%9A%84%E8%BF%9C%E7%A8%8B%E8%AE%BF%E9%97%AE%E6%9D%83%E9%99%90%2C%E6%88%91%E4%BB%AC%E5%90%8E%E7%BB%AD%E5%B0%B1%E5%8F%AF%E4%BB%A5%E9%80%9A%E8%BF%87root%E7%94%A8%E6%88%B7%E8%BF%9C%E7%A8%8B%E8%AE%BF%E9%97%AE%E8%AF%A5MySQL%E4%BA%86%20

 

ubuntu 16.04安装redis的两种方式教程详解(apt和编译方式）：http://www.imxmx.com/Item/1/211097.html

 

Ubuntu安装Idea,细节教程：https://blog.csdn.net/qq_43646721/article/details/108152206

 

超详细的Idea与MySQL的连接（从入门到精通）https://blog.csdn.net/Royalic/article/details/119604763

 

配置了jdk的环境，idea却提示找不到jdk：https://blog.csdn.net/qq_44791662/article/details/131944161