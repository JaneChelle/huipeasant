function telps(){
	// bindingTel();
	var tel=$('#tel').val();
	if(tel.length <=0)
       {
           	$('.user-wain').html("<i class='fa fa-times'></i> 请先输入手机号");
    		return false;
       }
	else if( /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g.test(tel)){
		$('.user-wain').html("<i class='fa fa-check'>");
        return true;
    }else {
    	$('.user-wain').html("<i class='fa fa-times'></i> 手机格式错误");
        return false;
    }
}
// 手机号绑定
function bindingTel() {
    $.ajax({
        url: "/LogUserController/validationPhone",
        type: "POST",
        data: {
            phoneNumber: $('#tel').val(),
        },
        datatype: "JSON",
        success: function (data) {
        	telps();
            if (data.code == 0) {
                if(telps()){
                    $('.user-wain').html("<i class='fa fa-check'> 手机号可用");
                    document.getElementById("register").setAttribute("type","submit");
                    return true;
				}

            }
            else {
                $('.user-wain').html("<i class='fa fa-times'></i> 手机号已被注册");
                return false;
            }
        },
        error: function () {
			alert("jdn");
        }
    })
}
//密码
function passWord(){
	var pword=$("#password").val();
	if(pword.length <=0)     
       {     
           	$('.pword-wain').html("<i class='fa fa-times'></i> 请先输入密码");
    		return false;
       }    
	else if( /^[a-zA-Z]\w{5,17}$/g.test(pword) ){
		$('.pword-wain').html("<i class='fa fa-check'>");
        return true;
    }else {
    	$('.pword-wain').html("<i class='fa fa-times'></i> 密码格式错误,密码，以字母开头，长度在6~18之间，只能包含字母、数字和下划线");
        return false;
    }
}
// 预留信息
function rese(){
    var reservedInf=$("#reservedInf").val();
    if(reservedInf.length <=0)
    {
        $('.code-wain').html("<i class='fa fa-times'></i> 请先输入预留信息");
        return false;
    }
    else{
        $('.code-wain').html("<i class='fa fa-check'>");
        return true;
    }
}