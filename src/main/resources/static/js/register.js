var getElem = function(id){
    return document.getElementById(id);
}
//一、定义一个获取DOM元素的方法
var $ = function(selector){
        return  document.querySelector(selector);
    },
    box = $(".drag"),//容器
    bg = $(".bg"),//背景
    text = $(".text"),//文字
    btn = $(".btn"),//滑块
    success = false,//是否通过验证的标志
    step1=$(".next-step"),
    step2=$(".next-step-second"),
    codes=$(".checkCode")
distance = box.offsetWidth - btn.offsetWidth;//滑动成功的宽度（距离）

//二、给滑块注册鼠标按下事件
btn.onmousedown = function(e){

    //1.鼠标按下之前必须清除掉后面设置的过渡属性
    btn.style.transition = "";
    bg.style.transition ="";

    //说明：clientX 事件属性会返回当事件被触发时，鼠标指针向对于浏览器页面(或客户区)的水平坐标。

    //2.当滑块位于初始位置时，得到鼠标按下时的水平位置
    var e = e || window.event;
    var downX = e.clientX;

    //三、给文档注册鼠标移动事件
    document.onmousemove = function(e){

        var e = e || window.event;
        //1.获取鼠标移动后的水平位置
        var moveX = e.clientX;

        //2.得到鼠标水平位置的偏移量（鼠标移动时的位置 - 鼠标按下时的位置）
        var offsetX = moveX - downX;

        //3.在这里判断一下：鼠标水平移动的距离 与 滑动成功的距离 之间的关系
        if( offsetX > distance){
            offsetX = distance;//如果滑过了终点，就将它停留在终点位置
        }else if( offsetX < 0){
            offsetX = 0;//如果滑到了起点的左侧，就将它重置为起点位置
        }

        //4.根据鼠标移动的距离来动态设置滑块的偏移量和背景颜色的宽度
        btn.style.left = offsetX + "px";
        bg.style.width = offsetX + "px";

        //如果鼠标的水平移动距离 = 滑动成功的宽度
        if( offsetX == distance){

            //1.设置滑动成功后的样式
            text.innerHTML = "验证通过";
            text.style.color = "#fff";
            btn.innerHTML = "&radic;";
            btn.style.color = "green";
            bg.style.backgroundColor = "#5CB85C";


            //2.设置滑动成功后的状态
            success = true;
            //成功后，清除掉鼠标按下事件和移动事件（因为移动时并不会涉及到鼠标松开事件）
            btn.onmousedown = null;
            document.onmousemove = null;

            //3.成功解锁后的回调函数
            setTimeout(function(){
//                      alert('解锁成功！');
                telp();
                bindingTel();
            },100);
        }
    }

    //四、给文档注册鼠标松开事件
    document.onmouseup = function(e){

        //如果鼠标松开时，滑到了终点，则验证通过
        if(success){
            return;
        }else{
            //反之，则将滑块复位（设置了1s的属性过渡效果）
            btn.style.left = 0;
            bg.style.width = 0;
            btn.style.transition = "left 1s ease";
            bg.style.transition = "width 1s ease";
        }
        //只要鼠标松开了，说明此时不需要拖动滑块了，那么就清除鼠标移动和松开事件。
        document.onmousemove = null;
        document.onmouseup = null;
    }
}
//验证码
var code ; //在全局 定义验证码     
function createCode()
{
    code = "";
    var codeLength = 6;//验证码的长度
    var checkCode = document.getElementById("checkCode");
    var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的
    for(var i=0;i<codeLength;i++)
    {
        var charIndex = Math.floor(Math.random()*36);
        code +=selectChar[charIndex];
    }
    if(checkCode)
    {
        checkCode.className="code";
        checkCode.value = code;
        checkCode.blur();
    }
}
function validate ()   {
    var inputCode = getElem("validCode").value;
    if(inputCode.length <=0)
    {
        $('.item-code').html("<i class='fa fa-times'></i> 请先输入验证码");
        $(".item-code").fadeIn();
        return false;
    }
    else if(inputCode.toUpperCase() != code )
    {
        $('.item-code').html("<i class='fa fa-times'></i> 验证码输入错误");
        $(".item-code").fadeIn();
        createCode();//刷新验证码
        return false;
    }
    else
    {
        $('.item-code').html("<i class='fa fa-check'>");
        $(".item-code").fadeIn();
        return true;
    }
}
//手机号
function telp(){
    var tel=$('#tel').val();
    if(tel.length <=0)
    {
        $('.item-tel').html("<i class='fa fa-times'></i> 请先输入手机号");
        $(".item-tel").fadeIn();
        return false;
    }
    else if( /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g.test(tel) ){
        $('.item-tel').html("<i class='fa fa-check'>");
        $(".item-tel").fadeIn();
        return true;
    }else {
        $('.item-tel').html("<i class='fa fa-times'></i> 手机格式错误");
        $(".item-tel").fadeIn();
        return false;
    }
}
// 手机号绑定
function bindingTel() {
    $.ajax({
        url:"/LogUserController/validationPhone",
        type:"POST",
        data:{
            phoneNumber:$('#tel').val(),
        },
        datatype:"JSON",
        success: function (data) {
            if(data.code==0) {
                if (telp()) {
                    $('.item-tel').html("<i class='fa fa-check'> 手机号可用");
                    $(".item-tel").fadeIn();
                    step1.style.backgroundColor = "#5CB85C";
                    step1.style.cursor = "pointer";
                    console.log(step1.style.backgroundColor);
                    return true;
                }
            }
            else {
                    $('.item-tel').html("<i class='fa fa-times'></i> 手机号已被注册");
                    $(".item-tel").fadeIn();
                    return false;
                }
            
        }
    })
    //          第一步
    if(step1.style.backgroundColor == "rgb(92, 184, 92)"){
        $('#register-main').hide();
        $('#register-info').fadeIn();
        $(".ulist-2").addClass("active");
    }
}
//预留信息
function user(){
    var user=$('#user').val();
    if(user.length <=0)
    {
        $('.item-user').html("<i class='fa fa-times'></i> 请先输入预留信息");
        $(".item-user").fadeIn();
        return false;
    }
    else{
        $('.item-user').html("<i class='fa fa-check'>");
        $(".item-user").fadeIn();
        return true;
    }
}
//密码
function passWord(){
    var pword=$("#pword-1").val();
    if(pword.length <=0)
    {
        $('.item-pword').html("<i class='fa fa-times'></i> 请先输入密码");
        $(".item-pword").fadeIn();
        return false;
    }
    else if( /^[a-zA-Z]\w{5,17}$/g.test(pword) ){
        $('.item-pword').html("<i class='fa fa-check'>");
        $(".item-pword").fadeIn();
        return true;
    }else {
        $('.item-pword').html("<i class='fa fa-times'></i> 密码格式错误");
        $(".item-pword").fadeIn();
        return false;
    }
}
//确认密码
function sureWord(){
    var pword=$("#pword-1").val();
    var pword_2=$("#pword-2").val();
    if(pword_2.length <=0)
    {
        $('.item-pword-2').html("<i class='fa fa-times'></i> 请先确认密码");
        $(".item-pword-2").fadeIn();
        return false;
    }
    else if( pword == pword_2){
        $('.item-pword-2').html("<i class='fa fa-check'>");
        $(".item-pword-2").fadeIn();
        return true;
    }else {
        $('.item-pword-2').html("<i class='fa fa-times'></i> 两次密码不同");
        $(".item-pword-2").fadeIn();
        return false;
    }
}
//第二个 下一步
function allStep(){
    if(passWord() && sureWord() &&user() && validate ()){
        step2.style.backgroundColor = "#5CB85C";
        step2.style.cursor = "pointer";
        console.log(step2.style.backgroundColor);
        return true;
    }
    else{
        return false;
    }
}
// function allSteps(){
//     if(step2.style.backgroundColor == "rgb(92, 184, 92)"){
//         if(allStep()){
//             $('#register-info').hide();
//             $('#register-done').fadeIn();
//             $(".ulist-3").addClass("active");
//         }
//     }
// }
//验证码 背景
//var colors = ["#f5d76e", "#3ec27f", "#22a7f0", "#d2527f", "#1e824c", "#bf55ec", "#f64747", "#e67e22", "#4b77be", "#be90d4"]; 
//function codes(){
//	var n = Math.floor(Math.random() * 10); 
//  var color = colors[n] ;
//  codes.style.backgroundColor = color;
//}

//	if(step2.style.backgroundColor == "rgb(92, 184, 92)"){
//			$('.next-step-second').click(function(){
//			$('#register-info').hide();
//			$('#register-done').fadeIn();
//			$(".ulist-3").addClass("active");
//		}