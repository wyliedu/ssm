package com.hszs.stb.service.services.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hszs.stb.dao.SysLogDAO;
import com.hszs.stb.model.auth.AccountAuth;
import com.hszs.stb.model.system.LogInfo;
import com.hszs.stb.web.home.AuthHelper;

@Service
public class LogService{
	
	@Autowired
	SysLogDAO sysLogDAO;

	/**
	 * 查询日志列表
	 * @return
	 * @throws Exception
	 */
	public List<LogInfo> queryList() throws Exception{
		List<LogInfo> list = this.sysLogDAO.queryLogList();
		return list;
	}

	/**
	 * 新增日志
	 * @param item
	 */
	public void addLog(String description,int type,String method){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();     
        //读取session中的用户    
        AccountAuth accountAuth=AuthHelper.getSessionAccountAuth(request);
        //请求的IP    
        String ip = request.getRemoteAddr(); 
       //*========数据库日志=========*//    
       LogInfo item = new LogInfo();
       item.setRequestIp(ip);					//请求IP
       item.setDescription(description);   //方法描述
       item.setType(type);   //日志类型
       if(accountAuth!=null){
       		item.setOperator(accountAuth.getName());
       		item.setAccountid(accountAuth.getId());
       }
       item.setMethod(method);//请求方法
       this.sysLogDAO.addLog(item);
	}
	
}
