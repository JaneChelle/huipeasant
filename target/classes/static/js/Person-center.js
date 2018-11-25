$(document).ready(function () {
    var modify = $('.modify');
    var modals=$(".modals");
    var new_window=$(".new-window");
    var md_close=$(".md-close");
    for (let i = 0; i < modify.length; i++) {
        $(modify[i]).click(function () {
            //console.log(i);
            $(modals[i]).css("transform", "translateX(0)");
            $(modals[i]).css("opacity", "1");
            $(modals[i]).css("transition", "all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
            $(new_window[i]).css("display", "block");
            //console.log(modals[i]);
            //console.log(new_window[i]);

            $(md_close[i]).click(function () {
                $(modals[i]).css("transform", "translateX(20)");
                $(modals[i]).css("opacity", "0");
                $(modals[i]).css("transition", "all 1s cubic-bezier(0.17, 0.67, 0, 1.09)");
                $(new_window[i]).css("display", "none");
            });
        });
    };
    //1.获取地区请求
    var  tex=$(".tex").val();
    $(".address").click(function (){
        var   address= $(".text").val();
        $.ajax({
            url:"/UserManagementController/changeAddress",
            type:'POST',
            data:{
                /*"userId":userId,*/
                "address":address
            },
            dataType:"text",
            success:function(){
                $(".new-window").css("display","none");
                tex.innerHTML=address;
                location.reload();

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
                    $(".new-window").css("display","none");
                    location.reload();

                },
                error:function () {
                        alert("请求失败");
                    }
            });
        // }
    });
    // 3.性别请求
    var xingbie=$(".xingbie");
    $(".sex").click(function () {
        var options = $("#test option:selected"); //获取选中的项
        var sex = options.val(); //拿到选中项的值
        $.ajax({
            url: "/UserManagementController/changeSex",
            type: 'POST',
            data: {
                /*"userId":userId,*/
                "sex": sex
            },
            dataType: "text",
            success: function () {
                $(".new-window").css("display", "none");
                xingbie.innerHTML = sex;
                location.reload();

            },
            error: function () {
                alert("请求失败");
            }
        });
    });
        // 4.手机请求
        var phonenumber = $(".phonenumber");
        $(".phoneNumber").click(function () {
            var number = $(".number").val();
            $.ajax({
                url: "/UserManagementController/changePhone",
                type: 'POST',
                data: {
                    /*"userId":userId,*/
                    "phoneNumber": number
                },
                dataType: "text",
                success: function () {
                    $(".new-window").css("display", "none");
                    phonenumber.innerHTML = number;
                    location.reload();

                },
                error: function () {
                    alert("请求失败");
                }
            });

        });
    // 5.预留信息请求
    var infotex = $(".infotex");
    $(".reservedInf").click(function () {
        var reservedInf = $(".reserve").val();
        $.ajax({
            url: "/UserManagementController/changePhone",
            type: 'POST',
            data: {
                /*"userId":userId,*/
                "reservedInf": reservedInf
            },
            dataType: "text",
            success: function () {
                $(".new-window").css("display", "none");
                infotex.innerHTML = reservedInf;
                location.reload();

            },
            error: function () {
                alert("请求失败");
            }
        });
    });






















    });

