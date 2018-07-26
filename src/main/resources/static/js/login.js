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

//昵称
function user(){
	var user=$('#user').val();
	if(user.length <=0)     
       {     
           	$('.user-wain').html("<i class='fa fa-times'></i> 请先输入昵称");
    		return false;
       }else {
    	$('.user-wain').html("<i class='fa fa-check'>");
        return false;
    }
}
//密码
function passWord(){
	var pword=$("#password").val();
	if(pword.length <=0)     
       {     
           	$('.pword-wain').html("<i class='fa fa-times'></i> 请先输入密码");
    		return false;
       }  
       else {
    	$('.pword-wain').html("<i class='fa fa-check'>");
        return false;
    }
}
//登录
function login(){
	if(user() && passWord()){
		alert("djbhf")
        return true;
	}
	else{
		return false;
	}
}