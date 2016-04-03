package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.StudentService;

@Controller
public class StuAnalysis {
	
	@Autowired
	StudentService service;
	
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
    	String stu_account = (String) request.getAttribute("stu_account");
    	JSONArray examArray = service.getExams(stu_account);
    	request.setAttribute("examArray", examArray);
		return "view/student/stuAnalysis";
	}

}
