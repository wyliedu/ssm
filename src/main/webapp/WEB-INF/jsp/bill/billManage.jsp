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
				<div class="col-md-12">
					<div class="panel panel-default">
					<div class="panel-body">      		工单id：<input type="text" class="" id="billid_q" name="billid_q"> &nbsp;&nbsp;&nbsp;&nbsp;
      		客户：<select  id="contractor_q"name="contractor_q"></select>&nbsp;&nbsp;&nbsp;&nbsp;
      		施工组织：<select  id="team_q"> </select>&nbsp;&nbsp;&nbsp;&nbsp;

  			<button class="btn btn-primary query">查询</button></div>
						<div class="panel-heading">工单列表 
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped table-hover">
								<thead>
								<tr>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 5%;">序号</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">工单id</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">客户名称</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">施工描述</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">施工地址</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">客户联系人</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">最晚联系用户的时间</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">施工组织</th>
								<th class="text-center" style="background: #3499db; color: #ffffff; width: 10%;">工单状态</th>
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
							<label class="col-sm-3 control-label">态度评分：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" placeholder="最高五分" id="attitude" name="attitude"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">质量评分：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" placeholder="最高五分" id="quality" name="quality"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">效率评分：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" placeholder="最高五分" id="efficiency" name="efficiency"></div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">备注：</label>
							<div class="col-sm-6"><input type="text" class="form-control col-sm-6" id="acceptancenote" name="acceptancenote"></div>
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
	$.ajax({
		type : "post",//不写此参数默认为get方式提交
		async : false, //设置为同步
		url : "/stb-web/contractor/list.do",//请求的uri
		data : {},//传递到后台的参数				
		cache : false,
		dataType : 'json',//后台返回前台的数据格式为json
		success : function(data) {
			var content = "<option value='0'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				content += "<option value='"+data[i].contractorid+"'>"
						+ data[i].name + "</option>";
			}
			$('#contractor_q').html(content);
			$('#contractor_q').selectpicker('refresh');
		},
		error : function() {
			alert('系统出现异常，请稍微再试!');
		}
	});
	$.ajax({
		type : "post",//不写此参数默认为get方式提交
		async : false, //设置为同步
		url : "/stb-web/senior/teamlist.do",//请求的uri
		data : {},//传递到后台的参数				
		cache : false,
		dataType : 'json',//后台返回前台的数据格式为json
		success : function(data) {
			var content = "<option value='0'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				content += "<option value='"+data[i].teamid+"'>"
						+ data[i].teamname + "</option>";
			}
			$('#team_q').html(content);
			$('#team_q').selectpicker('refresh');
		},
		error : function() {
			alert('系统出现异常，请稍微再试!');
		}
	});
	
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
			url: "/stb-web/bill/billlist.do",
			dataType: "json",
			dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
			data:function(d){
				var p={};
				p.billid = $('#billid_q').val();
				p.contractorid = $('#contractor_q').val();
				p.teamid = $('#team_q').val();
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
	              { data: "billid", "sClass" : "text-center" },
	              { data: "contractorname", "sClass" : "text-center" },
	              { data: "new_memo", "sClass" : "text-center" },
	              { data: "billaddress", "sClass" : "text-center" },
	              { data: "picname", "sClass" : "text-center" },
	              { data: "pnegotiationDatetime", "sClass" : "text-center" },
	              { data: "teamname", "sClass" : "text-center" },
	              { data: "billstatename", "sClass" : "text-center" },
	              { data: function (e) {//这里给最后一列返回一个操作列表
	                    //e是得到的json数组中的一个item ，可以用于控制标签的属性。
 	                    var sContent = '<a class="btn btn-info" href="/stb-web/bill/detail?billid='+e.billid+'">详情</a>&nbsp;&nbsp;&nbsp;';
	              		if(e.billstate==3){
	              			sContent += '<button class="btn btn-danger addBtn">验收</button>&nbsp;&nbsp;&nbsp;';
	              		}else if(e.billstate==1){
	              			sContent = sContent+'<a class="btn btn-info" href="/stb-web/bill/update?billid='+e.billid+'">修改</a>'; 
	              		}
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

$(document).on('click','.delete',function(){
	if (confirm("你确定要删除该师傅吗?"))  { 
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "/stb-web/senior/deleteSenior.do",//请求的uri
		data : {teamid:aData.teamid},//传递到后台的参数				
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

//添加对话框
$(document).on('click','.addBtn',function(){
	aData = $(".table").DataTable().row($(this).parents("tr")).data();
	$('.baseform')[0].reset();
	$('.baseform').validate().resetForm();
	$('.modal-title').html("验收");
	$('.modal').modal('show');
});

//新增设备提交
$(".summit").click(function () {
	$('.baseform').bootstrapValidator('validate');
	if(!$('.baseform').data('bootstrapValidator').isValid()){
		return false;
	}
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "/stb-web/bill/createAcceptance.do",//请求的uri
			data : {
				attitude:$('#attitude').val(),
				quality:$('#quality').val(),
				efficiency:$('#efficiency').val(),
				acceptancenote:$('#acceptancenote').val(),
				billid:aData.billid
				},//传递到后台的参数				
			cache : false,
			dataType : 'text',//后台返回前台的数据格式为json
			success : function(data) {
      			$('.baseform')[0].reset();
      			$(".table").dataTable().fnReloadAjax();
				$('.modal').modal('hide');
				alert("验收成功");
	},error: function () {
		alert('系统出现异常，请稍微再试!');
    }}); 
});

$(function(){

	$('.baseform').bootstrapValidator({
//        live: 'disabled',
        message: '输入的值不合规范',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	attitude: {
            	group: '.col-sm-6',
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^(([0-4]+\.[0-9]*[1-9][0-9]*)|([0-4]\.[0-9]+)|([0-5]))$/,
                        message: '请输入小于5的整数或小数'
                    }
                }
            },
            quality: {
            	group: '.col-sm-6',
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^(([0-4]+\.[0-9]*[1-9][0-9]*)|([0-4]\.[0-9]+)|([0-5]))$/,
                        message: '请输入小于5的整数或小数'
                    }
                }
            },
            efficiency: {
            	group: '.col-sm-6',
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        //regexp: /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,
                        regexp: /^(([0-4]+\.[0-9]*[1-9][0-9]*)|([0-4]\.[0-9]+)|([0-5]))$/,
                        message: '请输入小于5的整数或小数'
                    }
                }
            },
        }
    });
});
</script>
</html>
