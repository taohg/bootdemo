package com.thg.bootdemo;


import com.thg.bootdemo.common.DateUtil;
import com.thg.bootdemo.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    private static final Logger logger = LoggerFactory.getLogger(RedisTests.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception{

        stringRedisTemplate.opsForValue().set("key"+DateUtil.date2String(DateUtil.getSysdate(), DateUtil.DATE_PATTERN.YYYYMMDDHHMISS), "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception{
        SysUser user = new SysUser("username", "password", "email", "nickname", "regtime", new Date());
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("com.thg", user);
        operations.set("com.thg.f", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("tom.thg.f");
        if(exists){
            logger.debug("exists is true");
        }else{
            logger.debug("exists is false");
        }

        SysUser tmpUser = (SysUser) operations.get("com.thg.f");
        if(tmpUser != null){
            Assert.assertEquals("username", tmpUser.getUserName());
        }
    }
}
