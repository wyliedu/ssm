﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<input type="hidden" value="${contractor.contractorid}" id="contractorid">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                 <div class="row">
                    <div class="col-md-12">
                    
                        <h1 class="page-head-line">承包商名称：${contractor.name}</h1>
                     
                    </div>
                </div>
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">承包商员工列表 
						<button class="btn btn-success pull-right addBtn"><i class="glyphicon glyphicon-plus"></i>新增员工</button><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped table-hover">
								<thead>
								<tr>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 5%;">序号</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">名称</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">联系电话</th>
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
						<div class="form-group">
							<label class="col-sm-3 control-label">名称：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" id="name" name="name"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">联系电话：</label>
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
	<%@ include file="../base/pageFooter.jsp"%>
    <!-- /. FOOTER  -->
</body>
<script type="text/javascript">
var operationType = 0;
var aData= null;

$(function() {
	$(".table").dataTable({
		serverSide: false,	//分页，取数据等等的都放到服务端去
		processing: true,	//载入数据的时候是否显示“载入中”
		pageLength: 10,		//首次加载的数据条数
		ordering: false,		//排序操作
		"bLengthChange": false,
		"bFilter": false,
		"bDeferRender": true,
		"bAutoWidth" : true, //是否自适应宽度  
		ajax:  {//类似jquery的ajax参数，基本都可以用。
			type: "post",//后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
			url: "/stb-web/contractor/employeelist.do",
			dataType: "json",
			dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
			data:function(d){
				var p={};
				p.contractorid = $('#contractorid').val();
				return p;
			}
	    } ,
	    "columnDefs": [ {
            "searchable": false,
            "orderable": false,
            "targets": 0
        } ],
	    "order": [[ 1, 'asc' ]],
	    showRowNumber:true,
	    columns: [//对应上面thead里面的序列
	              {  data: "xh", "sClass" : "text-center" },
	              { data: "name", "sClass" : "text-center" },
	              { data: "payphone", "sClass" : "text-center" },
	              { data: function (e) {//这里给最后一列返回一个操作列表
	                    //e是得到的json数组中的一个item ，可以用于控制标签的属性。
 	                    var sContent = '<button class="btn-primary modify">修改</button>&nbsp;&nbsp;&nbsp;';
	                    sContent = sContent+'<button class="btn-danger delete">删除</button>'; 
	                    return sContent;
	                }, "sClass" : "text-center"
	              }
	    ] ,
	    initComplete: function (setting, json) {
	        //初始化完成之后替换原先的搜索框。
	        //本来想把form标签放到hidden_filter 里面，因为事件绑定的缘故，还是拿出来。
	       // $(tablePrefix+"_filter").html("<form id='filter_form'>" + $("#hidden_filter").html() + "</form>");
	    },
	    language: {
	        lengthMenu: '<select class="form-control input-xsmall">' + '<option value="5">5</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>条记录',//左上角的分页大小显示。
	        processing: "载入中",//处理页面数据的时候的显示
	        paginate: {//分页的样式文本内容。
	            previous: "上一页",
	            next: "下一页",
	            first: "第一页",
	            last: "最后一页"
	         },
	         zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
	         //下面三者构成了总体的左下角的内容。
	         info: "总共_PAGES_页，显示第_START_到第 _END_条 ",//左下角的信息显示，大写的词为关键字。
	         infoEmpty: "0条记录",//筛选为空时左下角的显示。
	         infoFiltered: ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
	    } 
	});   
});

$(document).on('click','.delete',function(){
	if (confirm("你确定要删除该员工吗?"))  { 
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "/stb-web/contractor/deleteEmployee.do",//请求的uri
		data : {employeeid:aData.employeeid},//传递到后台的参数				
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
	operationType = 2;
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$('.baseform')[0].reset();
	$('.baseform').validate().resetForm();
	$('#name').val(aData.name);
	$('#payphone').val(aData.payphone);
	$('.modal-title').html("修改员工");
	$('.modal').modal('show');
});

//添加对话框
$(document).on('click','.addBtn',function(){
	$('.baseform')[0].reset();
	$('.baseform').validate().resetForm();
	operationType = 1;
	$('.modal-title').html("添加员工");
	$('.modal').modal('show');
});

//新增设备提交
$(".summit").click(function () {
	var id = 0;
	if(operationType != 1){
		id = aData.employeeid;
	}
	if($(".baseform").valid()){
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "/stb-web/contractor/updateEmployee.do",//请求的uri
			data : {
				type:operationType,
				contractorid:$('#contractorid').val(),
				name:$('#name').val(),
				payphone:$('#payphone').val(),
				employeeid:id
				},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				if(data.code!=0){
					alert(data.message);
				}else{
	      			$('.baseform')[0].reset();
	      			$(".table").dataTable().fnReloadAjax();
					$('.modal').modal('hide');
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
                byteRangeLength:[0,100]
            },
            payphone:{
            	required:true,
            	phone:true
            }
        },
        messages:{
      	  	name:{
                required:"必填",
                byteRangeLength:"名称不超过100位(汉字占2位)"
            },
            payphone:{
            	required:"必填",
            	phone:"请输入正确的手机号码",
            }                   
        }       
    });
    
    jQuery.validator.addMethod("SerialCheck", function(value, element) {      
	      return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);      
	  }, "");
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
