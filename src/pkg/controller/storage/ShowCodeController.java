package pkg.controller.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowCodeController {
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
	public String showResult(@RequestParam("stu_id")String stu_id,@RequestParam("time")int time,@RequestParam("exam_id")String exam_id,
			@RequestParam("problem_name")String problem_name,HttpServletRequest request, HttpSession session) {
		//参数 学生学号,时间点,考试号,考题名
		return "";
	}

}
