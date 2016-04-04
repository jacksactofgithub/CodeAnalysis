package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.TeacherService;

@Controller
public class ExamDetail {
	

	@Autowired
	TeacherService service;

	/**
	 * 教师查看一次考试的学生答题情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/examDetail")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
		
		String id =request.getParameter("id");//考试id
		String tea_account = (String)session.getAttribute("tea_account");
		service.getExamStudent(Integer.parseInt(id), tea_account);
		JSONObject exam = service.getExamDetails(Integer.parseInt(id),tea_account);
		request.setAttribute("exam", exam);
		return "view/teacher/examDetail";
	}
	
}
