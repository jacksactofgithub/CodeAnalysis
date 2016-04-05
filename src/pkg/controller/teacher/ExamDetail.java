package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
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
		
		JSONArray studentArray = service.getExamStudent(Integer.parseInt(id), tea_account);
		request.setAttribute("studentArray", studentArray);
		//再此处确定题目数量
		try {
			JSONObject student = studentArray.getJSONObject(0);
			int problem2_id = student.getInt("problem2_id");
			int problem3_id = student.getInt("problem3_id");
			request.setAttribute("problem2_id", problem2_id );
			request.setAttribute("problem3_id", problem3_id );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return "view/teacher/examDetail";
	}
	
}
