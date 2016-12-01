package com.hszs.stb.model.api;

import java.io.Serializable;

import com.hszs.stb.common.helper.JSONUtil;


public class AbstractJsonModel implements Serializable{

	private static final long serialVersionUID = 19283472203766L;

	public String toString(){
		return JSONUtil.jsonEncode(this);
	}
}
