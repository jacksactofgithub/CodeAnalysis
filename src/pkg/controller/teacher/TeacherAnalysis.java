package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pkg.service.ExamService;

@Controller
public class TeacherAnalysis {

	@Autowired
	ExamService service;
	
	public TeacherAnalysis() {

	}

	/**
	 * 教师点击考试分析查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacherAnalysis")
	public String studentAnalysis(HttpServletRequest request,HttpSession session,@RequestParam("tea_id")int tea_id,
    		@RequestParam("tea_name")String tea_name,@RequestParam("uni_name")String uni_name) {
    	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
		if(tea_id!=0&&tea_name!=null){
        	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
        	session.setAttribute("tea_name", tea_name);
        	session.setAttribute("uni_name", uni_name);
        	session.setAttribute("tea_id", tea_id);
    	}else{
    		tea_id = (int)session.getAttribute("tea_id");
    	}
    	
    	JSONArray examArray = service.getTeacherFinishedExams(tea_id);
    	request.setAttribute("examArray", examArray);
    	
		return "view/teacher/teacherAnalysis";
	}

}
