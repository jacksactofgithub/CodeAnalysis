
package pkg;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Servlet implementation class ExamQue
 */
@Controller
public class ExamQue{
       
    public ExamQue() {
        super();
    }

    public String showQuestions(Model model,HttpServletRequest request){
    	return "view/examQue";
    }

}
