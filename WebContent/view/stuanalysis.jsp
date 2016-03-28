<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>MOOCTEST</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="keywords" content="慕测,MOOCTEST,慕课编程,自学编程">
        <meta name="description" content="慕测平台是编程类考试和练习的服务平台，教师可以轻松监管考试流程，学生可以自由练习编程。系统负责编程练习的自动化评估及可视化展现，配合当下红火的MOOC慕课课程，慕测平台将是学生自学编程的好帮手。目前已支持的编程类型有：Python统计编程，Java测试驱动编程，C++编程，Java测试，Jmeter性能测试。">

        <link rel="shortcut icon" type="image/png" href="/public/images/mooctest/favicon.png">
        <link rel="stylesheet" type="text/css" href="/public/css/itsbrain/main.css?v=20151123">
        <link rel="stylesheet" type="text/css" href="/public/css/common/common.css">
        <link rel="stylesheet" type="text/css" href="/public/css/common/itsbrain-width-full.css">
<style type="text/css">

</style>
<link rel="stylesheet" type="text/css" href="/public/css/others/introjs.css">
<style type="text/css">
.box{
	height: 300px;
	margin-top: 20px;
	position: relative;
	width: 978px;
}
.box .link{
	float: left;
	height: 280px;
	margin: 0 20px;
	width: 200px;
}
.link .icon{
	display: inline-block;
	height: 180px;
	transition: 0.4s ease-out;
	-ms-transition: 0.4s ease-out;
	-webkit-transition: 0.4s ease-out;
	-moz-transition: 0.4s ease-out;
	-o-transition: 0.4s ease-out;
	width: 100%;
}
.link .icon:hover{
	transform: rotate(360deg) scale(1.2);
	-ms-transform: rotate(360deg) scale(1.2);
	-webkit-transform: rotate(360deg) scale(1.2);
	-moz-transform: rotate(360deg) scale(1.2);
	-o-transform: rotate(360deg) scale(1.2);
}
.link-exam .icon{
	background: url(/public/images/exam.png) no-repeat center center;
}
.link-exercise .icon{
	background: url(/public/images/exercise.png) no-repeat center center;
}
.link-personal .icon{
	background: url(/public/images/personal.png) no-repeat center center;
}
.link-download .icon{
	background: url(/public/images/download.png) no-repeat center center;
}
.button{
	background: url(/public/images/arrow.png) no-repeat 145px center;
	border: 2px solid rgba(72,72,72,0.8);
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
.button:hover{
	background-position: 160px center;
	border: 2px solid rgba(72,72,72,1);
}
.button .line{
	background: none;
	display: block;
	position: absolute;
	transition: 0.4s ease-out;
	-ms-transition: 0.4s ease-out;
	-webkit-transition: 0.4s ease-out;
	-o-transition: 0.4s ease-out;
	-moz-transition: 0.4s ease-out;
}
.button:hover .line{
	background: #484848; 
}
.button .line-top{
	height: 2px;
	left: -110%;
	width: 0%;
	top: -2px;
}
.button:hover .line-top{
	left: -2px;
	width: 100%;
}
.button .line-right{
	height: 0%;
	right: -2px;
	top: -110%;
	width: 2px;
}
.button:hover .line-right{
	height: 100%;
	top: -2px;
}
.button .line-bottom{
	bottom: -110%;
	height: 0;
	left: -2px;
	width: 2px;
}
.button:hover .line-bottom{
	bottom: -2px;
	height: 100%;
}
.button .line-left{
	bottom: -2px;
	height: 2px;
	right: -110%;
	width: 0;	
}
.button:hover .line-left{
	right: -2px;
	width: 100%;
}
.tip{
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
.tip em{
	font-style: normal;
}
.tip span{
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
        


<!-- Header -->
<div id="header" class="wrapper">
    <div id="loginInfo">
        <img src="/public/images/userPic.png" alt=""/>
        <span>Welcome, <span class="text-info">刘硕</span>student! </span>
        <span>南京大学</span>
    </div>
    <div class="clearfix"></div>
    <div>
        <div id="logo">
            <a href="/stu/home" title="返回主页"></a>
        </div>
        <div class="clearfix" id="nav">
            <div class="middleNav pull-left">
                <ul>
                    <li class="iStats">
                        <a href="http://112.124.1.3:8050/?email=lshuo12@software.nju.edu.cn&vcode=4ac130b1c76c7858d41070528e4c3294" title="" target="_blank"><span>Python统计</span></a>
                    </li>
                    <li class="iExam">
                        <a href="/stu/exam/list" title=""><span>My Exam</span></a>
                    </li>
                    <li class="iClass">
                        <a href="/stu/class/list" title=""><span>我的班级</span></a>
                    </li>
                </ul>
            </div>
            <div class="middleNav pull-right">
                <ul>
                    <li class="iUser">
                        <a href="/stu/personal/profile" title=""><span>Profile</span></a>
                    </li>
                    <li class="iLogoff">
                        <a href="/logout" title=""><span>Logout</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>