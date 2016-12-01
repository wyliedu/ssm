package com.hszs.stb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Msgcode;



public interface SysAccountDAO {
	
	/**
	 * 登陆用户名密码验证
	 * @param username
	 * @param password
	 * @return
	 */
	public Account selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
	
	public Account selectByUsername(@Param("username")String username);
	
	//查询用户列表
	public List<Account> queryAccountList();
	
	//徒弟注册时校验手机号与验证码
	public Msgcode queryMsgcode(@Param("payphone")String payphone,@Param("msgcode")String msgcode);
	
	//删除邀请
	public void deleteMsgcode(@Param("payphone")String payphone);
	
	//添加邀请
	public void addMsgcode(@Param("teamid")int teamid,@Param("payphone")String payphone,@Param("msgcode")String msgcode);
	
	//添加邀请给师傅
	public void addMsgcodeToSenior(@Param("payphone")String payphone,@Param("msgcode")String msgcode);
	
	//上传头像-徒弟
	public void updateApprenticePortrait(@Param("userid")int userid,@Param("portraitAddress")String portraitAddress);
	
	//上传头像-师傅
	public void updateSeniorPortrait(@Param("userid")int userid,@Param("portraitAddress")String portraitAddress);
		
	//上传头像-承包商员工
	public void updateEmployeePortrait(@Param("userid")int userid,@Param("portraitAddress")String portraitAddress);
	
	public void deleteAccount(@Param("accountid")int accountid);
	
	public void addAccount(Account account);
	
	public void updateAccount(Account account);

}