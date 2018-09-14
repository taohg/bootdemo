package com.thg.bootdemo.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="me")
@PropertySource("classpath:me.properties")
public class PrefixProperties {
	private String pname;
	private String password;
	private String key;
	
	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getPrefixConfigInfo() {
		return "我是用prefix方式java bean方式读取的配置:"+pname + "---" + password+"-----"+key;
	}
}
