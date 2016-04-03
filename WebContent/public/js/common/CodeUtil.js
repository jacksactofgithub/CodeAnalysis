// 依赖：jquery, hljs, myGlobal, chosen
(function($, hljs, myGlobal){

    var CodeUtil = {
        displayCodeWithLine: function(){

        },

        displayCodeWithBlock: function(){

        },

        getSvgPath: function(subId, proName, className, methodName){
            return encodeURI('/public/svg/problems/' + subId + '/' + 
                proName + '/' + className + '- ' + methodName + '.svg');
        },

        xxx: function(){
            
        }
    };


    // export
    window.CodeUtil = CodeUtil;

})(jQuery, hljs, myGlobal);