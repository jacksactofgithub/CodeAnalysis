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
	            //layout: 'vertical',
	            //align: 'right',
	            //verticalAlign: 'middle',
	            //borderWidth: 0,
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

			<div class="breadCrumbHolder module" style="position:absolute;height:33px;">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li ><a href="http://mooctest.net/tea/home" title="考试分析">考试分析</a></li>
						<li ><a href="http://mooctest.net/tea/home" title="Java覆盖练习1"><%= %></a></li>
						<li ><%=(JSONObject)request.getAttribute("exam") %></li>
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
				JSONObject stajson = (JSONObject)request.getAttribute("staJson");
				JSONArray timeStamps = stajson.getJSONArray("timestamp");//代码时间戳
				
				int len = timeStamps.length();//横坐标长度;
				//得到的run结果已经是筛选过的 每分钟一次的结果了;就按照每分钟统计一次计算
				//构造成一个补全的二维数组
				
				JSONObject json = null;
				JSONArray array = null;
				int runNum =0;//运行次数
				
				try{
					json =(JSONObject)request.getAttribute("runResultJson");
					array=json.getJSONArray("result");//运行结果的jsonarray
				}catch(Exception e){
					e.printStackTrace();
				}
				caseNum = json.getInt("caseNum");
				int[][] tdarray = new int[caseNum][len];//row col是代码统计的次数;对应就应该有这么多列
				runNum = array.length();//运行次数
				//取出每次运行的时间戳
				//int [] runtimestamp = new int[runNum];
				
				int count =0;//计数运行结果统计的坐标
				int time = 0;
				
				for(int i=0;i<len;i++){//每次统计时的运行情况
					
					if(count>=array.length()){//将后面全部变成最后一个
						for(int p=i+1;p<len;p++){
							for(int q=0;q<caseNum;q++){
								tdarray[q][p] = tdarray[q][p-1];//与前一行相同
							}
						}
					}
					else{
						time = (int)array.getJSONObject(count).get("time");//寻找代码统计结束时最近的运行结果
						JSONArray passNo = array.getJSONObject(count).getJSONArray("passNo");//要处理最后一段
						
						if(timeStamps.getInt(i)==time){//如果统计代码的时间与运行时间相同
							for(int k=0;k<passNo.length();k++){
								int m =passNo.getInt(k);//用例标号变成整数存储
								tdarray[m-1][i] = 1;// 用例标号是从1开始的 所以要减一
							}
							count++;
						}else if(timeStamps.getInt(i)<time){
							if(i==0){
								for(int k=1;k<caseNum+1;k++){
									tdarray[k][i] = 0;//全部失败
									//System.out.print("fail");
								}
							}else{
								for(int k=0;k<caseNum;k++){
									tdarray[k][i] = tdarray[k][i-1];//与前一行相同
									//System.out.print("suc");
								}
							}//次数count不加;因为下一个代码统计的时间可能还是接近运行时间
						}
					}
				}
				if(time<len){
					for(int i=time;i<len;i++){
						for(int k=0;k<caseNum;k++){
							tdarray[k][i] = tdarray[k][i-1];//与前一行相同
						}
					}
				}
				%>
				
				<table class="table table-bordered" style="position: absolute;height:300px;table-layout:fixed;">
					<%for(int i=0;i<caseNum;i++){%>
					<tr>
						<td style="word-wrap:break-word;"><%=json.getJSONArray("caseName").getString(i)%></td>
					</tr>
					<% }%>
				</table>
				<table class="table table-bordered" style="position:relative; height:300px;width:920px;left: 60px">
				<%for(int i=0;i<caseNum;i++){%>
					<tr style="">
						<%for(int j=0;j<len;j++){
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