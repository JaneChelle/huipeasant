$(document).ready(function () {
//  用户
    // 添加的弹窗
    $(".tianjia").click(function () {
        $(".windoww").fadeIn();
    });
    //关闭弹窗
    $(".cancel").click(function () {
        $(".windoww").fadeOut();
    });
 //视频
    $(".videotj").click(function () {
       $(".windoww").fadeIn();
    });
    $(".videocancel").click(function () {
        $(".windoww").fadeOut();
    });
//文章
	$(".articlejs").click(function () {
       $(".windoww").fadeIn();
    });
    $(".articlecanvel").click(function () {
        $(".windoww").fadeOut();
    });
//问题
	$(".qusetionjs").click(function () {
       $(".windoww").fadeIn();
    });
    $(".qusetioncancel").click(function () {
        $(".windoww").fadeOut();
    });
//资讯
	$(".infomjs").click(function () {
       $(".windoww").fadeIn();
    });
    $(".infomcancel").click(function () {
        $(".windoww").fadeOut();
    });
//模块
	$(".modeljs").click(function () {
       $(".windoww").fadeIn();
    });
    $(".modelcancel").click(function () {
        $(".windoww").fadeOut();
    });
});