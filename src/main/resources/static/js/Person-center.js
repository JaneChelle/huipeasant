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
    //1.获取地区请求

    $(".address").click(function (){
        var   address= $("textarea").val();
        $.ajax({
            url:"/UserManagementController/information",
            type:'POST',
            data:{
                /*"userId":userId,*/
                "address":address
            },
            dataType:"text",
            success:function(){
                $(".new-window2").css("display","none");

            },
            error:function () {
                alert("请求失败");
            }
        });
    });

    //2.修改昵称请求
  var   nicheng= $(".nicheng");
    $(".upload-nickname").click(function (){
        // if(user_test()==true){
            var nickname=$(".nickname").val();
            $.ajax({
                url:"/UserManagementController/changeInformation",
                type:'POST',
                data:{
                    /*"userId":userId,*/
                    "NickName":nickname
                },
                dataType:"text",
                success:function(){
                    nicheng.innerHTML=nickname;
                    $(".new-window2").css("display","none");

                },
                error:function () {
                        alert("请求失败");
                    }
            });
        // }
    });
    // 3.性别请求
    var options=$("#test option:selected"); //获取选中的项
    var sex=options.val(); //拿到选中项的值
    $(".sex").click(function (){
        var options=$("#test option:selected"); //获取选中的项
        var sex=options.val(); //拿到选中项的值
        $.ajax({
            url:"/UserManagementController/information",
            type:'POST',
            data:{
                /*"userId":userId,*/
                "sex":sex
            },
            dataType:"text",
            success:function(){
                $(".new-window2").css("display","none");

            },
            error:function () {
                alert("请求失败");
            }
        });

    });





});