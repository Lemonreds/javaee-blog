# MyBlog

a personal blog site based on Jsp/Servlet - 基于jsp/servlet的个人博客网站

## 简介

java web博客网站，尽最大可能实现mvc模式，没有使用到框架，实现了首页预览，文章发布，点赞，评论，Markdown格式编写，分类，标签，阅读排行，时间轴，管理员管理博客，访客记录等。

* 主要涉及到的知识点有jsp,servlet,mysql,bootstrap,html/css/js,ajax,json

* 数据库连接池使用了[c3p0](http://www.mchange.com/projects/c3p0/) 你可以在src/c3p0-config.xml配置连接池和数据库信息

* Markdown编辑器使用了[editor.md](https://github.com/pandao/editor.md)

### 快速运行

1. 配置 src/c3p0-config.xml 修改你的数据库信息，确认能建立连接。

2. 运行建表sql建立表 src/myblog.sql,可以在此任意插入一些数据以便检查。

3. 搭建服务器环境,如 eclipse、tomcat 导入整个demo.

4. localhost:xxx/blog/ 访问


### 数据库

数据库的设计不是很合理，仅作DEMO，当然你可以重新设计数据库。
你可以在src/目录下找到详细的sql文件。

* t_article - 文章表
* t_article_delet - 删除的文章表 避免误删(不过作用不大)
* t_comment - 评论表
* t_tag - 文章的标签表
* t_user - 管理员表
* t_visitor - 访问记录表

### 目录介绍

#### java

* blog/ajax 接受ajax请求的servlet
* blog/dao 数据库接口类
* blog/daoImple 数据库接口实现类
* blog/db 非主要业务的数据库操作
* blog/filter 过滤器
* blog/junit 测试包
* blog/model bean包
* blog/service 面向web的服务层
* blog/servlet 主要的控制器servlet
* blog/utils 工具包

#### web

* admin 管理员网页
* css 样式
* editormd 
* img 图片
* js javascript
* page 主要网页
* upload 图片上传文件夹

### 引用到的jar包

* mysql-connector-java   mysql的jdbc工具包

* c3p0-0.9.5.2.jar     c3p0数据库连接池
* mchange-commons-java.jar 

* commons-beanutils-1.9.3-bin.zip  BeanUtils
* commons-logging-1.2-bin.zip

* jstl.jar  JSTL标签库
* standard.jar 

* commons-fileupload.jar 文件上传
* commons-io-2.5.jar
* commons-lang-2.5.jar

* commons-collections-3.1 集合工具包

* json-lib-2.1-jdk15 JSON包
* ezmorph-1.0.3

### 图片预览

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/1.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/2.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/3.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/4.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/5.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/6.png)

![无法显示图片](https://github.com/Lemonreds/MyBlog/blob/master/screenshot/7.png)

### 最后

JSP注定要凉凉...