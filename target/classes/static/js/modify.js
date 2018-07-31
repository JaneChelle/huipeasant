function modify() {
	passWord();
	newPassword();
    $.ajax({
        type:"POST",
        url:"/UserManagementController/checkPassword",
        data:{
            password:$("#pword").val(),
            rePassword:$("#password").val(),
        },
        datatype:"JSON",
        success: function (data) {
            if(data.code==0) {
                $('.pword-wain').html("<i class='fa fa-check'>");
                location = "/LogUserController/toLogin";
                return true;
			}
			else {
                $('.pword-wain').html("<i class='fa fa-times'></i> 原密码错误！请重试");
                return false;
			}

        },
        error: function (msg) {//ajax请求失败后触发的方法
            alert("请求失败");//弹出错误信息
        }
    });
}
//原密码
function passWord(){
	var pword=$("#pword").val();
	if(pword.length <=0) {
           	$('.pword-wain').html("<i class='fa fa-times'></i> 请先输入密码");
    		return false;
	} else {
		return true;
	}
}
//xin密码
function newPassword(){
	var password=$("#password").val();
	if(password.length <=0)
       {     
           	$('.code-wain').html("<i class='fa fa-times'></i> 请先输入新密码");
    		return false;
       }
	else if( /^[a-zA-Z]\w{5,17}$/g.test(password) ){
		$('.code-wain').html("<i class='fa fa-check'>");
        return true;
    }else {
    	$('.code-wain').html("<i class='fa fa-times'></i> 密码格式错误");
        return false;
    }
}
