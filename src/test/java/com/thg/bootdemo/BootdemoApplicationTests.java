package com.thg.bootdemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.repository.SysUserRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootdemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(BootdemoApplicationTests.class);
	@Autowired
    private SysUserRepository sysUserRepository;

//	@Autowired
//    private RedisConfig redisConfig;
    @Autowired
    private RedisTemplate redisTemplate;
	
	@Test
	public void contextLoads() {
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = dateFormat.format(date);
        
        String tmp = String.valueOf(System.currentTimeMillis());
//        sysUserRepository.save(new SysUser("aa1", "aa123456", "aa@126.com", "aa", formattedDate));
        sysUserRepository.save(new SysUser("aa1_"+tmp, "aa123456", tmp+"aa@126.com", "aa"+tmp, formattedDate, date));
        sysUserRepository.save(new SysUser("bb2_"+tmp, "bb123456", tmp+"bb@126.com", "bb"+tmp, formattedDate, date));
        sysUserRepository.save(new SysUser("cc3_"+tmp, "cc123456", tmp+"cc@126.com", "cc"+tmp, formattedDate, date));

//        Assert.assertEquals(9, sysUserRepository.findAll().size());
//        Assert.assertEquals("bb", sysUserRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
//        sysUserRepository.delete(sysUserRepository.findByUserName("aa1"));
	}

	@Test
    public void testRedis(){
//	    redisConfig.setString("toahg123", "taohg123-value-中文来一把"+System.currentTimeMillis());
//	    redisConfig.getString("toahg123");
        String key = "toahg123";
        String value = "taohg123-value-中文来一把"+System.currentTimeMillis();
 	    redisTemplate.opsForValue().set(key, value);
        logger.debug("---RedisTemplate-----set:" + key);
	    value = String.valueOf(redisTemplate.opsForValue().get(key));
        logger.debug("---RedisTemplate-----get:key=" + key + "---value="+value);
    }

}
