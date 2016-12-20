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
				<div class="col-md-12">
					<div class="panel panel-default">
					<%-- <div class="panel-heading">用户管理列表<a class="btn btn-success pull-right" href="<%=request.getContextPath()%>/account/addAccount.do"><i class="glyphicon glyphicon-plus"></i>新增用户</a></div> --%>
						<div class="panel-heading">用户管理列表<button class="btn btn-success pull-right addBtn"><i class="glyphicon glyphicon-plus"></i>新增用户</button></div>
						<div class="panel-body">
							<table class="table table-bordered table-striped table-hover">
								<thead>
								<tr>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 5%;">序号</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">名字</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">手机号</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">角色</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 15%;">操作</th>
								</tr>
								</thead>
							</table>
						</div>
					</div></div>
				</div>
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    	<div class="modal fade modalArea" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal baseform" role="form">
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
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary summit">提交</button>
				</div>
			</div>
		</div>
	</div>
    <!-- /. WRAPPER  -->
    <%@ include file="../base/importJs.jsp"%>
	<%@ include file="../base/pageFooter.jsp"%>
    <!-- /. FOOTER  -->
</body>
<script type="text/javascript">
var aData= null;
var id = null;

$(function() {
	IndexFunction.showRoleList('#roleid',"<%=request.getContextPath()%>/role/index/selectAllUsingRole.do");
	var c = [//对应上面thead里面的序列
             {  data: "xh", "sClass" : "text-center" },
             { data: "name", "sClass" : "text-center" },
             { data: "username", "sClass" : "text-center" },
             { data: "rolename", "sClass" : "text-center" },
             { data: function (e) {//这里给最后一列返回一个操作列表
                   //e是得到的json数组中的一个item ，可以用于控制标签的属性。
                   var sContent = '<button class="btn-primary modify">修改</button>&nbsp;&nbsp;&nbsp;';
                   sContent = sContent+'<button class="btn-danger delete">删除</button>'; 
                   return sContent;
               }, "sClass" : "text-center"
             }
   ];
	IndexFunction.dataTable(".table",false,"<%=request.getContextPath()%>/account/list.do",c);
});

$(document).on('click','.delete',function(){
	if (confirm("你确定要删除该角色吗?"))  { 
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "<%=request.getContextPath()%>/account/index/deleteAccount.do",//请求的uri
		data : {accountid:aData.accountid},//传递到后台的参数				
		cache : false,
		dataType : 'text',//后台返回前台的数据格式为json
		success : function(data) {
  			$(".table").dataTable().fnReloadAjax();
			alert("操作成功!");
},error: function () {
	alert('系统出现异常，请稍微再试!');
}}); 
	}
});

//修改对话框
$(document).on('click','.modify',function(){//修改方案
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	id = aData.accountid;
	$('.baseform')[0].reset();
	$('.baseform').validate().resetForm();
	$('#name').val(aData.name);
	$('#payphone').val(aData.username);
	$('.modal-title').html("修改角色");
	$('.modal').modal('show');
});

//添加对话框
$(document).on('click','.addBtn',function(){
	id = null;
	$('.baseform')[0].reset();
	$('.baseform').validate().resetForm();
	$('.modal-title').html("添加用户");
	$('.modal').modal('show');
});

//新增设备提交
$(".summit").click(function () {
	if($(".baseform").valid()){
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "<%=request.getContextPath()%>/account/updateAccount.do",//请求的uri
			data : {
				accountid:id,
				name:$('#name').val(),
				roleid:$('#roleid').val(),
				payphone:$('#payphone').val(),
				},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				if(data.code!=0){
					alert(data.message);
				}else{
					$('.modal').modal('hide');
      				$('.baseform')[0].reset();
      				$(".table").dataTable().fnReloadAjax();
					alert("操作成功!");
				}
	},error: function () {
		alert('系统出现异常，请稍微再试!');
    }}); 
	  	}
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
        }       
    });
});
</script>
</html>
