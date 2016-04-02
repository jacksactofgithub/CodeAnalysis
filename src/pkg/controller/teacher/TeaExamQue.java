package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeaExamQue {

	/**
	 * 教师查看一次考试的考题列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/teaExamQue")
	public String studentAnalysis(HttpServletRequest request) {
		return "view/teacher/teaExamQue";
	}

}
