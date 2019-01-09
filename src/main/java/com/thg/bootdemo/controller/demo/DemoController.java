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
	BootProperties ps;  //代码中直接通过  bean 给属性初始化值
	@Autowired
	PrefixProperties prefixBean;  //通过配置文件，指定前缀初始化值
	
	@RequestMapping(value="/getname", method=RequestMethod.GET)
	public String getName(String userName) {
		return "Hello, " + userName + "..";
	}
	
	//----------begin  直接读取配置文件属性值
	@Value("${com.neo.title}")
	private String title;
	
	@Value("${com.neo.description}")
	private String description;
	
	@Value("${me.key}")
	private String key;
	
	@RequestMapping(value="/getProperties", method=RequestMethod.GET)
	public String getProperties() {
		String res = "我是通过注解@Value获取的属性值----com.neo.title："+title+"\n";
		res = res + "-----com.neo.description："+description+"\n";
		res = res + "----me.key1:"+key+"\n";
//		System.out.println(res);
		logger.error("***1****:"+res);
//		System.out.println(ps.getConfigInfo());
		logger.debug("===debug==="+ps.getConfigInfo());
		logger.debug("{}", "===debug==="+ps.getConfigInfo());
		logger.info("===info==="+ps.getConfigInfo());
		logger.warn("===warn==="+ps.getConfigInfo());
		logger.info("-----------log.info-----prefixBean.getPrefixConfigInfo():"+prefixBean.getPrefixConfigInfo());
		return res;
	}
	//----------end  直接读取配置文件属性值
}
