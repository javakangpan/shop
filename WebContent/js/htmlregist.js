
function $(id) {
	return document.getElementById(id);
}


function radioValue() {
	var a = $("sex1");
	var b = $("sex2");
	if(a.checked) {
		return a.value;
	}
	if(b.checked) {
		return b.value;
	}

}


//-----------------------------------------------------------------------------------------------------------验证---------------------------------------------------

function T(o, c) {
	return "<span style='color:" + c + "'>" + o + "</span>";
}

//-----------------------------------------------------------------------------------------------用户输入框31
//用户输入框  根据键盘松开事件 当输入框内容不为空的时候 判断输入的内容
function fun31() {

	var a = $("userName").value;
	var aa = $("userNameSpan");
	var b = /^[a-zA-Z0-9]{3,15}$/;
	//	if($("userName").value != "") {
	if(b.test(a)) {
		aa.innerHTML = T("输入正确", "green");
		return true;
	} else {
		aa.innerHTML = T("输入错误", "red");
		return false;
	}
	//	}

}
//用户名输入框  根据输入框失去光标焦点 当输入内容不符合条件并且内容不为空的时候 提示信息
function fun32() {
	if(!fun31() && $("userName").value != "") {
		$("userNameSpan").innerHTML = T("必须是英文字母或数字，长度3-15", "red");
	}
	if($("userName").value == "") {
		$("userNameSpan").innerHTML = T("必须是英文字母或数字，长度3-15", "");
	}

}
//用户名输入框  当得到光标焦点的时候 调用函数判断并提示信息
function fun33() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("userNameSpan").innerHTML = T("请输入");

}
//用户名输入框  当鼠标移出 如果内容为空就提示
function fun34() {
	if($("userName").value == "") {
		$("userNameSpan").innerHTML = T("必须是英文字母或数字，长度3-15", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------密码输入框 41
//密码输入框 根据键盘松开事件 当输入框内容不为空的时候 判断输入的内容 与密码确认输入框同步
function fun41() {

	var a = $("pwd1").value;
	var aa = $("pwdSpan1");
	var b = /^.{3,15}$/;
	//	if($("pwd1").value != "") {

	if(b.test(a)) {
		aa.innerHTML = T("输入正确", "green");
		
		if($("pwd2").value != "" && $("pwd1").value == $("pwd2").value) {
			$("pwdSpan2").innerHTML = T("输入正确", "green");
		} else if($("pwd2").value != "" && $("pwd1").value != $("pwd2").value) {
			$("pwdSpan2").innerHTML = T("输入错误", "red");
		}
		return true;
	} else {
		aa.innerHTML = T("输入错误", "red");
		return false;
	}

	//	}

}
//密码输入框得到焦点
function fun44() {
	$("pwdSpan1").innerHTML = T("请输入", "");
}
//密码输入框 鼠标移动 事件
function fun45() {
	if($("pwd1").value != "" && ($("pwd2").value != "")) {
		fun52();
	} else if($("pwd1").value == "" && ($("pwd2").value != "")) {
		$("pwdSpan2").innerHTML = T("密码不一致", "red");
	}

}

//密码输入框  失去焦点 当输入内容不符合条件并且内容不为空的时候 提示信息长度3-15 并调用函数判断两个输入框的内容
function fun42() {
	if(!fun41() && $("pwd1").value != "") {
		$("pwdSpan1").innerHTML = T("长度3-15", "red");
	}
	fun52();
	if($("pwd1").value == "") {
		$("pwdSpan1").innerHTML = T("长度3-15", "");
	}
}
//密码输入框 当鼠标移出 如果内容为空就提示长度3-15
function fun43() {
	if($("pwd1").value == "") {
		$("pwdSpan1").innerHTML = T("长度3-15", "");
	}
}

//--------------------------------------------------------------------------------------------------------------------------------------------密码确认输入框
//密码确认输入框  得到焦点
function fun51() {
	if($("pwd1").value != $("pwd2").value) {
		$("pwdSpan2").innerHTML = T("密码不一致", "red");
	} else {

		$("pwdSpan2").innerHTML = T("请输入", "");
	}

}
//密码确认输入框  失去焦点  在密码确认输入框不为空的情况下   判断密码输入框和密码确认输入框的内容是否相等并且密码输入框的内容是否符合条件  条件都成立则提示输入正确 
//否则 如果密码输入框内容为空或者两个输入框的内容不一样或者密码输入框的内容不符合条件 则提示输入错误
function fun52() {
	if($("pwd2").value != "") {
		if($("pwd1").value == $("pwd2").value && fun41()) {
			$("pwdSpan2").innerHTML = T("输入正确", "green");

		} else if($("pwd1").value == "" || $("pwd1").value != $("pwd2").value || !fun41()) {
			$("pwdSpan2").innerHTML = T("输入错误", "red");
		}
	} else {
		$("pwdSpan2").innerHTML = T("值要和密码框的值相同", "darkgray");
	}

}
//密码确认输入框 键盘松开  当密码输入框和当前输入框内容不一致时提示密码不一致  当内容一样且密码输入框满足条件 则提示输入正确
function fun53() {
	if($("pwd1").value != $("pwd2").value) {

		$("pwdSpan2").innerHTML = T("密码不一致", "red");
		return false;

	} else if($("pwd1").value != "" && $("pwd1").value == $("pwd2").value && fun41()) {

		$("pwdSpan2").innerHTML = T("输入正确", "green");
		return true;

	}
}
//确认密码鼠标移出
function fun54() {
	if($("pwd2").value == "") {
		$("pwdSpan2").innerHTML = T("值要和密码框的值相同", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------单选按钮

//单选按钮 判断两个密码输入框的内容是否为空，不为空就调用函数    和     根据单选按钮被选中提示信息
function fun61() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	if(!$("sex1").checked) {
		$("sexSpan").innerHTML = T("女生", "green");
	} else {
		$("sexSpan").innerHTML = T("男生", "green");
	}

}

//--------------------------------------------------------------------------------------------------------------------------------------------真实姓名输入框 

//真实姓名输入框     键盘松开事件
function fun71() {
	var a = /^[\u4e00-\u9FA5]{2,10}$/;
	//	if($("trueName").value != "") {
	if(a.test($("trueName").value)) {
		$("trueNameSpan").innerHTML = T("输入正确", "green");

		return true;
	} else {
		$("trueNameSpan").innerHTML = T("输入错误", "red");
		return false;
	}
	//	}

}
//真实姓名输入框  失去焦点   内容不为空并且不符合正则表达式条件  提示用户信息
function fun72() {
	if(!fun71() && $("trueName").value != "") {
		$("trueNameSpan").innerHTML = T("中文,2-10个字符", "red");
	}
	if($("trueName").value == "")
		$("trueNameSpan").innerHTML = T("中文,2-10个字符", "");
}
// 真实姓名输入框  得到焦点 判断两个密码框是否为空不为空调用函数
function fun73() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("trueNameSpan").innerHTML = T("请输入", "");

}
//真实姓名输入框  鼠标移出 内容为空提示信息
function fun74() {
	if($("trueName").value == "") {
		$("trueNameSpan").innerHTML = T("中文,2-10个字符", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------出生日期
//出生日期   键盘弹起事件 
function fun81() {

	var reg = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
	var msg = $("birthDay").value;

	if(reg.test(msg)) {
		$("birthDaySpan").innerHTML = T("输入正确", "green");
		return true;
	} else {

		$("birthDaySpan").innerHTML = T("输入错误", "red");
		return false;
	}
}
//出生日期得到焦点
function fun82() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("birthDaySpan").innerHTML = T("请输入", "");
}

//出生日期失去焦点
function fun83() {
	if(!fun81() && $("birthDay").value != "") {
		$("birthDaySpan").innerHTML = T("格式yyyy-dd,按此日期算出的年龄应大于等于10岁", "red");
	}
	if($("birthDay").value == "")
		$("birthDaySpan").innerHTML = T("格式yyyy-dd,按此日期算出的年龄应大于等于10岁", "");
}

//出生日期鼠标移出
function fun84() {
	if($("birthDay").value == "") {
		$("birthDaySpan").innerHTML = T("格式yyyy-dd,按此日期算出的年龄应大于等于10岁", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------电子邮箱 
//电子邮箱   键盘弹起事件 
function fun91() {

	var reg = /^[0-9a-zA-Z_]{1,10}@[0-9a-zA-Z]{2,6}\.(com)|(cn) $/;
	var msg = $("email").value;

	if(reg.test(msg)) {
		$("emailSpan").innerHTML = T("输入正确", "green");
		return true;
	} else {

		$("emailSpan").innerHTML = T("输入错误", "red");
		return false;
	}
}
//电子邮箱 得到焦点
function fun92() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("emailSpan").innerHTML = T("请输入", "");
}

//电子邮箱 失去焦点
function fun93() {
	if(!fun91() && $("email").value != "") {
		$("emailSpan").innerHTML = T("格式要正确", "red");
	}
	if($("email").value == "")
		$("emailSpan").innerHTML = T("格式要正确", "");
}

//电子邮箱 鼠标移出
function fun94() {
	if($("email").value == "") {
		$("emailSpan").innerHTML = T("格式要正确", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------电话号码
//电话号码 键盘松开事件
function fun101() {
	var reg = /^0?(13|14|15|18)[0-9]{9}$/;
	var msg = $("phone").value;

	if(reg.test(msg)) {
		$("phoneSpan").innerHTML = T("输入正确", "green");
		return true;
	} else {

		$("phoneSpan").innerHTML = T("输入错误", "red");
		return false;
	}
}

//电话号码 输入框得到焦点事件
function fun102() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("phoneSpan").innerHTML = T("请输入", "");
}
//电话号码 失去焦点事件

function fun103() {
	if(!fun101() && $("phone").value != "") {
		$("phoneSpan").innerHTML = T("必须是数字", "red");
	}
	if($("phone").value == "")
		$("phoneSpan").innerHTML = T("必须是数字", "");
}
//电话号码鼠标移出事件
function fun104() {
	if($("phone").value == "") {
		$("phoneSpan").innerHTML = T("必须是数字", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------地址
//地址键盘松开事件
function fun201() {
	var reg = /^[\u4e00-\u9FA5]{1,100}$/;
	var msg = $("adresse").value;

	if(reg.test(msg)) {
		$("adresseSpan").innerHTML = T("输入正确", "green");
		return true;
	} else {

		$("adresseSpan").innerHTML = T("输入错误", "red");
		return false;
	}
}

//地址  输入框得到焦点事件
function fun202() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("adresseSpan").innerHTML = T("请输入", "");
}
//地址失去焦点事件
function fun203() {
	if(!fun201() && $("adresse").value != "") {
		$("adresseSpan").innerHTML = T("长度不能大于100", "red");
	}
	if($("adresse").value == "")
		$("adresseSpan").innerHTML = T("长度不能大于100", "");
}
//地址鼠标移出事件
function fun204() {
	if($("adresse").value == "") {
		$("adresseSpan").innerHTML = T("长度不能大于100", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------邮编
//邮编 键盘松开事件
function fun301() {
	var reg = /^[0-9]{6}$/;
	var msg = $("postal").value;

	if(reg.test(msg)) {
		$("postalSpan").innerHTML = T("输入正确", "green");
		return true;
	} else {

		$("postalSpan").innerHTML = T("输入错误", "red");
		return false;
	}
}

//邮编  输入框得到焦点事件
function fun302() {
	if($("pwd1").value != "" && ("pwd2").value != "") {
		fun52();
	}
	$("postalSpan").innerHTML = T("请输入", "");
}
//邮编 失去焦点事件
function fun303() {
	if(!fun301() && $("postal").value != "") {
		$("postalSpan").innerHTML = T("6位数字", "red");
	}
	if($("postal").value == "")
		$("postalSpan").innerHTML = T("6位数字", "");
}
//邮编鼠标移出事件
function fun304() {
	if($("postal").value == "") {
		$("postalSpan").innerHTML = T("6位数字", "");
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------表单提交
function form() {

	if(fun53() && fun31() && fun41() && fun71() && fun81 && fun91() && fun101 && fun201() && fun301()) {
		return true;
	} else {
		return false;

	}

}