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
   <link rel="stylesheet" href="<c:url value='/plugins/bootstrap-treeview/bootstrap-treeview.css'/>" type="text/css"/>
   <%@ include file="../base/importJs.jsp"%>
   <script type="text/javascript" src="<c:url value='/plugins/bootstrap-treeview/bootstrap-treeview.js'/>"></script>
</head>
<body>
    <div id="wrapper">
		<%@ include file="../base/pageHeader.jsp"%>
		
		<%@ include file="../base/sidebarMenu.jsp"%>
		<input type="hidden" value="${role.id}" id="roleid">
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    
                        <h1 class="page-head-line">系统设置
                  /权限绑定</h1>
                    </div>
                </div>
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">${role.name}
							<button class="btn-primary pull-right" id="save">保存</button><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
						</div>
						<div class="panel-body">
							<div id="tree" class="treelite_treebox"></div>
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

function refreshTree(){
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "<%=request.getContextPath()%>/role/index/getAuthorityList.do",//请求的uri
		data : {roleid:$('#roleid').val()},//传递到后台的参数				
		cache : false,
		dataType : 'json',//后台返回前台的数据格式为json
		success : function(data) {
			$('#tree').treeview({data: data,showCheckbox:true,showBorder:false,collapseIcon:'glyphicon glyphicon-minus',expandIcon:'glyphicon glyphicon-plus',
				onNodeSelected:function(event,data){
					$('#tree').treeview('toggleNodeExpanded', [ data.nodeId, { silent: true } ]);
	  			  },onNodeChecked:function(event,node){
	  				  $('#tree').treeview('expandNode', [ node.nodeId, { silent: true } ]);
	  				  if(node.parentId!=null){  //子节点，则父节点自动勾上,孙节点自动勾上
 	  					var nodelist = $('#tree').treeview('getEnabled', node.nodeId);
	  					for(var i=0;i<nodelist.length;i++){
	  						if(nodelist[i].parentId==node.nodeId){
	  							$('#tree').treeview('checkNode', [ nodelist[i].nodeId, { silent: true } ]);
	  						}
	  					}
	  					$('#tree').treeview('checkNode', [ node.parentId, { silent: true } ]);
	  					var pnode = $('#tree').treeview('getParent', node);  //父节点
	  					if(pnode.parentId!=null){
	  						$('#tree').treeview('checkNode', [ pnode.parentId, { silent: true } ]);
	  					} 
	  				  }else{
	  					var nodelist = $('#tree').treeview('getEnabled', node.nodeId);
	  					for(var i=0;i<nodelist.length;i++){
	  						if(nodelist[i].parentId==node.nodeId){
	  							var cnode = $('#tree').treeview('getNode', nodelist[i].nodeId); //子节点
	  							$('#tree').treeview('checkNode', [ nodelist[i].nodeId, { silent: true } ]);
	  							for(var j=0;j<nodelist.length;j++){
	  								if(nodelist[j].parentId==cnode.nodeId){
	  									$('#tree').treeview('checkNode', [ nodelist[j].nodeId, { silent: true } ]);
	  								}
	  							}
	  						}
	  					}
	  				  }
	  			  },onNodeUnchecked:function(event,node){
	  				  $('#tree').treeview('expandNode', [ node.nodeId, { silent: true } ]);
	  					var nodelist = $('#tree').treeview('getEnabled', node.nodeId);
	  					for(var i=0;i<nodelist.length;i++){
	  						if(nodelist[i].parentId==node.nodeId){
	  							var cnode = $('#tree').treeview('getNode', nodelist[i].nodeId); //子节点
	  							$('#tree').treeview('uncheckNode', [ nodelist[i].nodeId, { silent: true } ]);
	  							for(var j=0;j<nodelist.length;j++){
	  								if(nodelist[j].parentId==cnode.nodeId){
	  									$('#tree').treeview('uncheckNode', [ nodelist[j].nodeId, { silent: true } ]);
	  								}
	  							}
	  						}
	  					}

		  			 }});

	}}); 
}

$(document).on('click','#save',function(){
	var checkedlist = JSON.stringify($('#tree').treeview('getChecked', null));
	$.ajax( {
		type:"post",//不写此参数默认为get方式提交
		async:false,   //设置为同步
		url : "<%=request.getContextPath()%>/role/saveAuthorityOfRole.do",//请求的uri
		data : {checkedlist:checkedlist,
			roleid:$('#roleid').val()
			},//传递到后台的参数				
		cache : false,
		dataType : 'json',//后台返回前台的数据格式为json
		success : function(data) {
			refreshTree();
			if(data.code!=0){
				alert(data.message);
			}else{
				alert("保存成功!");
			}
		},error: function () {
            alert('系统出现异常，请稍微再试!');
        }});
});

$(function() {
	refreshTree();
}); 
</script>
</html>
