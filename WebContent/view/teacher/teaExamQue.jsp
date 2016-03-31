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
</head>
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
				<h5>考试分析</h5>
			</div>

			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li ><a href="http://mooctest.net/tea/home" title="考试分析">考试分析</a></li>
						<li class="firstB">Java覆盖练习1</li><!-- 从request中取得考试信息类中的考试名 -->
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
					<h5 class="iBook">考题列表</h5>
				</div>
				<div class="body">
					<div class="problem-section">
						<div class="info">
							<ul class="item-list">
								<li class="item">
									<div class="item-name">第1题</div>
									<div class="item-value">
										<span>考题类型：Java覆盖测试；</span> <span>权重：30%</span>
									</div>
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
										<th>最高分</th>
										<th>考题描述</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<!-- 此处跳转至考生的具体题目代码分析 -->
										<td><a href="/tea/analysis/problem?exam=51&pro=1"
											class="underline">ArrayPartition</a></td>
										<td>1</td>
										<td>84.6</td>
										<td>100.0</td>
										<td>第一题</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>