var CommonFunction =  {
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
	IsChinese :function (str){
		if(str.length!=0){ 
			reg=/^[\u0391-\uFFE5]+$/; 
			if(!reg.test(str)){ 
				// alert("对不起，您输入的字符串类型格式不正确!"); 
			return false; 
			} 
		} 
		return true;
	},
	isEmpty:function (str){
		if(str==null||typeof str=="undefined"||str.trim()==""){ 
			return true; 
		}else{ 
			return false; 
		} 
	}
}

/**
 * 将时间戳转换成日期格式
 * @param format 日期格式 如'Y-m-d H:i:s'  ，'Y-m-d'  ，'H:i:s'
 * @param timestamp  时间戳
 * @returns
 */
function ToDate(format, timestamp) {
	var a, jsdate = ((timestamp) ? new Date(timestamp * 1000) : new Date());
	var pad = function(n, c) {
		if ((n = n + "").length < c) {
			return new Array(++c - n.length).join("0") + n;
		} else {
			return n;
		}
	};
	var txt_weekdays = [ "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" ];
	var txt_ordin = {
		1 : "st",
		2 : "nd",
		3 : "rd",
		21 : "st",
		22 : "nd",
		23 : "rd",
		31 : "st"
	};
	var txt_months = [ "", "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" ];
	var f = {
		// Day
		d : function() {
			return pad(f.j(), 2);
		},
		D : function() {
			return f.l().substr(0, 3);
		},
		j : function() {
			return jsdate.getDate();
		},
		l : function() {
			return txt_weekdays[f.w()];
		},
		N : function() {
			return f.w() + 1;
		},
		S : function() {
			return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th';
		},
		w : function() {
			return jsdate.getDay();
		},
		z : function() {
			return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0;
		},
		// Week
		W : function() {
			var a = f.z(), b = 364 + f.L() - a;
			var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
			if (b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b) {
				return 1;
			} else {
				if (a <= 2 && nd >= 4 && a >= (6 - nd)) {
					nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
					return date("W", Math.round(nd2.getTime() / 1000));
				} else {
					return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
				}
			}
		},
		// Month
		F : function() {
			return txt_months[f.n()];
		},
		m : function() {
			return pad(f.n(), 2);
		},
		M : function() {
			return f.F().substr(0, 3);
		},
		n : function() {
			return jsdate.getMonth() + 1;
		},
		t : function() {
			var n;
			if ((n = jsdate.getMonth() + 1) == 2) {
				return 28 + f.L();
			} else {
				if (n & 1 && n < 8 || !(n & 1) && n > 7) {
					return 31;
				} else {
					return 30;
				}
			}
		},
		// Year
		L : function() {
			var y = f.Y();
			return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0;
		},
		// o not supported yet
		Y : function() {
			return jsdate.getFullYear();
		},
		y : function() {
			return (jsdate.getFullYear() + "").slice(2);
		},
		// Time
		a : function() {
			return jsdate.getHours() > 11 ? "pm" : "am";
		},
		A : function() {
			return f.a().toUpperCase();
		},
		B : function() {
			// peter paul koch:
			var off = (jsdate.getTimezoneOffset() + 60) * 60;
			var theSeconds = (jsdate.getHours() * 3600)
					+ (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
			var beat = Math.floor(theSeconds / 86.4);
			if (beat > 1000)
				beat -= 1000;
			if (beat < 0)
				beat += 1000;
			if ((String(beat)).length == 1)
				beat = "00" + beat;
			if ((String(beat)).length == 2)
				beat = "0" + beat;
			return beat;
		},
		g : function() {
			return jsdate.getHours() % 12 || 12;
		},
		G : function() {
			return jsdate.getHours();
		},
		h : function() {
			return pad(f.g(), 2);
		},
		H : function() {
			return pad(jsdate.getHours(), 2);
		},
		i : function() {
			return pad(jsdate.getMinutes(), 2);
		},
		s : function() {
			return pad(jsdate.getSeconds(), 2);
		},
		// u not supported yet
		// Timezone
		// e not supported yet
		// I not supported yet
		O : function() {
			var t = pad(Math.abs(jsdate.getTimezoneOffset() / 60 * 100), 4);
			if (jsdate.getTimezoneOffset() > 0)
				t = "-" + t;
			else
				t = "+" + t;
			return t;
		},
		P : function() {
			var O = f.O();
			return (O.substr(0, 3) + ":" + O.substr(3, 2));
		},
		// T not supported yet
		// Z not supported yet
		// Full Date/Time
		c : function() {
			return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":"
					+ f.i() + ":" + f.s() + f.P();
		},
		// r not supported yet
		U : function() {
			return Math.round(jsdate.getTime() / 1000);
		}
	};
	return format.replace(/[\\]?([a-zA-Z])/g, function(t, s) {
		if (t != s) {
			// escaped
			ret = s;
		} else if (f[s]) {
			// a date function exists
			ret = f[s]();
		} else {
			// nothing special
			ret = s;
		}
		return ret;
	});
}
/**
 * 将当前时间转成时间戳
 * @param day  日期
 */
/*function getTime(day){ 
	alert("www");
	re = /(\d{4})(?:-(\d{1,2})(?:-(\d{1,2}))?)?(?:\s+(\d{1,2}):(\d{1,2}):(\d{1,2}))?/.exec(day); 
	return new Date(re[1],(re[2]||1)-1,re[3]||1,re[4]||0,re[5]||0,re[6]||0).getTime()/1000; 
} */