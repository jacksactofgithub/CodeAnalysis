package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeaCodeExhibition {
	
	@RequestMapping("/teaCodeExh")
	public String showResult(HttpServletRequest request, HttpSession session) {
		return "";
	}

}
