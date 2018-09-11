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