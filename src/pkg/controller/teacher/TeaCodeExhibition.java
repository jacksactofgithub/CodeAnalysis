package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeaCodeExhibition {
	
	@RequestMapping("/teaCodeExh")
	public String showResult(@RequestParam("exam_id")int exam_id,@RequestParam("stu_id")int stu_id,
			@RequestParam("problem_name")String problem_name, HttpServletRequest request, HttpSession session) {
		request.setAttribute("code_stu_id", stu_id);
		request.setAttribute("code_exam_id", exam_id);
		request.setAttribute("code_problem_name", problem_name);
		
		return "view/teacher/teaCodeExh";
	}

}
