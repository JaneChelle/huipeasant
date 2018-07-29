//加入书架成功提醒
function bssucc(){
    $('.bookshelf').removeClass('none').addClass('block');
    $('.point').text('请先登录');
    setTimeout("bsclose()", 5000);
}
function bsclose() {
    $('.bookshelf').removeClass('block').addClass('none');
}
$('.point').text('成功加入书签');
//我要评论
$(document).ready(function(){
  $('.recommend').click(function(){
		if($("#content").val()==""){
			$('.bookshelf').removeClass('none').addClass('block');
			$('.point').text('评论内容不能为空');
			setTimeout("bsclose()", 5000);
		}
	})
});
