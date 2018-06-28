# ssm

|author|tan0130|
|------|-------|
|email|1311230692@qq.com|

一、框架
=
* 前端：easyui
* javascript: jquery
* 框架：spring + springmvc + mybatis
* 数据库：mysql
* 日志： logej2

二、包结构
=

* java
  * ssm
    * aop &nbsp;&nbsp;&nbsp;&nbsp;AoP实例<br>
    * aspect  &nbsp;&nbsp;&nbsp;&nbsp;使用@Aspect注解实现AoP<br>
    * controller    &nbsp;&nbsp;&nbsp;&nbsp;用户操作控制器实现<br>
    * dao     &nbsp;&nbsp;&nbsp;&nbsp;用户操作接口<br>
    * dbconnection   &nbsp;&nbsp;&nbsp;&nbsp;log4j2将操作日志写入数据库，数据库连接文件<br>
    * entity &nbsp;&nbsp;&nbsp;&nbsp;用户实体类<br>
    * ioc   &nbsp;&nbsp;&nbsp;&nbsp;ioc注入方式实例<br>
    * service  &nbsp;&nbsp;&nbsp;&nbsp;用户操作业务逻辑层实现<br>
    * test &nbsp;&nbsp;&nbsp;&nbsp;单元测试、IoC和AoP测试<br>
  * resources
    * aop  &nbsp;&nbsp;&nbsp;&nbsp;四种方式实现AoP对应的配置文件<br>
    * ioc  &nbsp;&nbsp;&nbsp;&nbsp;ioc依赖注入的配置文件<br>
    * log  &nbsp;&nbsp;&nbsp;&nbsp;log4j2实现日志记录的配置文件<br>
    * mapper &nbsp;&nbsp;&nbsp;&nbsp;dao接口的配置文件<br>
    * spring &nbsp;&nbsp;&nbsp;&nbsp;springmvc的配置文件<br>
    * applicationContext.xml &nbsp;&nbsp;&nbsp;&nbsp;配置数据库连接的配置文件

三、功能
=

* 页面登录
 * 验证码实现
 * 输入框离开焦点验证
 
* 操作界面
 * 密码修改
 * 信息增删改查
 
 





