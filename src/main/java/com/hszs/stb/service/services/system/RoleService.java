package com.hszs.stb.service.services.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.dao.SysRoleDAO;
import com.hszs.stb.model.auth.Role;


@Service
public class RoleService{
	
	@Autowired
	SysRoleDAO sysRoleDAO;

	/**
	 * 获取所有角色列表
	 */
	public List<Role> queryAllRolesList() {
		List<Role> list = this.sysRoleDAO.queryAllRoles();
		return list;
	}
	
	public List<Role> selectAllUsingRole(){
		List<Role> list = this.sysRoleDAO.selectAllUsingRole();
		return list;
	}
	
	public void usingRole(int roleid){
		this.sysRoleDAO.usingRole(roleid);
	}
	
	//删除角色
	public void deleteRole(int roleid){
		this.sysRoleDAO.deleteRole(roleid);
	}
	
	//新增角色
	public void addRole(Role role){
		Role role2 = this.sysRoleDAO.selectByName(role.getName(),null);
		if(role2!=null){
			throw new ServiceException(ApiCode.ERR_USER_EXITS, "该角色已经存在,请修改角色名称");
		}
		this.sysRoleDAO.addRole(role.getName());
	}
		
	//修改角色
	public void updateRole(Role role){
		Role role2 = this.sysRoleDAO.selectByName(role.getName(),role.getId());
		if(role2!=null){
			throw new ServiceException(ApiCode.ERR_USER_EXITS, "该角色已经存在,请修改角色名称");
		}
		this.sysRoleDAO.updateRole(role.getName(),role.getRoleid());
	}
	
	public void deleteAuthorityOfRole(int roleid){
		this.sysRoleDAO.deleteAuthorityOfRole(roleid);
	}
	
	public void addAuthorityOfRole(int authorityid,int roleid){
		this.sysRoleDAO.addAuthorityOfRole(authorityid, roleid);
	}
}
