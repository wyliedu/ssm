package com.hszs.stb.web.system;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.extension.StringHelper;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.AuthPassport;
import com.hszs.stb.model.home.ResponseResult;
import com.hszs.stb.model.home.SystemControllerLog;
import com.hszs.stb.model.home.TableResult;
import com.hszs.stb.service.services.system.AccountService;

/**
 * 用户管理
 * @author wylie
 *
 */
@Controller
@RequestMapping(value = "/account")
public class AccountManageController{  
	
	@Autowired
    private AccountService accountService;
	
	@AuthPassport
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,HttpServletResponse response) throws Exception { 	
		return "system/accountManage";
    }  
	
	@AuthPassport
    @RequestMapping(value = "/addAccount")
    public String addAccount(HttpServletRequest request,HttpServletResponse response) throws Exception { 	
        return "system/addAccount";
    }
	
	/**
	 * 返回用户列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST})
	@SystemControllerLog(description = "读取用户列表",type=1) 
	@ResponseBody
    public TableResult accountList(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		TableResult result = new TableResult();
		List<Account> list = this.accountService.queryAccountList();
		int xh = 1;   //添加序号
		for(Account item:list){
			item.setXh(xh);
			xh++;
		}
		result.setData(list);
		return result;
    }  
	
	/**
	 * 删除角色
	 * @param contractorid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/index/deleteAccount.do", method = {RequestMethod.POST})
    public void deleteRole(
    		@RequestParam("accountid") int accountid,
    		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		this.accountService.deleteAccount(accountid);
    } 
	
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = {"/updateAccount.do"}, method = {RequestMethod.POST})
	@ResponseBody
    public ResponseResult addAccount(
    		Account account,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseResult rr = new ResponseResult();
		try{
			if(account.getAccountid()==null){
				this.accountService.addAccount(account);
			}else{
				this.accountService.updateAccount(account);
			}
		}catch(ServiceException e){
			rr.setCode(-1);
			rr.setMessage(e.getMessage());
		}
		return rr;
    }
}  
