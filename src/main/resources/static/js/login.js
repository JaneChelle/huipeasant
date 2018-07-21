var code ;
function createCode(){ 
	code = new Array();
	var codeLength = 4;
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";
	var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
	for(var i=0;i<codeLength;i++) {
		var charIndex = Math.floor(Math.random()*32);
		code +=selectChar[charIndex];
	}
	checkCode.value = code;
}
createCode();
//function validate() {
//  var inputCode = document.getElementById("yzm").value.toUpperCase();
//  alert(inputCode);
//	if(inputCode != code ){
//		alert("验证码错误！");
//		return false;
//	}
//	else {
//		alert("验证码正确！");
//		return true;
//}
//登录
function disPword(){
    var aa=document.getElementById("ico").title;
    if(aa == "隐藏密码"){
        document.getElementById("password").type="password";
        document.getElementById("ico").title = "显示密码";
        $('#cols_1').html('&#xe7a6;');
    }else{
        document.getElementById("ico").title = "隐藏密码";
        document.getElementById("password").type="text";
        $('#cols_1').html('&#xe606;');
    }
}
//$(document).ready(function(){
//	$(".login-in").click(function(){
//		if($('#user').val()==''){
//			$(".user-wain").html("请输入账号！");
//	        return false;
//	   }
//		else{
//			$(".user-wain").html(" ");
//			return true;
//		}
//	    if($('#password').val()==''){
//	    	$(".pword-wain").html("请输入密码！");
//	        return false;
//	    }
//	    else{
//	    	$(".pword-wain").html(" ");
//	    	return true;
//	    }
//	})
//})

