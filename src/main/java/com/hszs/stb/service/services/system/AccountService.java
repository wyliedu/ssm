package com.hszs.stb.service.services.system;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.helper.CodecHelper;
import com.hszs.stb.dao.SysAccountDAO;
import com.hszs.stb.dao.SysRoleDAO;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.SystemServiceLog;
import com.hszs.stb.service.interfaces.system.IAccountService;


@Service
public class AccountService{
	
	@Autowired
	SysAccountDAO sysAccountDAO;
	
	@Autowired
	SysRoleDAO sysRoleDAO;

	@Cacheable(value = "accountCache")
	// 使用了一个缓存名叫 accountCache
	public Account getAccountByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account." + userName);
		return getFromDB(userName);
	}

	private Account getFromDB(String acctName) {
		System.out.println("real querying db..." + acctName);
		return new Account();
	}
	
	/**
	 * 查询所有可用账户列表
	 */
	@SystemServiceLog(description = "查询用户")   
	public List<Account> queryAccountList() throws Exception{
		List<Account> list = this.sysAccountDAO.queryAccountList();
		return list;
	}

	/**
	 * 获取所有角色列表
	 */
	public Map<Integer, String> queryAllRolesMap() {
		List<Role> list = this.sysRoleDAO.queryAllRoles();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(Role role:list){
			map.put(role.getId(), role.getName());
		}
		return map;
	}
	
	//删除账户
	public void deleteAccount(int accountid){
		this.sysAccountDAO.deleteAccount(accountid);
	}
		
	//新增账户
	public void addAccount(Account account){
		Account account2 = this.sysAccountDAO.selectByUsername(account.getPayphone());
		if(account2!=null){
			throw new ServiceException(ApiCode.ERR_USER_EXITS, "该用户已经存在");
		}
		account.setPassword(CodecHelper.md5(account.getPayphone()));  //默认手机号为初始密码
		this.sysAccountDAO.addAccount(account);;
	}
			
	//修改承包商
	public void updateAccount(Account account){
		this.sysAccountDAO.updateAccount(account);
	}
}
