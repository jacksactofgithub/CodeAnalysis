
package pkg;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class ExamQue
 */
@Controller
public class ExamQue{
       
    public ExamQue() {
        super();
    }
    @RequestMapping("examQue")
    public String showQuestions(Model model,HttpServletRequest request){
    	return "view/examQue";
    }

}
