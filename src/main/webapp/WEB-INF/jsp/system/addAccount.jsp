<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../base/taglib.jsp"%>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>121师徒帮</title>

   <%@ include file="../base/importCss.jsp"%>
   <%@ include file="../base/importJs.jsp"%>
</head>
<body>
    <div id="wrapper">
		<%@ include file="../base/pageHeader.jsp"%>
		
		<%@ include file="../base/sidebarMenu.jsp"%>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">${requestScope.permissionMenu.rootName}
                  /${requestScope.permissionMenu.subName}</h1>
                        

                    </div>
                </div>
				<div class="row">
					<form class="form-horizontal baseform" method="POST" role="form" action="<%=request.getContextPath()%>/account/updateAccount2.do">
						<div class="form-group" role="form">
						<label class="col-sm-3 control-label">角色：</label>
							<div class="col-sm-6">
							<select class="form-control col-sm-6" id="roleid" name="roleid"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">名称：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" id="name" name="name"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">手机号：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" id="payphone" name="payphone"></div>
						</div>
												<div class="form-group">
							<label class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-6"><input type="password" class="form-control col-sm-6" id="password" name="password"></div>
						</div>
							<button type="button" class="btn btn-default">关闭</button>
					<button type="submit" class="btn btn-primary summit">提交</button>
					</form>
				</div>
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
	<%@ include file="../base/pageFooter.jsp"%>
    <!-- /. FOOTER  -->
</body>
<script type="text/javascript">

//下拉框显示
function showRoleList() {
	$.ajax({
		type : "post",//不写此参数默认为get方式提交
		async : false, //设置为同步
		url : "<%=request.getContextPath()%>/role/selectAllUsingRole.do",//请求的uri
		data : {},//传递到后台的参数				
		cache : false,
		dataType : 'json',//后台返回前台的数据格式为json
		success : function(data) {
			var content = "";
			for (var i = 0; i < data.length; i++) {
				content += "<option value='"+data[i].roleid+"'>"
						+ data[i].name + "</option>";
			}
			$('#roleid').html(content);
			$('#roleid').selectpicker('refresh');
		},
		error : function() {
			alert('系统出现异常，请稍微再试!');
		}
	});
}

$(function() {
	showRoleList();  
});

$(function(){
    $(".baseform").validate({  
  	  errorElement: 'em', 
		  errorClass: 'help-block',
		  focusInvalid: false,   
        rules:{
      	  	name:{
                required:true,
                byteRangeLength:[0,20]
            },
            payphone:{
                required:true,
                phone:true
            },
            password:{
                required:true,
                //passwordcheck:true,
                SerialCheck:true
            }
        },
        messages:{
      	  	name:{
                required:"必填",
                byteRangeLength:"名称不超过20位(汉字占2位)"
            },
            payphone:{
                required:"必填",
            },
            password:{
                required:"必填",
            }
        },
		submitHandler: function (form) {  //通过验证后运行的函数
			form.submit();
		}
    });
    
    jQuery.validator.addMethod("SerialCheck", function(value, element) {      
	      return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);      
	  }, "必须为字母或数字");
    // usernamecheck 用户名校验
     jQuery.validator.addMethod("usernamecheck", function(value, element) {      
	      return this.optional(element) || /^[a-zA-Z_][a-zA-Z0-9_]*$/.test(value);      
	  }, "必须为字母、数字或下划线，不能以数字开头");
    //passwordcheck 密码校验
    jQuery.validator.addMethod("passwordcheck", function(value, element) {      
	      return this.optional(element) || /^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[0-9a-zA-Z\W]\S{6,18}$/.test(value);      
	  }, "密码应包含字母、数字、符号中的至少2种");
    jQuery.validator.addMethod("phone", function(value, element) {   
        var tel = /^1[3-8]\d{9}$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入正确的手机号码");
    //中文两个字节
    jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {      
    	var length = value.length;      
  	for(var i = 0; i < value.length; i++){      
      if(value.charCodeAt(i) > 127){      
      length++;      
      }      
    	}      
  	return this.optional(element) || ( length >= param[0] && length <= param[1] );      
		}, "不得多余16字");
});
</script>
</html>
