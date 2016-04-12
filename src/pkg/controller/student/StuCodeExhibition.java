package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StuCodeExhibition {
	
	@RequestMapping("/stuCodeExh")
	public String showResult(HttpServletRequest request,@RequestParam("exam_id")String exam_id,
			@RequestParam("problem_name")String problem_name, HttpSession session) {
		//得到学生id 考试id 题目名称
		int stu_id = (int) session.getAttribute("stu_id");
		request.setAttribute("stu_id", stu_id);
		request.setAttribute("exam_id", exam_id);
		request.setAttribute("problem_name", problem_name);
		return "view/student/stuCodeExh";
	}

}
