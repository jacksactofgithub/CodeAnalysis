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
 <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
 <script type="text/javascript">
 $(function () {
	 var myjson = eval(<%=(String)request.getAttribute("staJsonstr")%>);
	 var chart1=$('#container').highcharts({
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
							href="<%=request.getContextPath()%>/teacherAnalysis" title=""><span>考试分析</span></a>
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

			<div class="breadCrumbHolder module" style="position:absolute;height:33px;">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li ><a href="<%=request.getContextPath()%>/teacherAnalysis" title="考试分析">考试分析</a></li>
						<li ><a href="<%=request.getContextPath()%>/examDetail?id=<%=((JSONObject)request.getAttribute("exam")).getString("id") %>"><%=((JSONObject)request.getAttribute("exam")).getString("exam_name") %></a></li>
						<li ><%=((JSONObject)request.getAttribute("exam")).getString("exam_name")  %></li>
						<!-- 从request中取得考试信息类中的考试名 题目名-->
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="wrapper">
		<!-- Content -->
		<div class="content" >
			<div class="title">
				<h5>代码统计</h5>
			</div>
			<div style="position:absolute;width:30px;height:350px;background: url(view/pic/wbg.png)">
				<!-- 空白 -->
				<span style="position:absolute;top:110px;left:10px;">单位: 个<br>/<br>行</span>
			</div>
			<div id="container" style="position:relative;left:30px;width:960px;height:350px;">
				<!-- 代码统计 -->
			</div>
		</div>
	</div>
	
	<div class="wrapper" style="position:relative; top:0px;">
		<!-- Content -->
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
				
				caseNum = json.getInt("caseNum");
				len = len/3;
				System.out.println(len);
				System.out.println("============================================================");
				int[][] tdarray = new int[caseNum][len];//row col是代码统计的次数;对应就应该有这么多列
				
				
				for(int i=0;i<len;i=i+3){//每次统计时的运行情况
					JSONObject obj = array.getJSONObject(i);
					JSONArray passArray = obj.getJSONArray("passNo");
					for(int j = 0;j<passArray.length();j++){
						int m = passArray.getInt(j);
						tdarray[m-1][i]=1;// 用例标号是从1开始的 所以要减一
					}
				}
				%>
				
				<table class="table table-bordered" style="position: absolute;height:300px;">
					<%for(int i=0;i<caseNum;i++){%>
					<tr>
						<td style="word-wrap:break-word;"><%=json.getJSONArray("caseName").getString(i)%></td>
					</tr>
					<% }%>
				</table>
				<table class="table table-bordered" style="position:relative; height:371px;width:920px;left: 60px">
				<%for(int i=0;i<caseNum;i++){%>
					<tr>
						<%for(int j=0;j<len;j++){
							System.out.println(len);
							System.out.println("============================================================");
							String url="url(view/pic/fail.png)";//错误颜色
							//System.out.print(tdarray[i][j]);
							if(tdarray[i][j]==1){
								url = "url(view/pic/pass.png)";
							}%>
						<td  style="background:<%=url%>;"></td>
						<% }//System.out.println();%>
					</tr>
				<% }%>
				</table>
			</div>
			</div>
	</div>

</body>
</html>