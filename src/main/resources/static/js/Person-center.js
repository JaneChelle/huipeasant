$(document).ready(function () {
    // 设置高亮
   // $(".addColor").on("click",function () {
   //     $(".addColor").css("background","#fff");
   //     $(this).css("background","#599524");
   //
   // });
   // 滑动
   // $(".list_one").click(function () {
   //     $(this).siblings(".list_two").slideToggle("1s");
   //     $(this).parent().siblings().children(".list_two").slideUp("1s");
   // });
   //  修改头像
    $(".editavatar").click(function () {
        $('.modal').css("transform","translateX(0)");
        $('.modal').css("opacity","1");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window").css("display","block");
    });
    $(".md-close").click(function () {
        $('.modal').css("transform","translateX(20)");
        $('.modal').css("opacity","0");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window").css("display","none");

    });
    // 修改昵称
    $(".editnickname").click(function () {
        $('.modal').css("transform","translateX(0)");
        $('.modal').css("opacity","1");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window2").css("display","block");
    });
    $(".md-close").click(function () {
        $('.modal').css("transform","translateX(20)");
        $('.modal').css("opacity","0");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window2").css("display","none");

    });
    //修改手机号
    $(".editphone").click(function () {
        $('.modal').css("transform","translateX(0)");
        $('.modal').css("opacity","1");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window3").css("display","block");
    });
    $(".md-close").click(function () {
        $('.modal').css("transform","translateX(20)");
        $('.modal').css("opacity","0");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window3").css("display","none");

    });
    //修改作物
    $(".editcrops").click(function () {
        $('.modal').css("transform","translateX(0)");
        $('.modal').css("opacity","1");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window4").css("display","block");
    });
    $(".md-close").click(function () {
        $('.modal').css("transform","translateX(20)");
        $('.modal').css("opacity","0");
        $(".modal").css("transition","all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
        $(".new-window4").css("display","none");

    });

//获取地址
console.log($(".pro").val());
console.log($(".city").val());
console.log($(".dis").val());
$(".ipt").val()


});