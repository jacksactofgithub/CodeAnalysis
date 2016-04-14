
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
     * 学生一次考试各个题目得分等信息的显示;可以由此页面跳转至题目分析页面
     * @param request
     * @return
     */
    
    @RequestMapping("/stuExamQue")
    public String showQuestions(HttpServletRequest request,HttpSession session){
    	//点击一次考试传入了一次考试的id
    	int exam_id = Integer.parseInt(request.getParameter("exam_id"));
    	int stu_id =(int)session.getAttribute("stu_id");
    	JSONObject exam;
		try {
			exam = getExam(exam_id,stu_id);
			request.setAttribute("exam", exam);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	//将一次考试的json写入request
    	JSONArray problemArray = problemService.getExamProblems(stu_id, exam_id);
    	request.setAttribute("problemArray",problemArray);
    	return "view/student/stuExamQue";
    }

	public JSONObject getExam(int exam_id, int stu_id) throws JSONException {// 通过题目id得到题目名称
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
