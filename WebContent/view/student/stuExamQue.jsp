<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
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
<style type="text/css">
.problem-section{
    margin: 10px 0;
}
.problem-section .info{

}
.problem-section .detail{
    margin-top: -1px;
    position: relative;
}
.problem-section table{
    border: 1px solid #ccc;
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
</head>
<body>
	<!-- Header -->
	<div id="header" class="wrapper">
		<div id="loginInfo">
			<img src="http://mooctest.net/public/images/userPic.png" alt="" /> <span>Welcome,
				<span class="text-info"><%=session.getAttribute("stu_name") %></span>student!
			</span> <span><%=session.getAttribute("uni_name") %></span>
		</div>

		<div class="clearfix"></div>
		<div>
			<div id="logo">
				<a href="http://mooctest.net/stu/home" title="返回主页"></a>
			</div>
			<div class="clearfix" id="nav">
				<div class="middleNav pull-left">
					<ul>
						<li class="iStats"><a
							href="http://112.124.1.3:8050/?email=lshuo12@software.nju.edu.cn&vcode=4ac130b1c76c7858d41070528e4c3294"
							title="" target="_blank"><span>Python统计</span></a></li>
						<li class="iExam"><a href="http://mooctest.net/stu/exam/list"
							title=""><span>My Exam</span></a></li>
						<li class="iClass"><a
							href="http://mooctest.net/stu/class/list" title=""><span>我的班级</span></a>
						</li>
						<li class="iExam"><a
							href="<%=request.getContextPath() %>/stuAnalysis" title=""><span>考试分析</span></a>
						</li>
					</ul>
				</div>
				<div class="middleNav pull-right">
					<ul>
						<li class="iUser"><a
							href="http://mooctest.net/stu/personal/profile" title=""><span>Profile</span></a>
						</li>
						<li class="iLogoff"><a href="http://mooctest.net/logout"
							title=""><span>Logout</span></a></li>
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
			
			<%
				JSONObject exam = ((JSONObject)request.getAttribute("exam"));
			%>
			
			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 参数可能更改 -->
						<li ><a href="<%=request.getContextPath() %>/stuAnalysis" title="考试分析">考试分析</a></li><!-- 同一工程下的链接跳转 -->
						<li class="firstB"><%=exam.getString("exam_name") %></li>
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
							<div class="item-value"><%=exam.getString("exam_name") %></div>
						</li>
						<li class="item">
							<div class="item-name">考试日期</div>
							<div class="item-value">
							<%=(exam.getString("exam_begin_time")).split(" ")[0]%></div>
						</li>
					</ul>
				</div>
			</div>

			<div class="widget">
				<div class="head">
					<h5 class="iBook">考题列表</h5>
				</div>
				<div class="body">
				
					<% JSONArray problemArray = (JSONArray)request.getAttribute("problemArray");
					int len = problemArray.length();
					for(int i=0;i<len;i++){
						JSONObject problem = problemArray.getJSONObject(i);%>
					<div class="problem-section">
						<div class="info">
							<ul class="item-list">
								<li class="item">
									<div class="item-name">第<%=i+1 %>题</div>
									<!--<div class="item-value">
										 <span>考题类型：Java覆盖测试；</span> <span>权重：30%</span>  
									</div>-->
								</li>
							</ul>
						</div>
						<div class="detail">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="span2">考题名称</th>
										<th>难度</th>
										<th>得分</th>
										<th>平均分</th>
										<th>考题描述</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<!-- 此处跳转至考生的具体题目代码分析 -->
										<td><a href="<%=request.getContextPath() %>/stuAnalysisResult?exam_name=<%=exam.getString("exam_name")  %>&problem_name=<%=problem.getString("problem_name")%>"
											class="underline" title="查看考题统计"><%=problem.getString("problem_name") %></a></td>
										<td><%=problem.getString("difficulty") %></td>
										<td><%=problem.getString("score") %></td>
										<td><%=problem.getDouble("ave_score")%></td>
										<td><%=problem.getString("description")%></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<%} %>
					
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
        var EXAM_LIST = "考试分析";
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
        LINKS[EXAM_LIST] = '<%=request.getContextPath() %>/stuAnalysis';
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
<script type="text/javascript">
$(function(){

    // ================================
    // 执行
    // ================================
    BreadCrumbUtil.update(
        BreadCrumbUtil.HOME, BreadCrumbUtil.EXAM_LIST, 
        ['<%=((JSONObject)request.getAttribute("exam")).getString("exam_name")%>', '#']
    );

});
</script>
</body>
</html>