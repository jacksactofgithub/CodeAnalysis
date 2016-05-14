package pkg.controller.teacher;

import java.io.UnsupportedEncodingException;

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
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacherAnalysis")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
    	int tea_id=-1;
    	if(request.getParameter("tea_id")!=null){
    		tea_id = Integer.parseInt(request.getParameter("tea_id"));
    	}
    	
    	if(tea_id==3){
    		tea_id=34;
    	}
    	
    	String tea_name = request.getParameter("tea_name");
    	String uni_name = request.getParameter("uni_name");
    	
		if(tea_id!=-1&&tea_name!=null){
			try {
    			tea_name = new String(tea_name.getBytes("ISO-8859-1") , "UTF-8");
    			uni_name = new String(uni_name.getBytes("ISO-8859-1") , "UTF-8");
    		} catch (UnsupportedEncodingException e1) {
    			e1.printStackTrace();
    		}
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
