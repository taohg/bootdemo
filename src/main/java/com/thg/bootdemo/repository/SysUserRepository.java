package com.thg.bootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thg.bootdemo.entity.SysUser;


@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	SysUser findByUserName(String userName);
	SysUser findByUserNameOrEmail(String username, String email);
}
