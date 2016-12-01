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
		<input type="hidden" value="${senior.teamid}" id="teamid">
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="panel panel-info">
				
					<div class="panel-heading">师傅信息：
							<button class="btn-primary pull-right" id="addBill">保存</button><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="panel-body">
						<form id="defaultForm" class="form-horizontal">
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">师傅姓名：</label>
								<div class="col-md-6">
									<label class="control-label">${senior.name}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">性别：</label>
								<div class="col-md-6">
									<label class="control-label">${senior.gendername}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">民族：</label>
								<div class="col-md-6">
							<label class="control-label">${senior.nationname}</label>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">籍贯：</label>
								<div class="col-md-6">
								<input type="text" class="form-control origin" name="origin" value="${senior.origin}">

								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">出生年月：</label>
								<div class="col-md-6">
									<input class="span2 dateselect birthdate" name="birthdate" value="${senior.birthday}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">政治面貌：</label>
								<div class="col-md-6">
								<select class="form-control col-sm-6 politicalstatus">
									<option value=''>请选择</option>
								</select>
								<input type="hidden" value="${senior.politicalstatus}" id="politicalstatus">
								</div>
							</div>
						</div>
						<br>
																		<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">省：</label>
								<div class="col-md-6 provincediv">
									<select class="form-control col-sm-6 province"><option value='0'>请选择</option></select>
								</div>
								<input type="hidden" value="${senior.provinceid}" id="provinceid">
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">城市：</label>
								<div class="col-md-6 citydiv">
									<select class="form-control col-sm-6 city"><option value='0'>请选择</option></select>
								</div>
								<input type="hidden" value="${senior.cityid}" id="cityid">
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">区：</label>
								<div class="col-md-6 areadiv">
								<select class="form-control col-sm-6 area"><option value='0'>请选择</option></select>
								</div>
								<input type="hidden" value="${senior.areaids}" id="areaid">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">手机：</label>
								<div class="col-md-6">
								<input type="text" class="form-control payphone" name="payphone" value="${senior.payphone}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">QQ号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control qq" name="qq" value="${senior.qq}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">邮 箱：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control email" name="email" value="${senior.email}">
								</div>
							</div>
						</div>
						<br>
												<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">微信号：</label>
								<div class="col-md-6">
								<input type="text" class="form-control wechat" name="wechat" value="${senior.wechat}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">支付宝账号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control alipay" name="alipay" value="${senior.alipay}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍前职务：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control armyjob" name="armyjob" value="${senior.armyjob}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">入伍时间：</label>
								<div class="col-md-6">
									<input class="span2 dateselect inarmy" name="inarmy" value="${senior.inarmydate}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍时间：</label>
								<div class="col-md-6">
									<input class="span2 dateselect outarmy" name="outarmy" value="${senior.outarmydate}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍前职务军衔：</label>
								<div class="col-md-6">
									<input type="text" class="form-control armyrank" name="armyrank" value="${senior.armyrank}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">爱好与特长：</label>
								<div class="col-md-8">
								<input type="text" class="form-control hobby" name="hobby" value="${senior.hobby}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身体状况：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control health" name="health" value="${senior.health}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">家人电话：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control familyphone" name="familyphone" value="${senior.familyphone}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">初次学历：</label>
								<div class="col-sm-6">
								<select class="form-control col-md-12 education firsteducation"></select>
								<input type="hidden" value="${senior.firsteducation}" id="firsteducation">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">现有学历：</label>
								<div class="col-sm-6">
								      <select class="form-control education currenteducation" ></select>
								      <input type="hidden" value="${senior.currenteducation}" id="currenteducation">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身份证号：</label>
								<div class="col-md-6">
								<input type="text" class="form-control idcard" name="idcard" value="${senior.idcard}">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">队伍名称：</label>
								<div class="col-md-6">
									<input type="text" class="form-control teamname" name="teamname" value="${senior.teamname}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-3 control-label">现家庭住址：</label>
								<div class="col-sm-9">
								<input type="text" class="form-control address" name="address" value="${senior.address}">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">工作地点：</label>
								<div class="col-sm-6">
								         <select class="form-control workplace" name="workplace"></select>
								         <input type="hidden" value="${senior.workplace}" id="workplace">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">入伍后简历及担任职务：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 armyresume" rows="4"
										  name="armyresume">${senior.armyresume}</textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">何时何因受何奖励：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 reward" rows="4"
										 name="reward">${senior.reward}</textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">自我评价：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 selfevaluation" rows="4"
										 name="selfevaluation">${senior.selfevaluation}</textarea>
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
		if($('.area').val()==null){
			alert("所在区不能为空");
			return false;
		}
		var billtaskstandardid_array = $('.area').val();
		var billtaskstandardids = "";
		for(var i=0;i<billtaskstandardid_array.length;i++){
			billtaskstandardids += billtaskstandardid_array[i]+" ";
		}
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "/stb-web/senior/updateSenior.do",//请求的uri
			data : {teamid:$('#teamid').val(),
				teamname:$('.teamname').val(),
				origin:$('.origin').val(),
				birthdate:$('.birthdate').val(),
				politicalstatus:$('.politicalstatus').val(),
				armyrank:$('.armyrank').val(),
				firsteducation:$('.firsteducation').val(),
				currenteducation:$('.currenteducation').val(),
				qq:$('.qq').val(),
				email:$('.email').val(),
				hobby:$('.hobby').val(),
				health:$('.health').val(),
				familyphone:$('.familyphone').val(),
				address:$('.address').val(),
				workplace:$('.workplace').val(),
				armyresume:$('.armyresume').val(),
				reward:$('.reward').val(),
				selfevaluation:$('.selfevaluation').val(),
				inarmy:$('.inarmy').val(),
				outarmy:$('.outarmy').val(),
				wechat:$('.wechat').val(),
				alipay:$('.alipay').val(),
				armyjob:$('.armyjob').val(),
				payphone:$('.payphone').val(),
				idcard:$('.idcard').val(),
				locationid:$('.area').val(),
				locationids:billtaskstandardids
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
	
	function queyLocation(id,level,e,div){
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : "/stb-web/parametric/locationlist.do",//请求的uri
			data : {id:id,level:level},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = "<select class='form-control col-sm-6 "+e+" selectpicker'><option value='0'>请选择</option>";
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				content+="</select>";
				$(div).html(content);
				$("."+e).selectpicker('refresh');
			},
			error : function() {
				alert('系统出现异常，请稍微再试!');
			}
		});
	}
	
	$(document).on('change','.province',function(){
		queyLocation($('.province').val(),1,'city','.citydiv');
	});
	function queyArea(id,level,e,div){
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : "/stb-web/parametric/locationlist.do",//请求的uri
			data : {id:id,level:level},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = '<select class="form-control col-sm-6 area selectpicker" multiple>';
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				content+="</select>";
				$(div).html(content);
				$("."+e).selectpicker({noneSelectedText:'请选择',actionsBox:true});
			},
			error : function() {
				alert('系统出现异常，请稍微再试!');
			}
		});
	}
	$(document).on('change','.city',function(){
		queyArea($('.city').val(),2,'area','.areadiv');
	});
	
	$(function() {
		queyLocation(0,0,'province','.provincediv');
		queyLocation($('#provinceid').val(),1,'city','.citydiv');
		queyArea($('#cityid').val(),2,'area','.areadiv');
		$('.province').val($('#provinceid').val());
		$('.province').selectpicker('refresh');
		$('.city').val($('#cityid').val());
		$('.city').selectpicker('refresh');
		var billtaskstandardid_array = $('#areaid').val();
		var arr=billtaskstandardid_array.split(' ');
	    $('.area').selectpicker('val', arr);
	    $('.area').selectpicker({noneSelectedText:'请选择'});
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
	            name: {
	            	group: '.col-md-6',
	                validators: {
	                    notEmpty: {
	                        message: '师傅姓名不能为空'
	                    }
	                }
	            },
/* 	            origin: {
	            	group: '.col-md-6',
	                validators: {
	                    notEmpty: {
	                        message: '籍贯不能为空'
	                    }
	                }
	            }, */
	            teamname: {
	            	group: '.col-md-6',
	                validators: {
	                    notEmpty: {
	                        message: '队伍名称不能为空'
	                    }
	                }
	            },
	            payphone: {
	            	group: '.col-md-6',
	                validators: {
	                    notEmpty: {
	                        message: '手机号不能为空'
	                    },
	                    regexp: {
	                        regexp: /^1[3|4|5|7|8]\d{9}$/,
	                        message: '请输入正确的手机号'
	                    }
	                }
	            },
	            familyphone: {
	            	group: '.col-md-6',
	                validators: {
	                    regexp: {
	                        regexp: /^1[3|4|5|7|8]\d{9}$/,
	                        message: '请输入正确的手机号'
	                    }
	                }
	            },
	            idcard: {
	            	group: '.col-md-6',
	                validators: {
/* 	                    notEmpty: {
	                        message: '身份证号不能为空'
	                    }, */
	                    regexp: {
	                        regexp: /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/,
	                        message: '请输入正确的身份证号'
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
