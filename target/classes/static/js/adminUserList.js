$(document).ready(function () {
//  用户
    // 添加的弹窗
    $(".tianjia").click(function () {
        $(".windoww").css("display","block");
    });
    //关闭弹窗
    $(".cancel").click(function () {
        $(".windoww").css("display","none");
    });
 //视频
    $(".videotj").click(function () {
       $(".windoww").css("display","block");
    });
    $(".videocancel").click(function () {
        $(".windoww").css("display","none");
    });
//文章
	$(".articlejs").click(function () {
       $(".windoww").css("display","block");
    });
    $(".articlecanvel").click(function () {
        $(".windoww").css("display","none");
    });
//问题
	$(".qusetionjs").click(function () {
       $(".windoww").css("display","block");
    });
    $(".qusetioncancel").click(function () {
        $(".windoww").css("display","none");
    });
//资讯
	$(".infomjs").click(function () {
       $(".windoww").css("display","block");
    });
    $(".infomcancel").click(function () {
        $(".windoww").css("display","none");
    });
//模块
	$(".modeljs").click(function () {
       $(".windoww").css("display","block");
    });
    $(".modelcancel").click(function () {
        $(".windoww").css("display","none");
    });
});