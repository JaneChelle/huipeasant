//手机号
function tels(){
    var tel=$('#tel').val();
    if(tel.length <=0)
    {
        $('.tel-wain').html("<i class='fa fa-times'></i> 请先输入手机号");
        return false;
    }
    else if( /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g.test(tel) ){
        $('.tel-wain').html("<i class='fa fa-check'> 手机号格式正确");
        return true;
    }else {
        $('.tel-wain').html("<i class='fa fa-times'></i> 手机号格式错误");
        return false;
    }
}
// 重置密码
function resetting() {
    var pword_1=$("#pword_1").val();
    if(pword_1.length <=0)
    {
        $('.pword_1').html("<i class='fa fa-times'></i> 请先输入密码");
        return false;
    }
    else if(/^[a-zA-Z]\w{5,17}$/g.test(pword_1) ){
        $('.pword_1').html("<i class='fa fa-check'>");
        return true;
    }else {
        $('.pword_1').html("<i class='fa fa-times'></i> 密码格式错误");
        return false;
    }
}
//确认重置密码
function surePword(){
    var pword_1=$("#pword_1").val();
    var pword_2=$("#pword_2").val();
    if(pword_2.length <=0)
    {
        $('.pword_2').html("<i class='fa fa-times'></i> 请先确认密码");
        $(".pword_2").fadeIn();
        return false;
    }
    else if( pword_1 == pword_2){
        $('.pword_2').html("<i class='fa fa-check'>");
        $(".pword_2").fadeIn();
        return true;
    }else {
        $('.pword_2').html("<i class='fa fa-times'></i> 两次密码不同");
        $(".pword_2").fadeIn();
        return false;
    }
}
// 重置密码确认
$(document).ready(function(){
    $("#sureModify").click(function () {
        if(resetting() && surePword()){
            document.getElementById("sureModify").setAttribute("type","submit");
        }
    });
})
