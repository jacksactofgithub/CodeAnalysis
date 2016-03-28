<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="view/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="view/css/common.css" rel="stylesheet" type="text/css">
<link href="view/css/question.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" type="image/png"
	href="http://mooctest.net/public/images/mooctest/favicon.png">
<script type="text/javascript"
	src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>慕测平台</title>
</head>
<body>
	<div id="header" class="wrapper">
		<div id="loginInfo"
			style="left: 67%; top: 1%; position: absolute; font-size: 13px;">
			<img src="http://mooctest.net/public/images/userPic.png" alt="" /> <span>欢迎,
				<span class="text-info">刘钦</span>老师!
			</span> <span>南京大学</span>
		</div>
		<div id="logo"
			style="background: url(view/pic/logo.png); left: 23%; top: 3%; width: 200px; height: 80px; position: absolute;">
			<a href="http://mooctest.net/tea/home" title="返回主页"></a>
		</div>
		<div class="clearfix"></div>
		<div>
			<div class="clearfix" id="nav"
				style="left: 64%; top: 5%; position: absolute;">
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
	
	<div style="left:15%; top:15%;width:70%;height:45%;position: absolute; ">
		<div style="left:15%; top:0%;width:70%;height:45px; position: absolute;border-top:1px solid #C0C0C0;border-left:1px solid #C0C0C0;
			border-right:1px solid #C0C0C0;border-bottom:1px solid #C0C0C0;font-size: 20px;padding-top:8px;padding-bottom:8px;
			padding-left:10px;border-radius:3px;">
			<img src="http://mooctest.net/public/images/userPic.png" alt="" /><span>考生信息</span>
		</div>
		
		<div style="left:15%; top:45px;width:70%;height:70%; position: absolute;border-top:1px solid #C0C0C0;border-left:1px solid #C0C0C0;
			border-right:1px solid #C0C0C0;border-bottom:1px solid #C0C0C0;font-size: 20px;padding-left:10px;border-radius:3px;padding-top:-50px;">
			
			<div style="left:5%; top:0;width:90%;height:25%; position: absolute;border-bottom:1px solid #C0C0C0;font-size: 20px;padding-top:3%;
			padding-left:20px;padding-bottom:5%;">
				<span>考生姓名</span> <span style="left:30%;position: absolute;">刘睿</span>
			</div>
			
			<div style="left:5%; top:18%;width:90%;height:25%; position: absolute;border-bottom:1px solid #C0C0C0;font-size: 20px;padding-top:5%;
			padding-left:20px;padding-bottom:5%;">
				<span>学号</span> <span style="left:30%;position: absolute;">121250088</span>
			</div>
			
			<div style="left:5%; top:43%;width:90%;height:28%; position: absolute;border-bottom:1px solid #C0C0C0;font-size: 20px;padding-top:5%;
			padding-left:20px;padding-bottom:5%;">
				<span>班级</span> <span style="left:30%;position: absolute;">2012级2班</span>
			</div>
			
			<div style="left:5%; top:68%;width:90%;height:25%; position: absolute;font-size: 20px;padding-top:5%;
			padding-left:20px;padding-bottom:5%; ">
				<span>能力排名</span> <span style="left:30%;position: absolute;">前1%(顶尖水平)</span>
			</div>
			
		</div>
		
	</div>
	
	<div style="left:15%; top:61%; width:70%;position: absolute; font-size: 15px;">
		<div style="left:15%; top:0%;width:70%;height:40px; position: absolute;border-top:1px solid #C0C0C0;border-left:1px solid #C0C0C0;
		border-right:1px solid #C0C0C0;border-bottom:1px solid #C0C0C0;font-size: 20px;
		padding-left:10px;padding-top:7px;padding-bottom:7px;border-radius:3px;">
			<img src="view/pic/exam.png" alt="" /><span>考题列表</span>
		</div>
	<table class="table table-bordered" id="examMemberTable" style="left:15%; top:40px; width:70%;position: absolute; font-size: 15px;">
			<thead>
				<tr>
					<th class="span2">考题名称</th>
					<th class="span2">考试日期</th>
					<th class="span2" data-sorter="false">得分</th>
					<th class="span1" data-empty="zero">考试描述</th>
					<th class="span3" data-sorter="false">考题类型</th>
				</tr>
			</thead>
			<tbody>
				<tr data-mem="0">
					<td>ArrayPartition</td>
					<td>2015-02-01</td>
					<td>测验题</td>
					<td><span class="text-success"> <span
							class="final-score">83.0</span> 分
					</span></td>
					<td>Java覆盖测试</td>
				</tr>
				<tr data-mem="0">
					<td>Sorting</td>
					<td>2015-02-01</td>
					<td>测验题</td>
					<td><span class="text-success"> <span
							class="final-score">90.0</span> 分
					</span></td>
					<td>Java覆盖测试</td>
				</tr>
				<tr data-mem="0">
					<td>Schedule</td>
					<td>2015-02-01</td>
					<td>测验题</td>
					<td><span class="text-success"> <span
							class="final-score">90.0</span> 分
					</span></td>
					<td>Java覆盖测试</td>
				</tr>
				<tr data-mem="0">
					<td>SundySearch</td>
					<td>2015-02-01</td>
					<td>测验题</td>
					<td><span class="text-success"> <span
							class="final-score">78.0</span> 分
					</span></td>
					<td>Java覆盖测试</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>