
package pkg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class ExamQue
 */
@Controller
public class ExamQue{
       
    public ExamQue() {
        super();
    }
    /**
     * 学生一次考试各个题目得分等信息的显示;可以由此页面跳转至题目分析页面
     * @param request
     * @return
     */
    
    @RequestMapping("/examQue")
    public String showQuestions(HttpServletRequest request){
    	return "view/examQue";
    }

}
