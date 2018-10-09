package com.thg.bootdemo.common;

import com.thg.bootdemo.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisConfig extends CachingConfigurerSupport {
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;
	
	public void setString(String key, String value) throws GeneralException {
		if(StringUtils.isEmpty(key)){
			throw new GeneralException("SYS0001");
		}
		stringRedisTemplate.opsForValue().set(key, value);
		logger.debug("---setString-----set:" + key);
	}

	public String getString(String key){
		String value = stringRedisTemplate.opsForValue().get(key);
		logger.debug("---getString-----get:key=" + key + "---value="+value);
		return value;
	}

	public void setObject(String key, Object objValue) throws GeneralException {
		if(StringUtils.isEmpty(key)){
			throw new GeneralException("SYS0001");
		}
		redisTemplate.opsForValue().set(key, objValue);
	}

	public Object getObject(String key){
		Object resObject = null;
		resObject = redisTemplate.opsForValue().get(key);
		return resObject;
	}
}
