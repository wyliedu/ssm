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
	var c = [//对应上面thead里面的序列
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
   ];
	IndexFunction.dataTable(".table","<%=request.getContextPath()%>/log/list.do",c);
});
</script>
</html>
