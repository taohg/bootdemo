package com.thg.bootdemo.controller.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thg.bootdemo.util.RedisUtil;
import com.thg.bootdemo.web.config.BootProperties;
import com.thg.bootdemo.web.config.PrefixProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="/demo")
@PropertySource(value="classpath:me.properties", encoding="UTF-8")    //自定义属性乱码要通过encoding设置编码格式，感谢楼主分享 https://www.cnblogs.com/tulpen/p/9803116.html
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	BootProperties ps;  //代码中直接通过  bean 给属性初始化值
	@Autowired
	PrefixProperties prefixBean;  //通过配置文件，指定前缀初始化值

	@Autowired
	private RedisUtil redisUtil;


	@RequestMapping(value="/getname", method=RequestMethod.GET)
	public String getName(String userName) {
		return "Hello, " + userName + "..";
	}
	
	//----------begin  通过@Value注解获取配置文件属性值
	@Value("${com.neo.title}")
	private String title;
	
	@Value("${com.neo.description}")
	private String description;
	
	@Value("${me.key}")
	private String key;
	
	@RequestMapping(value="/getProperties", method=RequestMethod.GET)
	public String getProperties() {
		String res = "我是通过注解@Value获取的属性值";
		res = res + "-----com.neo.title："+title+"|||||||||";
		res = res + "-----com.neo.description："+description+"|||||||||";
		res = res + "-----me.key:"+key+"|||||||||";
//		System.out.println(res);
		logger.error("-----------log.error-----:"+res);
		logger.error("-----------log.error-----prefixBean.getPrefixConfigInfo():"+prefixBean.getPrefixConfigInfo());
//		System.out.println(ps.getConfigInfo());
		logger.debug("===debug==="+ps.getConfigInfo());
		logger.debug("{}", "===debug==="+ps.getConfigInfo());
		logger.info("===info==="+ps.getConfigInfo());
		logger.warn("===warn==="+ps.getConfigInfo());
		return res;
	}
	//----------end  通过@Value注解获取配置文件属性值


	@RequestMapping(value="/testredis", method=RequestMethod.POST)
	public String testRedis(@RequestBody Map param){
		String resultStr = null;

		for (Object o : param.keySet()){
			String key = (String)o;
			logger.debug("--------key:"+ key + "----value:" + param.get(key));
			redisUtil.set(key, param.get(key));
			logger.debug("--------key:"+ key + "----redisUtil.get:" + redisUtil.get(key));
		}

		resultStr = "true";
		return resultStr;
	}


	@RequestMapping("/uid")
	public String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}

	@RequestMapping("/uuid")
	public Map uuid(HttpServletRequest request) {
		Map resMap = new HashMap();
		HttpSession session = request.getSession();
		String sessionID = session.getId();
		String requestUrl = request.getRequestURL().toString();
		logger.debug("--------sessionID:" + sessionID);

		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		resMap.put("sessionID", sessionID);
		resMap.put("uid", uid);
		resMap.put("requestUrl", requestUrl);
		return resMap;
	}

}
