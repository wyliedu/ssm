package com.hszs.stb.web.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.model.api.ApiResponseBody;
import com.hszs.stb.model.home.ResponseResult;

public abstract class AbstractController {
	
	private static final Logger logger=LoggerFactory.getLogger(AbstractController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiResponseBody handleException(HttpServletRequest request, 
			Exception ex, 
			HttpServletResponse response){
		
		logger.error("未处理异常", ex);
		
		return ApiResponseBody.create(ApiCode.ERR_UNKNOWN_ERROR, ex.getMessage(), null);
	}
	
	/**
	 * 入参校验
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)   
	@ResponseBody  
	public ApiResponseBody handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {  
	    BindingResult bindingResult = ex.getBindingResult();  
	    String errorMesssage = "错误信息:";  
	  
	    for (FieldError fieldError : bindingResult.getFieldErrors()) {  
	        errorMesssage += fieldError.getField()+fieldError.getDefaultMessage() + ";";  
	    }  
	    return ApiResponseBody.create(ApiCode.ERR_WRONG_PARAMS, errorMesssage, null);
	}
	
	@ExceptionHandler(BindException.class)   
	@ResponseBody  
	public ApiResponseBody handleBindException(BindException ex) {  
	    BindingResult bindingResult = ex.getBindingResult();  
	    String errorMesssage = "错误信息:";  
	    for (FieldError fieldError : bindingResult.getFieldErrors()) {  
	        errorMesssage += fieldError.getField()+fieldError.getDefaultMessage() + ";";  
	    }  
	    return ApiResponseBody.create(ApiCode.ERR_WRONG_PARAMS, errorMesssage, null);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiResponseBody handleParameterException(HttpServletRequest request, 
			MissingServletRequestParameterException ex, 
			HttpServletResponse response){
		
		return ApiResponseBody.create(ApiCode.ERR_WRONG_PARAMS, ex.getMessage(), null);
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiResponseBody handleServiceException(HttpServletRequest request, 
			ServiceException ex, 
			HttpServletResponse response){
		
		return ApiResponseBody.create(ex.getCode(), ex.getMessage(), null);
	}
	

}
