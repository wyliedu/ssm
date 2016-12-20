package com.hszs.stb.web.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hszs.stb.annotation.AuthPassport;
import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.helper.CodecHelper;
import com.hszs.stb.common.helper.PwdHelper;
import com.hszs.stb.enums.LogType;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.AccountAuth;
import com.hszs.stb.model.auth.AccountRole;
import com.hszs.stb.model.auth.Authority;
import com.hszs.stb.model.auth.AuthorityMenu;
import com.hszs.stb.model.auth.PermissionMenu;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.AccountUser;
import com.hszs.stb.service.interfaces.home.IHomeService;
import com.hszs.stb.service.services.home.HomeService;
import com.hszs.stb.service.services.system.LogService;



@Controller
@RequestMapping(value = "/home")
public class HomeController{  
	
	@Autowired
	//@Qualifier("HomeService")   //如果有多个接口实现类，则实例化指定的这个
    private HomeService homeService;
	@Autowired
    private LogService logService;
    
	/**
	 * 跳转到首页
	 * @return
	 */
    @AuthPassport
    @RequestMapping(value = "/index")
    public String index() { 	
        return "home/index";
    }  
    
	/**
	 * 跳转到欢迎界面
	 * @return
	 */
    @AuthPassport
    @RequestMapping(value = "/welcome")
    public String welcome() { 	
        return "home/welcome";
    }  
    
	/**
	 * 跳转到登录界面
	 * @return
	 */
    @RequestMapping(value = "/toLogin")
    public String toLogin() { 	
        return "home/toLogin";
    }   
    
    @RequestMapping(value = "/notfound")
    public ModelAndView notfound() { 
    	
    	ModelAndView mv = new ModelAndView();  
    	mv.setViewName("404");  
    	
    	return mv;  
    }
    
    /**
     * 跳转到登录界面
     * @param model
     * @return
     */
    @RequestMapping(value="/login", method = {RequestMethod.GET})
    public String login(Model model){
		if(!model.containsAttribute("contentModel"))
            model.addAttribute("contentModel", new AccountUser());
		AuthHelper.removeSession();
    	return "home/login";
    }
    
    /**
     * 登录
     * @param request
     * @param model
     * @param accountUser
     * @param result
     * @return
     * @throws Exception 
     */
    //@Valid的参数后必须紧挨着一个BindingResult 参数，否则spring会在校验不通过时直接抛出异常
    @RequestMapping(value="/login", method = {RequestMethod.POST})
	public String login(HttpServletRequest request, Model model, @Validated @ModelAttribute("contentModel") AccountUser accountUser, BindingResult result) throws Exception{
    	//如果有验证错误 返回到form页面
        if(result.hasErrors())
            return login(model);
        Account account = this.homeService.login(accountUser.getUserAccount().trim());
        if(account==null){
	        result.addError(new FieldError("contentModel","userPassword","用户不存在"));
	        return login(model);
        }else if(!StringUtils.equals(account.getPassword(), CodecHelper.md5(accountUser.getUserPassword())) ){
        	result.addError(new FieldError("contentModel","userPassword","密码不正确"));
        	return login(model);
        }else if(!account.isEnable()){
        	result.addError(new FieldError("contentModel","userPassword","此用户被禁用，不能登录"));
        	return login(model);
        }else if(account.getRoleid()==null){
        	result.addError(new FieldError("contentModel","userPassword","此用户当前未被授权，不能登录"));
        	return login(model);
        }else{
			AccountAuth accountAuth = new AccountAuth(account.getAccountid(), account.getName(), account.getUsername());
			Role role = this.homeService.getRoleById(account.getRoleid());   //获取角色信息
			if(!role.isEnable()){
				result.addError(new FieldError("contentModel","userPassword","此用户角色被禁用，不能登录"));
	        	return login(model);
			}
			AccountRole accountRole = new AccountRole(role.getRoleid(),role.getName());
			
			List<AuthorityMenu> authorityMenus=new ArrayList<AuthorityMenu>();
        	List<Authority> roleAuthorities = this.homeService.getAuthorityByRoleId(role.getRoleid());  //获取该角色所有的权限
        	//转换成树结构便于页面展示
        	for(Authority authority :roleAuthorities){
        		if(authority.getParentId()==null){    //一级菜单
        			AuthorityMenu authorityMenu=new AuthorityMenu(authority.getAuthorityid(), authority.getName(), authority.getItemIcon(), authority.getUrl());
        			
        			List<AuthorityMenu> childrenAuthorityMenus=new ArrayList<AuthorityMenu>();  //子菜单
        			for(Authority subAuthority :roleAuthorities){   				
        				if(subAuthority.getParentId()!=null && subAuthority.getParentId().equals(authority.getAuthorityid()))
        					childrenAuthorityMenus.add(new AuthorityMenu(subAuthority.getAuthorityid(), subAuthority.getName(), subAuthority.getItemIcon(), subAuthority.getUrl()));
        			}
        			authorityMenu.setChildrens(childrenAuthorityMenus);
        			authorityMenus.add(authorityMenu);
        		}
        	}
        	//获取所有权限内的菜单
    		List<PermissionMenu> permissionMenus=new ArrayList<PermissionMenu>(); 	
        	for(Authority authority : roleAuthorities){ 	  		
        		List<Authority> parentAuthorities=new ArrayList<Authority>();
        		Authority tempAuthority=authority;
        		while(tempAuthority.getParentId()!=null){
        			Authority fatherAuthority = this.homeService.getAuthorityById(tempAuthority.getParentId());
        			parentAuthorities.add(fatherAuthority);
        			tempAuthority=fatherAuthority;
        		}
        		if(parentAuthorities.size()>=2)   //三级以上
        			permissionMenus.add(new PermissionMenu(parentAuthorities.get(parentAuthorities.size()-1).getAuthorityid(),parentAuthorities.get(parentAuthorities.size()-1).getName(),parentAuthorities.get(parentAuthorities.size()-2).getAuthorityid(),parentAuthorities.get(parentAuthorities.size()-2).getName(),authority.getName(),authority.getMatchUrl()));
        		else if(parentAuthorities.size()==1)   //二级菜单
        			permissionMenus.add(new PermissionMenu(parentAuthorities.get(0).getAuthorityid(),parentAuthorities.get(0).getName(),authority.getAuthorityid(),authority.getName(),authority.getName(),authority.getMatchUrl()));
        		else    //一级菜单
        			permissionMenus.add(new PermissionMenu(authority.getAuthorityid(),authority.getName(),authority.getAuthorityid(),authority.getName(),authority.getName(),authority.getMatchUrl()));
        	}
        	accountRole.setAuthorityMenus(authorityMenus);
        	accountRole.setPermissionMenus(permissionMenus);
			accountAuth.setAccountRole(accountRole);
			AuthHelper.setSessionAccountAuth(request, accountAuth);
			this.logService.addLog("登录成功", LogType.login, "com.hszs.stb.web.home.HomeController.login()");
			
        }
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="/home/index";
    	return "redirect:"+returnUrl;	
	}
}  
