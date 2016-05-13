package pkg.controller.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.CodeService;
import pkg.service.ExamService;
import pkg.service.RunService;

/**
 * Servlet implementation class AnalysisResult
 */
@Controller
public class StuAnalysisResult {

	@Autowired
	ExamService service;
	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;

	public StuAnalysisResult() {
		super();
	}

	@RequestMapping("/stuAnalysisResult")
	public String showResult(HttpServletRequest request, HttpSession session) {

 		int stu_id = (int) session.getAttribute("stu_id");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		String problem_name = request.getParameter("problem_name");
		JSONObject exam;
		try {
			exam = getExam(exam_id,stu_id);
			request.setAttribute("exam", exam);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject runResultJson = null;
		try {
			runResultJson = runService.getRuns(stu_id, problem_name, exam_id);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}

		request.setAttribute("runResultJson", runResultJson);
		
		ArrayList<String> files = (ArrayList<String>) codeService.getStuFileNamesByStuId(stu_id, exam_id, problem_name);
		request.setAttribute("files", files);
		
		JSONArray codeJson = codeService.getCodeRecord(stu_id, problem_name, exam_id,files.get(0));//代码统计
		
		JSONObject stasJson = null;
		try {
			stasJson = reverse(codeJson);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		request.setAttribute("staJsonstr", stasJson.toString());
		request.setAttribute("staJson", stasJson);

		return "view/student/stuAnalysisResult";
	}

	public JSONObject reverse(JSONArray stasArray) throws JSONException {//

		JSONObject stasJson = new JSONObject();
		int len = stasArray.length();
		
		JSONArray timeArray = new JSONArray();
		JSONArray lineCount = new JSONArray();
		JSONArray noteCount = new JSONArray();
		JSONArray methodCount = new JSONArray();
		JSONArray varCount = new JSONArray();
		JSONArray maxCy = new JSONArray();
		
		for (int i = 0; i < len; i++) {
			JSONObject staObj = stasArray.getJSONObject(i);
			timeArray.put(staObj.get("timestamp"));
			lineCount.put(staObj.get("lineCount"));
			noteCount.put(staObj.get("noteCount"));
			methodCount.put(staObj.get("methodCount"));
			varCount.put(staObj.get("varyCount"));
			maxCy.put(staObj.get("maxCyclomaticCpl"));
		}

		stasJson.put("timestamp", timeArray);
		stasJson.put("lineCount", lineCount);
		stasJson.put("noteCount", noteCount);
		stasJson.put("methodCount", methodCount);
		stasJson.put("varCount", varCount);
		stasJson.put("maxCy", maxCy);
		return stasJson;
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
