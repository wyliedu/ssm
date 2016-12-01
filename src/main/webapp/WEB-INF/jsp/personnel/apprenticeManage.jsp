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
		<input type="hidden" value="${senior.teamid}" id="teamid">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    
                        <h1 class="page-head-line">师傅名称：${senior.name}</h1>
                     
                    </div>
                </div>
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
															<div class="panel-body">      		徒弟名称：<input type="text" class="" id="name_q" name="name_q"> &nbsp;&nbsp;&nbsp;&nbsp;
      		徒弟电话：<input type="text" class="" id="phone_q" name="phone_q">&nbsp;&nbsp;&nbsp;&nbsp;

  			<button class="btn btn-primary query">查询</button></div>
						<div class="panel-heading">徒弟列表 
						<a class="btn btn-success pull-right addBtn" href="/stb-web/apprentice/new?teamid=${senior.teamid}"><i class="glyphicon glyphicon-plus"></i>新增徒弟</a><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped table-hover">
								<thead>
								<tr>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 5%;">序号</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">徒弟名称</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">籍贯</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">联系电话</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">身份证号码</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">状态</th>
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
			url: "/stb-web/apprentice/apprenticelist.do",
			dataType: "json",
			dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
			data:function(d){
				var p={};
				p.teamid = $('#teamid').val();
				p.name = $('#name_q').val();
				p.phone = $('#phone_q').val();
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
	              { data: "origin", "sClass" : "text-center" },
	              { data: "payphone", "sClass" : "text-center" },
	              { data: "idcard", "sClass" : "text-center" },
	              { data: function (e) {
	            	  	var sContent = '';
	              		if(e.using==1){
	              			sContent = "启用";
	              		}else {
	              			sContent = "不启用";
	              		}
	                    return sContent;
	            	  }, "sClass" : "text-center" },
	              { data: function (e) {//这里给最后一列返回一个操作列表
	                    //e是得到的json数组中的一个item ，可以用于控制标签的属性。
 	                    var sContent = '';
 	                    if(e.using==1){
	              			sContent = sContent+'<button class="btn btn-warning using">不启用</button>&nbsp;&nbsp;&nbsp;';
	              		}else {
	              			sContent = sContent+'<button class="btn btn-warning using">启用</button>&nbsp;&nbsp;&nbsp;';
	              		}
	                    sContent = sContent+'<a class="btn btn-info" href="/stb-web/apprentice/update?apprenticeid='+e.apprenticeid+'">修改</a>&nbsp;&nbsp;&nbsp;<button class="btn btn-danger delete">删除</button>'; 
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

//查询
$(document).on('click','.query',function(){
	$(".table").dataTable().fnReloadAjax();  //刷新datatables
});

$(document).on('click','.using',function(){
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "/stb-web/apprentice/usingApprentice.do",//请求的uri
		data : {apprenticeid:aData.apprenticeid},//传递到后台的参数				
		cache : false,
		dataType : 'text',//后台返回前台的数据格式为json
		success : function(data) {
  			$(".table").dataTable().fnReloadAjax();
			alert("操作成功!");
},error: function () {
	alert('系统出现异常，请稍微再试!');
}}); 
});

$(document).on('click','.delete',function(){
	if (confirm("你确定要删除该师傅吗?"))  { 
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "/stb-web/apprentice/deleteApprentice.do",//请求的uri
		data : {apprenticeid:aData.apprenticeid,
			teamid:$('#teamid').val()},//传递到后台的参数				
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


$(function(){

});
</script>
</html>
