package pkg.controller.teacher;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pkg.service.CodeService;
import pkg.service.RunService;

@Controller
public class TeaCodeExhibition {
	
	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;
	
	@RequestMapping("/teaCodeExh")
	public String showResult(@RequestParam("exam_id")int exam_id,@RequestParam("stu_id")int stu_id,
			@RequestParam("problem_name")String problem_name, HttpServletRequest request, HttpSession session) {
		request.setAttribute("code_stu_id", stu_id);
		request.setAttribute("code_exam_id", exam_id);
		request.setAttribute("code_problem_name", problem_name);
		
		String code = codeService.getStuCode(stu_id, 0, exam_id, problem_name);//初始化第0分钟的代码
		request.setAttribute("code", code);
		
		ArrayList<String>testCases = (ArrayList<String>) runService.findCommonTestCases(problem_name, exam_id);
		request.setAttribute("testCases", testCases);
		
		return "view/teacher/teaCodeExh";
	}

}
