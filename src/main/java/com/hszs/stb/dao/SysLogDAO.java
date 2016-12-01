package com.hszs.stb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.system.LogInfo;



public interface SysLogDAO {
	
	//新增日志信息
	public void addLog(LogInfo logInfo);
	
	//查询日志列表
	public List<LogInfo> queryLogList();
}