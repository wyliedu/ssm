package com.hszs.stb.web.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
import com.hszs.stb.common.helper.CodecHelper;
import com.hszs.stb.model.auth.Account;
import com.hszs.stb.model.auth.Authority;
import com.hszs.stb.model.auth.AuthorityMenu;
import com.hszs.stb.model.auth.Role;
import com.hszs.stb.model.home.AuthPassport;
import com.hszs.stb.model.home.ResponseResult;
import com.hszs.stb.model.home.TableResult;
import com.hszs.stb.model.system.NodeState;
import com.hszs.stb.model.system.TreeNode;
import com.hszs.stb.service.services.home.HomeService;
import com.hszs.stb.service.services.system.AccountService;
import com.hszs.stb.service.services.system.RoleService;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleManageController{  
	
	@Autowired
    private RoleService roleService;
	@Autowired
    private HomeService homeService;
	
	@AuthPassport
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,HttpServletResponse response) throws Exception { 	
        return "system/roleManage";
    }  
	
	/**
	 * 角色管理列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/index/rolelist.do", method = {RequestMethod.POST})
	@ResponseBody
    public TableResult rolelist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		TableResult result = new TableResult();
		List<Role> list = this.roleService.queryAllRolesList();
		int xh = 1;   //添加序号
		for(Role item:list){
			item.setXh(xh);
			xh++;
		}
		result.setData(list);
		return result;
    }
	
	/**
	 * 启用/不启用角色
	 * @param contractorid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/index/usingRole.do", method = {RequestMethod.POST})
    public void usingRole(
    		@RequestParam("roleid") int roleid,
    		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		this.roleService.usingRole(roleid);
    } 
	
	/**
	 * 删除角色
	 * @param contractorid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/index/deleteRole.do", method = {RequestMethod.POST})
    public void deleteRole(
    		@RequestParam("roleid") int roleid,
    		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		this.roleService.deleteRole(roleid);
    } 
	
	/**
	 * 更新角色
	 * @param type
	 * @param roleid
	 * @param name
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = {"/index/updateRole.do"}, method = {RequestMethod.POST})
	@ResponseBody
    public ResponseResult updateRole(
    		Role role,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseResult rr = new ResponseResult();
		try{
	    	if(role.getRoleid()==null){    //新增
	    		this.roleService.addRole(role);
	    	}else{    //修改
	    		this.roleService.updateRole(role);
	    	}
		}catch(ServiceException e){
			rr.setCode(-1);
			rr.setMessage(e.getMessage());
		}
		return rr;
    }
	
	/**
	 * 查询所有在启用的角色
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
	@RequestMapping(value = "/index/selectAllUsingRole.do", method = {RequestMethod.POST})
	public List selectAllUsingRole(
   		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<Role> list = this.roleService.selectAllUsingRole();
		return list;
	} 
	
	/**
	 * 跳转到角色权限界面
	 * @param roleid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
    @RequestMapping(value = "/index/authority/{roleid}")
    public String authority(@PathVariable String roleid,HttpServletRequest request,HttpServletResponse response) throws Exception { 
		String a = StringHelper.decode64(roleid);
		Role role = this.homeService.getRoleById(Integer.valueOf(a));
		request.setAttribute("role", role);
		return "system/roleAuthorityManage";
    } 
	
	/**
	 * 查询角色的权限
	 * @param roleid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
	@RequestMapping(value = "/index/getAuthorityList.do", method = {RequestMethod.POST})
	public List getAuthorityList(
		@RequestParam("roleid") int roleid,
   		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<Authority> list = this.homeService.getAuthorityByRoleId(roleid);
		List<Authority> allAuthoritys = this.homeService.getAllAuthority();
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
    	//转换成树结构便于页面展示
    	for(Authority authority :allAuthoritys){
    		if(authority.getParentId()==null){    //一级菜单
    			NodeState nodeState = null; 
    			for(Authority roleAuthority :list){   
    				if(roleAuthority.getAuthorityid().equals(authority.getAuthorityid())){
    					nodeState = new NodeState(true, false, true, false);
    				}
    			}
    			TreeNode treenode = new TreeNode(authority.getAuthorityid(), authority.getName(),authority.getItemIcon(),true,nodeState);
    			List<TreeNode> childrenTreeNodes=new ArrayList<TreeNode>();  //子菜单
    			for(Authority subAuthority :allAuthoritys){   				
    				if(subAuthority.getParentId()!=null && subAuthority.getParentId().equals(authority.getAuthorityid())){
    					NodeState cnodeState = null; 
    	    			for(Authority roleAuthority :list){   
    	    				if(roleAuthority.getAuthorityid().equals(subAuthority.getAuthorityid())){
    	    					cnodeState = new NodeState(true, false, true, false);
    	    				}
    	    			}
    	    			TreeNode childrennode = new TreeNode(subAuthority.getAuthorityid(), subAuthority.getName(), subAuthority.getItemIcon(),true,cnodeState);
    	    			List<TreeNode> grandchildrenTreeNodes=new ArrayList<TreeNode>();  //孙菜单
    	    			for(Authority grandAuthority :allAuthoritys){   	
    	    				if(grandAuthority.getParentId()!=null && grandAuthority.getParentId().equals(subAuthority.getAuthorityid())){
    	    					NodeState gnodeState = null; 
    	    	    			for(Authority roleAuthority :list){   
    	    	    				if(roleAuthority.getAuthorityid().equals(grandAuthority.getAuthorityid())){
    	    	    					gnodeState = new NodeState(true, false, true, false);
    	    	    				}
    	    	    			}
    	    	    			TreeNode gnodchildrennode = new TreeNode(grandAuthority.getAuthorityid(), grandAuthority.getName(), grandAuthority.getItemIcon(),true,gnodeState);
    	    	    			grandchildrenTreeNodes.add(gnodchildrennode);
    	    				}
    	    			}
    	    			childrennode.setNodes(grandchildrenTreeNodes);
    	    			childrenTreeNodes.add(childrennode);
    				}
    			}
    			treenode.setNodes(childrenTreeNodes);
    			treeNodes.add(treenode);
    		}
    	}
		return treeNodes;
	} 
	
	/**
	 * 给角色赋权限
	 * @param roleid
	 * @param checkedlist
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
	@RequestMapping(value = "/saveAuthorityOfRole.do", method = {RequestMethod.POST})
	public ResponseResult saveAuthorityOfRole(
		@RequestParam("roleid") String roleid,
		@RequestParam("checkedlist") String checkedlist,
   		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		ResponseResult rr = new ResponseResult();
		try{
			JSONArray ja = (JSONArray)JSONSerializer.toJSON(checkedlist);
			this.roleService.deleteAuthorityOfRole(Integer.valueOf(roleid));
			for(int i=0;i<ja.size();i++){
				JSONObject jo = ja.getJSONObject(i);
				this.roleService.addAuthorityOfRole(jo.getInt("id"), Integer.valueOf(roleid));
			}
		}catch(Exception e){
			rr.setCode(-1);
			rr.setMessage(e.getMessage());
		}
		return rr;
	} 
}  
