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
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/tablesorter/theme.blue.css">
<link rel="stylesheet" type="text/css" href="http://mooctest.net/public/css/tablesorter/jquery.tablesorter.pager.css">
 <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
 <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
<style type="text/css">
.problem-analysis-link {
    background-color: #9ff;
    margin-left: 10px;
}
</style>
 </head>
<body>
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
			<%
				JSONObject exam = ((JSONObject)request.getAttribute("exam"));
				String exam_id = exam.getString("exam_id");
			%>
			<div class="breadCrumbHolder module">
				<div class="breadCrumb module">
					<ul id="breadCrumbList">
						<li class="firstB"><a href="http://mooctest.net/tea/home"
							title="主页">主页</a></li>
						<!-- 这里是stuanalysis页面 -->
						<li class="firstB"><%=exam.getString("exam_name")%></li>
						<!-- 从request中取得考试信息类中的考试名 -->
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
							<div class="item-value"><%=exam.getString("exam_name")%></div>
						</li>
						<li class="item">
							<div class="item-name">考试日期</div>
							<div class="item-value"><%=exam.getString("exam_begin_time").split(" ")[0]%></div>
						</li>
						<li class="item">
							<div class="item-name">考试人数</div>
							<div class="item-value"><%=exam.getInt("stu_count")%></div>
						</li>
					</ul>
				</div>
			</div>

			<div class="widget">
				<div class="head">
					<h5 class="iUser">考生状态</h5>
				</div>
				<div class="body">
					<div id="examMemberTableWrapper">
					<div class="text-right">
		                <span>搜索学号或姓名</span>
		                <input class="search table-search-input" type="search" data-column="all" placeholder="">
		            </div>
						<table class="table tablesorter" id="examMemberTable">
							<thead>
								<tr>
									<th class="span2" style="width:50px;">学校</th>
									<th class="span2" style="width:50px;">学号</th>
									<th class="span2" data-sorter="false" style="width:50px;">姓名</th>
									<th class="span1" data-empty="zero">总成绩</th>
									<th class="span1">题目一得分</th>
									<th class="span1" id="th6">题目二得分</th>
									<th class="span1" id="th7">题目三得分</th>
								</tr>
							</thead>
							<tbody>
							<%JSONArray studentArray = (JSONArray)request.getAttribute("studentArray");
								int len = studentArray.length();
								int problem_num=1;
								//{id:,exam_name:,teacher_name:,exam_begin_time:,exam_end_time:,exam_duration:,score:,}
 								for(int i = 0;i<len;i++){
									JSONObject student = (JSONObject)studentArray.get(i);
									int stu_id = student.getInt("class_member_id");//班级id
									String stu_name = student.getString("student_name");
									String stu_no = student.getString("stu_no");
									String uni_name =student.getString("uni_name");
									double score = student.getDouble("score");//总得分
									JSONArray pro_array = student.getJSONArray("pro_result");
									problem_num = pro_array.length();
								%>
								<tr data-mem="0">
									<td><%=uni_name %></td>
									<td><%=stu_no %></td>
									<td><%=stu_name %></td>
									<td><span class="text-success"> <span
											class="final-score"><%=score %></span> 分
									</span></td>
									<%for(int j=0;j<problem_num;j++){
										double problem_score = pro_array.getJSONObject(j).getDouble("score");
										String problem_name = pro_array.getJSONObject(j).getString("problem_name");
									%>
									<td><a
										href="<%=request.getContextPath() %>/teaAnalysisResult?stu_id=<%=stu_id%>&exam_id=<%=exam_id%>&problem_name=<%=pro_array%>"
										title="查看题目代码统计"> <span class="final-score"><%=problem_score %></span>分
									</a> <a
										href="<%=request.getContextPath() %>/teaCodeExh?exam_id=<%=exam_id %>&problem_name=<%=problem_name%>&stu_id=<%=stu_id%>"
										class="underline problem-analysis-link" title="查看代码详情">
											[查看代码详情]</a></td>
									<%} %>
								</tr>
							<%} %>
							</tbody>
						</table>
						<div class="text-center" id="examMemberTablePager"></div>
					</div>
				</div>
			</div>

		</div>
	</div>


        <!-- 全局多语言文案，供通用js使用 -->
        <script type="text/javascript">
        window.LANG_TEXT = {
            OK: "确定",
            CANCEL: "取消",
            DONE: "完成",
            SAVE: "保存",
            EDIT: "修改",
            CLICK_EDIT: "点击修改",
            CLOSE: "关闭",
            HINT: "提示",
            FAIL: "操作失败",
            ERROR: "错误",
            DELETE: "删除",
            MAX_ITEMS: function(num){
                var preCompiled = "最多添加[null]项";
                return preCompiled.replace('[null]', num);
            },
            NO_HINT: "下次不再提示",  //en版本里有单引号
            NEXT_STEP: '下一步',
            PREV_STEP: '上一步',
            I_KNOW: '知道了'
        };

        // for SchoolBox.js
        window.SCHOOL_TEXT = {
            SELECT_SCHOOL: "选择学校",
            SEARCH: "搜索",
            NO_SCHOOL_FOUND: "找不到该学校",
            ADD_SCHOOL_HINT: "没找到？点击添加学校",
            ADD_SCHOOL: "点击添加学校",
            SCHOOL_NAME_INVALID: "学校名称不能超过50个字符",
            ADD_SCHOOL_FAIL: "添加学校失败",
            OTHER: "其他",
            OK: "确定",
            CANCEL: "取消",
            CLOSE: "关闭",
            HINT: "提示"
        };
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
        
        <!-- 消息队列 + tooltip + 弹框 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.jgrowl.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.tipsy.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/ui/jquery.alerts.js"></script>

        <!-- 面包屑导航 + 返回顶部 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/jBreadCrumb.1.1.js"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/itsbrain/jquery.ToTop.js"></script>

        <!-- my common可复用 -->
        <script type="text/javascript" src="http://mooctest.net/public/js/common/application-util.js?v=20151204"></script>
        <script type="text/javascript" src="http://mooctest.net/public/js/common/application-execution.js"></script>

        <!-- cookie -->
        <script type="text/javascript" src="http://mooctest.net/public/js/others/jquery.cookie.1.4.1.js"></script>

	<script type="text/javascript">
        
        $(document).ready(function(){ //隐藏table的某行
        	var len = '<%=problem_num%>';
        	if(len==1){
            	$('#th6').hide();//隐藏第六列
            	$('#th7').hide();//隐藏第七列
        	}
        	if(len==2){
            	$('#th7').hide();//隐藏第六列
        	}
        	//修改图片路径
       	}); 
     </script>
        
        <script type="text/javascript">
