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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.extension.StringHelper;
import com.hszs.stb.common.util.DateHelper;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.AuthPassport;
import com.hszs.stb.model.home.ResponseResult;
import com.hszs.stb.model.home.SystemControllerLog;
import com.hszs.stb.model.home.TableResult;
import com.hszs.stb.model.system.LogInfo;
import com.hszs.stb.service.services.system.AccountService;
import com.hszs.stb.service.services.system.LogService;

/**
 * 用户管理
 * @author wylie
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogManageController{  
	
	@Autowired
    private LogService logService;
	
	@AuthPassport
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,HttpServletResponse response) throws Exception { 	
        return "system/logManage";
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
	@ResponseBody
    public TableResult accountList(
    		@RequestParam("start") Integer start,   //开始的序号
    		@RequestParam("length") Integer length,
    		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		TableResult result = new TableResult();
		//在你需要进行分页的Mybatis方法前调用PageHelper.startPage静态方法即可，紧跟在这个方法后的第一个Mybatis查询方法会被进行分页。
		int pageNum =start/length+1;
		length = length == null?1:length;
	    PageHelper.startPage(pageNum, length);
		List<LogInfo> list = this.logService.queryList();
		PageInfo page = new PageInfo(list);
		for(LogInfo item:list){
			start++;
			item.setCreatetimevalue(DateHelper.timestamp2DatetimeString(item.getCreatetime()));
			item.setXh(start);    
		}
		result.setPage(page);
		result.setData(list);
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
    }  
	
}  
