package pkg.controller.student;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
		
		List<String>testCases = (List<String>) runService.findOneStudentCommon(stu_id, problem_name, exam_id);
		List<String>files = (List<String>) codeService.getStuFileNamesByStuId(stu_id, exam_id, problem_name);
		
		request.setAttribute("testCases", testCases);
		request.setAttribute("files", files);
		
		String code = codeService.getStuCode(stu_id, 0, exam_id, problem_name,files.get(0)); 
		code = code.replaceAll("<", "&lt;");
		try {
			byte[] bytes = code.getBytes("UTF-8");
			
			code = new String(bytes , "ISO-8859-1");
			
			code = new String(code.getBytes("ISO-8859-1") , "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("code", code);
		
		return "view/student/stuCodeExh";
	}

}
