package com.hszs.stb.service.interfaces.home;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Authority;
import com.hszs.stb.model.auth.Role;


public interface IHomeService {

	public Account login(String username) throws NoSuchAlgorithmException;
	
	public Role getRoleById(Integer id);
	
	public List<Authority> getAuthorityByRoleId(Integer id);
	
	public Authority getAuthorityById(Integer id);

}