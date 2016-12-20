package com.hszs.stb.service.services.home;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;    
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hszs.stb.annotation.SystemControllerLog;
import com.hszs.stb.annotation.SystemServiceLog;
import com.hszs.stb.common.helper.JSONUtil;
import com.hszs.stb.common.helper.RequestHelper;
import com.hszs.stb.dao.SysLogDAO;
import com.hszs.stb.model.auth.AccountAuth;
import com.hszs.stb.model.system.LogAnnotation;
import com.hszs.stb.model.system.LogInfo;
import com.hszs.stb.web.home.AuthHelper; 
/**
 * 日志记录
 * @author du
 *
 */
//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class LogRecord {

	private final static Log log = LogFactory.getLog(LogRecord.class);
	
	@Autowired
	SysLogDAO sysLogDAO;
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
/*	@Pointcut("execution(* com.hszs.stb.service..*(..))")
	public void aspect(){	}*/
	
    //Service层切点    
    @Pointcut("@annotation(com.hszs.stb.annotation.SystemServiceLog)")    
     public  void serviceAspect() {    }    
    
    //Controller层切点    
    @Pointcut("@annotation(com.hszs.stb.annotation.SystemControllerLog)")    
     public  void controllerAspect() {    }
    
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
/*	@Before("aspect()")
	public void before(JoinPoint joinPoint){
		if(log.isInfoEnabled()){
			log.info("before " + joinPoint);
		}
	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint){
		if(log.isInfoEnabled()){
			log.info("after " + joinPoint);
		}
	}
	
	//配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public void around(JoinPoint joinPoint){
		long start = System.currentTimeMillis();
		try {
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
			}
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
			}
		}
	}
	
	//配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint){
		if(log.isInfoEnabled()){
			log.info("afterReturn " + joinPoint);
		}
	}
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		if(log.isInfoEnabled()){
			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}*/
	
	/**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
    @Before("controllerAspect()")    
     public  void doBefore(JoinPoint joinPoint) {     
         try {         
             HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();     
             //读取session中的用户    
             AccountAuth accountAuth=AuthHelper.getSessionAccountAuth(request);
             //请求的IP    
             //String ip = request.getRemoteAddr(); 
             String ip = RequestHelper.getRemoteAddr(request);
            //*========数据库日志=========*//    
            LogInfo item = new LogInfo();
            item.setRequestIp(ip);					//请求IP
            LogAnnotation logAnnotation = getControllerMethodDescription(joinPoint);
            item.setDescription(logAnnotation.getDescription());   //方法描述
            item.setType(logAnnotation.getType());   //日志类型
            if(accountAuth!=null){
            	item.setOperator(accountAuth.getName());
            	item.setAccountid(accountAuth.getId());
            }
            item.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));//请求方法
            this.sysLogDAO.addLog(item);
        }  catch (Exception e) {    
            //记录本地异常日志    
            log.error("==前置通知异常==");    
            log.error("异常信息:{}", e);    
        }    
    }     
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        AccountAuth accountAuth=AuthHelper.getSessionAccountAuth(request);   
        //获取请求ip    
        String ip = RequestHelper.getRemoteAddr(request);  
        //获取用户请求方法的参数并序列化为JSON格式字符串    
        String params = "";    
         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
             for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
                params += JSONUtil.jsonEncodeAllFields(joinPoint.getArgs()[i]) + ";";    
            }    
        }    
         try {      
            /*==========数据库日志=========*/    
            LogInfo item = new LogInfo();
            item.setRequestIp(ip);					//请求IP
            item.setDescription(getServiceMthodDescription(joinPoint));   //方法描述
            item.setOperator(accountAuth.getName());
            item.setAccountid(accountAuth.getId());
            item.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));//请求方法
            item.setExceptionDetail(e.getMessage());			//异常信息
            item.setExceptionCode(e.getClass().getName());		//异常代码
            item.setParams(params);//请求参数
            this.sysLogDAO.addLog(item);
        }  catch (Exception ex) {    
            //记录本地异常日志    
            log.error("==异常通知异常==");    
            log.error("异常信息:{}", ex);    
        }    
    }    
    
    
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getServiceMthodDescription(JoinPoint joinPoint)    
             throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemServiceLog. class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static LogAnnotation getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
    	 LogAnnotation logAnnotation = new LogAnnotation();
    	 String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemControllerLog. class).description();  
                    int type = method.getAnnotation(SystemControllerLog. class).type(); 
                    logAnnotation.setDescription(description);
                    logAnnotation.setType(type);
                     break;    
                }    
            }    
        }    
         return logAnnotation;    
    }    
}    
