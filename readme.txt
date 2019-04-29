1、【构建项目】
	1、[Maven 构建项目]：访问http://start.spring.io/，用来生成基础工程；选择构建工具 Maven Project、Java、Spring Boot 版本 2.1.3 以及一些工程基本信息；点击 Generate Project 下载项目压缩包；解压后，使用 Idea 导入项目，File -> New -> Model from Existing Source.. -> 选择解压后的文件夹 -> OK，选择 Maven 一路 Next，OK done!；如果使用的是 Eclipse，Import -> Existing Maven Projects -> Next -> 选择解压后的文件夹 -> Finsh，OK done!
	2、[IDea构建项目]：选择 File -> New —> Project... 弹出新建项目的框；选择 Spring Initializr，Next 也会出现上述类似的配置界面，Idea 帮我们做了集成；填写相关内容后，点击 Next 选择依赖的包再点击 Next，最后确定信息无误点击 Finish。

2、【单元测试】
    1、创建单元测试类 DemoControllerTests （类的目录一定要和工程启动类的包路径相同，即src/test/java/com/thg/bootdemo，不能直接放到java下）

3、使用eclipse，Import -> Existing Maven Projects -> Next ->选择解压后的文件夹-> Finsh，导入工程

4、引入web模块：
	4.1 添加web模块依赖包；
	4.2 编写controller内容：@RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了
	4.3 启动主程序，打开浏览器访问controller中服务的url，如http://localhost:8080/hello
	
5、修改springBoot对调试的支持，使代码修改后自动重启生效：
	5.1 添加开发调试依赖包spring-boot-devtools
	5.2 设置插件spring-boot-maven-plugin的配置参数fork为true
	
6、自定义过滤器：常常在项目中会使用filters用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。Spring Boot自动添加了OrderedCharacterEncodingFilter和HiddenHttpMethodFilter，并且我们可以自定义Filter
	6.1 自定义过滤器实现Filter接口，实现Filter方法；
	6.2 添加@Configuration 注解（示例中新增WebConfiguration.java作为过滤器的处理类），将自定义Filter加入过滤链

7、读取Property配置属性数据
	7.1 指定属性文件路径：貌似springboot默认的配置文件application.properties无需手工指定路径加载，但自定义属性文件需要通过注解@PropertySource指定；
	7.2 属性数据可以直接通过@Value注解获取；也可以通过java bean读取所有属性数据(注解@Component不能少，否则启动会报错；get/set方法不可少)，然后在需要引用的类中通过@Autowired自动装配

8、集成日志配置（查看pom.xml的日志依赖判断当前用的什么日志框架）
	8.1 使用默认的基本日志配置（如果使用了 Starters ，那么默认使用 Logback），在application.properties中添加配置:
	8.2 使用logback的高级日志配置：删除application.properties中的默认配置；增加logback.xml日志配置文件即可。
	8.3 使用log4j的高级日志配置：1、排除springboot默认的logback日志框架，引入springboot的log4j依赖；2、删除application.properties中关于日志的配置部分，并重命名logback.xml文件使之失效；3、增加log4j.properties文件（设置日志文件编码集为utf-8后文件日志竟然还是乱码，后来莫名其妙又好了，估计跟application.properties中的日志配置没删除有关系）
	8.4 使用log4j2的高级日志配置：1、排除springboot默认的logback日志框架，引入springboot的log4j2依赖；2、增加log4j2.xml文件（日志文件乱码莫名其妙，但用记事本和sublime打开却不是乱码）

9、数据库操作（参数hibernate.hbm2ddl.auto=update没有成功修改表结构）
	9.1 添加依赖包 mysql、spring data jpa；
	9.2 增加数据库和jpa的属性配置；
	9.3 自定义增加jpa的java配置类
	9.4 增加实体类的jpa映射关系，即java类和数据库表间的对应关系（注意引入的注解类大多都是javax.persistence包下）；
	9.5 创建持久层Dao，是一个接口类，该接口类继承了JpaRepository。
	9.6 编写测试用例，通过@Autowired自动装配持久层repository实例，验证数据库的CRUD操作


10、集成redis
	10.1 引入 spring-boot-starter-data-redis依赖包（使用spring-boot-starter-redis依赖包没成功）；
	10.2 配置文件 application.properties 中添加redis的相关属性配置；
	10.3 在自定义类中通过@Autowired自动装配注解获取redis操作实例，可直接进行redis的相关操作

11、集成Spring Session （Spring Session 提供了集群 Session（Clustered Sessions）功能，默认采用外置的 Redis 来存储 Session 数据，以此来解决 Session 共享的问题）
    11.1 引入依赖包 spring-session-data-redis；
    11.2 增加SessionConfig类通过注解configuration配置session（maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，原 Spring Boot 的 server.session.timeout 属性不再生效）
    11.3 前台通过HttpSession发起web请求时后台服务器就会创建session会话；
    11.4 分布式验证：
        1）将工程bootdemo复制为另一个工程，示例中将工程目录、.project文件，以及pom.xml文件中的name和artifactId标签都改为bootdemo2；
        2）application.properties文件中增加server.port=8082属性修改内嵌的web应用服务器监听端口（第一次改为8081的时候提示该端口被占用，通过命令发现是WiFiMaster进程占用了）；
        3）在同一浏览器输入http://localhost:8080/demo/uid和http://localhost:8082/demo/uid返回相同的 uid；在不同的浏览器返回的是不同的 uid





