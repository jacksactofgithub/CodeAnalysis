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
        <img src="http://mooctest.net/public/images/userPic.png" alt=""/>
        <span>欢迎, <span class="text-info">卢依宁</span>老师! </span>
        <span>南京大学</span>
    </div>
    <div class="clearfix"></div>
    <div>
        <div id="logo">
        	<a href="http://mooctest.net/tea/home" title="返回主页"></a>
        </div>
        <div class="clearfix" id="nav">
            <div class="middleNav pull-left">
                <ul>
                    <li class="iExam">
                        <a href="http://mooctest.net/tea/exam/create" title=""><span>新建考试</span></a>
                    </li>
                    <!-- <li class="iUpload">
                        <a href="/tea/upload" title=""><span>上传题目</span></a>
                    </li> -->
                    <li class="iFinish">
                        <a href="http://mooctest.net/tea/exercise/overview" title=""><span>学生练习</span></a>
                    </li>
                    <li class="iStats">
                        <a href="http://mooctest.net/tea/exercise/overview" title=""><span>考试分析</span></a>
                    </li>
                </ul>
            </div>
            <div class="middleNav pull-right">
                <ul>
                    <li class="iUser">
                        <a href="http://mooctest.net/tea/personal/profile" title=""><span>个人信息</span></a>
                    </li>
                    <li class="iLogoff">
                        <a href="http://mooctest.net/logout" title=""><span>注销用户</span></a>
                    </li>
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
									<th class="span3">开始时间</th>
									<th class="span3">结束时间</th>
									<th class="span2">考试时长</th>
									<th class="span2">平均得分</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><!-- 此处id是从后台得到 -->
										<a href="/tea/analysis/exam?id=51" class="underline problem-analysis-link">2015性能测试1-备用</a>
										<a href="/tea/analysis/exam?id=51" class="underline problem-analysis-link">学生成绩分析</a>
									</td>
									<td>2015-07-01 10:00</td>
									<td>2015-07-01 12:00</td>
									<td>60&nbsp;分钟</td>
									<td>83.0</td>
								</tr>
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

</body>
</html>