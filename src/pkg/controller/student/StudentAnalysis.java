package pkg.controller.student;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.CodeService;
import pkg.service.ExamService;
import pkg.service.StorageService;

@Controller
public class StudentAnalysis {
	@Autowired
	CodeService codeService;
	@Autowired
	StorageService storageService;
	@Autowired
	ExamService examService;
	
    public StudentAnalysis() {
        super();
    }
    
    /**
     * @param request
     * @return String view
     */
    
    @RequestMapping("/stuAnalysis")
    public String studentAnalysis(HttpServletRequest request,HttpSession session){
    	int stu_id=-1;
    	if(request.getParameter("stu_id")!=null){
    		stu_id = Integer.parseInt(request.getParameter("stu_id"));
    	}
    	String stu_name = request.getParameter("stu_name");
    	String uni_name = request.getParameter("uni_name");
    	
    	if(stu_id!=-1&&stu_name!=null){
    		
    		try {
    			stu_name = new String(stu_name.getBytes("ISO-8859-1") , "UTF-8");
    			uni_name = new String(uni_name.getBytes("ISO-8859-1") , "UTF-8");
    		} catch (UnsupportedEncodingException e1) {
    			e1.printStackTrace();
    		}
    		
        	session.setAttribute("stu_name", stu_name);
        	session.setAttribute("uni_name", uni_name);
        	session.setAttribute("stu_id", stu_id);
    	}else{
    		stu_id = (int)session.getAttribute("stu_id");
    	}
    	
    	JSONArray examArray = examService.getStudentExams(stu_id);
    	try {
			request.setAttribute("examArray", deleteUnAnalysis(examArray));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "view/student/stuAnalysis";
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
