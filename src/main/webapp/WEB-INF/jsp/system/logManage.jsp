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
						<div class="panel-heading">日志管理列表</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped table-hover">
								<thead>
								<tr>
								<th class="text-center" >序号</th>
								<th class="text-center" >描述</th>
								<th class="text-center" >ip</th>
								<th class="text-center" >操作人</th>
								<th class="text-center" >创建时间</th>
								<th class="text-center" >操作</th>
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
$(function() {
	$(".table").dataTable({
		serverSide: true,	//分页，取数据等等的都放到服务端去
		processing: true,	//载入数据的时候是否显示“载入中”
		pageLength: 10,		//首次加载的数据条数
		ordering: false,		//排序操作
		"bLengthChange": false,
		"bFilter": false,
		"bDeferRender": true,
		"bAutoWidth" : true, //是否自适应宽度  
		ajax:  {//类似jquery的ajax参数，基本都可以用。
			type: "post",//后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
			url: "<%=request.getContextPath()%>/log/list.do",
			dataType: "json",
			dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
			data:function(d){
				var param = {};//因为服务端排序，可以新建一个参数对象
                param.start = d.start;//开始的序号
                param.length = d.length;//要取的数据的
                return param;//自定义需要传递的参数。
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
	              { data: "description", "sClass" : "text-center" },
	              { data: "requestIp", "sClass" : "text-center" },
	              { data: "operator", "sClass" : "text-center" },
	              { data: "createtimevalue", "sClass" : "text-center" },
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
	            first: "首页",
	            last: "尾页"
	         },
	         zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
	         //下面三者构成了总体的左下角的内容。
	         info: "总共_PAGES_页，显示第_START_到第_END_条 ",//左下角的信息显示，大写的词为关键字。
	         infoEmpty: "0条记录",//筛选为空时左下角的显示。
	         infoFiltered: ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
	    } 
	});   
});
</script>
</html>
