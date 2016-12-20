package com.hszs.stb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * TODO 自定义注解 拦截Controller  
 * @author duzhenhua
 * @createTime 2015-2-26 下午02:31:20
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
	String description()  default "";   
	int type()  default 0;
}
