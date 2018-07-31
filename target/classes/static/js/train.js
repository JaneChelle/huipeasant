
$(document).ready(function(){
    //二维码
  $("#qr-code").mouseenter(function(){
    $(".qr-code").fadeIn();
  });
  $(".qr-code").mouseleave(function(){
    $(".qr-code").fadeOut();
  });
    // 农机培训 电商培训
    $(".train-item2").click(function(){
        $(".train-item2").css("color","#ff4400");
        $(".train-item1").css("color","#000");
        $(".item-1").hide();
        $(".item-2").fadeIn();
        $(".wrapper_list1").hide();
        $(".wrapper_list2").fadeIn();
    })
    $(".train-item1").click(function(){
        $(".train-item1").css("color","#ff4400");
        $(".train-item2").css("color","#000");
        $(".item-2").hide();
        $(".item-1").fadeIn();
        $(".wrapper_list2").hide();
        $(".wrapper_list1").fadeIn();
    })
});
//搜索框
function spin(){
    if($('#applyCertNum').val() != ""){
        $('.spin').fadeIn();
    }
    else{
        $('.spin').fadeOut();
    }
}
onkeyup(spin());
function spainner(){
    $.ajax({
        type: "POST",//数据发送的方式（post 或者 get）
        url: "/user/keyword",//要发送的后台地址
        data: {
            keyword:$('#applyCertNum').val(),
        },//要发送的数据（参数）格式为{'val1':"1","val2":"2"}
        dataType:"JSON",
        success: function (data) {//ajax请求成功后触发的方法
            var datas=data.data;
            console.log(datas);
            if(data.code==0){
                $('.spinners').html(" ");
                for (var i=0;i<datas.length;i++){
                    var aa="<a href=/user/textview/"+ datas[i].dataId+ " >"+datas[i].contentsTitle+ "</a>";
                    $('.spinners').append(aa);
                }
            }else{
                alert(data.msg);
            }
        },
        error: function (msg) {//ajax请求失败后触发的方法
            alert("网络故障");//弹出错误信息
        }
    });
}