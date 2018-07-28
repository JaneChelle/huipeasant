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

//手机号
function account(){
    var user=$('#user').val();
    if(user.length <=0)
    {
        $('.user-wain').html("<i class='fa fa-times'></i> 请先输入手机号");
        return false;
    }
    else if( /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g.test(user) ){
        $('.user-wain').html("<i class='fa fa-check'>");
        return true;
    }else {
        $('.user-wain').html("<i class='fa fa-times'></i> 手机格式错误");
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
    else if( /^[a-zA-Z]\w{5,17}$/g.test(pword) ){
        $('.pword-wain').html("<i class='fa fa-check'>");
        return true;
    }else {
        $('.pword-wain').html("<i class='fa fa-times'></i> 密码格式错误");
        return false;
    }
}
//登录
function login(){
    if(account() && passWord()){
        return true;
    }
    else{
        return false;
    }
}