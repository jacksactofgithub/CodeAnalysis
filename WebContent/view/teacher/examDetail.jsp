<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>慕测平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="慕测,MOOCTEST,慕课编程,自学编程">
<meta name="description"
	content="慕测平台是编程类考试和练习的服务平台，教师可以轻松监管考试流程，学生可以自由练习编程。系统负责编程练习的自动化评估及可视化展现，配合当下红火的MOOC慕课课程，慕测平台将是学生自学编程的好帮手。目前已支持的编程类型有：Python统计编程，Java测试驱动编程，C++编程，Java测试，Jmeter性能测试。">

<link rel="shortcut icon" type="image/png"
	href="http://mooctest.net/public/images/mooctest/favicon.png">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/itsbrain/main.css?v=20151123">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/common/common.css">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/common/itsbrain-width-full.css">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/others/introjs.css">
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/tablesorter/theme.blue.css">
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/tablesorter/jquery.tablesorter.pager.css">
<style type="text/css">
.box {
	height: 300px;
	margin-top: 20px;
	position: relative;
	width: 978px;
}

.box .link {
	float: left;
	height: 280px;
	margin: 0 20px;
	width: 200px;
}

.link .icon {
	display: inline-block;
	height: 180px;
	transition: 0.4s ease-out;
	-ms-transition: 0.4s ease-out;
	-webkit-transition: 0.4s ease-out;
	-moz-transition: 0.4s ease-out;
	-o-transition: 0.4s ease-out;
	width: 100%;
}

.link .icon:hover {
	transform: rotate(360deg) scale(1.2);
	-ms-transform: rotate(360deg) scale(1.2);
	-webkit-transform: rotate(360deg) scale(1.2);
	-moz-transform: rotate(360deg) scale(1.2);
	-o-transform: rotate(360deg) scale(1.2);
}

