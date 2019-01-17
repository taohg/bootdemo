1、访问http://start.spring.io/，用来生成基础工程

2、选择构建工具Maven Project、Spring Boot版本1.3.6以及一些工程基本信息，点击生成并下载工程压缩包

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
	6.2 添加@Configuration 注解，将自定义Filter加入过滤链

7、读取Property配置属性数据（浏览器中输入http://localhost:8080/demo/getProperties调用接口验证）
	7.1 指定属性文件路径：貌似springboot默认的配置文件application.properties无需手工指定路径加载，但自定义属性文件需要通过注解@PropertySource指定；
	7.2 属性数据可以直接通过@Value注解获取；也可以通过java bean读取所有属性数据(注解@Component不能少，否则启动会报错；get/set方法不可少)，然后在需要引用的类中通过@Autowired自动装配
	    但存在乱码问题，IDEA中设置文件编码格式为utf-8且“Transparent native-to-ascii conversion”，貌似会自动将中文转换成unicode码

8、集成日志配置
	8.1 使用默认的基本日志配置（如果使用了 Starters ，那么默认使用 Logback），在application.properties中添加配置:
	8.2 使用logback的高级日志配置：删除application.properties中的默认配置；增加logback.xml日志配置文件即可。
	8.3 使用log4j的高级日志配置：1、排除springboot默认的logback日志框架，引入springboot的log4j依赖；2、删除application.properties中关于日志的配置部分，并重命名logback.xml文件使之失效；3、增加log4j.properties文件（设置日志文件编码集为utf-8后文件日志竟然还是乱码，后来莫名其妙又好了，估计跟application.properties中的日志配置没删除有关系）
	8.3 使用log4j2的高级日志配置：1、排除springboot默认的logback日志框架，引入springboot的log4j2依赖；2、增加log4j2.xml文件（日志文件乱码莫名其妙，但用记事本和sublime打开却不是乱码）

9、数据库操作
	9.1 添加依赖包 mysql、spring data jpa；
	9.2 增加数据库和jpa的属性配置；
	9.3 自定义增加jpa的java配置类
	9.4 增加实体类的jpa映射关系，即java类和数据库表间的对应关系（注意引入的注解类大多都是javax.persistence包下）；
	9.5 创建持久层Dao，是一个接口类，该接口类继承了JpaRepository。
	9.6 编写测试用例，通过@Autowired自动装配持久层repository实例，验证数据库的CRUD操作

10、集成redis
    10.1 添加依赖包 spring-boot-starter-data-redis、jedis；
    10.2 增加redis配置属性文件 redis.properties；
    10.3 封装 RedisTemplate 的 RedisUtil 缓存操作工具类（遇到【非法字符：“\ufeff”】异常时，把redis升级到2.8以上版本即可，可参考https://blog.csdn.net/shenshizhong/article/details/52725069）；
    10.4 封装 redis 单机版配置类 RedisConfig，参考 【https://blog.csdn.net/plei_yue/article/details/79362372】配置集群模式和哨兵模式
    10.5 在DemoController中增加接口方法testRedis，通过接口测试工具postman输入地址http://localhost:8080/demo/testredis验证接口

11、集成spring session共享
    11.1 添加依赖包 spring-session-data-redis；
    11.2 增加自定义 SessionConfig 类，并通过注解 @EnableRedisHttpSession 开启 spring session 支持，maxInactiveIntervalInSeconds设置session失效时间单位是秒；
    11.3 application.properties中增加redis配置
    11.4 在 DemoController 中增加接口方法 uid，通过浏览器输入地址http://localhost:8080/demo/uid验证接口
    11.5 运行两套工程代码，在application.properties中设置内置tomcat的端口分别为8080和8090，在同一浏览器中通过不同端口分别访问 http://localhost:8080/demo/uuid 接口


