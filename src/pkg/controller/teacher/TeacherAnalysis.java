package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherAnalysis {

	public TeacherAnalysis() {

	}

	/**
	 * 教师点击考试分析查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacherAnalysis")
	public String studentAnalysis(HttpServletRequest request) {
		String teacherId = request.getParameter("teacherId");//得到教师id;getAttribute
		return "view/teacher/teacherAnalysis";
	}

}