.link-exam .icon {
	background: url(http://mooctest.net/public/images/exam.png) no-repeat
		center center;
}

.link-exercise .icon {
	background: url(http://mooctest.net/public/images/exercise.png)
		no-repeat center center;
}

.link-personal .icon {
	background: url(http://mooctest.net/public/images/personal.png)
		no-repeat center center;
}

.link-download .icon {
	background: url(http://mooctest.net/public/images/download.png)
		no-repeat center center;
}

.button {
	background: url(http://mooctest.net/public/images/arrow.png) no-repeat
		145px center;
	border: 2px solid rgba(72, 72, 72, 0.8);
	box-sizing: border-box;
	-ms-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	color: #484848;
	display: block;
	font-family: Arial;
	font-weight: bolder;
	font-size: 24px;
	height: 50px;
	line-height: 50px;
	margin: 0 auto;
	transition: 0.4s ease;
	-ms-transition: 0.4s ease;
	-webkit-transition: 0.4s ease;
	-moz-transition: 0.4s ease;
	-o-transition: 0.4s ease;
	padding-left: 15px;
	padding-top: 0;
	position: relative;
	width: 200px;
}

.button:hover {
	background-position: 160px center;
	border: 2px solid rgba(72, 72, 72, 1);
}

.button .line {
	background: none;
	display: block;
	position: absolute;
	transition: 0.4s ease-out;
	-ms-transition: 0.4s ease-out;
	-webkit-transition: 0.4s ease-out;
	-o-transition: 0.4s ease-out;
	-moz-transition: 0.4s ease-out;
}

.button:hover .line {
	background: #484848;
}

.button .line-top {
	height: 2px;
	left: -110%;
	width: 0%;
	top: -2px;
}

.button:hover .line-top {
	left: -2px;
	width: 100%;
}

.button .line-right {
	height: 0%;
	right: -2px;
	top: -110%;
	width: 2px;
}

.button:hover .line-right {
	height: 100%;
	top: -2px;
}

.button .line-bottom {
	bottom: -110%;
	height: 0;
	left: -2px;
	width: 2px;
}

.button:hover .line-bottom {
	bottom: -2px;
	height: 100%;
}

.button .line-left {
	bottom: -2px;
	height: 2px;
	right: -110%;
	width: 0;
}

.button:hover .line-left {
	right: -2px;
	width: 100%;
}

.tip {
	background: #707070;
	border-radius: 3px;
	-ms-border-radius: 3px;
	-webkit-border-radius: 3px;
	-o-border-radius: 3px;
	-moz-border-radius: 3px;
	color: #fff;
	font-size: 18px;
	height: 35px;
	line-height: 35px;
	margin: 0 auto;
	opacity: 0;
	position: absolute;
	padding: 0 14px;
	top: 100px;
	z-index: -9999;
}

.tip em {
	font-style: normal;
}

.tip span {
	border: 7px solid transparent;
	border-top-color: #707070;
	display: block;
	height: 0;
	left: 50%;
	margin-left: -4px;
	overflow: hidden;
	position: absolute;
	top: 35px;
	width: 0;
}
</style>

<!-- 百度统计代码 -->
<script type="text/javascript">
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?20e1ba16c6fd76bd293d234e7ddabe39";
            var s = document.getElementsByTagName("script")[0]; 
            s.parentNode.insertBefore(hm, s);
        })();
        </script>
<body>
	<div id="header" class="wrapper">
		<div id="loginInfo">
			<img src="http://mooctest.net/public/images/userPic.png" alt="" /> <span>欢迎,
				<span class="text-info">卢依宁</span>老师!
			</span> <span>南京大学</span>
		</div>
		<div class="clearfix"></div>
		<div>
			<div id="logo">
				<a href="http://mooctest.net/tea/home" title="返回主页"></a>
			</div>
			<div class="clearfix" id="nav">
				<div class="middleNav pull-left">
					<ul>
						<li class="iExam"><a
							href="http://mooctest.net/tea/exam/create" title=""><span>新建考试</span></a>
						</li>
						<!-- <li class="iUpload">
                        <a href="/tea/upload" title=""><span>上传题目</span></a>
                    </li> -->
						<li class="iFinish"><a
							href="http://mooctest.net/tea/exercise/overview" title=""><span>学生练习</span></a>
						</li>
						<li class="iStats"><a
							href="http://mooctest.net/tea/exercise/overview" title=""><span>考试分析</span></a>
						</li>
					</ul>
				</div>
				<div class="middleNav pull-right">
					<ul>
						<li class="iUser"><a
							href="http://mooctest.net/tea/personal/profile" title=""><span>个人信息</span></a>
						</li>
						<li class="iLogoff"><a href="http://mooctest.net/logout"
							title=""><span>注销用户</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="wrapper">
		<!-- Content -->
		<div class="content">
			<div class="title">
				<h5>考试分析</h5>
			</div>

			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home"
							title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li><a href="http://mooctest.net/tea/home" title="考试分析">考试分析</a></li>
						<li class="firstB">Java覆盖练习1</li>
						<!-- 从request中取得考试信息类中的考试名 -->
					</ul>
				</div>
			</div>

			<div class="widget">
				<div class="head">
					<h5 class="iInfo">考试信息</h5>
				</div>
				<div class="body">
					<ul class="item-list tabled">
						<li class="item">
							<div class="item-name">考试名称</div>
							<div class="item-value">Java覆盖练习1</div>
						</li>
						<li class="item">
							<div class="item-name">考试日期</div>
							<div class="item-value">2015-02-01</div>
						</li>
						<li class="item">
							<div class="item-name">考试人数</div>
							<div class="item-value">10</div>
						</li>
					</ul>
				</div>
			</div>


			<div class="widget">
				<div class="head">
					<h5 class="iUser">考生状态</h5>
				</div>
				<div class="body">
					<div id="examMemberTableWrapper">
					<div class="text-right">
		                <span>搜索学号或姓名</span>
		                <input class="search table-search-input" type="search" data-column="all" placeholder="">
		            </div>
						<table class="table tablesorter" id="examMemberTable">
							<thead>
								<tr>
									<th class="span2">学校</th>
									<th class="span2">学号</th>
									<th class="span2" data-sorter="false">姓名</th>
									<th class="span1" data-empty="zero">总成绩</th>
									<th class="span1" data-empty="zero">题目一得分</th>
									<th class="span1" data-empty="zero">题目二得分</th>
									<th class="span1" data-empty="zero">题目三得分</th>
								</tr>
							</thead>
							<tbody>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>dg1132002</td>
									<td>张智轶</td>

									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>dg1332004</td>
									<td>汪亚斌</td>

									<td><span class="text-success"> <span
											class="final-score">90.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>dg1432006</td>
									<td>孙一</td>

									<td><span class="text-success"> <span
											class="final-score">90.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>dg1432008</td>
									<td>张伟强</td>

									<td><span class="text-success"> <span
											class="final-score">78.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>

								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1232002</td>
									<td>豆梦宇</td>
									<td><span class="text-success"> <span
											class="final-score">84.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>

								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1332003</td>
									<td>冯奕彬</td>


									<td><span class="text-success"> <span
											class="final-score">89.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>

								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1332009</td>
									<td>卢依宁</td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1432002</td>
									<td>郝蕊</td>

									<td><span class="text-success"> <span
											class="final-score">68.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>

								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1432008</td>
									<td>李舒颖</td>
									<td><span class="text-success"> <span
											class="final-score">74.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>

								</tr>
								<tr data-mem="0">
									<td>南京大学</td>
									<td>mg1432009</td>
									<td>刘子聪</td>
									<td><span class="text-success"> <span
											class="final-score">91.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
									<td><span class="text-success"> <span
											class="final-score">83.0</span> 分
									</span></td>
								</tr>

							</tbody>
						</table>
						<!-- 这里是排序的页面<div class="text-center" id="examMemberTablePager"></div>  -->
					</div>
				</div>
			</div>

		</div>
	</div>


        <!-- 全局多语言文案，供通用js使用 -->
        <script type="text/javascript">
        window.LANG_TEXT = {
            OK: "确定",
            CANCEL: "取消",
            DONE: "完成",
            SAVE: "保存",
            EDIT: "修改",
            CLICK_EDIT: "点击修改",
            CLOSE: "关闭",
            HINT: "提示",
            FAIL: "操作失败",
            ERROR: "错误",
            DELETE: "删除",
            MAX_ITEMS: function(num){
                var preCompiled = "最多添加[null]项";
                return preCompiled.replace('[null]', num);
            },
            NO_HINT: "下次不再提示",  //en版本里有单引号
            NEXT_STEP: '下一步',
            PREV_STEP: '上一步',
            I_KNOW: '知道了'
        };

        // for SchoolBox.js
        window.SCHOOL_TEXT = {
            SELECT_SCHOOL: "选择学校",
            SEARCH: "搜索",
            NO_SCHOOL_FOUND: "找不到该学校",
            ADD_SCHOOL_HINT: "没找到？点击添加学校",
            ADD_SCHOOL: "点击添加学校",
            SCHOOL_NAME_INVALID: "学校名称不能超过50个字符",
            ADD_SCHOOL_FAIL: "添加学校失败",
            OTHER: "其他",
            OK: "确定",
            CANCEL: "取消",
            CLOSE: "关闭",
            HINT: "提示"
        };
        </script>

        <!-- jquery 1.7.2 业内最稳定版本 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/jquery/jquery-1.7.2.min.js"></script>

        <!-- jquery UI（ui_custom.css做过改动） -->
        <script type="text/javascript" src="http://mooctest.net/public/js/jquery-ui/jquery-ui-1.8.24.min.js"></script>
                <script type="text/javascript" src="http://mooctest.net/public/js/jquery-ui/jquery-ui-datepicker-zh-CN.js"></script>
        
        <!-- 借鉴bootstrap模态框 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/bootstrap/bootstrap-modal-2.3.2.js"></script>

        <!-- form validation -->
        <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.validationEngine-2.6.2.js"></script>
                <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.validationEngine-zh_CN.js"></script>
        
        <!-- 消息队列 + tooltip + 弹框 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.jgrowl.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.tipsy.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.alerts.js"></script>

        <!-- 面包屑导航 + 返回顶部 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/jBreadCrumb.1.1.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/jquery.ToTop.js"></script>

        <!-- my common可复用 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/common/application-util.js?v=20151204"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/common/application-execution.js"></script>

        <!-- cookie -->
        <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.cookie.1.4.1.js"></script>

        <!-- itsbrain精简后的执行 -->
        <script type="text/javascript">
        $(function(){

            //===== ToTop =====//
            $().UItoTop({ easingType: 'easeOutQuart' });


            //===== Tooltip =====//
            $('.leftDir').tipsy({fade: true, gravity: 'e'});
            $('.rightDir').tipsy({fade: true, gravity: 'w'});
            $('.topDir').tipsy({fade: true, gravity: 's'});
            $('.botDir').tipsy({fade: true, gravity: 'n'});


            //===== Tabs =====//
            $.fn.simpleTabs = function(){ 
                //Default Action
                $(this).find(".tabContent").hide(); //Hide all content
                $(this).find("ul.tabs li:first").addClass("activeTab").show(); //Activate first tab
                $(this).find(".tabContent:first").show(); //Show first tab content
            
                //On Click Event
                $("ul.tabs li").click(function() {
                    $(this).parent().parent().find("ul.tabs li").removeClass("activeTab"); //Remove any "active" class
                    $(this).addClass("activeTab"); //Add "active" class to selected tab
                    $(this).parent().parent().find(".tabContent").hide(); //Hide all tab content
                    var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
                    $(activeTab).show(); //Fade in the active content
                    return false;
                });
            };
            //Run function on any div with class name of "widget"
            $("div[class^='widget']").simpleTabs();
            

            //===== alerts按钮文字 =====//
            $.alerts.okButton = window.LANG_TEXT.OK;
            $.alerts.cancelButton = window.LANG_TEXT.CANCEL;

        });
        </script>
        
        <script type="text/javascript">
$(function(){
    // ================================
    // 面包屑导航 global
    // ================================
    window.BreadCrumbUtil = (function(){
        var TEXT_CODE_BRANCH = '分支',
            TEXT_CODE_BLOCK = '代码块';

        // keys
        var HOME = "主页";
        var EXAM_LIST = "考试列表";
        var EXAM_CREATE = "新建考试";
        var EXAM_VIEW = "考试";
        var CLASS_LIST = "班级列表";
        var CLASS_CREATE = "新建班级";
        var CLASS_VIEW = "班级";
        var PROBLEM_UPLOAD = "上传题目";
        var PERSONAL_PROFILE = "个人信息";
        var PERSONAL_PASSWORD = "修改密码";
        var QUIZ_LIST = "小测列表";
        var QUIZ_VIEW = "小测";
        var QUIZ_CREATE = "小测预备";
        var EXERCISE_OVERVIEW = '练习科目';
        var APP_TEST_ORDER = 'App预约';
        var APP_TEST_ORDER_CREATE = '新预约';

        // 导航链接
        var LINKS = {};
        LINKS[HOME] = '/tea/home';
        LINKS[EXAM_LIST] = '/tea/exam/list';
        LINKS[EXAM_CREATE] = '/tea/exam/create';
        LINKS[EXAM_VIEW] = '#';
        LINKS[CLASS_LIST] = '/tea/class/list';
        LINKS[CLASS_CREATE] = '/tea/class/create';
        LINKS[CLASS_VIEW] = '#';
        LINKS[PROBLEM_UPLOAD] = '/tea/upload';
        LINKS[PERSONAL_PROFILE] = '/tea/personal/profile';
        LINKS[PERSONAL_PASSWORD] = '/tea/personal/password';
        LINKS[QUIZ_LIST] = '/tea/quiz/list';
        LINKS[QUIZ_VIEW] = '#';
        LINKS[QUIZ_CREATE] = '/tea/quiz/create';
        LINKS[EXERCISE_OVERVIEW] = '/tea/exercise/overview';
        LINKS[APP_TEST_ORDER] = '/tea/apptest/orderList';
        LINKS[APP_TEST_ORDER_CREATE] = '#';

        // 元素
        var $target = $('.breadCrumb');
        var $list = $('#breadCrumbList');
        var $firstB = $list.children().first();

        var clearExceptFirst = function(){
            $list.children().not($firstB).remove();
        };

        var getItemKey = function(item){
            if(typeof item === 'string'){
                return item;
            }
            return item[0];
        };

        var getItemLink = function(item){
            if(typeof item === 'string'){
                return LINKS[item];
            }
            return item[1];
        };

        var generateListItem = function(item, enable){
            var $el = $('<li>' + getItemKey(item) + '</li>');
            if(enable){
                $el.wrapInner('<a href="' + getItemLink(item) + '"></a>');
            }
            $list.append($el);
        };

        return {
            'HOME': HOME,
            'EXAM_LIST': EXAM_LIST,
            'EXAM_CREATE': EXAM_CREATE,
            'EXAM_VIEW': EXAM_VIEW,
            'CLASS_LIST': CLASS_LIST,
            'CLASS_CREATE': CLASS_CREATE,
            'CLASS_VIEW': CLASS_VIEW,
            'PROBLEM_UPLOAD': PROBLEM_UPLOAD,
            'PERSONAL_PROFILE': PERSONAL_PROFILE,
            'PERSONAL_PASSWORD': PERSONAL_PASSWORD,
            'QUIZ_LIST': QUIZ_LIST,
            'QUIZ_VIEW': QUIZ_VIEW,
            'QUIZ_CREATE': QUIZ_CREATE,
            'EXERCISE_OVERVIEW': EXERCISE_OVERVIEW,
            'APP_TEST_ORDER': APP_TEST_ORDER,
            'APP_TEST_ORDER_CREATE': APP_TEST_ORDER_CREATE,

            'COV_MAP': {
                'branch': TEXT_CODE_BRANCH,
                'block': TEXT_CODE_BLOCK
            },

            /* arguments
                []: 隐藏导航
                [HOME, EXAM_LIST or [yourKey, yourLink], ...]: 面包屑路径
            */
            update: function(){
                clearExceptFirst();
                var len = arguments.length;

                if(len == 0){
                    $target.hide();
                }
                if(len > 0){
                    $target.show();
                }
                if(len > 1){
                    for(var i=1; i<len; i++){
                        generateListItem(arguments[i], i<len-1 ? true : false);
                    }
                }
            }
        };
    })();

});
</script>
<script type="text/javascript" src="http://mooctest.net/public/js/tablesorter/jquery.tablesorter-2.17.7.min.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/tablesorter/jquery.tablesorter.widgets-2.17.7.min.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/tablesorter/jquery.tablesorter.pager-2.17.6.min.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/highcharts-4.0.4/highcharts.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/common/AjaxChart.js"></script>
<script type="text/javascript">
$(function(){
    // ================================
    // 日期时间
    // ================================
    $('.datepicker').datepicker({ 
        defaultDate: +7,
        autoSize: true,
        dateFormat: 'yy-mm-dd',
    });

    // ================================
    // 表单Editable
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();

        var ExamTimeUtil = {
            /* 与当前时间比较
                after当前时间，return true
                else, return false
            */
            checkTimeWithNow: function(timeString){
                var date = new Date(timeString);
                var now = new Date();
                if(myGlobal.compareDate(date, now) > 0){
                    return true;
                }
                return false;
            }
        };

        var syncCheckExamName = function(examName){
            var result = false;
            $.ajax({
                type: 'POST',
                async: false,
                url: '/tea/exam/ajaxCheckExamNameWhenEdit',
                data: {
                    examId: curExamId,
                    newName: examName
                },
                success: function(data){
                    result = ($.parseJSON(data)).success;
                }
            });
            return result;
        };

        $('#examForm').myAjaxEditable({
            url: '/tea/exam/ajaxEditExam',
            extraParam: 'examId=' + curExamId,
            data: [
                {
                    paramKey: 'examName',
                    paramValue: function(){
                        return $('#examName').val();
                    },
                    check: function(){
                        var newName = this.paramValue();
                        // static check
                        if($.trim(newName).length == 0){
                            jAlert('考试名称不能为空', '提示');
                            return false;
                        }
                        if($.trim(newName).length > 15){
                            jAlert('考试名称不能超过15个字符', '提示');
                            return false;
                        }
                        // NOTE:同步ajax check
                        if(syncCheckExamName(newName) == false){
                            jAlert('您已创建过相同名称的考试了！', '提示');
                            return false;
                        }
                        return true;
                    }
                },
                {
                    paramKey: 'startTime',
                    paramValue: function(){
                        var timeString = myGlobal.getDateTimeString($('#examStartDate').val(), 
                                $('#examStartTimeHH').val(), $('#examStartTimeMM').val());
                        return timeString
                    },
                    check: function(){
                        var timeString = this.paramValue();
                        if(typeof timeString === 'undefined'){
                            jAlert('请选择考试开始时间', '提示');
                            return false;
                        }
                        if(ExamTimeUtil.checkTimeWithNow(timeString) == false){
                            jAlert('考试开始时间已过，请重新选择', '提示');
                            return false;
                        }
                        // TODO: 与结束时间的比较
                        return true;
                    }
                },
                {
                    paramKey: 'duration',
                    paramValue: function(){
                        return $('#examDuration').val();
                    },
                    check: function(){
                        // TODO: 与(结束-开始)时间的比较
                        return true;
                    }
                },
                {
                    paramKey: 'endTime',
                    paramValue: function(){
                        var timeString = myGlobal.getDateTimeString($('#examEndDate').val(), 
                                $('#examEndTimeHH').val(), $('#examEndTimeMM').val());
                        return timeString
                    },
                    check: function(){
                        var timeString = this.paramValue();
                        if(typeof timeString === 'undefined'){
                            jAlert('请选择考试结束时间', '提示');
                            return false;
                        }
                        if(ExamTimeUtil.checkTimeWithNow(timeString) == false){
                            jAlert('考试结束时间已过，请重新选择', '提示');
                            return false;
                        }
                        // TODO: 与开始时间的比较
                        return true;
                    }
                }
            ]
        });
    })();
    }

    // ================================
    // 延长考试
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();

        var displayTarget = function($el, command){
            if(command === 'hide'){
                $el.addClass('hide');
            }
            else{
                $el.removeClass('hide');
            }
        };

        $('#expandDurationLink').click(function(){
            displayTarget($('#expandDurationTarget'));
        });
        $('#expandDurationCancel').click(function(){
            displayTarget($('#expandDurationTarget'), 'hide');
        });

        $('#expandDurationOk').click(function(){
            $.post(
                '/tea/exam/ajaxExpandExamDuration',
                {
                    examId: curExamId,
                    minutes: Number($('#expandDurationSelect').val())
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        jAlert('延长时间成功', '提示');
                        // 成功则刷新页面
                        window.location.href = window.location.href;
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }
                }
            );
            displayTarget($('#expandDurationTarget'), 'hide');
        });

        // TODO:延迟结束时间
    })();
    }



    // ================================
    // 考生表格
    // ================================
    (function(){
        var $sTable = $('#examMemberTable').tablesorter({
            theme: 'blue',
            widgets: ["zebra", "filter"],
            widgetOptions : {
                // filter_anyMatch replaced! Instead use the filter_external option
                // Set to use a jQuery selector (or jQuery object) pointing to the
                // external filter (column specific or any match)
                filter_external : '.table-search-input',
                // include column filters
                filter_columnFilters: false,
                filter_saveFilters : false
            }

        }).tablesorterPager({
            // target the pager markup - see the HTML block below
            container: '#examMemberTablePager',
            // output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows} 或{filteredRows}
            output: '第{page}页 / 共{filteredPages}页',
            // starting page of the pager (zero based index)
            page: 0,
            // Number of visible rows - default is 10
            size: 10,
            // Save pager page & size if the storage script is loaded
            savePages : false

        }).bind('filterEnd', function(e, filter){
            // 被过滤掉后页面长度突然变短回缩，通过滚动减缓
            myGlobal.scrollTo($('#examMemberTableWrapper'));
        });

    })();


    
    // ================================
    // 成绩统计
    // ================================
    if($('#statisticsDiv').length > 0){
    (function(){
        var data = [];
        var num = 0;
        var sum = 0;
        var max = -1;
        var min = 9999;
        var average = 0;
        var median = 0;  //中位数
        var variance = 0;  //方差
        var deviation = 0;  //标准差

        // 第一遍扫描，放入缓存，并求num, sum, max, min, average
        $('#examMemberTable').find('tbody tr').each(function(){
            var score;
            try{
                score = Number($(this).find('.final-score').text());
            }
            catch(e){
                score = 0;
            }
            
            num++;
            sum += score;
            if(score > max){
                max = score;
            }
            if(score < min){
                min = score;
            }

            data.push(score);
        });

        average = (sum / num).toFixed(1);

        // 第二遍扫描，求方差
        for(var i=0; i<data.length; i++){
            variance += Math.pow((data[i] - average), 2);
        }

        variance = variance / num;
        deviation = Math.sqrt(variance).toFixed(1);

        // 第三遍扫描，快速排序，求中位数
        data = myGlobal.quickSort(data);
        median = data[Math.floor(data.length / 2)];

        // show
        $('#statisTotalNum').text(num);
        $('#statisAverage').text(average);
        $('#statisMax').text(max);
        $('#statisMin').text(min);
        $('#statisMedian').text(median);
        $('#statisDeviation').text(deviation);


        // *********
        // 柱状图
        // *********
        var scoreChart = new AjaxChart({
            wrapperId: 'scoreRegionChart',
            chartType: 'column'
        });

        // 手动更新数据（非ajax）
        var REGION_NUM = 10;
        var REGION_SIZE = 100 / REGION_NUM;
        var regionData = [];

        // 第四遍扫描（排序后），分区间
        var regionIndex = 0;
        var regionTempCount = 0;
        for(var i=0; i<data.length; i++){
            if(data[i] < REGION_SIZE * (regionIndex + 1)
                || (data[i] <= REGION_SIZE * (regionIndex + 1))
                        && regionIndex == REGION_NUM - 1){
                regionTempCount++;
            }
            else{
                regionData[regionIndex] = regionTempCount;
                regionIndex++;
                regionTempCount = 0;
                // 在下一个区间里重新扫描该值
                i--;
            }
        }

        // patch: 循环结束时的最后一个区间
        regionData[regionIndex] = regionTempCount;

        // 用0补齐
        for(var i=regionIndex+1; i<REGION_NUM; i++){
            regionData[i] = 0;
        }
        
        // x轴区间标示
        var xLabels = [];
        for(var i=0; i<REGION_NUM; i++){
            var low = i * REGION_SIZE;
            var high = (i + 1) * REGION_SIZE - 1;
            // 最后一列把100分也包含进去
            if(i == REGION_NUM - 1){
                high += 1;
            }
            xLabels.push(low + '~' + high);
        }

        // 非ajax更新chart
        scoreChart.refresh({
            chartData: {
                series: [{
                    name: '全体',
                    data: regionData
                }],
                categories: xLabels
            }
        });

    })();
    }


    // ================================
    // 考生导出、成绩分析、邮件通知
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();
        var ajaxFlag = false;  //是否进行中

        var ajaxScoreAnalysis = function(){
            // onAjaxStart
            $('#loadingIcon').show();
            ajaxFlag = true;

            $.post(
                '/tea/exam/ajaxDoScoreAnalysis',
                {
                    examId: curExamId
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        jAlert('成绩分析成功', '提示', function(){
                            // 刷新当前页面
                            window.location.href = window.location.href;
                        });
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }

                    // onAjaxComplete
                    $('#loadingIcon').hide();
                    ajaxFlag = false;
                }
            );
        };

        $('#scoreAnalysisLink').click(function(){
            // 如果ajax正在进行中，阻止用户连续点击
            if(ajaxFlag){
                return false;
            }
            jConfirm('成绩分析后该考试将被关闭，所有原始数据将不可修改，<br>' + 
                '确定要进行成绩分析吗？', '提示', function(r){
                if(r){
                    ajaxScoreAnalysis();
                }
            });
        });

        $('#closedLink').click(function(){
            jAlert('您已经进行过成绩分析', '提示');
        });



        // *************
        var ajaxSendExamEmail = function(ajaxUrl){
            // onAjaxStart
            $('#emailLoadingIcon').show();
            ajaxFlag = true;

            $.post(
                ajaxUrl,
                {
                    examId: curExamId
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        var count = result.extra.split('|');
                        jAlert('成功发送 ' + count[0] + ' 封邮件，失败 ' + count[1] + ' 封', '提示');
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }

                    // onAjaxComplete
                    $('#emailLoadingIcon').hide();
                    ajaxFlag = false;
                }
            );
        };

        $('#sendSecretCodeLink').click(function(){
            if(ajaxFlag){
                return false;
            }
            ajaxSendExamEmail('/tea/exam/ajaxSendSecretCodeEmail');
        });

        $('#sendFinalScoreLink').click(function(){
            if(ajaxFlag){
                return false;
            }
            ajaxSendExamEmail('/tea/exam/ajaxSendFinalScoreEmail');
        });
    })();
    }

});
</script>
<script type="text/javascript">
$(function(){
    // ================================
    // 执行
    // ================================
    BreadCrumbUtil.update(BreadCrumbUtil.HOME, BreadCrumbUtil.EXAM_LIST, BreadCrumbUtil.EXAM_VIEW);

});
</script>
</body>
</html>