package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.CodeService;
import pkg.service.RunService;
import pkg.service.StorageService;
import pkg.service.StudentService;

@Controller
public class StudentAnalysis {
	@Autowired
	StudentService service;
	@Autowired
	StorageService storageService;
	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;
	
    public StudentAnalysis() {
        super();
    }
    
    /**
     * 学生点击考试分析页面;显示自己参加过的考试
     * @param request
     * @return
     */
    
    @RequestMapping("/stuAnalysis")
    public String studentAnalysis(HttpServletRequest request,HttpSession session){
    	storageService.startStore(382, 1);
    	//在尚未接通慕测的情况下全用假数据
    	request.setAttribute("stu_account", "397342673@qq.com");
    	request.setAttribute("stu_name", "毕潇晗");
    	request.setAttribute("uni_name", "南京大学");
    	//在第一次进的时候如果request中带了参数则将session中参数设置为登录信息;否则session中信息不变(如果是本网站的请求)
    	session.setAttribute("stu_account", "397342673@qq.com");
    	session.setAttribute("stu_name", "毕潇晗");
    	session.setAttribute("uni_name", "南京大学");
    	session.setAttribute("stu_id", 6605);
    	
    	String stu_account = (String) request.getAttribute("stu_account");
    	//String stu_account = (String) request.getParameter("stu_account");
    	//request.setAttribute("stu_account",stu_account);//根据提供参数的方法不同更改
    	JSONArray examArray = service.getExams(stu_account);//改成用id查询
    	request.setAttribute("examArray", examArray);
		return "view/student/stuAnalysis";
	}
    
}
