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
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="panel panel-primary">
					<div class="panel-heading">工单基本信息：
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">客户名称：</label>
								<div class="col-md-6">
									<label class="control-label">${billDetail.bill.contractorname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">联系人：</label>
								<div class="col-sm-6">
								<label class="control-label">${billDetail.bill.picname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">联系人联系方式：</label>
								<div class="col-md-6">
									<label class="control-label col-md-3" id="payphone">${billDetail.bill.picpayphone}
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">施工组织：</label>
								<div class="col-md-6">
									<label class="control-label">${billDetail.bill.teamname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">需要人数：</label>
								<div class="col-sm-6">
								      <label class="control-label">${billDetail.bill.new_personnum}</label>   
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">预计完成天数：</label>
								<div class="col-sm-6">
									<label class="control-label">${billDetail.bill.new_period}</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">项目开始时间：</label>
								<div class="col-md-6">
									<label class="control-label">${billDetail.bill.startDate}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">施工描述：</label>
								<div class="col-sm-8">
									<label class="control-label">${billDetail.bill.new_memo}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">施工地址：</label>
								<div class="col-sm-8">
									<label class="control-label">${billDetail.bill.billaddress}</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">态度评分：</label>
								<div class="col-md-6">
									<label class="control-label">${billDetail.bill.attitude}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">质量评分：</label>
								<div class="col-sm-8">
									<label class="control-label">${billDetail.bill.quality}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">效率评分：</label>
								<div class="col-sm-8">
									<label class="control-label">${billDetail.bill.efficiency}</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-8">
								<label class="col-md-2 control-label">验收备注：</label>
								<div class="col-md-10">
									<label class="control-label">${billDetail.bill.acceptancenote}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">师傅名称：</label>
								<div class="col-md-6">
									<label class="control-label">${billDetail.bill.seniorname}</label>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-heading panel-info">师傅检查签到信息：</div>
					<div class="panel-body">
						<table class="table table-hover">	
						<th>签到时间 </th><th>签到照片 </th>
      					<c:forEach items="${billDetail.seniorSignList}" var="item" varStatus="status">
      					<tr><td>${item.createtime}</td><td><c:forEach items="${item.picture}" var="item3" varStatus="status"><img src="${item3}" width="200" height="255">&nbsp;&nbsp;</c:forEach></td></tr>
      					</c:forEach>
      					</table>
					</div>
					<div class="panel-heading panel-info">徒弟施工签到信息：</div>
					<div class="panel-body">
						<table class="table table-hover">	
						<th>徒弟姓名</th><th>徒弟照片 </th><th>签到时间 </th><th>签到照片 </th>
      					<c:forEach items="${billDetail.apprenticeSignList}" var="item" varStatus="status">
      					<tr><td>${item.name}</td><td><img alt="" src="${item.portrait}" width="200" height="255"></td><td>${item.createtime}

      						</td><td><c:forEach items="${item.picture}" var="item3" varStatus="status"><img src="${item3}" width="200" height="255">&nbsp;&nbsp;</c:forEach></td></tr>
      					</c:forEach>
      					</table>
					</div>
					<div class="panel-heading panel-info">徒弟记录问题：</div>
					<div class="panel-body">
						<table class="table table-hover">
						<th>徒弟名称</th><th>照片</th><th>施工问题类型</th><th>施工问题处理 </th>
						 <c:forEach items="${billDetail.apprenticeProList}" var="item" varStatus="status">
						 <tr><td>${item.name}</td><td><c:forEach items="${item.picture}" var="item3" varStatus="status"><img src="${item3}" width="200" height="255">&nbsp;&nbsp;</c:forEach></td>
						 <td>${item.problemtypename}</td><td>${item.problemsolvename}</td></tr>
      					</c:forEach>
						</table>

					</div>
					<div class="panel-heading panel-info">徒弟工作量：</div>
					<div class="panel-body">
						<table class="table table-hover">
						<th>徒弟名称</th><th>时间</th><th>工作量 </th>
      					<c:forEach items="${billDetail.apprenticeWorkloadList}" var="item" varStatus="status">
      						<tr><td>${item.name}</td><td>${item.createtime}</td><td>
      					     <c:forEach items="${item.workloadlist}" var="item2" varStatus="status">
      						${item2.itemclassname}——${item2.itemtypename}:${item2.itemnumname} <br>
      						</c:forEach>
      						</td></tr>
      					</c:forEach>
      					</table>
					</div>
					<div class="panel-heading panel-info">师傅检查施工评价(多次)：</div>
					<div class="panel-body">
						<table class="table table-hover">
						<th>态度</th><th>质量 </th><th>效率 </th><th>备注</th>
      					<c:forEach items="${billDetail.sensiorEvaList}" var="item" varStatus="status">
      					<tr><td>${item.attitude}</td><td>${item.quality}</td><td>${item.tech}</td><td>${item.evaluatetext}</td></tr>
      					</c:forEach>
      					</table>
					</div>
					<div class="panel-heading panel-info">师傅检查整体施工效果：</div>
					<div class="panel-body">
						<table class="table table-hover">
						<th>照片</th><th width="150">备注</th><th width="150">施工现场环境检查</th>
      					<c:forEach items="${billDetail.sensiorConList}" var="item" varStatus="status">
      					<tr><td>
      					<c:forEach items="${item.picture}" var="item2" varStatus="status"><img src="${item2}" width="200" height="255">&nbsp;&nbsp;</c:forEach>
      					</td><td width="100">${item.constructiontext}</td><td width="100"><c:choose><c:when test="${item.constructionflag eq 1}">
							合格
						</c:when>
						<c:otherwise>
							不合格
						</c:otherwise></c:choose></td></tr>
      					</c:forEach>
      					</table>
					</div>
					<div class="panel-heading panel-info">师傅检查施工质量：</div>
					<c:forEach items="${billDetail.qualityCheckList}" var="item" varStatus="status">
						<div class="panel-body">
							<table class="table table-hover">
								<th>施工项目类别</th><th>照片 </th><th>检查项</th>
								<c:forEach items="${item.taskcheck}" var="item2" varStatus="status">
								<tr><td width="150">${item2.standardname}</td>
								<td><c:forEach items="${item2.picture}" var="item3" varStatus="status"><img src="${item3}" width="200" height="255">&nbsp;&nbsp;</c:forEach></td>
								<td width="200"><c:forEach items="${item2.taskcheckitem}" var="item4" varStatus="status">${item4.doctext}:
								<c:choose><c:when test="${item4.checked eq 1}">
							合格
						</c:when>
						<c:otherwise>
							不合格
						</c:otherwise></c:choose><br></c:forEach></td>
								</tr>
								</c:forEach>
							</table>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<%@ include file="../base/pageFooter.jsp"%>
</body>
<script type="text/javascript">
</script>
</html>
