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
		<input type="hidden" value="${apprentice.apprenticeid}" id="apprenticeid">
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="panel panel-info">
				
					<div class="panel-heading">徒弟信息：
						<button class="btn-primary pull-right" id="addBill">保存</button><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="panel-body">
						<form id="defaultForm" class="form-horizontal">
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">徒弟姓名：</label>
								<div class="col-md-6">
									<input type="text" class="form-control name" name="name" value="${apprentice.name}">
									<%-- <label class="control-label">${apprentice.name}</label> --%>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">性别：</label>
								<div class="col-md-6">
								<label class="control-label">${apprentice.gendername}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">民族：</label>
								<div class="col-md-6">
									<label class="control-label">${apprentice.nationname}</label>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">籍贯：</label>
								<div class="col-md-6">
								<input type="text" class="form-control origin" name="origin" value="${apprentice.origin}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">出生年月：</label>
								<div class="col-md-6">
									<input class="span2 dateselect birthdate" name="birthdate" value="${apprentice.birthday}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">政治面貌：</label>
								<div class="col-md-6">
								<select class="form-control col-sm-6 politicalstatus">
									<option value=''>请选择</option>
								</select>
								<input type="hidden" value="${apprentice.politicalstatus}" id="politicalstatus">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">手机：</label>
								<div class="col-md-6">
								<input type="text" class="form-control payphone" name="payphone" value="${apprentice.payphone}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">QQ号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control qq" name="qq" value="${apprentice.qq}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">邮 箱：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control email" name="email" value="${apprentice.email}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">微信号：</label>
								<div class="col-md-6">
								<input type="text" class="form-control wechat" name="wechat" value="${apprentice.wechat}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">支付宝账号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control alipay" name="alipay" value="${apprentice.alipay}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身体状况：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control health" name="health">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">专业特长：</label>
								<div class="col-md-8">
								<input type="text" class="form-control hobby" name="hobby" value="${apprentice.hobby}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">家人电话：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control familyphone" name="familyphone" value="${apprentice.familyphone}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">初次学历：</label>
								<div class="col-sm-6">
								<select class="form-control col-md-12 education firsteducation"></select>
								<input type="hidden" value="${apprentice.firsteducation}" id="firsteducation">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">现有学历：</label>
								<div class="col-sm-6">
								      <select class="form-control education currenteducation" ></select>
								      <input type="hidden" value="${apprentice.currenteducation}" id="currenteducation">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身份证号：</label>
								<div class="col-md-6">
									<input type="text" class="form-control idcard" name="idcard" value="${apprentice.idcard}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-6">
								<label class="col-sm-3 control-label">现家庭住址：</label>
								<div class="col-sm-9">
								<input type="text" class="form-control address" name="address" value="${apprentice.address}">
								</div>
							</div>
							<div class="col-md-6">
								<label class="col-sm-3 control-label">工作地点：</label>
								<div class="col-sm-9">
								      <select class="form-control workplace" name="workplace"></select>
								      <input type="hidden" value="${apprentice.workplace}" id="workplace">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">从事弱电工作经历：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 reward" rows="4"
										 name="reward">${apprentice.reward}</textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">自我评价：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 selfevaluation" rows="4"
										 name="selfevaluation">${apprentice.selfevaluation}</textarea>
								</div>
							</div>
						</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
		<%@ include file="../base/pageFooter.jsp"%>
