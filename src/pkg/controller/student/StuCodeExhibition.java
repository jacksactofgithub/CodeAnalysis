package pkg.controller.student;

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
public class StuCodeExhibition {
	
	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;
	
	@RequestMapping("/stuCodeExh")
	public String showResult(HttpServletRequest request,@RequestParam("exam_id")int exam_id,
			@RequestParam("problem_name")String problem_name, HttpSession session) {
		int stu_id = (int) session.getAttribute("stu_id");
		request.setAttribute("stu_id", stu_id);
		request.setAttribute("code_exam_id", exam_id);
		request.setAttribute("code_problem_name", problem_name);
		
		ArrayList<String>testCases = (ArrayList<String>) runService.findOneStudentCommon(stu_id, problem_name, exam_id);
		ArrayList<String>files = (ArrayList<String>) codeService.getStuFileNamesByStuId(stu_id, exam_id, problem_name);
		
		request.setAttribute("testCases", testCases);
		request.setAttribute("files", files);
		
		String code = codeService.getStuCode(stu_id, 0, exam_id, problem_name,files.get(0)); 
		code = code.replaceAll("<", "&lt;");
		request.setAttribute("code", code);
		
		return "view/student/stuCodeExh";
	}

}
