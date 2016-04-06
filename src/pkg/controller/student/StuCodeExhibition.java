package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StuCodeExhibition {
	
	@RequestMapping("/stuCodeExh")
	public String showResult(HttpServletRequest request, HttpSession session) {
		return "";
	}

}
