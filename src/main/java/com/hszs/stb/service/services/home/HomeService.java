package com.hszs.stb.service.services.home;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hszs.stb.dao.SysAccountDAO;
import com.hszs.stb.dao.SysLocationDAO;
import com.hszs.stb.dao.SysParametricDAO;
import com.hszs.stb.dao.SysRoleDAO;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Authority;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.ParameterInfo;
import com.hszs.stb.model.home.SystemServiceLog;
import com.hszs.stb.model.system.Location;
import com.hszs.stb.service.interfaces.home.IHomeService;


@Service
public class HomeService{
	
	@Autowired
	SysAccountDAO sysAccountDAO;
	
	@Autowired
	SysRoleDAO sysRoleDAO;
	
	@Autowired
	SysParametricDAO sysParametricDAO;
	@Autowired
	SysLocationDAO sysLocationDAO;
	
	/**
	 * 登录验证
	 * @param userAccount
	 * @param userPassword
	 * @return 
	 * @throws Exception 
	 */
	public Account login(String username){
		Account account = this.sysAccountDAO.selectByUsername(username);
		return account;
	}
	
	/**
	 * 通过角色id获取角色信息
	 * @param id
	 * @return
	 */
	public Role getRoleById(Integer id){
		Role role = this.sysRoleDAO.selectById(id);
		return role;
	}
	
	public List<Authority> getAuthorityByRoleId(Integer id){
		List<Authority> authorityList = this.sysRoleDAO.selectAuthorityByRoleId(id);
		return authorityList;
	}
	
	public List<Authority> getAllAuthority(){
		List<Authority> authorityList = this.sysRoleDAO.selectAllAuthority();
		return authorityList;
	}
	
	public Authority getAuthorityById(Integer id){
		Authority authority = this.sysRoleDAO.selectAuthorityById(id);
		return authority;
	}
	
	public List<ParameterInfo> queryParameterByCode(String paramcode){
		List<ParameterInfo> list = this.sysParametricDAO.queryParameterByCode(paramcode);
		return list;
	}
	
	public String getMemoParameterByCodeAndValue(String paramcode,int paramvalue){
		String result = "";
		ParameterInfo pi= this.sysParametricDAO.queryParameterByCodeAndValue(paramcode, paramvalue);
		if(pi!=null){
			result = pi.getMemo();
		}
		return result;
	}
	
	public List<Location> queryLocationByParentid(int id,int level){
		return this.sysLocationDAO.queryLocationByParentid(id,level);
	}
}
