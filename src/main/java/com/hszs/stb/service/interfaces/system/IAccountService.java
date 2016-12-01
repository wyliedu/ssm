package com.hszs.stb.service.interfaces.system;

import java.util.List;
import java.util.Map;

import com.hszs.stb.model.auth.Account;


public interface IAccountService {
	
	public List<Account> queryAccountList();
	
	public Map<Integer, String> queryAllRolesMap();
}