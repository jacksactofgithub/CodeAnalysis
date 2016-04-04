// TODO: 依赖于jAlert，挪出去改成errorCallback
// TODO: 依赖于btn样式class，单独定义插件的css文件

(function($){
    // ================================
    // global helper
    // ================================
    window.myGlobal = $.extend({
        // 动画
        // =======================
        scrollTo: function($el){
            $('html,body').animate({scrollTop: $el.offset().top}, 'normal');
        },

        scrollBottom: function(){
            // FUCK height计算
            $('html,body').animate({scrollTop: $(document).height()}, 'normal');
        },

        innerScrollTop: function($parent){
            $parent.animate({scrollTop: 0}, 'normal');
        },

        innerScrollBottom: function($parent){
            var $target = $parent.children().last();
            var dist = $target.offset().top + $target.height() - ($parent.offset().top + $parent.height());
            $parent.animate({scrollTop: $parent.scrollTop() + dist}, 'normal');
        },

        removeFade: function($el, callback){
            $el.fadeOut('normal', function(){
                $(this).remove();
                callback && callback();
            });
        },

        // 数据格式
        // =======================
        /* return
            Date 值合法时
            undefined 值缺失或不合法 */
        parseDateTimeGroup: function(dateString, hh, mm){
            if(myGlobal.notEmpty(dateString) && myGlobal.notEmpty(hh) && myGlobal.notEmpty(mm)){
                return new Date(dateString.replace(/-/g, '/') + ' ' + hh + ':' + mm);
            }
            return undefined;
        },

        generateDateTimeGroup: function(date){
            if(myGlobal.notEmpty(date)){
                // NOTE: 括号不能少！
                return [
                    date.getFullYear() + '-' + 
                        ((date.getMonth() < 9) ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + '-' + 
                        ((date.getDate() < 10) ? ('0'+date.getDate()) : date.getDate()),
                    (date.getHours() < 10) ? ('0'+date.getHours()) : date.getHours(),
                    (date.getMinutes() < 10) ? ('0'+date.getMinutes()) : date.getMinutes()
                ];
            }
            return ['', '', ''];
        },

        /* return
            String format: yyyy-MM-dd HH:mm
            undefined 值缺失或不合法 */
        getDateTimeString: function(dateString, hh, mm){
            var date = myGlobal.parseDateTimeGroup(dateString, hh, mm);
            if(myGlobal.notEmpty(date)){
                return dateString + ' ' + hh + ':' + mm;
            }
            return undefined;
        },

        compareDate: function(date1, date2){
            var compare = Date.parse(date1) - Date.parse(date2);
            return compare == 0 ? 0 : (compare < 0 ? -1 : 1);
        },

        calTimeInterval: function(fromDate, toDate){
            var millisecond = Date.parse(toDate) - Date.parse(fromDate);
            // 毫秒 -> 分钟
            return (millisecond / 1000) / 60;
        },

        addTimeInterval: function(fromDate, minutes){
            return new Date(fromDate.getTime() + minutes * 60 * 1000);
        },

        floatToPercent: function(val){
            return Math.round(val * 100) + '%';
        },

        // 通用
        // =======================
        notEmpty: function(val){
            if(typeof val === 'string'){
                return $.trim(val).length > 0;
            }
            if(typeof val === 'number' || typeof val === 'boolean' || typeof val === 'function'){
                return true;
            }
            if(typeof val === 'object'){
                //TODO: empty {} []
                return true;
            }
            return false;
        },

        isEmpty: function(val){
            return !myGlobal.notEmpty(val);
        },

        objectKeys: function(obj){
            // 标准浏览器已实现
            if(Object.keys){
                return Object.keys(obj);
            }

            var keys = [];
            for(var key in obj){
                obj.hasOwnProperty(key) && keys.push(key);
            }
            return keys;
        },

        quickSort: function(array){
            if(array.length <= 1){
                return array;
            }

            var pivotIndex = Math.floor(array.length / 2);
            var pivot = array.splice(pivotIndex, 1)[0];
            var left = [];
            var right = [];

            for(var i=0; i<array.length; i++){
                if(array[i] < pivot){
                    left.push(array[i]);
                }
                else{
                    right.push(array[i]);
                }
            }

            // NOTE: 这里访问不到自身变量，而var quickSort = function(){}的写法可以
            return arguments.callee(left).concat([pivot], arguments.callee(right));
        },

        arrayIntersect: function(array1, array2){
            var result = [];
            for(var i=0; i<array1.length; i++){
                for(var j=0; j<array2.length; j++){
                    if(array1[i] == array2[j]){
                        result.push(array1[i]);
                        break;
                    }
                }
            }
            return result;
        },

        codeReplace: function(code){
            return code.replace('>', '&gt;').replace('<', '&lt;')
                    .replace(/\n|\r/g, '<br>').replace(/\t/g, '&nbsp;&nbsp;&nbsp;&nbsp;');
        },

        // 事件处理
        // =======================
        inputNumAllowed: function(){
            $(this).val($(this).val().replace(/\D/g,''));
        },

        stopPropagation: function(event){
            if(event && event.stopPropagation){
                event.stopPropagation();
            }
            else{
                window.event.cancelBubble = true;
            }
        },

        preventDefault: function(event){
            if(event && event.preventDefault)
                event.preventDefault();
            else
                window.event.returnValue = false;
            return false;
        },
        
        xxxxxx: function(){

        }
    }, window.myGlobal);



    // ================================
    // 向导
    // ================================
    // 用法
    // $formEl.myStepGuide({
    //     'initStep': 1,
    //     'stepBtn': '#xxxBtn',
    //     'beforeSubmit': function(){},
    //     'submitCheck': function(){ return true or false }
    //     'customSubmit': function(){}  //即采用自定义的ajax提交方式
    // });
    // 前置条件：step数大于1
    // ================================
    $.fn.myStepGuide = function(options){
        var STEP_CLASS_NAME = 'myStepGuide-step';
        var STEP_ID_PREFIX = 'myStepGuide_step_';
        var TEXT_DONE = window.LANG_TEXT.DONE;

        var opts = $.extend({
            'initStep': 1,
            'stepBtn': 'input[type="submit"]'
        }, options);

        var totalStep = 0;  //初始化
        var currentStep = opts.initStep;
        var $stepBtnEl = $(opts.stepBtn);
        var $formEl = this;


        var showStep = function(step){
            var $el = $('#' + STEP_ID_PREFIX + step);
            $el.show();
            window.myGlobal.scrollTo($el);
        };

        var nextStep = function(){
            if(currentStep < totalStep - 1){
                showStep(++currentStep);
            }
            else if(currentStep == totalStep - 1){
                showStep(++currentStep);
                $stepBtnEl.removeClass('blueBtn')
                    .addClass('greenBtn')
                    .val(TEXT_DONE);
            }
            // 已到最后一步
            else{
                opts.beforeSubmit && opts.beforeSubmit();

                var toSubmit = opts.submitCheck ? opts.submitCheck() : true;
                if(toSubmit){
                    opts.customSubmit ? opts.customSubmit() : $formEl.submit();
                }
            }
        };
        
        var init = function($el){
            var step = 1;
            $el.find('.' + STEP_CLASS_NAME).each(function(){
                if(step > 1){
                    $(this).addClass('hide');
                }
                $(this).attr('id', STEP_ID_PREFIX + (step++));
                // NOTE:累计step
                totalStep++;
            });

            // ======================
            // event bind
            $stepBtnEl.click(nextStep);
        };

        // 只能用于表单元素
        if(this.prop('tagName') === 'FORM' || this.prop('tagName') === 'form'){
            return init(this);
        }
    };



    // ================================
    // 表单Editable
    // ================================
    // 用法
    // $formEl.myAjaxEditable({
    //     triggerLink: true or false,  //default is true 表示会在末尾添加edit的link
    //                                     false 表示直接点击文字内容进入编辑状态
    //     url: 'yourAjaxUrl',
    //     extraParam: 'say=hello&id=fuck',  //this field is optional
    //     data: [
    //         {
    //             paramKey: 'xxx',
    //             paramValue: 'xxx' or a function,
    //             replaceValue: 'xxx' or a function,           //optional 替换静态的值
    //             check: function(){ return true or false },   //optional
    //             callback: function(data){                    //optional
    //                 //do someting with ajax return data
    //             }
    //         },
    //         ...
    //     ]
    // });
    // 前置条件：data根据index，要与静态元素的顺序保持一致
    // ================================
    // TODO: css file
    // TODO: 取消后reset input
    $.fn.myAjaxEditable = function(options){
        var EDITABLE_STATIC_CLASS = 'myAjaxEditable-static';
        var EDITABLE_TARGET_CLASS = 'myAjaxEditable-target';
        var EDITABLE_BTN_GROUP_CLASS = 'myAjaxEditable-btn-group';
        var EDITABLE_SAVE_CLASS = 'myAjaxEditable-save';
        var EDITABLE_CANCEL_CLASS = 'myAjaxEditable-cancel';

        var EDITABLE_STATIC_ID_PREFIX = 'myAjaxEditable_static_';
        var EDITABLE_STATIC_VALUE_CLASS = 'myAjaxEditable-static-value';
        var EDITABLE_TRIGGER_LINK_CLASS = 'myAjaxEditable-trigger-link';

        var TEXT_EDITABLE_HINT = window.LANG_TEXT.CLICK_EDIT;
        var TEXT_EDITABLE_LINK = window.LANG_TEXT.EDIT;
        var TEXT_SAVE = window.LANG_TEXT.SAVE;
        var TEXT_CANCEL = window.LANG_TEXT.CANCEL;
        var TEXT_HINT = window.LANG_TEXT.HINT;
        var TEXT_FAIL = window.LANG_TEXT.FAIL;


        var opts = $.extend({
            triggerLink: true
        }, options);

        var EXTRA_PARAM = (function(){
            var param = {};
            if(opts.extraParam){
                var groups = opts.extraParam.split('&');
                for(var i=0; i<groups.length; i++){
                    var temp = groups[i].split('=');
                    param[temp[0]] = temp[1];
                }
            }
            return param;
        })();

        var getDataParam = function(index){
            var param = {};
            var key = opts.data[index].paramKey;
            param[key] = getDataParamValue(index);
            return param;
        };

        var getDataParamValue = function(index){
            return (typeof opts.data[index].paramValue === 'function') ? 
                opts.data[index].paramValue() : opts.data[index].paramValue;
        }

        var getDataReplaceValue = function(index){
            var value = (typeof opts.data[index].replaceValue === 'function') ? 
                opts.data[index].replaceValue() : opts.data[index].replaceValue;
            return (typeof value === 'undefined') ? getDataParamValue(index) : value;
        };

        var getStaticElementId = function(index){
            // 当paramKey不重复时
            // 等同于 EDITABLE_STATIC_ID_PREFIX + opts.data[index].paramKey
            return EDITABLE_STATIC_ID_PREFIX + index;
        };



        var $formEl = this;

        var $btnGroup = $('<div class="' + EDITABLE_BTN_GROUP_CLASS + '" style="display:inline-block;"></div>');
        $btnGroup.append('<input type="button" class="blueBtn ' + EDITABLE_SAVE_CLASS + '" value="' + TEXT_SAVE + '"/>');
        $btnGroup.append('<input type="button" class="basicBtn ' + EDITABLE_CANCEL_CLASS + '" value="' + TEXT_CANCEL + '"/>');


        var focusInput = function($input){
            //TODO:光标到最后
            //FUCK
            $input.focus();
        };

        var showEditable = function($staticEl){
            var $target = $staticEl.siblings('.' + EDITABLE_TARGET_CLASS);
            $target.append($btnGroup.clone());

            $staticEl.hide();
            $target.show();
            // focus要在show后面
            focusInput($target.find('input'));
        };

        var hideEditable = function($targetEl){
            $targetEl.find('.' + EDITABLE_BTN_GROUP_CLASS).remove();
            $targetEl.hide();

            var $staticEl = $targetEl.siblings('.' + EDITABLE_STATIC_CLASS);
            $staticEl.show();
        };

        var onStaticClick = function(){
            // 如果是link触发的话，它父元素即为 $staticEl
            if($(this).hasClass(EDITABLE_TRIGGER_LINK_CLASS)){
                showEditable($(this).parent());
            }
            else{
                showEditable($(this));
            }
        };

        var onCancelClick = function(){
            //TODO: 还原初始输入值
            hideEditable($(this).closest('.' + EDITABLE_TARGET_CLASS));
        };

        var onSaveClick = function(){
            var $target = $(this).closest('.' + EDITABLE_TARGET_CLASS);
            var index = $formEl.find('.' + EDITABLE_TARGET_CLASS).index($target);

            if(opts.data[index].check ? opts.data[index].check() : true){
                var params = $.extend(getDataParam(index), EXTRA_PARAM);
                var callback = opts.data[index].callback;

                $.post(
                    opts.url,
                    params,
                    function(data){
                        var result = $.parseJSON(data);
                        if(result.success){
                            var value = getDataReplaceValue(index);
                            var $el = $('#' + getStaticElementId(index));
                            var $valueEl = $el.find('.' + EDITABLE_STATIC_VALUE_CLASS);

                            if($valueEl.length > 0){
                                $valueEl.text(value);
                            }
                            else{
                                // 如果没有 $valueEl
                                // patch: 有没有trigger link
                                var $link = $el.find('.' + EDITABLE_TRIGGER_LINK_CLASS);
                                if($link.length > 0){
                                    // 保留link再重新绑定
                                    $link.detach();
                                    $el.html(value).append($link);
                                }
                                else{
                                    // 直接替换纯text的html
                                    $el.html(value);
                                }
                            }
                            
                            // 自动隐藏
                            hideEditable($target);
                        }
                        else{
                            jAlert(TEXT_FAIL, TEXT_HINT);
                            // TODO: 给callback去处理error
                        }

                        // custom回调
                        callback && callback(data);
                    }
                );
            }
        };

        var init = function($el){
            $('.' + EDITABLE_TARGET_CLASS).each(function(index){
                var $staticEl = $(this).siblings('.' + EDITABLE_STATIC_CLASS);
                $staticEl.attr('id', getStaticElementId(index));

                if(opts.triggerLink){
                    // append link
                    $staticEl.append(
                        '<a href="javascript:void(0)" class="' + EDITABLE_TRIGGER_LINK_CLASS + '">' + 
                            TEXT_EDITABLE_LINK + 
                        '</a>');
                }
                else{
                    $staticEl.css('cursor', 'pointer').attr('title', TEXT_EDITABLE_HINT);
                    $staticEl.bind('click', onStaticClick);
                }
            });

            $('.' + EDITABLE_TRIGGER_LINK_CLASS).live('click', onStaticClick);
            $('.' + EDITABLE_CANCEL_CLASS).live('click', onCancelClick);
            $('.' + EDITABLE_SAVE_CLASS).live('click', onSaveClick);
        };

        return init(this);
    };



    // ================================
    // 表格Editable
    // ================================
    // 用法
    // $tableEl.myTableEditable({
    //     triggerClass: 'some-edit-link',
    //     url: 'yourAjaxUrl',
    //     rowExtraParam: {
    //         say: 'hello',
    //         someRowId: function($tr){
    //             return $tr.find('someting');
    //         }
    //     },
    //     inputParamKeys: ['param1', 'param2', ...],  //即每个input框的值传到后台时的key
                                                       //input与key数目一致，顺序与表格列顺序一致
    //     rowUpdate: function($tr, param){},  //optional 替换row的新数据
    //     check: function($tr){ return true or false },  //optional
    //     success: function($tr){},  //optional
    //     fail: function($tr){}  //optional
    // });
    // 前置条件：表格中一列只能有一个input框，且表格最后一列为操作按钮
    // ================================
    $.fn.myTableEditable = function(options){
        var EDITABLE_CLASS_NAME = 'myTableEditable';
        var EDITABLE_CELL_HIDE = 'myTableEditable-cell-hide';
        var EDITABLE_CELL_EDIT = 'myTableEditable-cell-edit';
        var EDITABLE_CELL_INPUT = 'myTableEditable-cell-input';
        var EDITABLE_BTN_GROUP = 'myTableEditable-btn-group';
        var EDITABLE_BTN_OK = 'myTableEditable-btn-ok';
        var EDITABLE_BTN_CANCEL = 'myTableEditable-btn-cancel';

        var TEXT_OK = window.LANG_TEXT.OK;
        var TEXT_CANCEL = window.LANG_TEXT.CANCEL;
        var TEXT_ERROR = window.LANG_TEXT.ERROR;
        var TEXT_FAIL = window.LANG_TEXT.FAIL;

        var opts = $.extend({}, options);

        var getRowParams = function($tr){
            var params = {};
            // edit输入框以外的param
            for(var key in opts.rowExtraParam){
                params[key] = (typeof opts.rowExtraParam[key] !== 'function') ? 
                                opts.rowExtraParam[key] : (opts.rowExtraParam[key])($tr);
            }

            // edit输入框里的param
            $.each(opts.cols, function(i, v){
                if(typeof v.getValue === 'function'){
                    params[v.key] = v.getValue($tr.find('td').eq(i));
                }
                else{
                    params[v.key] = $tr.find('td').eq(i).find('input.' + EDITABLE_CELL_INPUT).val();
                }
            });

            return params;
        };

        var updateRow = function($tr){
            var cellNum = $tr.children('td').length;

            $tr.children('td').each(function(i){
                // 除去最后一列（为btn-group）
                if(i < cellNum-1){
                    if(typeof opts.cols[i].update === 'function'){
                        // 使用自定义方式更新col
                        opts.cols[i].update($(this).find('.' + EDITABLE_CELL_HIDE), $(this).find('.' + EDITABLE_CELL_EDIT));
                    }
                    else{
                        // 默认就把input的内容直接替换到原来的td里（纯text的html）
                        var text = $(this).find('input.' + EDITABLE_CELL_INPUT).val();
                        $(this).find('.' + EDITABLE_CELL_HIDE).html(text);
                    }
                }
            });
        };
        

        var ajaxEditRow = function($tr){
            var params = getRowParams($tr);

            if(!opts.check || opts.check($tr)){
                $.post(
                    opts.url,
                    params,
                    function(data){
                        var result = $.parseJSON(data);
                        if(result.success){
                            debugger
                            // opts.rowUpdate($tr, params);
                            updateRow($tr);
                            opts.success && opts.success($tr);
                            closeEditRow($tr);
                        }
                        else{
                            jAlert(TEXT_FAIL, TEXT_ERROR);
                            opts.fail && opts.fail($tr);
                        }
                    }
                );
            }
        };

        var closeEditRow = function($tr){
            var cellNum = $tr.children('td').length;

            $tr.children('td').each(function(i){
                if(i < cellNum - 1){
                    $(this).find('.' + EDITABLE_CELL_EDIT).remove();
                }
                else{
                    $(this).find('.' + EDITABLE_BTN_GROUP).remove();
                }

                // NOTE: wrap里为text时，不能直接unwrap
                // $(this).find('.' + EDITABLE_CELL_HIDE).children().unwrap();
                $(this).html($(this).find('.' + EDITABLE_CELL_HIDE).html());
            });
        };


        var initEditRow = function($tr){
            var cellNum = $tr.children('td').length;
            var rawCellData = [];

            // cellData长度比cellNum少1，最后1格为btn-group
            for(var i=0; i<cellNum-1; i++){
                rawCellData.push($tr.children('td').eq(i).text());
            }

            // 隐藏原来的内容，添加input
            $tr.children('td').each(function(i){
                $(this).wrapInner('<div class="' + EDITABLE_CELL_HIDE + '"></div>');

                if(i < cellNum - 1){
                    $(this).append('<div class="' + EDITABLE_CELL_EDIT + '"></div>');

                    if(typeof opts.cols[i].init === 'function'){
                        // 使用自定义方式初始化input
                        opts.cols[i].init($(this).find('.' + EDITABLE_CELL_HIDE), $(this).find('.' + EDITABLE_CELL_EDIT));
                    }
                    else{
                        // 默认添加input
                        $(this).find('.' + EDITABLE_CELL_EDIT).append(
                            '<input type="text" class="' + EDITABLE_CELL_INPUT + '" value="' + rawCellData[i] + '"/>');
                    }
                }
                else{
                    $(this).append(
                        '<div class="' + EDITABLE_BTN_GROUP + '">' + 
                            '<input type="button" class="blueBtn ' + EDITABLE_BTN_OK + '" value="' + TEXT_OK + '"/>' + 
                            '<input type="button" class="basicBtn ' + EDITABLE_BTN_CANCEL + '" value="' + TEXT_CANCEL + '"/>' +
                        '</div>');
                }
            });

            // bind event
            $tr.find('.' + EDITABLE_BTN_OK).bind('click', function(){
                ajaxEditRow($(this).closest('tr'));
            });
            $tr.find('.' + EDITABLE_BTN_CANCEL).bind('click', function(){
                closeEditRow($(this).closest('tr'));
            });
        };
        
        var init = function($el){
            $el.addClass(EDITABLE_CLASS_NAME);

            $el.find('.' + opts.triggerClass).live('click', function(){
                initEditRow($(this).closest('tr'));
            });
        };

        // 只能用于表单元素
        if(this.prop('tagName') === 'TABLE' || this.prop('tagName') === 'table'){
            // trigger元素存在的情况下
            if(this.find('.' + opts.triggerClass).length > 0){
                return init(this);
            }
        }
    };



    // ================================
    // 自定义分页
    // ================================
    // 用法
    // <div id="xxxPagination" data-pn="1" data-tpn="10"></div>
    // $('#xxxPagination').myPagination();  // 默认使用元素属性里的pn和tpn；默认使用当前location.pathname
    // $('#xxxPagination').myPagination({pn: 5, tpn: 12});  // 手动设置pn和tpn
    // $('#xxxPagination').myPagination({hrefPrefix: '/somepage?aa=xx&page='});  // 自定义分页path
    // $('#xxxPagination').myPagination({pageClick: function(){}});  // 自定义ajax分页
    // ================================
    $.fn.myPagination = function(options){
        var CLASS_PAGINATION = 'pagination';
        var CLASS_PAGE_LIST = 'pages';
        var CLASS_PAGE = 'page';
        var CLASS_PRE_PAGE = 'pre-page';
        var CLASS_NEXT_PAGE = 'next-page';
        var CLASS_PRE_OMIT = 'pre-omit';
        var CLASS_NEXT_OMIT = 'next-omit';

        var CLASS_ACTIVE = 'active';
        var CLASS_DISABLED = 'disabled';
        var CLASS_HIDE = 'hide';

        var TEXT_PRE_PAGE = '&lt;';
        var TEXT_NEXT_PAGE = '&gt;';
        var TEXT_OMIT = '...';
        var TEXT_EMPTY = '&nbsp';


        // default value of 'hrefPrefix'
        var defaultPath = window.location.pathname + '?page=';
        if(/\?(\w+)=/.test(window.location.href)){
            defaultPath = window.location.href + 
                (/page=/.test(window.location.href) ? '' : '&page=');
        }

        // default option values
        var opts = $.extend({
            'pn': this.attr('data-pn') || 1,
            'tpn': this.attr('data-tpn') || 1,
            'hrefPrefix': defaultPath
        }, options);


        // page link
        var getPageHref = function(pageNo){
            return opts.hrefPrefix.replace(/page=\d*/, 'page=' + pageNo);
        };

        // page click
        var targetClick = function($li){
            var $link = $li.find('a.' + CLASS_PAGE);
            if(!$link.hasClass(CLASS_ACTIVE) 
                && !$link.hasClass(CLASS_DISABLED) 
                && !$link.hasClass(CLASS_HIDE)){
                // 是否刷页面
                /^\/|((http|https|svn|ftp|file):\/\/)/.test($link.prop('href')) ? 
                    (window.location.href = $link.prop('href')) : $link.click();
            }
        }


        var generatePages = function($el){
            // ====================================
            // PART-1: initialize empty page list
            // ====================================
            var $list = $('<ul class="' + CLASS_PAGE_LIST + '"></ul>');
            // prePage
            $list.append('<li class="' + CLASS_DISABLED + '"><a href="javascript:void(0);" class="' + CLASS_PRE_PAGE + '">' + TEXT_PRE_PAGE + '</a></li>');
            // firstPage
            $list.append('<li class="' + CLASS_ACTIVE + '"><a href="javascript:void(0);" class="' + CLASS_PAGE + '" data-page="1">1</a></li>');
            // preOmit
            $list.append('<li class="' + CLASS_DISABLED + '"><a href="javascript:void(0);" class="' + CLASS_PRE_OMIT + '">' + TEXT_OMIT + '</a></li>');
            // nextOmit
            $list.append('<li class="' + CLASS_DISABLED + '"><a href="javascript:void(0);" class="' + CLASS_NEXT_OMIT + '">' + TEXT_OMIT + '</a></li>');
            // lastPage
            $list.append('<li class=""><a href="javascript:void(0);" class="' + CLASS_PAGE + '" data-page="">' + TEXT_EMPTY + '</a></li>');
            // nextPage
            $list.append('<li class="' + CLASS_DISABLED + '"><a href="javascript:void(0);" class="' + CLASS_NEXT_PAGE + '">' + TEXT_NEXT_PAGE + '</a></li>');
            // append to list
            $el.empty().append($list);

            // ======================================================
            // PART-2: generate specific pages with a sliding-window
            // ======================================================
            var $dummies = $list.children('li');
            var dummyLen = $dummies.length;

            // targets reference
            var $firstPage = $dummies.eq(1);
            var $lastPage = $dummies.eq(dummyLen-2);
            var $prePage = $dummies.first();  // eq(0)
            var $nextPage = $dummies.last();  // eq(dummyLen-1)
            var $preOmitted = $dummies.eq(2);
            var $nextOmitted = $dummies.eq(dummyLen-3);

            // a copy for dynamic page, initial
            var $pageCopy = $firstPage.clone().removeAttr('class');

            // page number region
            var pn = Number(opts.pn);
            var tpn = Number(opts.tpn);
            var lowerPn = Math.max(pn-2, 2);  // 第1页已写在html中
            var upperPn = Math.min(pn+2, tpn-1);  //最后1页已写在html中

            // generate new specific pages（第2到最后第2页）
            for(var i=lowerPn; i<=upperPn; i++){
                var $newPage = $pageCopy.clone();
                $newPage.find('a').attr('data-page', i).text(i);
                $newPage.insertBefore($nextOmitted);
                // add current page flag
                i == pn && $newPage.addClass(CLASS_ACTIVE);
            }

            // check first page
            $firstPage.removeClass(CLASS_ACTIVE);
            pn == 1 && $firstPage.addClass(CLASS_ACTIVE);

            // check last page
            $lastPage.find('a').attr('data-page', tpn).text(tpn);
            $lastPage.removeClass(CLASS_ACTIVE);
            pn == tpn && $lastPage.addClass(CLASS_ACTIVE);

            // check if last page equals first page
            $lastPage.removeClass(CLASS_HIDE)
            tpn == 1 && $lastPage.addClass(CLASS_HIDE);

            // check Previous page
            $prePage.attr('class', CLASS_DISABLED);
            pn > 1 && $prePage.removeClass(CLASS_DISABLED);
            
            // check Next page
            $nextPage.attr('class', CLASS_DISABLED);
            pn < tpn && $nextPage.removeClass(CLASS_DISABLED);

            // check pre omitted
            $preOmitted.removeClass(CLASS_HIDE);
            lowerPn <= 2 && $preOmitted.addClass(CLASS_HIDE);

            // check next omitted
            $nextOmitted.removeClass(CLASS_HIDE);
            upperPn == tpn-1 && $nextOmitted.addClass(CLASS_HIDE);
        };


        var bindPageEvents = function($el){
            // Previous page
            $el.find('.' + CLASS_PRE_PAGE).click(function(){
                if($(this).parent().hasClass(CLASS_DISABLED)){
                    return false;
                }
                var $target = $el.find('li.' + CLASS_ACTIVE).prev();
                // omitted patch
                if($target.hasClass(CLASS_DISABLED)){
                    $target = $target.prev();
                    if($target.hasClass(CLASS_DISABLED)){
                        $target = $target.prev();
                    }
                }
                targetClick($target);
            });

            // Next page
            $el.find('.' + CLASS_NEXT_PAGE).click(function(){
                if($(this).parent().hasClass(CLASS_DISABLED)){
                    return false;
                }
                var $target = $el.find('li.' + CLASS_ACTIVE).next();
                // omitted patch
                if($target.hasClass(CLASS_DISABLED)){
                    $target = $target.next();
                    if($target.hasClass(CLASS_DISABLED)){
                        $target = $target.next();
                    }
                }
                targetClick($target);
            });

            // =================================================
            // PART-3: bind 'click' or 'href' of specific pages
            // =================================================
            if('pageClick' in opts){
                $el.find('li').not('.' + CLASS_ACTIVE).find('a.' + CLASS_PAGE)
                    .unbind('click').bind('click', opts.pageClick);
            }
            if('hrefPrefix' in opts){
                $el.find('a.' + CLASS_PAGE).each(function(){
                    $(this).attr('href', getPageHref($(this).attr('data-page')));
                });
            }
        };


        var init = function($el){
            $el.addClass(CLASS_PAGINATION);
            generatePages($el);
            bindPageEvents($el);
        };

        return init(this);
    };



    // ================================
    // 自定义添加条目数目
    // ================================
    // 用法 demo
    // <div id="parentDiv">
    //     <div class="children-item">
    //         <!-- label元素以及input select等 -->
    //     </div>
    // </div>
    // $('#parentDiv').myCustomAppend({
    //     addLink: '#yourAddItemLink',
    //     numLabel: 'label',      // parentDiv的每个children中如何找到label
    //                             // 即$('#parentDiv').children().find(numLabel)
    //     numText: 'alphabet',    // label格式 'alphabet'或'number' optional (default='number')
    //     maxNum: 10,             // 最大条目数 optional (default=10) 
    //     minNum: 2,              // 最小条目数 optional (default=1)
    //     initNum: 2              // 初始条目数 optional (default=children数)
    // });
    // ================================
    $.fn.myCustomAppend = function(options){
        var CLASS_REMOVE_LINK = 'myCustomAppend-remove-link';
        var TEXT_REMOVE = window.LANG_TEXT.DELETE;
        var TEXT_HINT = window.LANG_TEXT.HINT;

        var initChildrenNum = this.children().length;
        var $removeCopy = $('<a href="javascript:void(0)" class="' + CLASS_REMOVE_LINK + '">' + TEXT_REMOVE + '</a>');


        var opts = $.extend({
            numText: 'number',
            maxNum: 10,
            minNum: 1,
            initNum: initChildrenNum
        }, options);

        // storage: 数目状态
        var curNum;


        var getNextLabel = function($el){
            var temp = $el.children().last().find(opts.numLabel).text();
            var curLabel = temp.split('.')[0];

            // 字母序
            if(opts.numText === 'alphabet'){
                return String.fromCharCode(curLabel.charCodeAt(0) + 1) + '.';
            }
            // 数字序
            else if(opts.numText === 'number'){
                return (Number(curLabel) + 1) + '.';
            }
            // 无序号
            else{
                return '';
            }
        };

        var initItemInput = function($item){
            $item.find('input').each(function(){
                $(this).val('');
            });
            // TODO: select, checkbox, radio, textarea
        };


        var addChildren = function($el){
            if(curNum >= opts.maxNum){
                jAlert(window.LANG_TEXT.MAX_ITEMS(opts.maxNum), TEXT_HINT);
                return false;
            }

            var $copy = $el.children().last().clone();
            $copy.find(opts.numLabel).text(getNextLabel($el));
            initItemInput($copy);

            // 添加removeLink
            if(curNum >= opts.minNum && $copy.find('.' + CLASS_REMOVE_LINK).length == 0){
                $copy.append($removeCopy);
            }

            $el.append($copy);
            curNum++;
        };

        var removeChildren = function($el, $item){
            var removeIndex = $item.index();
            var $childrens = $el.children();

            // label逐个交换
            for(var i=curNum-1; i>=removeIndex+1; i--){
                $childrens.eq(i).find(opts.numLabel).text(
                    $childrens.eq(i-1).find(opts.numLabel).text());
            }

            $item.remove();
            curNum--;
        };


        var init = function($el){
            var childrenNum = $el.children().length;
            // 保持数量一致
            if(childrenNum < opts.initNum){
                // TODO: addChildren补齐
            }
            if(childrenNum > opts.initNum){
                // TODO: removeChildren一致
            }
            
            // 数目状态
            curNum = opts.initNum;

            // 添加removeLink
            $el.children().each(function(i){
                if(i+1 > opts.minNum){
                    $(this).append($removeCopy.clone());
                }
            });

            // removeLink绑定
            $el.find('.' + CLASS_REMOVE_LINK).live('click', function(){
                removeChildren($el, $(this).parent());
            });

            // addLink绑定
            $(opts.addLink).unbind('click').bind('click', function(){
                addChildren($el);
            });
        };


        // NOTE: throw Error会阻止其他js执行
        if(this.children().length == 0){
            // throw new Error('No custom item template to append.');
        }
        else if(!opts.addLink){
            // throw new Error('No trigger element.');
        }
        else{
            return init(this);
        }
    };


})(jQuery);
