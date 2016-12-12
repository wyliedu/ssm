var IndexFunction =  {
		//e元素；param为查询人参json格式；columns为字段数组
		 dataTable :function (e,url,columns,param){
			$(e).dataTable({
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
					url: url,
					dataType: "json",
					dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
					data:function(d){
						var page = {};//因为服务端排序，可以新建一个参数对象
						page.start = d.start;//开始的序号
						page.length = d.length;//要取的数据的
		                var c = $.extend({}, page,param);
		                return c;//自定义需要传递的参数。
					}
			    } ,
			    "columnDefs": [ {
		            "searchable": false,
		            "orderable": false,
		            "targets": 0
		        } ],
			    "order": [[ 1, 'asc' ]],
			    showRowNumber:true,
			    columns: columns ,
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
		},
	//角色下拉框：e元素；
	showRoleList :function (e,url){
		$.ajax({
			type : "post",//不写此参数默认为get方式提交
			async : false, //设置为同步
			url : url,//请求的uri
			data : {},//传递到后台的参数				
			cache : false,
			dataType : 'json',//后台返回前台的数据格式为json
			success : function(data) {
				var content = "";
				for (var i = 0; i < data.length; i++) {
					content += "<option value='"+data[i].roleid+"'>"
							+ data[i].name + "</option>";
				}
				$(e).html(content);
				$(e).selectpicker('refresh');
			},
			error : function(data) {
				alert('系统出现异常，请稍微再试!');
			}
		});
	},
}

jQuery.validator.addMethod("SerialCheck", function(value, element) {      
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);      
}, "");
// usernamecheck 用户名校验
jQuery.validator.addMethod("usernamecheck", function(value, element) {      
    return this.optional(element) || /^[a-zA-Z_][a-zA-Z0-9_]*$/.test(value);      
}, "必须为字母、数字或下划线，不能以数字开头");
//passwordcheck 密码校验
jQuery.validator.addMethod("passwordcheck", function(value, element) {      
    return this.optional(element) || /^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[0-9a-zA-Z\W]\S{6,18}$/.test(value);      
}, "密码应包含字母、数字、符号中的至少2种");
jQuery.validator.addMethod("phone", function(value, element) {   
  var tel = /^1[3-8]\d{9}$/;
  return this.optional(element) || (tel.test(value));
}, "请输入正确的手机号码");
//中文两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {      
	var length = value.length;      
for(var i = 0; i < value.length; i++){      
if(value.charCodeAt(i) > 127){      
length++;      
}      
	}      
return this.optional(element) || ( length >= param[0] && length <= param[1] );      
	}, "不得多余16字");