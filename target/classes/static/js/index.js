window.onload=function(){
    var wripimg=document.getElementsByClassName('wrip-img');
    var prev=document.getElementById('prev');
    var next=document.getElementById('next');
    var wrip=document.getElementsByClassName('wrip')[0];

    //向右
    next.onclick = function () {
        for(var j=0; j<wripimg.length; j++){
            if(hasClass(wripimg[j],'right')){
                break;
            }else{
                addClass(wripimg[j],'right')
            }
        }
        for(var i=0; i<wripimg.length; i++){
            if(wripimg[i].className.indexOf('max')>-1){
                rightImg(i);
                break;
            }
        }
    };
    //向左轮播
    prev.onclick=function () {

        for(var j=0; j<wripimg.length; j++){
            if(hasClass(wripimg[j],'right')){
                removeClass(wripimg[j],'right')
            }
        }
        for(var j=0; j<wripimg.length; j++){
            if(wripimg[j].className.indexOf('max')>-1){
                leftImg(j);
                break;
            }
        }
    };
    function rightImg(i) {
        i = parseInt(i);
        wripimg[i].style.zIndex = 0;
        var index = i;
        if(i == 4){
            i = 0;
            index = 4;
        }else{
            i++;
        }
        wripimg[i].style.zIndex = 100;
        wripimg[i].className = wripimg[index].className;
        setTimeout(function () {
            wripimg[index].className = 'wrip-img';
            wripimg[index].style.zIndex = 0;
        },1000)
    }
    function leftImg(j) {
        j = parseInt(j);
        wripimg[j].style.zIndex = 0;
        var index = j;
        if(j == 0){
            j = 4;
            index = 0;
        }else{
            j--;
        }
        wripimg[j].style.zIndex = 100;
        wripimg[j].className = wripimg[index].className;
        setTimeout(function () {
            wripimg[index].className = 'wrip-img';
            wripimg[index].style.zIndex = 0;
        },1000)
    }
    function addClass(obj, cls){
        var obj_class = obj.className,//获取 class 内容.
            blank = (obj_class != '') ? ' ' : '';//判断获取到的 class 是否为空, 如果不为空在前面加个'空格'.
        added = obj_class + blank + cls;//组合原来的 class 和需要添加的 class.
        obj.className = added;//替换原来的 class.
    }
    function removeClass(obj, cls){
        var obj_class = ' '+obj.className+' ';//获取 class 内容, 并在首尾各加一个空格. ex) 'abc    bcd' -> ' abc    bcd '
        obj_class = obj_class.replace(/(\s+)/gi, ' '),//将多余的空字符替换成一个空格. ex) ' abc    bcd ' -> ' abc bcd '
            removed = obj_class.replace(' '+cls+' ', ' ');//在原来的 class 替换掉首尾加了空格的 class. ex) ' abc bcd ' -> 'bcd '
        removed = removed.replace(/(^\s+)|(\s+$)/g, '');//去掉首尾空格. ex) 'bcd ' -> 'bcd'
        obj.className = removed;//替换原来的 class.
    }
    function hasClass(obj, cls){
        var obj_class = obj.className,//获取 class 内容.
            obj_class_lst = obj_class.split(/\s+/);//通过split空字符将cls转换成数组.
        x = 0;
        for(x in obj_class_lst) {
            if(obj_class_lst[x] == cls) {//循环数组, 判断是否包含cls
                return true;
            }
        }
        return false;
    }
    // 定时器
    var times;
    function play() {
        times = setInterval(function () {
            next.onclick()
        }, 2500)
    }
    play();
};
//面包屑导航
// $(document).ready(function(){
//     $("#one").click(function () {
//         $("#echo").text("农药");
//     });
//     $("#two").click(function () {
//         $("#echo").text("肥料");
//     });
//     $("#three").click(function () {
//         $("#echo").text("农膜");
//     });
//     $("#four").click(function () {
//         $("#echo").text("种子种苗");
//     });
//     $("#five").click(function () {
//         $("#echo").text("植保技术");
//     });
//     $("#six").click(function () {
//         $("#echo").text("解决方案");
//     });
//
//
//
//
// });