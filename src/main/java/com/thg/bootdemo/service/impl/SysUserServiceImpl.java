package com.thg.bootdemo.service.impl;

import com.thg.bootdemo.common.Constants;
import com.thg.bootdemo.common.RedisConfig;
import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.exception.GeneralException;
import com.thg.bootdemo.repository.SysUserRepository;
import com.thg.bootdemo.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private RedisConfig redisConfig;

    @Override
    public SysUser getSysUserByName(String userName) throws GeneralException {
        SysUser resUser = null;
        String redisKey = Constants.REDIS_KEY.TABLE_SYSUSER + userName;

        resUser = (SysUser) redisConfig.getObject(redisKey);
        logger.debug("---------从redis取数据resUser:" + resUser);
        if(resUser == null){
            resUser = sysUserRepository.findByUserName(userName);
            logger.debug("---------从数据库取数据resUser:" + resUser);
            if(resUser != null){
                redisConfig.setObject(redisKey, resUser);
            }
        }
        return resUser;
    }
}