</body>
<script type="text/javascript">
	
	//修改社区基本资料
	$(document).on('click','#addBill',function(){
		$('#defaultForm').bootstrapValidator('validate');
		if(!$('#defaultForm').data('bootstrapValidator').isValid()){
			return false;
		}
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "/stb-web/apprentice/updateApprentice.do",//请求的uri
			data : {payphone:$('.payphone').val(),
				origin:$('.origin').val(),
				birthdate:$('.birthdate').val(),
				name:$('.name').val(),
				apprenticeid:$('#apprenticeid').val(),
				firsteducation:$('.firsteducation').val(),
				currenteducation:$('.currenteducation').val(),
				politicalstatus:$('.politicalstatus').val(),
				qq:$('.qq').val(),
				email:$('.email').val(),
				hobby:$('.hobby').val(),
				health:$('.health').val(),
				familyphone:$('.familyphone').val(),
				address:$('.address').val(),
				workplace:$('.workplace').val(),
				reward:$('.reward').val(),
				selfevaluation:$('.selfevaluation').val(),
				wechat:$('.wechat').val(),
				alipay:$('.alipay').val(),
				payphone:$('.payphone').val(),
				idcard:$('.idcard').val()
				},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				if(data.code!=0){
					alert(data.message);
				}else{
					alert("修改成功!");
					history.go(-1);
				}
			},error: function () {
	            alert('系统出现异常，请稍微再试!');
	        }});
	});
	
	$(function() {
			
			$.ajax({
				type : "post",//不写此参数默认为get方式提交
				async : false, //设置为同步
				url : "/stb-web/parametric/educationlist.do",//请求的uri
				data : {},//传递到后台的参数				
				cache : false,
				dataType : 'json',//后台返回前台的数据格式为json
				success : function(data) {
					var content = "<option value='0'>请选择</option>";
					for (var i = 0; i < data.length; i++) {
						content += "<option value='"+data[i].paramvalue+"'>"
								+ data[i].memo + "</option>";
					}
					$('.education').html(content);
					$('.firsteducation').val($('#firsteducation').val());
					$('.currenteducation').val($('#currenteducation').val());
					$('.education').selectpicker('refresh');
				},
				error : function() {
					alert('系统出现异常，请稍微再试!');
				}
			});
			
			$.ajax({
				type : "post",//不写此参数默认为get方式提交
				async : false, //设置为同步
				url : "/stb-web/parametric/politicalstatuslist.do",//请求的uri
				data : {},//传递到后台的参数				
				cache : false,
				dataType : 'json',//后台返回前台的数据格式为json
				success : function(data) {
					var content = "<option value='0'>请选择</option>";
					for (var i = 0; i < data.length; i++) {
						content += "<option value='"+data[i].paramvalue+"'>"
								+ data[i].memo + "</option>";
					}
					$('.politicalstatus').html(content);
					$('.politicalstatus').val($('#politicalstatus').val());
					$('.politicalstatus').selectpicker('refresh');
				},
				error : function() {
					alert('系统出现异常，请稍微再试!');
				}
			});
			
			$.ajax({
				type : "post",//不写此参数默认为get方式提交
				async : false, //设置为同步
				url : "/stb-web/parametric/workplacelist.do",//请求的uri
				data : {},//传递到后台的参数				
				cache : false,
				dataType : 'json',//后台返回前台的数据格式为json
				success : function(data) {
					var content = "<option value='0'>请选择</option>";
					for (var i = 0; i < data.length; i++) {
						content += "<option value='"+data[i].paramvalue+"'>"
								+ data[i].memo + "</option>";
					}
					$('.workplace').html(content);
					$('.workplace').val($('#workplace').val());
					$('.workplace').selectpicker('refresh');
				},
				error : function() {
					alert('系统出现异常，请稍微再试!');
				}
			});

		$('.dateselect').datepicker({
			format:"yyyy-mm-dd",
			language: "zh-CN",
			todayBtn: "linked",
			todayHighlight: true,
			autoclose: true
		});
		
		$('#defaultForm').bootstrapValidator({
//	        live: 'disabled',
	        message: '输入的值不合规范',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            familyphone: {
	            	group: '.col-md-6',
	                validators: {
	                    regexp: {
	                        regexp: /^1[3|4|5|7|8]\d{9}$/,
	                        message: '请输入正确的手机号'
	                    }
	                }
	            },
	            email: {
	            	group: '.col-md-6',
	                validators: {
	                    emailAddress: {
	                        message: '请输入正确的邮箱地址'
	                    }
	                }
	            },
	        }
	    });
	});
</script>
</html>
