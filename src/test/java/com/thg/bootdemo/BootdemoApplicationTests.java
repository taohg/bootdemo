package com.thg.bootdemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.repository.SysUserRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootdemoApplicationTests {
	@Autowired
    private SysUserRepository sysUserRepository;
	
	@Test
	public void contextLoads() {
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");        
        String formattedDate = dateFormat.format(date);
        
        String tmp = String.valueOf(System.currentTimeMillis());
//        sysUserRepository.save(new SysUser("aa1", "aa123456", "aa@126.com", "aa", formattedDate));
        sysUserRepository.save(new SysUser("aa1_"+tmp, "aa123456", tmp+"aa@126.com", "aa"+tmp, formattedDate));
        sysUserRepository.save(new SysUser("bb2_"+tmp, "bb123456", tmp+"bb@126.com", "bb"+tmp, formattedDate));
        sysUserRepository.save(new SysUser("cc3_"+tmp, "cc123456", tmp+"cc@126.com", "cc"+tmp, formattedDate));

//        Assert.assertEquals(9, sysUserRepository.findAll().size());
//        Assert.assertEquals("bb", sysUserRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
//        sysUserRepository.delete(sysUserRepository.findByUserName("aa1"));
	}

}
