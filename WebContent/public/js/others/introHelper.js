// 依赖：jQuery, introJs

(function($, introJs){
    var TEXT_NO_HINT = window.LANG_TEXT.NO_HINT;
    var TEXT_NEXT = window.LANG_TEXT.NEXT_STEP;
    var TEXT_PREV = window.LANG_TEXT.PREV_STEP;
    var TEXT_CLOSE = window.LANG_TEXT.CLOSE;
    var TEXT_I_KNOW = window.LANG_TEXT.I_KNOW;

    window.introHelper = {
        // intro文字后附加的checkbox
        _checkboxRow: '<div class="custom-element"><input type="checkbox" id="introNeverShow"/><label for="introNeverShow">' + TEXT_NO_HINT + '</label></div>',

        // 下次不再提示
        _processNeverShow: function(cookieName){
            if(cookieName && $('#introNeverShow').prop('checked')){
                $.cookie(cookieName, 'yes', { expires: 365 });  // 默认保留365天
            }
        },

        // 是否还要显示提示
        _checkIntroShow: function(cookieName){
            if(cookieName && $.cookie(cookieName)){
                return false;
            }
            return true;
        },

        // intro构造
        // introHelper.construct({
        //     steps: [],
        //     cookieName: '',
        //     beforeIntro: function(){},
        //     afterIntro: function(){}
        // });
        construct: function(options){
            // 如果需要存cookie，添加checkbox
            if(options.cookieName){
                $.each(options.steps, function(i, step){
                    step.intro += introHelper._checkboxRow;
                });
            }

            // intro构造
            var intro = introJs();

            intro.setOptions({
                nextLabel: TEXT_NEXT + ' &rarr;',
                prevLabel: '&larr; ' + TEXT_PREV,
                skipLabel: TEXT_CLOSE,
                doneLabel: TEXT_I_KNOW,
                exitOnOverlayClick: false,
                showBullets: false,
                steps: options.steps
            });

            // intro回调
            intro.oncomplete(function(){
                introHelper._processNeverShow(options.cookieName);
                options.afterIntro && options.afterIntro();
            });

            intro.onexit(function(){
                introHelper._processNeverShow(options.cookieName);
                options.afterIntro && options.afterIntro();
            });

            // 根据cookie是否start
            if(introHelper._checkIntroShow(options.cookieName)){
                options.beforeIntro && options.beforeIntro();
                intro.start();
            }
        }
    };

})(jQuery, introJs);