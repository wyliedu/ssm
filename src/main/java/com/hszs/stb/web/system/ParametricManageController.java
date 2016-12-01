package com.hszs.stb.web.system;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hszs.stb.model.home.AuthPassport;
import com.hszs.stb.model.home.ParameterInfo;
import com.hszs.stb.model.system.Location;
import com.hszs.stb.service.services.home.HomeService;

/**
 * 参数下拉框
 * @author du
 *
 */
@Controller
@RequestMapping(value = "/parametric")
public class ParametricManageController{  
	
	@Autowired
    private HomeService homeService;
	
	/**
	 * 性别下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/genderlist.do", method = {RequestMethod.POST})
    public List genderlist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("gender");
		return list;
    }  
	
	/**
	 * 名族
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/nationlist.do", method = {RequestMethod.POST})
    public List nationlist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("nation");
		return list;
    } 
	
	/**
	 * 学历
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/educationlist.do", method = {RequestMethod.POST})
    public List educationlist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("education");
		return list;
    } 
	
	/**
	 * 政治面貌
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/politicalstatuslist.do", method = {RequestMethod.POST})
    public List politicalstatuslist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("politicalstatus");
		return list;
    } 
	
/*	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/armyranklist.do", method = {RequestMethod.POST})
    public List armyranklist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("armyrank");
		return list;
    } */
	
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/workplacelist.do", method = {RequestMethod.POST})
    public List workplacelist(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<ParameterInfo> list = this.homeService.queryParameterByCode("workplace");
		return list;
    } 
	
	@AuthPassport
	@ResponseBody
    @RequestMapping(value = "/locationlist.do", method = {RequestMethod.POST})
    public List provincelist(
    		@RequestParam("id") int id,
    		@RequestParam("level") int level,
    		HttpServletRequest request,HttpServletResponse response) throws Exception { 
		List<Location> list = this.homeService.queryLocationByParentid(id,level);
		return list;
    }
	
}  
