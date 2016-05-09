package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pkg.service.CodeService;
import pkg.service.ExamService;
import pkg.service.RunService;

@Controller
public class StudentAnalysis {
	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;
	@Autowired
	ExamService examService;
	
    public StudentAnalysis() {
        super();
    }
    
    /**
     * 学生点击考试分析页面;显示自己参加过的考试
     * @param request
     * @return
     */
    
    @RequestMapping("/stuAnalysis")
    public String studentAnalysis(HttpServletRequest request,HttpSession session,@RequestParam("stu_id")int stu_id,
    		@RequestParam("stu_name")String stu_name,@RequestParam("uni_name")String uni_name){
    	//首先看request中有没有参数 如果有设置session否则去session中找
    	if(stu_id!=0&&stu_name!=null){
        	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
        	session.setAttribute("stu_name", stu_name);
        	session.setAttribute("uni_name", uni_name);
        	session.setAttribute("stu_id", stu_id);
    	}else{
    		stu_id = (int)session.getAttribute("stu_id");
    	}
    	
    	JSONArray examArray = examService.getStudentExams(stu_id);
    	request.setAttribute("examArray", examArray);
		return "view/student/stuAnalysis";
	}
    
}
