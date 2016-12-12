package com.hszs.stb.web.home;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hszs.stb.common.exception.PermissionException;
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
		ResponseResult rr = new ResponseResult();
		rr.setCode(-1);
		rr.setMessage(e.getMessage());
		return rr;
    }  
	
}  
