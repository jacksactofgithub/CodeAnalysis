package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.ExamService;
import pkg.service.StorageService;

@Controller
public class TeacherAnalysis {

	@Autowired
	ExamService service;
	@Autowired
	StorageService storageService;
	
	public TeacherAnalysis() {

	}

	/**
	 * 教师点击考试分析查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacherAnalysis")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
    	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
    	int tea_id=-1;
    	if(request.getParameter("tea_id")!=null){
    		tea_id = Integer.parseInt(request.getParameter("tea_id"));
    	}
    	String tea_name = request.getParameter("tea_name");
    	String uni_name = request.getParameter("uni_name");
		if(tea_id!=0&&tea_name!=null){
        	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
        	session.setAttribute("tea_name", tea_name);
        	session.setAttribute("uni_name", uni_name);
        	session.setAttribute("tea_id", tea_id);
    	}else{
    		tea_id = (int)session.getAttribute("tea_id");
    	}
    	
    	JSONArray examArray = service.getTeacherFinishedExams(tea_id);
    	try {
			request.setAttribute("examArray", deleteUnAnalysis(examArray));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "view/teacher/teacherAnalysis";
	}

	 public JSONArray deleteUnAnalysis(JSONArray array) throws JSONException{
	    	JSONArray newArray = new JSONArray();
	    	for(int i=0;i<array.length();i++){
	    	  	int state = storageService.getAnalyseState(array.getJSONObject(i).getInt("exam_id"));
	    	  	if(state==1){
	    	  		newArray.put(array.get(i));
	    	  	}
	    	}
	    	return newArray;
	   }
	
}
