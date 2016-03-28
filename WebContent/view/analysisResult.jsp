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
<link href="view/css/analysis.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" type="image/png" href="http://mooctest.net/public/images/mooctest/favicon.png">
 <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
 <script type="text/javascript">
 $(function () {
	 var myjson = eval(<%=(String)request.getAttribute("staJsonstr")%>);
	 
	 var chart1=$('#container').highcharts({
	        title: {
	            text: '代码统计',
	            x: -20 //center
	        },
	        xAxis: {
	            categories: myjson.timestamp
	        },
	        yAxis: {
	            title: {
	                text: ''//${test}
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
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
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
<div id="header" class="wrapper">
    <div id="loginInfo" style="left:67%;top:1%;position:absolute;font-size: 13px;">
        <img src="http://mooctest.net/public/images/userPic.png" alt=""/>
        <span>欢迎, <span class="text-info">刘钦</span>老师! </span>
        <span>南京大学</span>
    </div>
    <div id="logo" style="background:url(view/pic/logo.png);left:23%;top:3%;width:200px;height:100px;position:absolute;">
       <a href="http://mooctest.net/tea/home" title="返回主页"></a>
    </div>
    <div class="clearfix"></div>
    <div>
        <div class="clearfix" id="nav" style="left:64%;top:5%;position:absolute;">
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

<!-- 
<nav class="navbar navbar-default" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="#" style="font-size:20px;">软件学院</a>
   </div>
   <div>
      <ul class="nav navbar-nav">
         <li style="font-size:20px;"><a href="#">本科生上机考试过程分析</a></li>
         <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              	刘晓明
               <b class="caret"></b>
            </a>
         </li>
      </ul>
   </div>
</nav>
 -->
	<div id="container" style="position: absolute;width:90%;height:30%;left:8%;top:12%;">
		<!-- 代码统计 --><!-- 色块图 -->
	</div>
	
	<div style="position: absolute;left:45%;top:50%;font-size:20px;">测试用例运行结果统计
	</div>
	
	<div id="pigment" style="position: absolute;width:100%;left:0;top: 55%;">
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
		
		<table class="table table-bordered" style="position: absolute;top:3%;width:5%;height:300px;left:6%">
			<%for(int i=0;i<caseNum;i++){%>
			<tr style=" width:100px;">
				<td><%=json.getJSONArray("caseName").getString(i)%></td>
			</tr>
			<% }%>
		</table>
		<table class="table table-bordered" style="position: absolute;top:3%;width:82%;height:300px;left:10%">
		<%for(int i=0;i<caseNum;i++){%>
			<tr style="">
				<%for(int j=0;j<len;j++){
					String color="red";
					//System.out.print(tdarray[i][j]);
					if(tdarray[i][j]==1){
						color = "#3CB371";
					}%>
				<td  bgcolor=<%=color%>></td>
				<% }//System.out.println();%>
			</tr>
		<% }%>
		</table>
	</div>
</body>
</html>