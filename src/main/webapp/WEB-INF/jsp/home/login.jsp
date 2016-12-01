<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>121师徒帮</title>
   <%@ include file="../base/importCss.jsp"%>
   <%@ include file="../base/importJs.jsp"%>
    <!-- GOOGLE FONTS-->
    <link rel="stylesheet" href="<c:url value='/plugins/Fonts.css'/>" type="text/css"/>
	<script src="<c:url value='/js/account.validate.js'/>" type="text/javascript"></script> 
</head>
<body style="background-size:cover;background-image:url('<c:url value='/assets/img/background-login2.jpg'/>'); ">
    <div class="container">
        <div class="row text-center " style="padding-top:220px">
            <div class="col-md-12">
                <%-- <img src="<c:url value='/assets/img/logo-login3.png'/>" /> --%>
            </div>
        </div>
         <div class="row ">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                           
                            <div class="panel-body">
                            <form:form modelAttribute="contentModel" class="form-horizontal login-form" method="POST" role="form" action="/ssm/home/login">
                                    <hr />
                                    <h5>请输入用户名和密码</h5>
                                       <br />
                                     <div class="form-group">
                                     <div class="input-group input-icon">
                                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                            <form:input  path="userAccount" class="form-control"  placeholder="请输入用户名"/>
                                            <form:errors path="userAccount" class="field-has-error"></form:errors> 
                                        </div></div> 
                                        <div class="form-group">
                                        <div class="input-group input-icon">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <!-- <input type="password" class="form-control"  placeholder="Your Password" /> -->
                                            <form:password path="userPassword" class="form-control placeholder-no-fix"  placeholder="请输入密码"/>
                                        <%-- <form:errors path="userPassword" class="field-has-error"></form:errors> --%>
                                        </div></div> 
                                    <div class="form-group">
							<form:errors path="userPassword" class="field-has-error"></form:errors>
						</div>
						<div class="form-group">
							<div style="text-align: center;">
							<button type="submit" class="btn btn-primary" style="background-color:#0a59de;width: 90%;">登录</button>   
							</div> 
						</div> 
<!--                                      <div style="text-align: center;">
                                     <button type="submit" class="btn btn-primary ">登陆</button></div> -->
                                    <hr />
                                    <!-- Not register ? <a href="index.html" >click here </a> or go to <a href="index.html">Home</a>  -->
                                    <%-- </form> --%></form:form>
                            </div>
                           
                        </div>
                
                
        </div>
    </div>
	<script type="text/javascript">
		$(function() {
			//$("#userAccount").focus();
			AccountValidate.handleLogin();
	    }); 
	</script>
</body>
</html>
