$(document).ready(function() {
    // 收缩
    $(".shrinkage").click(function () {
        $(".master").hide();
        $(".side-left").css("width", "110px");
        $(".shrinkage").css("margin-left", "110px");
        $(".side-left>ul>li").css("width", "110px");
        $(".frame-div").css("left","110px");
        $(".unfold").css("opacity", "1");
        $(".shrinkage").css("opacity", "0");
    });
    // 展开
    $(".unfold").click(function () {
        $(".master").show();
        $(".side-left").css("width", "200px");
        $(".shrinkage").css("margin-left", "200px");
        $(".side-left>ul>li").css("width", "200px");
        $(".frame-div").css("left","200px");
        $(".unfold").css("opacity", "0");
        $(".shrinkage").css("opacity", "1");
    });
    //菜单切换
// //  用户
//     $(".user").click(function () {
//         $(".ifm").attr("src","/adminUserController/adminUserList");
//     });
//视频
    $(".video").click(function () {
        $(".ifm").attr("src","/AdminVideoController/adminVideoList");
    });
//文章
    $(".article").click(function () {
        $(".ifm").attr("src","/AdminDataController/toViewdata/1/1");
    });
//问题
	$(".question").click(function () {
        $(".ifm").attr("src","/AdminDataController/toViewdata/2/1");
    });
//资讯
	$(".inform").click(function () {
        $(".ifm").attr("src","/AdminDataController/toViewdata/3/1");
    });
//模块
	$(".model").click(function () {
        $(".ifm").attr("src","/AdminDataController/modulesView");
    });
	//台前
    $(".taiqian").click(function () {
        $(".ifm").attr("src","/AdminVideoController/ds");
    });
//设置高亮
    $(".addColor").on('click',function () {
        $(".addColor").css("backgroundColor","#282B33");
        $(this).css("backgroundColor","#009688");
    })


});
