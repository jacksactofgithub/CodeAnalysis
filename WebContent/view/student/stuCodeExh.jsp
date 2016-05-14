<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.List"%>
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
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/others/chosen-1.2.0.min.css">
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/others/highlight.github.css">
<link href="view/css/prism.css" rel="stylesheet" />
<style>
.scale_panel{
	font-size:12px;
	color:#999;
	width:840px;
	position:absolute; 
	line-height:18px; 
	left:70px;
	top:-0px;
}
.scale_panel .r{
	float:right;
}
.scale span{
	background:url(view/pic/scroll.gif) no-repeat; 
	width:8px;
	height:16px; 
	position:absolute; 
	left:-2px;
	top:-1px;
	cursor:pointer;
}
.scale{
	background:url(view/pic/red.gif) repeat-x 0 100%;
	border-left:1px #83BBD9 solid;
	border-right:1px red solid;
	width:850px;
	height:10px; 
	position:relative; 
	font-size:0px;
}
.scale div{
	background:url(view/pic/blue.gif) repeat-x;
	width:0px; 
	position:absolute; 
	width:0;
	left:0;
	height:5px;
	bottom:0;
}
#scroll_li{
	font-size:12px;
	line-height:50px;
	position:relative; 
	height:50px; 
	list-style:none;
}
#codeBlock{
    float: left;
    width: 70%;
}
#coverageBlock{
    float: left;
    margin-left: 20px;
    width: 25%;
}
#coverageBlock a.watch{
    margin-left: 5px;
    visibility: hidden;
}
#coverageBlock tr:hover a.watch{
    visibility: visible;
}
#coverageBlock a.watch img{
    vertical-align: text-bottom;
}
#codeTable tr.highlight code{
    background-color: #FFFF99;
}
#coverageSelect{
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
	<script src="view/js/prism.js"></script>
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
							href="<%=request.getContextPath() %>/stuAnalysis" title=""><span>编程分析</span></a>
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
		
		<div class="content" style="height:15px;">
			<div class="title">
				<h5>考试列表</h5>
			</div>

			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home" title="主页">主页</a></li>
						<li ><a href="<%=request.getContextPath() %>/stuAnalysis" title="编程分析">编程分析</a></li><!-- 同一工程下的链接跳转 -->
						<li class="firstB"><%=request.getParameter("problem_name") %></li>
					</ul>
				</div>
			</div>
		</div>
		

			<div class="widget" style="overflow:scroll;height:500px;">
				<div class="head">
					<h5 class="iInfo">学生代码</h5>
					<div class="analysis-block" id="coverageBlock"
						style="height: 38px; padding-top: -13px;">
						<div class="operation" style="height: 38px; margin-top: 6px;">
							<span style="margin-top: 1px;">文件列表：</span> <select
								id="files" onchange="changeFile()">
								<%
								@SuppressWarnings("unchecked")
								List<String> files = (List<String>)request.getAttribute("files");
								for(int i=0;i<files.size();i++){
									String file = files.get(i);
								%>
								<option value="<%=file%>"><%=file%></option>
								<%} %>
							</select>
						</div>
					</div>
				</div>
				<div class="body" style="float:left;margin-left:-350px">
					<pre id="stuCode" >
						<code class="language-java" id="prismCode">
							<%=request.getAttribute("code") %>
						</code>
					</pre>
				</div>
			</div>
	</div>
	
	<div class="wrapper" style="position:relative; top:0px;">
		<div class="content">

			<div class="widget">
				<div class="head">
					<h5 class="iInfo">时间轴</h5>
				</div>
				<div class="body">
					<ul>
						<li id="scroll_li">时间 <span id="title">0min</span>
							<div class="scale_panel">
								<span class="r">120</span>0
								<div class="scale" id="bar">
									<div></div>
									<span id="btn"></span>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<div class="wrapper" style="position:relative; top:-80px;height:300px;">
		<div class="widget">
			<div class="head">
				<h5 class="iInfo">运行信息</h5>
			</div>
			<div class="body">
				<%
					@SuppressWarnings("unchecked")
					List<String> testCases = (List<String>) request.getAttribute("testCases");
					for (int i = 0; i < testCases.size(); i++) {
						String testCase = testCases.get(i);
				%>
					<span style="margin-top: 1px;"><%=testCase%></span><br>
				<%
					}
				%>
			</div>
		</div>
	</div> -->
