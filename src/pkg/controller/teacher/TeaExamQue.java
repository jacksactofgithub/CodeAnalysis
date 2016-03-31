package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeaExamQue {
	
	 @RequestMapping("/teaExamQue")
	 public String studentAnalysis(HttpServletRequest request){
			return "view/teacher/teaExamQue";
	 }

}
