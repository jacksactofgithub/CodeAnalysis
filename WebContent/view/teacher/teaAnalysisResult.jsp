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
<link rel="shortcut icon" type="image/png" href="http://mooctest.net/public/images/mooctest/favicon.png">
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
<link rel="stylesheet" type="text/css" 
	href="http://mooctest.net/public/css/others/chosen-1.2.0.min.css">
<script type="text/javascript" src="http://mooctest.net/public/js/jquery/jquery-1.7.2.min.js"></script>
<style>
#left_td{
	white-space:nowrap;
	text-overflow:ellipsis;
	-o-text-overflow:ellipsis; 
	overflow: hidden;
}
#files{
    width: 150px;
    height:25px;
	position: relative;
    display: inline-block;
    vertical-align: middle;
    font-size: 13px;
    zoom: 1;
    -webkit-user-select: none;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	margin-top:5px;
}
 </style>
 <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
 <script type="text/javascript">
 var chart;
 $(function () {
	 var myjson = eval(<%=(String)request.getAttribute("staJsonstr")%>);
	 chart = new Highcharts.Chart({   
		 chart: {
			 renderTo: 'container',       
		 },
	        title: {
	            text: '',//'代码统计',
	            x: -20 //center
	        },
	        xAxis: {
	        	 title: {
	                 enabled: true,
	                 text: '时 间'
	             },
	            categories: myjson.timestamp
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            align: 'left', //水平方向位置
	            verticalAlign: 'top', //垂直方向位置
	            x: 0, //距离x轴的距离
	            y: 0 //距离Y轴的距离
	        },
	        credits:enabled=false,
	        series: [{
	            name: '代码总量',
	            data: myjson.lineCount
	        }, {
	            name: '变量个数',
	            data: myjson.varCount
	        }, {
	            name: '注释总量',
	            data: myjson.noteCount
	        }, {
	            name: '方法总量',
	            data: myjson.methodCount
	        }, {
	            name: '最大圈复杂度',
	            data: myjson.maxCy
	        }]
	    });
	});
 </script>
<title>慕测平台</title>
</head>
<body>
<!-- Header -->
	<div id="header" class="wrapper">
		<div id="loginInfo">
			<img src="http://mooctest.net/public/images/userPic.png" alt="" /> <span>欢迎,
				<span class="text-info"><%=session.getAttribute("tea_name") %></span>老师!
			</span> <span><%=session.getAttribute("uni_name") %></span>
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
							href="<%=request.getContextPath()%>/teacherAnalysis" title=""><span>编程分析</span></a>
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
				<h5>编程分析</h5>
			</div>
			<%
				JSONObject examJson = (JSONObject)request.getAttribute("exam");
				String exam_id = examJson.getString("exam_id");
				String exam_name = examJson.getString("exam_name");
			%>
			<div class="breadCrumbHolder module" style="position:absolute;height:33px;">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li ><a href="<%=request.getContextPath()%>/teacherAnalysis" title="编程分析">编程分析</a></li>
						<li ><a href="<%=request.getContextPath()%>/examDetail?exam_id=<%=exam_id %>"><%=exam_name%></a></li>
						<li ><%=exam_name%></li>
						<!-- 从request中取得考试信息类中的考试名 题目名-->
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="wrapper" style="position:relative; top:-25px;">
		<!-- Content -->
		<div class="content" >
			<div class="title">
				<h5>代码统计</h5>
				<div class="operation" style="height: 38px; margin-top: 6px;">
					<h5>文件列表：</h5> <select id="files"
						onchange="changeFile()">
						<%
							ArrayList<String> files = (ArrayList<String>) request.getAttribute("files");
							for (int i = 0; i < files.size(); i++) {
								String file = files.get(i);
						%>
						<option value="<%=file%>"><%=file%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div
				style="position: absolute; width: 30px; height: 350px; background: url(view/pic/wbg.png)">
				<!-- 空白 -->
				<span style="position: absolute; top: 110px; left: 10px;">单位:
					个<br>/<br>行
				</span>
			</div>
			<div id="container" style="position:relative;left:30px;width:960px;height:350px;">
				<!-- 代码统计 -->
			</div>
		</div>
	</div>
	
	<div class="wrapper" style="position:relative; top:-70px;">
		<div class="content" >
			<div class="title">
				<h5>运行结果</h5>
			</div>
	
			<div id="pigment" style="width:980px;">
				<%//此处将json处理成二维数组格式
				int caseNum=0;//测试用例数
				
				JSONObject json = null;
				JSONArray array = null;
				int runNum =0;//运行次数
				
				try{
					json =(JSONObject)request.getAttribute("runResultJson");
					array=json.getJSONArray("result");//运行结果的jsonarray
				}catch(Exception e){
					e.printStackTrace();
				}
				
				int len = array.length();//运行统计的数目
				int col = len/3;
				
				caseNum = json.getInt("caseNum");
				int[][] tdarray = new int[caseNum][col];//row col是代码统计的次数;对应就应该有这么多列
				
				
				for(int i=0;i<len;i=i+3){//每次统计时的运行情况
					JSONObject obj = array.getJSONObject(i);
					JSONArray passArray = obj.getJSONArray("passNo");
					for(int j = 0;j<passArray.length();j++){
						int m = passArray.getInt(j);
						int td_col = i/3;
						if(td_col<col){
							tdarray[m][td_col]=1;
						}
					}
				}
				%>
				
				<table class="table table-bordered" style="position: absolute;width:82px;height:300px;table-layout:fixed;">
					<%for(int i=0;i<caseNum;i++){%>
					<tr style="width:82px;">
						<td id="left_td" title="<%=json.getJSONArray("caseName").getString(i)%>">
							<%=json.getJSONArray("caseName").getString(i)%>
						</td>
					</tr>
					<% }%>
				</table>
				<table class="table table-bordered" style="position:relative; height:371px;width:900px;left: 80px">
				<%for(int i=0;i<caseNum;i++){%>
					<tr>
						<%for(int j=0;j<col;j++){
							String url="url(view/pic/fail.png)";//错误颜色
							if(tdarray[i][j]==1){
								url = "url(view/pic/pass.png)";
							}%>
						<td  style="background:<%=url%>;"></td>
						<% }%>
					</tr>
				<% }%>
				</table>
			</div>
	</div>
</div>
<script type="text/javascript">

function changeFile(){
	var files=$("#files option:selected");
	var exam_id = '<%=exam_id%>';
	var problem_name = '<%=request.getAttribute("problem_name")%>';
	var stu_id = '<%=request.getAttribute("stu_id")%>';
	$.ajax({
		type : "POST",
		dataType:'json',
        url : "getCodeStas",
        data : {
        	stu_id:stu_id,
       	 	exam_id:exam_id,
	 		problem_name: problem_name,
	 		file_name :files.val()
        },
        success : function (data){
        	chart.series[0].setData(data.lineCount);
        	chart.series[1].setData(data.varCount);
        	chart.series[2].setData(data.noteCount);
        	chart.series[3].setData(data.methodCount);
        	chart.series[4].setData(data.maxCy);
        }
	 });
}

</script>
</body>
</html>