package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamDetail {

	/**
	 * 教师查看一次考试的学生答题情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/ExamDetail")
	public String studentAnalysis(HttpServletRequest request) {
		return "view/teacher/examDetail";
	}
	
}
