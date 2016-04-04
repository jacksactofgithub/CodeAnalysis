// 页面初始化时默认绑定/执行
// 依赖：application-util.js

(function($, myGlobal){

    //===== input输入限制 =====//
    $('input.myOnlyNum-input').live('keyup', myGlobal.inputNumAllowed);

    //===== 验证码切换 TODO: 插件化 =====//
    $('#vcodeLink').click(function(){
        var now = new Date();
        $('#vcodeImg').attr('src', '/vcode?t=' + now.getTime());
        $('#vcodeInput').val('').focus();
    });

    //===== 分页插件 =====//
    $('.pagination').myPagination();

})(jQuery, myGlobal);