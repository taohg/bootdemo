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

7、读取Property配置属性数据
	7.1 指定属性文件路径：貌似springboot默认的配置文件application.properties无需手工指定路径加载，但自定义属性文件需要通过注解@PropertySource指定；
	7.2 属性数据可以直接通过@Value注解获取；也可以通过java bean读取所有属性数据(注解@Component不能少，否则启动会报错；get/set方法不可少)，然后在需要引用的类中通过@Autowired自动装配

8、集成日志配置
	8.1 使用默认的基本日志配置（如果使用了 Starters ，那么默认使用 Logback），在application.properties中添加配置:
	8.2 使用logback的高级日志配置：删除application.properties中的默认配置；增加logback.xml日志配置文件即可。



