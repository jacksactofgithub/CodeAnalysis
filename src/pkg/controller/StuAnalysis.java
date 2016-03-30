package pkg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class StudentSta
 */
@Controller
public class StuAnalysis {
	
    public StuAnalysis() {
        super();
    }
    
    /**
     * 学生点击考试分析页面;显示自己参加过的考试
     * @param request
     * @return
     */
    
    @RequestMapping("/stuAnalysis")
    public String studentAnalysis(HttpServletRequest request){
		return "view/stuanalysis";
	}

}
