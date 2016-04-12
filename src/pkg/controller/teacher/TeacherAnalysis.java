package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
    	//在尚未接通慕测的情况下全用假数据
    	request.setAttribute("tea_name", "刘钦");
    	request.setAttribute("uni_name", "南京大学");
    	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
    	session.setAttribute("tea_id", 34);//刘钦真是id
    	session.setAttribute("tea_name", "刘钦");
    	session.setAttribute("uni_name", "南京大学");
    	
    	int teaId = (int) session.getAttribute("tea_id");
    	JSONArray examArray = service.getTeacherFinishedExams(teaId);
    	request.setAttribute("examArray", examArray);
    	
		return "view/teacher/teacherAnalysis";
	}

}
