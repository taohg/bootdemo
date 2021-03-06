package com.thg.bootdemo.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BootProperties {
	@Value("${com.neo.title}")
	private String title;
	
	@Value("${com.neo.description}")
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getConfigInfo() {
		return "我是java bean方式读取的配置:\\r\\n ---com.neo.title:"+title + "\\r\\n---com.neo.description:" + description;
	}
	
}
