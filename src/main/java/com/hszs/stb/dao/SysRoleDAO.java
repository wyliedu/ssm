package com.hszs.stb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hszs.stb.model.auth.Authority;
import com.hszs.stb.model.auth.Role;



public interface SysRoleDAO {
	
	/**
	 * 登陆用户名密码验证
	 * @param username
	 * @param password
	 * @return
	 */
	public Role selectById(@Param("id")Integer id);
	
	public List<Authority> selectAuthorityByRoleId(@Param("roleId")Integer id);
	
	public List<Authority> selectAllAuthority();

	public Authority selectAuthorityById(@Param("id")Integer id);
	
	public List<Role> queryAllRoles();
	
	public List<Role> selectAllUsingRole();
	
	public void usingRole(@Param("roleid")int roleid);
	
	//删除角色
	public void deleteRole(@Param("roleid")int roleid);
	
	//通过查询角色，防止重名
	public Role selectByName(@Param("name")String name,@Param("roleid")Integer roleid);
	
	//新增角色
	public void addRole(@Param("name")String name);
	
	//修改角色
	public void updateRole(@Param("name")String name,@Param("roleid")int roleid);
	
	public void deleteAuthorityOfRole(@Param("roleid")int roleid);
	
	public void addAuthorityOfRole(@Param("authorityid")int authorityid,@Param("roleid")int roleid);
}