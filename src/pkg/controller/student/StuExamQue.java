
package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.StudentService;

/**
 * Servlet implementation class ExamQue
 */
@Controller
public class StuExamQue{
       
    public StuExamQue() {
        super();
    }
    
    @Autowired
    StudentService service;
    /**
     * 学生一次考试各个题目得分等信息的显示;可以由此页面跳转至题目分析页面
     * @param request
     * @return
     */
    
    @RequestMapping("/stuExamQue")
    public String showQuestions(HttpServletRequest request){
    	int id = Integer.parseInt(request.getParameter("id"));
    	String stu_account = (String) request.getAttribute("stu_account");
    	JSONObject exam = service.getExamDetail(stu_account, id);//此处可能接口更改
    	request.setAttribute("exam", exam);
    	JSONArray problemArray = service.getProblems(id,stu_account);
    	request.setAttribute("problemArray",problemArray);
    	return "view/student/stuExamQue";
    }

}