$(function(){
    // ================================
    // 面包屑导航 global
    // ================================
    window.BreadCrumbUtil = (function(){
        var TEXT_CODE_BRANCH = '分支',
            TEXT_CODE_BLOCK = '代码块';

        // keys
        var HOME = "主页";
        var EXAM_LIST = "考试分析";
        var EXAM_CREATE = "新建考试";
        var EXAM_VIEW = "<%=exam.getString("exam_name")%>";
        var CLASS_LIST = "班级列表";
        var CLASS_CREATE = "新建班级";
        var CLASS_VIEW = "班级";
        var PROBLEM_UPLOAD = "上传题目";
        var PERSONAL_PROFILE = "个人信息";
        var PERSONAL_PASSWORD = "修改密码";
        var QUIZ_LIST = "小测列表";
        var QUIZ_VIEW = "小测";
        var QUIZ_CREATE = "小测预备";
        var EXERCISE_OVERVIEW = '练习科目';
        var APP_TEST_ORDER = 'App预约';
        var APP_TEST_ORDER_CREATE = '新预约';

        // 导航链接
        var LINKS = {};
        LINKS[HOME] = '/tea/home';
        LINKS[EXAM_LIST] = '<%=request.getContextPath() %>/teacherAnalysis';
        LINKS[EXAM_CREATE] = '/tea/exam/create';
        LINKS[EXAM_VIEW] = '#';
        LINKS[CLASS_LIST] = '/tea/class/list';
        LINKS[CLASS_CREATE] = '/tea/class/create';
        LINKS[CLASS_VIEW] = '#';
        LINKS[PROBLEM_UPLOAD] = '/tea/upload';
        LINKS[PERSONAL_PROFILE] = '/tea/personal/profile';
        LINKS[PERSONAL_PASSWORD] = '/tea/personal/password';
        LINKS[QUIZ_LIST] = '/tea/quiz/list';
        LINKS[QUIZ_VIEW] = '#';
        LINKS[QUIZ_CREATE] = '/tea/quiz/create';
        LINKS[EXERCISE_OVERVIEW] = '/tea/exercise/overview';
        LINKS[APP_TEST_ORDER] = '/tea/apptest/orderList';
        LINKS[APP_TEST_ORDER_CREATE] = '#';

        // 元素
        var $target = $('.breadCrumb');
        var $list = $('#breadCrumbList');
        var $firstB = $list.children().first();

        var clearExceptFirst = function(){
            $list.children().not($firstB).remove();
        };

        var getItemKey = function(item){
            if(typeof item === 'string'){
                return item;
            }
            return item[0];
        };

        var getItemLink = function(item){
            if(typeof item === 'string'){
                return LINKS[item];
            }
            return item[1];
        };

        var generateListItem = function(item, enable){
            var $el = $('<li>' + getItemKey(item) + '</li>');
            if(enable){
                $el.wrapInner('<a href="' + getItemLink(item) + '"></a>');
            }
            $list.append($el);
        };

        return {
            'HOME': HOME,
            'EXAM_LIST': EXAM_LIST,
            'EXAM_CREATE': EXAM_CREATE,
            'EXAM_VIEW': EXAM_VIEW,
            'CLASS_LIST': CLASS_LIST,
            'CLASS_CREATE': CLASS_CREATE,
            'CLASS_VIEW': CLASS_VIEW,
            'PROBLEM_UPLOAD': PROBLEM_UPLOAD,
            'PERSONAL_PROFILE': PERSONAL_PROFILE,
            'PERSONAL_PASSWORD': PERSONAL_PASSWORD,
            'QUIZ_LIST': QUIZ_LIST,
            'QUIZ_VIEW': QUIZ_VIEW,
            'QUIZ_CREATE': QUIZ_CREATE,
            'EXERCISE_OVERVIEW': EXERCISE_OVERVIEW,
            'APP_TEST_ORDER': APP_TEST_ORDER,
            'APP_TEST_ORDER_CREATE': APP_TEST_ORDER_CREATE,

            'COV_MAP': {
                'branch': TEXT_CODE_BRANCH,
                'block': TEXT_CODE_BLOCK
            },

            /* arguments
                []: 隐藏导航
                [HOME, EXAM_LIST or [yourKey, yourLink], ...]: 面包屑路径
            */
            update: function(){
                clearExceptFirst();
                var len = arguments.length;

                if(len == 0){
                    $target.hide();
                }
                if(len > 0){
                    $target.show();
                }
                if(len > 1){
                    for(var i=1; i<len; i++){
                        generateListItem(arguments[i], i<len-1 ? true : false);
                    }
                }
            }
        };
    })();

});
</script>
<script type="text/javascript" src="http://mooctest.net/public/js/tablesorter/jquery.tablesorter-2.17.7.min.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/tablesorter/jquery.tablesorter.widgets-2.17.7.min.js"></script>
<script type="text/javascript" src="public/js/tablesorter/jquery.tablesorter.pager-2.17.6.min.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/highcharts-4.0.4/highcharts.js"></script>
<script type="text/javascript" src="http://mooctest.net/public/js/common/AjaxChart.js"></script>
<script type="text/javascript">

