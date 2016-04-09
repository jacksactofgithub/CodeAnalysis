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
        <span>欢迎, <span class="text-info"><%=session.getAttribute("tea_name") %></span>老师! </span>
        <span><%=session.getAttribute("uni_name") %></span>
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
                        <a href="<%=request.getContextPath()%>/teacherAnalysis" title=""><span>考试分析</span></a>
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

			<div class="widget">
				<div class="head">
					<h5 class="iInfo">学生代码</h5>
				</div>
				<div class="body">
					<pre>
					public JSONObject reverse(JSONArray stasArray) throws JSONException {、
						JSONObject stasJson = new JSONObject();
						int len = stasArray.length();
						int[] timestamp = new int[len];
						double[] lineCount = new double[len];
						int[] noteCount = new int[len];
						int[] methodCount = new int[len];
						int[] varCount = new int[len];
						int[] maxCy = new int[len];

						stasJson.put("timestamp", timestamp);
						stasJson.put("lineCount", lineCount);
						stasJson.put("noteCount", noteCount);
						stasJson.put("methodCount", methodCount);
						stasJson.put("varCount", varCount);
						stasJson.put("maxCy", maxCy);
						return stasJson;
					}
					</pre>
				</div>
			</div>
		</div>
	</div>
	
	<div class="wrapper" style="position:relative; top:-20px;">
		<!-- Content -->
		<div class="content">

			<div class="widget">
				<div class="head">
					<h5 class="iInfo">测试代码</h5>
				</div>
				<div class="body">
					<pre>
					public void test(JSONArray stasArray) throws JSONException {
						JSONObject stasJson = new JSONObject();
						int len = stasArray.length();
						int[] timestamp = new int[len];
						double[] lineCount = new double[len];
						int[] noteCount = new int[len];
						int[] methodCount = new int[len];
						int[] varCount = new int[len];
						int[] maxCy = new int[len];
					}
					</pre>
				</div>
			</div>
		</div>
</div>

<div class="wrapper" style="position:relative; top:0px;height:80px;">
		<!-- Content -->
		<div class="content" >
			<div class="title">
				<h5>运行结果</h5>
			</div>
	
			<div id="pigment" style="width:980px;">
				
				
				<table class="table table-bordered" style="position: absolute;height:40px;table-layout:fixed;">

					<tr>
						<td style="word-wrap:break-word;">testcase1</td>
					</tr>

				</table>
				<table class="table table-bordered" style="position:relative; height:40px;width:920px;left: 60px">
					<tr style="">
						<% int[] array = {0,0,0,0,0,0,0,0,1,1,1,1,1,0,1};
						String url;
							for(int j=0;j<15;j++){
							if(array[j]==0){
								url="url(view/pic/fail.png)";
							}else{
								url = "url(view/pic/pass.png)";
							}
							%>
							<td  style="background:<%=url%>;"></td>
						<% }%>
					</tr>
				</table>
			</div>
		</div>
	</div>
<div class="wrapper" style="position:relative; top:0px;height:80px;">
</div>
</body>
</html>