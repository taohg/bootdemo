package com.thg.bootdemo.controller.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thg.bootdemo.web.config.BootProperties;
import com.thg.bootdemo.web.config.PrefixProperties;

@RestController
@RequestMapping(value="/demo")
@PropertySource("classpath:me.properties")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	BootProperties ps;
	@Autowired
	PrefixProperties prefixBean;
	
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
}