$(function(){
    // ================================
    // 日期时间
    // ================================
    $('.datepicker').datepicker({ 
        defaultDate: +7,
        autoSize: true,
        dateFormat: 'yy-mm-dd',
    });

    // ================================
    // 表单Editable
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();

        var ExamTimeUtil = {
            /* 与当前时间比较
                after当前时间，return true
                else, return false
            */
            checkTimeWithNow: function(timeString){
                var date = new Date(timeString);
                var now = new Date();
                if(myGlobal.compareDate(date, now) > 0){
                    return true;
                }
                return false;
            }
        };

        var syncCheckExamName = function(examName){
            var result = false;
            $.ajax({
                type: 'POST',
                async: false,
                url: '/tea/exam/ajaxCheckExamNameWhenEdit',
                data: {
                    examId: curExamId,
                    newName: examName
                },
                success: function(data){
                    result = ($.parseJSON(data)).success;
                }
            });
            return result;
        };

        $('#examForm').myAjaxEditable({
            url: '/tea/exam/ajaxEditExam',
            extraParam: 'examId=' + curExamId,
            data: [
                {
                    paramKey: 'examName',
                    paramValue: function(){
                        return $('#examName').val();
                    },
                    check: function(){
                        var newName = this.paramValue();
                        // static check
                        if($.trim(newName).length == 0){
                            jAlert('考试名称不能为空', '提示');
                            return false;
                        }
                        if($.trim(newName).length > 15){
                            jAlert('考试名称不能超过15个字符', '提示');
                            return false;
                        }
                        // NOTE:同步ajax check
                        if(syncCheckExamName(newName) == false){
                            jAlert('您已创建过相同名称的考试了！', '提示');
                            return false;
                        }
                        return true;
                    }
                },
                {
                    paramKey: 'startTime',
                    paramValue: function(){
                        var timeString = myGlobal.getDateTimeString($('#examStartDate').val(), 
                                $('#examStartTimeHH').val(), $('#examStartTimeMM').val());
                        return timeString
                    },
                    check: function(){
                        var timeString = this.paramValue();
                        if(typeof timeString === 'undefined'){
                            jAlert('请选择考试开始时间', '提示');
                            return false;
                        }
                        if(ExamTimeUtil.checkTimeWithNow(timeString) == false){
                            jAlert('考试开始时间已过，请重新选择', '提示');
                            return false;
                        }
                        return true;
                    }
                },
                {
                    paramKey: 'duration',
                    paramValue: function(){
                        return $('#examDuration').val();
                    },
                    check: function(){
                        return true;
                    }
                },
                {
                    paramKey: 'endTime',
                    paramValue: function(){
                        var timeString = myGlobal.getDateTimeString($('#examEndDate').val(), 
                                $('#examEndTimeHH').val(), $('#examEndTimeMM').val());
                        return timeString
                    },
                    check: function(){
                        var timeString = this.paramValue();
                        if(typeof timeString === 'undefined'){
                            jAlert('请选择考试结束时间', '提示');
                            return false;
                        }
                        if(ExamTimeUtil.checkTimeWithNow(timeString) == false){
                            jAlert('考试结束时间已过，请重新选择', '提示');
                            return false;
                        }
                        return true;
                    }
                }
            ]
        });
    })();
    }

    // ================================
    // 延长考试
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();

        var displayTarget = function($el, command){
            if(command === 'hide'){
                $el.addClass('hide');
            }
            else{
                $el.removeClass('hide');
            }
        };

        $('#expandDurationLink').click(function(){
            displayTarget($('#expandDurationTarget'));
        });
        $('#expandDurationCancel').click(function(){
            displayTarget($('#expandDurationTarget'), 'hide');
        });

        $('#expandDurationOk').click(function(){
            $.post(
                '/tea/exam/ajaxExpandExamDuration',
                {
                    examId: curExamId,
                    minutes: Number($('#expandDurationSelect').val())
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        jAlert('延长时间成功', '提示');
                        // 成功则刷新页面
                        window.location.href = window.location.href;
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }
                }
            );
            displayTarget($('#expandDurationTarget'), 'hide');
        });

    })();
    }



    // ================================
    // 考生表格
    // ================================
    (function(){
        var $sTable = $('#examMemberTable').tablesorter({
            theme: 'blue',
            widgets: ["zebra", "filter"],
            widgetOptions : {
                // filter_anyMatch replaced! Instead use the filter_external option
                // Set to use a jQuery selector (or jQuery object) pointing to the
                // external filter (column specific or any match)
                filter_external : '.table-search-input',
                // include column filters
                filter_columnFilters: false,
                filter_saveFilters : false
            }

        }).tablesorterPager({
            // target the pager markup - see the HTML block below
            container: '#examMemberTablePager',
            // output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows} 或{filteredRows}
            output: '第{page}页 / 共{filteredPages}页',
            // starting page of the pager (zero based index)
            page: 0,
            // Number of visible rows - default is 10
            size: 10,
            // Save pager page & size if the storage script is loaded
            savePages : false

        }).bind('filterEnd', function(e, filter){
            // 被过滤掉后页面长度突然变短回缩，通过滚动减缓
            myGlobal.scrollTo($('#examMemberTableWrapper'));
        });

    })();


    
    // ================================
    // 成绩统计
    // ================================
    if($('#statisticsDiv').length > 0){
    (function(){
        var data = [];
        var num = 0;
        var sum = 0;
        var max = -1;
        var min = 9999;
        var average = 0;
        var median = 0;  //中位数
        var variance = 0;  //方差
        var deviation = 0;  //标准差

        // 第一遍扫描，放入缓存，并求num, sum, max, min, average
        $('#examMemberTable').find('tbody tr').each(function(){
            var score;
            try{
                score = Number($(this).find('.final-score').text());
            }
            catch(e){
                score = 0;
            }
            
            num++;
            sum += score;
            if(score > max){
                max = score;
            }
            if(score < min){
                min = score;
            }

            data.push(score);
        });

        average = (sum / num).toFixed(1);

        // 第二遍扫描，求方差
        for(var i=0; i<data.length; i++){
            variance += Math.pow((data[i] - average), 2);
        }

        variance = variance / num;
        deviation = Math.sqrt(variance).toFixed(1);

        // 第三遍扫描，快速排序，求中位数
        data = myGlobal.quickSort(data);
        median = data[Math.floor(data.length / 2)];

        // show
        $('#statisTotalNum').text(num);
        $('#statisAverage').text(average);
        $('#statisMax').text(max);
        $('#statisMin').text(min);
        $('#statisMedian').text(median);
        $('#statisDeviation').text(deviation);


        // *********
        // 柱状图
        // *********
        var scoreChart = new AjaxChart({
            wrapperId: 'scoreRegionChart',
            chartType: 'column'
        });

        // 手动更新数据（非ajax）
        var REGION_NUM = 10;
        var REGION_SIZE = 100 / REGION_NUM;
        var regionData = [];

        // 第四遍扫描（排序后），分区间
        var regionIndex = 0;
        var regionTempCount = 0;
        for(var i=0; i<data.length; i++){
            if(data[i] < REGION_SIZE * (regionIndex + 1)
                || (data[i] <= REGION_SIZE * (regionIndex + 1))
                        && regionIndex == REGION_NUM - 1){
                regionTempCount++;
            }
            else{
                regionData[regionIndex] = regionTempCount;
                regionIndex++;
                regionTempCount = 0;
                // 在下一个区间里重新扫描该值
                i--;
            }
        }

        // patch: 循环结束时的最后一个区间
        regionData[regionIndex] = regionTempCount;

        // 用0补齐
        for(var i=regionIndex+1; i<REGION_NUM; i++){
            regionData[i] = 0;
        }
        
        // x轴区间标示
        var xLabels = [];
        for(var i=0; i<REGION_NUM; i++){
            var low = i * REGION_SIZE;
            var high = (i + 1) * REGION_SIZE - 1;
            // 最后一列把100分也包含进去
            if(i == REGION_NUM - 1){
                high += 1;
            }
            xLabels.push(low + '~' + high);
        }

        // 非ajax更新chart
        scoreChart.refresh({
            chartData: {
                series: [{
                    name: '全体',
                    data: regionData
                }],
                categories: xLabels
            }
        });

    })();
    }


    // ================================
    // 考生导出、成绩分析、邮件通知
    // ================================
    if($('#viewRole').val() == 'tea'){
    (function(){
        var curExamId = $('#examId').val();
        var ajaxFlag = false;  //是否进行中

        var ajaxScoreAnalysis = function(){
            // onAjaxStart
            $('#loadingIcon').show();
            ajaxFlag = true;

            $.post(
                '/tea/exam/ajaxDoScoreAnalysis',
                {
                    examId: curExamId
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        jAlert('成绩分析成功', '提示', function(){
                            // 刷新当前页面
                            window.location.href = window.location.href;
                        });
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }

                    // onAjaxComplete
                    $('#loadingIcon').hide();
                    ajaxFlag = false;
                }
            );
        };

        $('#scoreAnalysisLink').click(function(){
            // 如果ajax正在进行中，阻止用户连续点击
            if(ajaxFlag){
                return false;
            }
            jConfirm('成绩分析后该考试将被关闭，所有原始数据将不可修改，<br>' + 
                '确定要进行成绩分析吗？', '提示', function(r){
                if(r){
                    ajaxScoreAnalysis();
                }
            });
        });

        $('#closedLink').click(function(){
            jAlert('您已经进行过成绩分析', '提示');
        });



        // *************
        var ajaxSendExamEmail = function(ajaxUrl){
            // onAjaxStart
            $('#emailLoadingIcon').show();
            ajaxFlag = true;

            $.post(
                ajaxUrl,
                {
                    examId: curExamId
                },
                function(data){
                    var result = $.parseJSON(data);
                    if(result.success){
                        var count = result.extra.split('|');
                        jAlert('成功发送 ' + count[0] + ' 封邮件，失败 ' + count[1] + ' 封', '提示');
                    }
                    else{
                        jAlert('操作失败', '提示');
                    }

                    // onAjaxComplete
                    $('#emailLoadingIcon').hide();
                    ajaxFlag = false;
                }
            );
        };

        $('#sendSecretCodeLink').click(function(){
            if(ajaxFlag){
                return false;
            }
            ajaxSendExamEmail('/tea/exam/ajaxSendSecretCodeEmail');
        });

        $('#sendFinalScoreLink').click(function(){
            if(ajaxFlag){
                return false;
            }
            ajaxSendExamEmail('/tea/exam/ajaxSendFinalScoreEmail');
        });
    })();
    }

});
</script>
<script type="text/javascript">
$(function(){
    // ================================
    // 执行
    // ================================
    BreadCrumbUtil.update(BreadCrumbUtil.HOME, BreadCrumbUtil.EXAM_LIST, BreadCrumbUtil.EXAM_VIEW);

});
</script>
</body>
</html>