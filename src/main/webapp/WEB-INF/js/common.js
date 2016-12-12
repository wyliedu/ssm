var CommonFunction =  {
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
	},
	// base64加密开始
	encode64:function(input) {
	    var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv" + "wxyz0123456789+/" + "=";
	    var output = "";
	    var chr1, chr2, chr3 = "";
	    var enc1, enc2, enc3, enc4 = "";
	    var i = 0;
	    input = ""+input;
	    while (i < input.length){
	            chr1 = input.charCodeAt(i++);
	            chr2 = input.charCodeAt(i++);
	            chr3 = input.charCodeAt(i++);
	            enc1 = chr1 >> 2;
	            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	            enc4 = chr3 & 63;
	            if (isNaN(chr2)) {
	                    enc3 = enc4 = 64;
	            } else if (isNaN(chr3)) {
	                    enc4 = 64;
	            }
	            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
	                            + keyStr.charAt(enc3) + keyStr.charAt(enc4);
	            chr1 = chr2 = chr3 = "";
	            enc1 = enc2 = enc3 = enc4 = "";
	    }
	    return output;
	},
	decode64 : function (input) {
		var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
		  var output = "";
		  var chr1, chr2, chr3;
		  var enc1, enc2, enc3, enc4;
		  var i = 0;
		  input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		  while (i < input.length) {
		   enc1 = _keyStr.indexOf(input.charAt(i++));
		   enc2 = _keyStr.indexOf(input.charAt(i++));
		   enc3 = _keyStr.indexOf(input.charAt(i++));
		   enc4 = _keyStr.indexOf(input.charAt(i++));
		   chr1 = (enc1 << 2) | (enc2 >> 4);
		   chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
		   chr3 = ((enc3 & 3) << 6) | enc4;
		   output = output + String.fromCharCode(chr1);
		   if (enc3 != 64) {
		    output = output + String.fromCharCode(chr2);
		   }
		   if (enc4 != 64) {
		    output = output + String.fromCharCode(chr3);
		   }
		  }
		  output = _utf8_decode(output);
		  return output;
		 },
		 // private method for UTF-8 encoding
		 utf8_encode : function (string) {
		  string = string.replace(/\r\n/g,"\n");
		  var utftext = "";
		  for (var n = 0; n < string.length; n++) {
		   var c = string.charCodeAt(n);
		   if (c < 128) {
		    utftext += String.fromCharCode(c);
		   } else if((c > 127) && (c < 2048)) {
		    utftext += String.fromCharCode((c >> 6) | 192);
		    utftext += String.fromCharCode((c & 63) | 128);
		   } else {
		    utftext += String.fromCharCode((c >> 12) | 224);
		    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
		    utftext += String.fromCharCode((c & 63) | 128);
		   }
		  }
		  return utftext;
		 },
		 // private method for UTF-8 decoding
		 utf8_decode : function (utftext) {
		  var string = "";
		  var i = 0;
		  var c = c1 = c2 = 0;
		  while ( i < utftext.length ) {
		   c = utftext.charCodeAt(i);
		   if (c < 128) {
		    string += String.fromCharCode(c);
		    i++;
		   } else if((c > 191) && (c < 224)) {
		    c2 = utftext.charCodeAt(i+1);
		    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
		    i += 2;
		   } else {
		    c2 = utftext.charCodeAt(i+1);
		    c3 = utftext.charCodeAt(i+2);
		    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
		    i += 3;
		   }
		  }
		  return string;
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