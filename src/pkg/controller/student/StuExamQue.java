
package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.ExamService;
import pkg.service.ProblemService;

/**
 * Servlet implementation class ExamQue
 */
@Controller
public class StuExamQue{
       
    public StuExamQue() {
        super();
    }
    
    @Autowired
    ExamService service;
    @Autowired
    ProblemService problemService;
    /**
     * @param request
     * @return
     */
    
    @RequestMapping("/stuExamQue")
    public String showQuestions(HttpServletRequest request,HttpSession session){
    	int exam_id = Integer.parseInt(request.getParameter("exam_id"));
    	int stu_id =(int)session.getAttribute("stu_id");
    	JSONObject exam;
		try {
			exam = getExam(exam_id,stu_id);
			request.setAttribute("exam", exam);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	JSONArray problemArray = problemService.getExamProblems(stu_id, exam_id);
    	request.setAttribute("problemArray",problemArray);
    	return "view/student/stuExamQue";
    }

	public JSONObject getExam(int exam_id, int stu_id) throws JSONException { 
		JSONArray examArray = service.getStudentExams(stu_id);
		for(int i=0;i<examArray.length();i++){
			JSONObject exam = examArray.getJSONObject(i);
			if(exam.getInt("exam_id")==exam_id){
				return exam;
			}
		}
		return examArray.getJSONObject(0);
	}
    
}
