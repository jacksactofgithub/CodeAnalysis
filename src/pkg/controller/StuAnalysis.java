package pkg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class StudentSta
 */
@Controller
public class StuAnalysis {
	
    public StuAnalysis() {
        super();
    }
    
    @RequestMapping("/stuAnalysis")
    public String studentAnalysis(Model model,HttpServletRequest request){
		return "view/stuanalysis";
	}

}
