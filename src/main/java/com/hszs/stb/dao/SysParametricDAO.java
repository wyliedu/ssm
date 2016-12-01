package com.hszs.stb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.home.ParameterInfo;




public interface SysParametricDAO {
	
	//查询参数
	public List<ParameterInfo> queryParameterByCode(@Param("paramcode")String paramcode);
	
	public ParameterInfo queryParameterByCodeAndValue(@Param("paramcode")String paramcode,@Param("paramvalue")int paramvalue);
}