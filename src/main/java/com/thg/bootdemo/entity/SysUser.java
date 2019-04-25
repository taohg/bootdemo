package com.thg.bootdemo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @description  
 * 实体类中引用的注解大多都是javax.persistence包下的
 * @author taohg
 *
 */

@Entity
@Table(name = "sysuser")
public class SysUser implements Serializable {
	private static final long serialVersionUID = -1020983866421446265L;
	
	@Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String regTime;
    @Column(nullable = true)
    private String rsrvStr1;
    @Column(nullable=true)
    private String rsrvStr2Test;
    @Column(nullable = true)
    private Date rsrvDate;
    
    public SysUser(){

	}


	public SysUser(String userName, String passWord, String email, String nickName, String regTime, Date rsrvDate) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.nickName = nickName;
		this.regTime = regTime;
		this.rsrvDate = rsrvDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getRsrvStr1() {
		return rsrvStr1;
	}
	public void setRsrvStr1(String rsrvStr1) {
		this.rsrvStr1 = rsrvStr1;
	}

	@Override
	public String toString() {
		return "This item:" +this.getId()+","+ this.getUserName()+","+this.getNickName()+","+this.getPassWord()+","+this.getRegTime();
	}
}
