package com.thg.bootdemo.controller.demo;

import com.thg.bootdemo.common.CommonUtil;
import com.thg.bootdemo.common.RedisConfig;
import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.exception.GeneralException;
import com.thg.bootdemo.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.thg.bootdemo.web.config.BootProperties;
import com.thg.bootdemo.web.config.PrefixProperties;

import java.util.Map;

@RestController
@RequestMapping(value="/demo")
@PropertySource("classpath:me.properties")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	BootProperties ps;
	@Autowired
	PrefixProperties prefixBean;

	@Autowired
	RedisConfig redisConfig;

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
	
	@RequestMapping(value="/getProperties", method=RequestMethod.GET)
	public String getProperties() {
		String res = "我是通过注解@Value获取的属性值----com.neo.title："+title;
		res = res + "-----com.neo.description："+description;
		res = res + "----me.key1:"+key1;
		System.out.println(res);
		logger.error("***1****"+res);
//		System.out.println(ps.getConfigInfo());
		logger.debug("===debug==="+ps.getConfigInfo());
		logger.debug("{}", "===debug==="+ps.getConfigInfo());
		logger.info("===info==="+ps.getConfigInfo());
		logger.warn("===warn==="+ps.getConfigInfo());
		System.out.println(prefixBean.getPrefixConfigInfo());
		return res;
	}
	//----------end  直接读取配置文件属性值


	@RequestMapping(value="/getRedisData", method=RequestMethod.POST)
	public String getData(@RequestBody Map inParam){
		String res = null;
		String key = CommonUtil.getString(inParam, "userName");
		String value = CommonUtil.getString(inParam, "email")+System.currentTimeMillis();
		SysUser sysUser = null;
//		stringRedisTemplate.opsForValue().set(key, value);
//		String res = stringRedisTemplate.opsForValue().get(key);
		try {
			redisConfig.setString(key, value);
			res = redisConfig.getString(key);

			sysUser = sysUserService.getSysUserByName(key);
		} catch (GeneralException e) {
			res = e.getMessage();
			e.printStackTrace();
		}
		logger.debug("++++++++++++++"+res+"----------sysUser:"+sysUser);
		return res;
	}
}
