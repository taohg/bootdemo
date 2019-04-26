package com.thg.bootdemo.controller.demo;

import com.alibaba.fastjson.JSONObject;
import com.thg.bootdemo.common.CommonUtil;
import com.thg.bootdemo.common.DateUtil;
import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.exception.GeneralException;
import com.thg.bootdemo.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import com.thg.bootdemo.web.config.BootProperties;
import com.thg.bootdemo.web.config.PrefixProperties;

import java.util.Map;

@RestController   //@RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
@RequestMapping(value="/demo")
@PropertySource("classpath:me.properties")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	BootProperties ps;
	@Autowired
	PrefixProperties prefixBean;

	@Autowired
    RedisTemplate redisTemplate;

	@Autowired
	SysUserServiceImpl sysUserService;     //要自动装配成功，必须先进行 bean 定义
	
	@RequestMapping(value="/getname", method=RequestMethod.GET)
	public String getName(String userName) {
		return "Hello, " + userName + "..";
	}
	
	//----------begin  直接读取配置文件属性值
	@Value("${com.neo.title}")
	private String title;
	
	@Value("${com.neo.description}")
	private String description;
	
	@Value("${me.key1}")
	private String key1;


	@RequestMapping("/hello")
	public String methodHelloWorld(){
		return "Hello World";
	}

	@RequestMapping("/getBean")
	public SysUser getJavaBean(){
		SysUser user = new SysUser();
		user.setId(1L);
		user.setNickName("testName");
		user.setEmail("123@163.com");
		return user;
	}
	@RequestMapping(value="/getProperties", method=RequestMethod.GET)
	public String getProperties() {
		String res = "我是通过注解@Value获取的属性值:\\r";
		res = res + "----默认--com.neo.title："+title + "\\r\\n";
		res = res + "----默认--com.neo.description："+description + "\\r\\n";
		res = res + "----自定义--me.key1:"+key1 + "\\r\\n";
		System.out.println(res);
		logger.error("***1****"+res);
//		System.out.println(ps.getConfigInfo());
		logger.debug("===debug==="+ps.getConfigInfo());
		logger.debug("{}", "===debug==="+ps.getConfigInfo());
		if(logger.isInfoEnabled()) {
            logger.info("===我是info，把我打印了没，info===" + ps.getConfigInfo());
        }
		String res2 = ps.getConfigInfo();
		String res3 = prefixBean.getPrefixConfigInfo();
		return res + "***" + res2 + "***" + res3;
	}
	//----------end  直接读取配置文件属性值

	@Cacheable(value="user-key") //根据方法生成缓存貌似没有实际意义，每次调用该rest接口都不执行方法体
	@RequestMapping("/getSysUser")
	public SysUser getSysUser() {
		SysUser sysuser = new SysUser();
		sysuser.setUserName("name" + DateUtil.getSysString());
        logger.info("user-key:::::" + JSONObject.toJSONString(sysuser));
		return sysuser;
	}

	@RequestMapping(value="/getRedisData", method=RequestMethod.POST)
	public String getData(@RequestBody Map inParam){
		String res = null;
		ValueOperations operations = redisTemplate.opsForValue();
		String key = CommonUtil.getString(inParam, "userName");
		String value = CommonUtil.getString(inParam, "email")+System.currentTimeMillis();
		SysUser sysUser = null;
//		stringRedisTemplate.opsForValue().set(key, value);
//		String res = stringRedisTemplate.opsForValue().get(key);
		try {
			operations.set(key, value);
			res = (String)operations.get(key);

			sysUser = sysUserService.getSysUserByName(key);
		} catch (GeneralException e) {
			res = e.getMessage();
			e.printStackTrace();
		}
		logger.debug("++++++++++++++"+res+"----------sysUser:"+sysUser);
		return res;
	}
}
