<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../base/taglib.jsp"%>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>121师徒帮</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css" />
<%@ include file="../base/importCss.jsp"%>
<%@ include file="../base/importJs.jsp"%>
<script src="http://webapi.amap.com/maps?v=1.3&key=719a4823ac1230f35bb05aa020617e55"></script>
<style type="text/css">
#page-inner {
    width: 100%;
    margin: 10px 20px 10px 0px;
    background-color: #fff!important;
    padding: 10px;
    min-height: 1200px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<%@ include file="../base/pageHeader.jsp"%>

		<%@ include file="../base/sidebarMenu.jsp"%>
		<input type="hidden" value="${bill.billid}" id="billid">
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="panel panel-info">
					<div class="panel-heading">工单信息：
							<button class="btn-primary pull-right" id="addBill">保存</button><div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
							<button class="btn-info pull-right" onclick="history.go(-1)">返回</button>
							<div class="pull-right">&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div class="panel-body">
						<form id="defaultForm" class="form-horizontal">
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">客户名称：</label>
								<div class="col-md-6">
									<label class="control-label">${bill.contractorname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">联系人：</label>
								<div class="col-sm-6">
								<label class="control-label">${bill.picname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-4 control-label">联系人联系方式：</label>
								<div class="col-md-6">
									<label class="control-label col-md-3" id="payphone"
										name="payphone">${bill.picpayphone}
									</label>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">施工组织：</label>
								<div class="col-md-6">
									<label class="control-label">${bill.teamname}</label>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-md-5 control-label">最晚联系用户的时间：</label>
								<div class="col-md-6">
									<input class="span2" id="pnegotiationtime" value="${bill.pnegotiationDatetime}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">任务类型：</label>
								<div class="col-sm-6">
								<select class="form-control col-sm-6 billtype" name="billtype">
									<option value=''>请选择</option>
								</select>
								<input type="hidden" value="${bill.billtype}" id="billtype">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-sm-4 control-label">施工项目类别：</label>
								<div class="col-sm-8">
								<select class="form-control col-md-12 standard selectpicker" multiple></select>
								<input type="hidden" value="${bill.billtaskstandardid_array}" id="billtaskstandardid_array">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">需要人数：</label>
								<div class="col-sm-6">
								         <input type="text" class="form-control personnum" value="${bill.new_personnum}" name="personnum" placeholder="个">
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">预计完成天数：</label>
								<div class="col-sm-6">
								         <input type="text" class="form-control period" value="${bill.new_period}" name="period" placeholder="天">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-4">
								<label class="col-md-4 control-label">项目开始时间：</label>
								<div class="col-md-6">
									<input class="span2 datepicker" id="starttime" value="${bill.startDate}" type="text" style="border:solid 1px #778899"/>
									<span class="add-on"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-4">
								<label class="col-sm-4 control-label">施工地址：</label>
								<div class="col-sm-8">
									<textarea class="form-control col-sm-1 disableInfo" rows="6"
										placeholder="施工地址不能超过64个字符" id="billaddress"
										name="billaddress">${bill.billaddress}</textarea>
								</div>
							</div>
														<div class="col-md-4">
								<label class="col-sm-4 control-label">施工描述：</label>
								<div class="col-sm-8">
									<textarea class="form-control col-sm-1 disableInfo" rows="6"
										placeholder="施工描述不能超过64个字符" id="billdes"
										name="billdes">${bill.new_memo}</textarea>
								</div>
							</div>
						</div>
					</form>
					</div>
					<div class="panel-heading">
			施工位置地图定位（定位后的坐标可拖动图标进行修改）
		</div>
						<div class="panel-body" style="position: absolute;width:1500px;height:680px;">
					<div id="mapContainer" ></div>
			<input type="hidden" value="${bill.billlocation}" id="billlocation">
				</div>
				</div>
			</div>
		</div>
		<%@ include file="../base/pageFooter.jsp"%>
</body>
<script type="text/javascript">
 var marker = null;    //地图位置标记
var communityLocation = $('#billlocation').val();
$(function() {
	var map = new AMap.Map('mapContainer');
	if(communityLocation!=null&&communityLocation!=""&&communityLocation!="null"){
		 // 设置缩放级别和中心点
	    map.setZoomAndCenter(16, communityLocation.split(","));
	    // 添加 marker 并设置中心点
	    marker = new AMap.Marker({
	      map: map,
	      position: communityLocation.split(",")
	    }); 
	    marker.setDraggable(true);
	}

	map.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale","AMap.MapType","AMap.PolyEditor","AMap.MouseTool"],function(){
		  //加载工具条
		tool = new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		});
		map.addControl(tool);
		//加载鹰眼
		view = new AMap.OverView();
		map.addControl(view);
		//加载比例尺
		scale = new AMap.Scale();
		map.addControl(scale);
		var type = new AMap.MapType({
			defaultType: 0
		});
		map.addControl(type);
	});
	
	map.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
	     var autoOptions = {
	          city: "", //城市，默认全国
	          input: "keyword"//使用联想输入的input的id
	     };
	     autocomplete= new AMap.Autocomplete(autoOptions); 
	     var placeSearch = new AMap.PlaceSearch({
	    	 pageSize: 5,
	         pageIndex: 1,
	         city:''
	         //map:map
	 });
	     $("#billaddress").focusout(function(){
	    placeSearch.search($('#billaddress').val(), function(status,result){
	 			if(result.poiList && result.poiList.pois && result.poiList.pois.length){
	 				var poi = result.poiList.pois[0];
	 				if(!marker){
	 					marker = new AMap.Marker();
	 				}
	 				map.setZoomAndCenter(13, poi.location);
	 				marker.setTitle([poi.name, poi.address].join(poi.name && poi.address ? "\n" : ""));
	 				marker.setPosition(poi.location);
	 				marker.setDraggable(true);
	 				marker.setMap(map);
	 			}
	 			else{
	 				alert("未能定位到地图坐标，请确认施工地址！！！");
	 			}
	 		})
	 });
	});
}); 
	function showStandardList() {
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : "/stb-web/bill/standardlist.do",//请求的uri
			data : {},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = "";
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].standardid+"'>"
							+ data[i].name + "</option>";
				}
				$('.standard').html(content);
				$('.standard').selectpicker({noneSelectedText:'请选择'});
				var billtaskstandardid_array = $('#billtaskstandardid_array').val();
				var arr=billtaskstandardid_array.split(',');
			    $('.standard').selectpicker('val', arr);
			},
			error : function() {
				alert('系统出现异常，请稍微再试!');
			}
		});
	}
	
	function showBilltypelist() {
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : "/stb-web/bill/billtypelist.do",//请求的uri
			data : {},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = "";
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].paramvalue+"'>"
							+ data[i].memo + "</option>";
				}
				$('.billtype').html(content);
				$('.billtype').val($('#billtype').val());
				$('.billtype').selectpicker('refresh');
			},
			error : function() {
				alert('系统出现异常，请稍微再试!');
			}
		});
	}
	
	//修改社区基本资料
	$(document).on('click','#addBill',function(){
		if($('#employee').val()==""){
			alert("请选择联系人!");
			return flase;
		}
		if($('#pnegotiationtime').val()==""){
			alert("最晚沟通承包商时间不能为空!");
			return flase;
		}
		if($('.standard').val()==null){
			alert("请选择施工项目类别!");
			return flase;
		}
		if($('#starttime').val()==""){
			alert("项目开始时间不能为空!");
			return flase;
		}
    	if(marker==null){
    		alert("施工位置未设置!");
    		return false;
    	} 
		$('#defaultForm').bootstrapValidator('validate');
		if(!$('#defaultForm').data('bootstrapValidator').isValid()){
			return false;
		}
		var billtaskstandardid_array = $('.standard').val();
		var billtaskstandardids = ""
		for(var i=0;i<billtaskstandardid_array.length;i++){
			billtaskstandardids += billtaskstandardid_array[i]+" ";
		}
		$.ajax( {
			type:"post",//不写此参数默认为get方式提交
			async:false,   //设置为同步
			url : "/stb-web/bill/updateBill.do",//请求的uri
			data : {
				billdes:$('#billdes').val(),
				billaddress:$('#billaddress').val(),
				billlocation:marker.getPosition().toString(),
				pnegotiationtime:$('#pnegotiationtime').val(),
				billtaskstandardid_array:billtaskstandardids,
				billtype:$('.billtype').val(),
				personnum:$('.personnum').val(),
				starttime:$('#starttime').val(),
				period:$('.period').val(),
				billid:$('#billid').val()
				},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				if(data.code!=0){
					alert(data.message);
				}else{
					alert("修改成功!");
				}
				
			},error: function () {
	            alert('系统出现异常，请稍微再试!');
	        }});
	});
	
	$(function() {
		showStandardList();
		showBilltypelist();
		$('#starttime').datepicker({
			format:"yyyy-mm-dd",
			language: "zh-CN",
			todayBtn: "linked",
			todayHighlight: true,
			autoclose: true,
			startDate:new Date()
		});
		$('#pnegotiationtime').datetimepicker({
			language:  'zh-CN',
		    format: 'yyyy-mm-dd hh:ii:ss'
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
	        	billdes: {
	            	group: '.col-sm-8',
	                validators: {
	                    notEmpty: {
	                        message: '施工描述不能为空'
	                    }
	                }
	            },
	            billaddress: {
	            	group: '.col-sm-8',
	                validators: {
	                    notEmpty: {
	                        message: '施工地址不能为空'
	                    }
	                }
	            },
	            personnum: {
	            	group: '.col-sm-6',
	                validators: {
	                    notEmpty: {
	                        message: '不能为空'
	                    },
	                    regexp: {
	                        regexp: /^[1-9]+[0-9]*]*$/,
	                        message: '请输入正整数'
	                    }
	                }
	            },
	            period: {
	            	group: '.col-sm-6',
	                validators: {
	                    notEmpty: {
	                        message: '不能为空'
	                    },
	                    regexp: {
	                        regexp: /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,
	                        message: '请输入正整数或者小数'
	                    }
	                }
	            }
	        }
	    });
	});
</script>
</html>
