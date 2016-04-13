package pkg.controller.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pkg.service.CodeService;

@Controller
public class ShowCodeController {
	
	@Autowired
	CodeService service;
	
	/**
	 * 
	 * @param stu_id 学生学号
	 * @param time 时间点
	 * @param exam_id 考试号
	 * @param problem_name 题目名称
	 * @return 所有代码(String)
	 */
	
	@ResponseBody
	@RequestMapping(value = "/showCode", method=RequestMethod.POST)
	public String showResult(@RequestParam("stu_id")int stu_id,@RequestParam("time")int time,@RequestParam("exam_id")int exam_id,
			@RequestParam("problem_name")String problem_name,HttpServletRequest request, HttpSession session) {
		//参数 学生学号,时间点,考试号,考题名
		String code = service.getStuCode(stu_id, time, exam_id, problem_name);
		System.out.println(stu_id);
		System.out.println(time);
		System.out.println(exam_id);
		System.out.println(problem_name);
		return code;
	}
	
	@ResponseBody
	@RequestMapping(value = "/showTestCase", method=RequestMethod.POST)
	public String showTestCase(@RequestParam("exam_id")int exam_id,	@RequestParam("problem_name")String problem_name,
			HttpServletRequest request, HttpSession session) {
		//参数 学生学号,时间点,考试号,考题名
		//String code = service.getStuCode(stu_id, time, exam_id, problem_name);
		String testCase = "success2";
		return testCase;
	}
	

}
