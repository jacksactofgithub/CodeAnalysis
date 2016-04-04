package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String studentAnalysis(HttpServletRequest request,HttpSession session){
    	//在尚未接通慕测的情况下全用假数据
    	request.setAttribute("stu_account", "lshuo12@software.nju.edu.cn");
    	request.setAttribute("stu_name", "刘硕");
    	request.setAttribute("uni_name", "南京大学");
    	
    	session.setAttribute("stu_account", "lshuo12@software.nju.edu.cn");
    	session.setAttribute("stu_name", "刘硕");
    	session.setAttribute("uni_name", "南京大学");
    	
    	String stu_account = (String) request.getAttribute("stu_account");
    	//String stu_account = (String) request.getParameter("stu_account");
    	//request.setAttribute("stu_account",stu_account);//根据提供参数的方法不同更改
    	JSONArray examArray = service.getExams(stu_account);
    	request.setAttribute("examArray", examArray);
		return "view/student/stuAnalysis";
	}

}