<script>
scale=function (btn,bar,title){
	this.btn=document.getElementById(btn);
	this.bar=document.getElementById(bar);
	this.title=document.getElementById(title);
	this.step=this.bar.getElementsByTagName("DIV")[0];//得到了拖动之后左边的条
	this.init();
};
scale.prototype={
	init:function (){
		var f=this,g=document,b=window,m=Math;
		f.btn.onmousedown=function (e){
			var x=(e||b.event).clientX;
			var l=this.offsetLeft;
			var max=f.bar.offsetWidth-this.offsetWidth;
			g.onmousemove=function (e){
				var thisX=(e||b.event).clientX;
				var to=m.min(max,m.max(-2,l+(thisX-x)));
				f.btn.style.left=to+'px';
				f.ondrag(m.round(m.max(0,to/max)*100),to);
				b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();
			};
			//g.onmouseup=new Function('this.onmousemove=null');
			g.onmouseup=function(e){
				this.onmousemove=null;
				var thisX=(e||b.event).clientX;
				var to=m.min(max,m.max(-2,l+(thisX-x)));
				showCode(m.round(m.max(0,to/max)*120));
			}
		};
	},
	ondrag:function (pos,x){
		this.step.style.width=Math.max(0,x)+'px';
		this.title.innerHTML=parseFloat(pos*1.2).toFixed(0)+'min';//pos*总时间就是拖动的时间
		//此处调用ajax
	}
}
new scale('btn','bar','title');

var exam_id = '<%=request.getParameter("exam_id")%>';
var problem_name = '<%=request.getParameter("problem_name")%>';
var stu_id = '<%=session.getAttribute("stu_id")%>';

function showCode(time){
	
	var files=$("#files option:selected");

  	 $.ajax({type : "POST",
           url : "showCode", 
           data : {
          	 stu_id : stu_id,
          	 time :time,
          	 exam_id:exam_id,
  	 		 problem_name :problem_name,
  	 		 file_name :files.val()
           },
           success : function (data){
        	   data = decodeURIComponent(data.replace(/\+/g, '%20'));
        	   data = data.replace(/</g,"&lt;");
        	   $("#prismCode").html(data);
           }
   	 });   
}

function changeFile(){
	var files=$("#files option:selected");

 	 $.ajax({type : "POST",
          url : "showCode", 
          data : {
         	 stu_id : stu_id,
          	 time :0,
          	 exam_id:exam_id,
  	 		 problem_name :problem_name,
  	 		 file_name :files.val()
          },
          success : function (data){
	       	   //data = decodeURIComponent(data.replace(/\+/g, '%20'));
	    	   data = data.replace(/</g,"&lt;");
	    	   $("#prismCode").html(data);
          }
  	 });
}

</script>

        <!-- jquery 1.7.2 业内最稳定版本 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/jquery/jquery-1.7.2.min.js"></script>

        <!-- jquery UI（ui_custom.css做过改动） -->
        <script type="text/javascript" src="http://mooctest.net/public/js/jquery-ui/jquery-ui-1.8.24.min.js"></script>
                <script type="text/javascript" src="http://mooctest.net/public/js/jquery-ui/jquery-ui-datepicker-zh-CN.js"></script>
        
        <!-- 借鉴bootstrap模态框 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/bootstrap/bootstrap-modal-2.3.2.js"></script>

        <!-- form validation -->
        <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.validationEngine-2.6.2.js"></script>
                <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.validationEngine-zh_CN.js"></script>

</body>
</html>