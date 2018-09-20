package com.thg.bootdemo.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
	@Bean
	public FilterRegistrationBean testFilterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
	
	public class MyFilter implements Filter {
        @Override
        public void destroy() {
            logger.debug("-----MyFilter------destory()");
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
        	logger.debug("-----MyFilter------doFilter()");
            HttpServletRequest request = (HttpServletRequest) srequest;
            logger.debug("this is MyFilter,url :"+request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        	logger.debug("-----MyFilter------init()");
        	BootProperties ps = new BootProperties();
        	logger.debug("-----NeoProperties:"+ps.getTitle()); //这样new属性类是拿不到属性数据的，正确用法参见备注第7点
        }
    }
}
