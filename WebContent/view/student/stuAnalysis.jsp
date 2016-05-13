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
	href="http://mooctest.net//public/images/mooctest/favicon.png">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/itsbrain/main.css?v=20151123">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/common/common.css">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/common/itsbrain-width-full.css">
<link rel="stylesheet" type="text/css"
	href="http://mooctest.net/public/css/others/introjs.css">
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
        
        function jump(){
        	document.toanalysis.submit();
        }
        
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
						<li class="iExam"><a id="analysis" onclick="jump()"><span>考试分析</span></a>
						</li><!-- 如果session无法共用 此处可以使用一个表单 -->
					</ul>
					<form action="<%=request.getContextPath()%>/stuAnalysis" name = "toanalysis" method="post">
						<input type="hidden" name="stu_id" value = "<%=session.getAttribute("stu_id")%>">
					</form>
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
				<h5>考试列表</h5>
			</div>

			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/stu/home" title="主页">主页</a></li>
						<li>考试列表</li>
					</ul>
				</div>
			</div>

			<div class="widget">
				<ul class="static-tabs">
					<li class="active"><a
						href="http://mooctest.net/stu/exam/list?type=all">全部考试</a></li>
					<!-- <li><a href="/stu/exam/list?type=missed">错过的考试</a></li> -->
				</ul>

				<div class="static-tab-container">
					<div class="static-tab-content">
						<table class="table table-striped" id="examTable">
							<thead>
								<tr>
									<th class="span3">考试名称</th>
									<th class="span2">主考老师</th>
									<th class="span3">开始时间</th>
									<th class="span3">结束时间</th>
									<th class="span2">考试时长</th>
									<th class="span2">考试成绩</th>
								</tr>
							</thead>
							<tbody>
								<%JSONArray examArray = (JSONArray)request.getAttribute("examArray");
								int len = examArray.length();
								//{id:,exam_name:,teacher_name:,exam_begin_time:,exam_end_time:,exam_duration:,score:,}
 								for(int i = 0;i<len;i++){
									JSONObject exam = (JSONObject)examArray.get(i);
									String teacher_name = exam.getString("teacher_name");
									String exam_name = exam.getString("exam_name");
									int exam_id = exam.getInt("exam_id");
									String exam_begin_time = exam.getString("exam_begin_time");
									String exam_end_time = exam.getString("exam_end_time");
									int exam_duration =exam.getInt("exam_duration");
									double score = exam.getDouble("score");
									%>
									<tr>
										<td>
											<a href="<%=request.getContextPath()%>/stuExamQue?exam_id=<%=exam_id%>" title="查看考题详情"class="underline problem-analysis-link">
											<%=exam_name %></a>
										</td>
										<td><%=teacher_name %></td>
										<td><%=exam_begin_time %></td>
										<td><%=exam_end_time %></td>
										<td><%=exam_duration %></td>
										<td><%=score %></td>
									</tr>
								<%}%>
							</tbody>
						</table>

						<!-- 错过的考试（不等于没提交的考试） -->
						<!-- 分页暂时去掉
					<div class="pagination" id="examTablePagination" data-pn="1"
						data-tpn="1"></div>  -->
					</div>
				</div>
				<div class="fix"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function(){
	    // ================================
	    // 面包屑导航 global
	    // ================================
	    // NOTE: 与base_teacher.html类似
	    window.BreadCrumbUtil = (function(){
	        // keys
	        var HOME = "主页";
	        var EXAM_LIST = "考试列表";
	        var CLASS_LIST = "班级列表";
	        var PERSONAL_PROFILE = "个人信息";
	        var PERSONAL_PASSWORD = "修改密码";
	
	        // 导航链接
	        var LINKS = {};
	        LINKS[HOME] = '/stu/home';
	        LINKS[EXAM_LIST] = '/stu/exam/list';
	        LINKS[CLASS_LIST] = '/stu/class/list';
	        LINKS[PERSONAL_PROFILE] = '/stu/personal/profile';
	        LINKS[PERSONAL_PASSWORD] = '/stu/personal/password';
	
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
	            'CLASS_LIST': CLASS_LIST,
	            'PERSONAL_PROFILE': PERSONAL_PROFILE,
	            'PERSONAL_PASSWORD': PERSONAL_PASSWORD,
	
	            'COV_MAP': {
	                'branch': '分支',
	                'block': '代码块'
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
	<script type="text/javascript" src="http://mooctest.net/public/js/others/clipboard.min.js"></script>
		<script type="text/javascript">
	$(function(){
	
	    // init
	    new Clipboard('.copy-code', {
	        target: function(el){
	            return el;
	        }
	    });
	
	    // kikbug apk
	    $('.qcode-link').hover(function(event){
	        var $qcode = $('#kikbugQR');
	        // 初始化元素
	        if($qcode.length == 0){
	            $qcode = $('<div id="kikbugQR"><img src="http://mooctest.net/public/images/kikbug/kikbug_apk.jpg"></div>');
	            $qcode.css({
	                width: '120px',
	                height: '120px',
	                border: '1px solid #ccc',
	                position: 'absolute'
	            });
	            $('body').append($qcode);
	        }
	        
	        // 定位到当前位置
	        var offset = $(this).offset();
	        $qcode.css({
	            top: (offset.top - 125) + 'px',
	            left: (offset.left - 10) + 'px',
	            display: 'block'
	        });
	    
	    }, function(){
	        var $qcode = $('#kikbugQR');
	        if($qcode.length > 0){
	            $qcode.hide();
	        }
	    });
	
	    // ================================
	    // 执行
	    // ================================
	    BreadCrumbUtil.update(BreadCrumbUtil.HOME, BreadCrumbUtil.EXAM_LIST);
	
	});
	window.onload=function(){
		alert("changeSuccess");
	}
</script>

</body>
</html>