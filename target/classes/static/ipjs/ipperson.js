// 昵称
function ipUsers() {
        $.ajax({
            url: "/UserManagementController/changeIPInformation",
            type: "POST",
            data: {
                NickName:$("#NickName").val(),
            },
            datatype: "JSON",
            success: function (data) {
                if (data.code == 0) {
                    location = "/UserManagementController/ipinfor";
                    location.reload();
                    return true;
                }
                else {
                    return false;
                }
            },
            error: function () {
                // alert("jdn");
            }
        })
    }
// 性别
function ipSex() {
    $.ajax({
        url: "/UserManagementController/changeIpSex",
        type: "POST",
        data: {
            sex:$("#sex").val(),
        },
        datatype: "JSON",
        success: function (data) {
            if (data.code == 0) {
                location = "/UserManagementController/ipinfor";
                location.reload();
                return true;
            }
            else {
                return false;
            }
        },
        error: function () {
            // alert("jdn");
        }
    })
}

var options=$('.aa');
console.log(options);
for (var i=0;i<options.length;i++){
    console.log($(options[i]));
    if ($('.bb').val() == $(options[i]).text()) {
        $(options[i]).attr("selected",true);
    }else {
        $(options[i]).attr("selected",false);
    }
}

