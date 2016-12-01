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
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-head-line">${requestScope.permissionMenu.rootName}/${requestScope.permissionMenu.subName}</h1>
					</div>
				</div>
				<div class="panel panel-info">
				
					<div class="panel-heading">师傅信息：</div>
					<div class="panel-body">
						<form id="defaultForm" class="form-horizontal">
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">师傅姓名：</label>
								<div class="col-md-6">
									<input type="text" class="form-control name" name="name">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">性别：</label>
								<div class="col-md-6">
								<select class="form-control col-sm-6 gender">
									<option value=''>请选择</option>
								</select>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">民族：</label>
								<div class="col-md-6">
								<select class="form-control col-sm-6 nation" >
									<option value=''>请选择</option>
								</select>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">籍贯：</label>
								<div class="col-md-6">
									<input type="text" class="form-control origin" name="origin">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">出生年月：</label>
								<div class="col-md-6">
									<input class="span2 dateselect birthdate" name="birthdate" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">政治面貌：</label>
								<div class="col-md-6">
								<select class="form-control col-sm-6 politicalstatus">
									<option value=''>请选择</option>
								</select>
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
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">城市：</label>
								<div class="col-md-6 citydiv">
									<select class="form-control col-sm-6 city"><option value='0'>请选择</option></select>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">区：</label>
								<div class="col-md-6 areadiv" >
								<select class="form-control col-sm-6 area selectpicker" multiple><option value='0'>请选择</option></select>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">手机：</label>
								<div class="col-md-6">
								<input type="text" class="form-control payphone" name="payphone">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">QQ号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control qq" name="qq">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">邮 箱：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control email" name="email">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">微信号：</label>
								<div class="col-md-6">
								<input type="text" class="form-control wechat" name="wechat">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">支付宝账号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control alipay" name="alipay">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍前职务：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control armyjob" name="armyjob">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">入伍时间：</label>
								<div class="col-md-6">
									<input class="span2 dateselect inarmy" name="inarmy" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍时间：</label>
								<div class="col-md-6">
									<input class="span2 dateselect outarmy" name="outarmy" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">退伍前军衔：</label>
								<div class="col-md-6">
									<input type="text" class="form-control armyrank" name="armyrank">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">爱好与特长：</label>
								<div class="col-md-8">
								<input type="text" class="form-control hobby" name="hobby">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身体状况：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control health" name="health">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">家人电话：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control familyphone" name="familyphone">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">初次学历：</label>
								<div class="col-sm-6">
								<select class="form-control col-md-12 education firsteducation"></select>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">现有学历：</label>
								<div class="col-sm-6">
								      <select class="form-control education currenteducation" ></select>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">身份证号：</label>
								<div class="col-md-6">
								         <input type="text" class="form-control idcard" name="idcard">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">队伍名称：</label>
								<div class="col-md-6">
									<input type="text" class="form-control teamname" name="teamname">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-3 control-label">现家庭住址：</label>
								<div class="col-sm-9">
								<input type="text" class="form-control address" name="address">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">工作地点：</label>
								<div class="col-sm-6">
									<select class="form-control workplace" name="workplace"></select>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">入伍后简历及担任职务：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 armyresume" rows="4"
										  name="armyresume"></textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">何时何因受何奖励：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 reward" rows="4"
										 name="reward"></textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">自我评价：</label>
								<div class="col-sm-10">
									<textarea class="form-control col-sm-1 selfevaluation" rows="4"
										 name="selfevaluation"></textarea>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="panel-body">
					
						<button class="btn-success center-block" id="addBill">新增师傅</button>
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
/* 		if($('.birthdate').val()==""){
			alert("出生日期不能为空");
			return false;
		} */
		
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
			url : "/stb-web/senior/addSenior.do",//请求的uri
			data : {payphone:$('.payphone').val(),
				name:$('.name').val(),
				teamname:$('.teamname').val(),
				nation:$('.nation').val(),
				gender:$('.gender').val(),
				politicalstatus:$('.politicalstatus').val(),
				armyrank:$('.armyrank').val(),
				firsteducation:$('.firsteducation').val(),
				currenteducation:$('.currenteducation').val(),
				origin:$('.origin').val(),
				qq:$('.qq').val(),
				email:$('.email').val(),
				hobby:$('.hobby').val(),
				health:$('.health').val(),
				familyphone:$('.familyphone').val(),
				idcard:$('.idcard').val(),
				address:$('.address').val(),
				workplace:$('.workplace').val(),
				armyresume:$('.armyresume').val(),
				reward:$('.reward').val(),
				selfevaluation:$('.selfevaluation').val(),
				birthdate:$('.birthdate').val(),
				inarmy:$('.inarmy').val(),
				outarmy:$('.outarmy').val(),
				wechat:$('.wechat').val(),
				alipay:$('.alipay').val(),
				armyjob:$('.armyjob').val(),
				locationids:billtaskstandardids
				},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				if(data.code!=0){
					alert(data.message);
				}else{
					alert("新增成功!");
					$('#defaultForm')[0].reset();
					$('#defaultForm').data('bootstrapValidator').resetForm();
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
	
	$(document).on('change','.city',function(){
		//queyLocation($('.city').val(),2,'.area');
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : "/stb-web/parametric/locationlist.do",//请求的uri
			data : {id:$('.city').val(),level:2},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = '<select class="form-control col-sm-6 area selectpicker" multiple>';
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				content+="</select>";
				$('.areadiv').html(content);
				$('.area').selectpicker({noneSelectedText:'请选择',actionsBox:true});
			},
			error : function() {
				alert('系统出现异常，请稍微再试!');
			}
		});
	});
	
	$(function() {
		$('.area').selectpicker({noneSelectedText:'请选择'});
		queyLocation(0,0,'province','.provincediv');
			$.ajax({
				type : "post",//不写此参数默认为get方式提交
				async : false, //设置为同步
				url : "/stb-web/parametric/genderlist.do",//请求的uri
				data : {},//传递到后台的参数				
				cache : false,
				dataType : 'json',//后台返回前台的数据格式为json
				success : function(data) {
					var content = "";
					for (var i = 0; i < data.length; i++) {
						content += "<option value='"+data[i].paramvalue+"'>"
								+ data[i].memo + "</option>";
					}
					$('.gender').html(content);
					$('.gender').selectpicker('refresh');
				},
				error : function() {
					alert('系统出现异常，请稍微再试!');
				}
			});
		
			$.ajax({
				type : "post",//不写此参数默认为get方式提交
				async : false, //设置为同步
				url : "/stb-web/parametric/nationlist.do",//请求的uri
				data : {},//传递到后台的参数				
				cache : false,
				dataType : 'json',//后台返回前台的数据格式为json
				success : function(data) {
					var content = "";
					for (var i = 0; i < data.length; i++) {
						content += "<option value='"+data[i].paramvalue+"'>"
								+ data[i].memo + "</option>";
					}
					$('.nation').html(content);
					$('.nation').selectpicker('refresh');
				},
				error : function() {
					alert('系统出现异常，请稍微再试!');
				}
			});
			
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
			autoclose: true,
			bootcssVer:3
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
