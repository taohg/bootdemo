package com.thg.bootdemo.service;

import com.thg.bootdemo.entity.SysUser;
import com.thg.bootdemo.exception.GeneralException;

public interface ISysUserService {
    public SysUser getSysUserByName(String userName) throws GeneralException;
}
