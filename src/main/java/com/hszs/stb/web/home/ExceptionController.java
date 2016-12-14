package com.hszs.stb.web.home;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.PermissionException;
import com.hszs.stb.model.api.ApiResponseBody;
import com.hszs.stb.model.home.ResponseResult;

/**
 * 捕获异常处理
 * @author wylie
 *
 */
@ControllerAdvice
public class ExceptionController {  
	
	/**
	 * 捕获没有权限的异常类 返回json格式  页面弹窗提示
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value={PermissionException.class}) 
	@ResponseBody
    public ResponseResult exception(HttpServletRequest request, Exception e) {  
		return ResponseResult.createErrorBody(-1, e.getMessage());
    }  
	
	/**
	 * 入参校验
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)   
	@ResponseBody  
	public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {  
	    BindingResult bindingResult = ex.getBindingResult();  
	    String errorMesssage = "错误信息:";  
	    for (FieldError fieldError : bindingResult.getFieldErrors()) {  
	        errorMesssage += fieldError.getField()+fieldError.getDefaultMessage() + ";";  
	    }  
	    return new ResponseResult(-1, errorMesssage);  
	}  
	    
	@ExceptionHandler(BindException.class)   
	@ResponseBody  
	public ResponseResult handleBindException(BindException ex) {  
	    BindingResult bindingResult = ex.getBindingResult();  
	    String errorMesssage = "错误信息:";  
	    for (FieldError fieldError : bindingResult.getFieldErrors()) {  
	        errorMesssage += fieldError.getField()+fieldError.getDefaultMessage() + ";";  
	    }  
	    return new ResponseResult(-1, errorMesssage);  
	}
}  
