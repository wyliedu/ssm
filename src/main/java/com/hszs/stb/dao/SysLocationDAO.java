package com.hszs.stb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.system.Location;



public interface SysLocationDAO {
	
	//查询通知列表
	public List<Location> queryLocationByParentid(@Param("id")int id,@Param("level")int level);
	
	public Location queryLocationById(@Param("id")int id);
	
	public Location queryLocationByCode(@Param("code")String code);
}