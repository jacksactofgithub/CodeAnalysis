package pkg;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class AnalysisResult
 */
@Controller
public class AnalysisResult{
	
    public AnalysisResult() {
        super();
    }

    
    @RequestMapping("/")
    public String showResult(Model model,HttpServletRequest request){
    	JSONObject runResultJson = null;//运行结果json
    	//{stuid:,examNo考试号:,questionNo:,caseNum:测试用例数目,result:[{time:从考试开始算起,passNo:,failNo:},{time:,passNo:,failNo:},...]}//运行结果的json
    	try {
    		String obj1="{'stuid':121250088,'examNo':1,'questionNo':'1','caseNum':6,'caseName':[triangle1,triangle2,triangle3,triangle4,triangle5,triangle6],'result':[{'time':1,'passNo':[1,2]},"
    				+ "{'time':3,'passNo':[1,2,3]},{'time':8,'passNo':[1,2,3,4]},{'time':11,'passNo':[1,2,3,4,5]},"
    				+ "{'time':14,'passNo':[1,2,3,4,5,6]}]}"; 
    		runResultJson = new JSONObject(obj1);
    		//runResultJson.put("result", new JSONArray("[{'runNo':1,'passNo':'1/2/3','failNo':'4/5/6'},{'runNo':1,'passNo':'1/2/3','failNo':'4/5/6'}]"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	//
    	request.setAttribute("runResultJson", runResultJson);//测试用例通过情况的json
    	
    	//代码统计数据jsonArray格式[{"timestamp": double, "source":string , "lineCount":int , "noteCount":int , "methodCount": int , "varyCount": int , "maxCyclomaticCpl": int},
    	//{"timestamp": double, "source":string , "lineCount":int , "noteCount":int , "methodCount": int , "varyCount": int , "maxCyclomaticCpl": int}]
    	//JSONObject codeSta =null;//代码统计json;调用刘睿模块
    		String obj3="[{'timestamp': 1, 'source':'q1' , 'lineCount':5 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{{'timestamp': 2, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 3, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 4, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 5, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 6, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 7, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 8, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
    				+ "{'timestamp': 9, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1}]";
    		reverse(obj3);
    		//解析成另一种格式{timestamp:[],source:,lineCount:[],noteCount[],methodCount:[],varCount:[],maxCy:[]}
    	
    	String obj4 = "{'source':'q1','timestamp':[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],'lineCount':[0,2,7,8,15,16,20,25,27,28,35,36,30,35,37],"
    			+ "'noteCount':[0,1,3,3,5,6,6,8,10,3,5,6,6,8,10],'methodCount':[0,1,1,1,1,2,2,2,2,1,1,2,2,3,3],"
    			+ "'varCount':[0,1,3,4,5,5,5,6,6,4,5,5,5,6,6],'maxCy':[1,1,1,2,2,2,3,3,3,2,2,2,3,3,3]}";
    	request.setAttribute("staJsonstr", obj4);//测试用例通过情况的json
    	try {
			request.setAttribute("staJson", new JSONObject(obj4));
		} catch (JSONException e) {
			e.printStackTrace();
		}//测试用例通过情况的json
    	
    	return "view/analysisResult";
    }
    
    public void reverse(String str){//将json形式转换
    	
    }

}

