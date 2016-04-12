package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.ExamService;

@Controller
public class ExamDetail {
	

	@Autowired
	ExamService service;

	/**
	 * 教师查看一次考试的学生答题情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/examDetail")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
		
		int id =Integer.parseInt(request.getParameter("id"));//考试id
		service.getExamPapers(id);
		JSONObject exam = service.getExamInfo(id);
		request.setAttribute("exam", exam);
		
		JSONArray studentArray = service.getExamPapers(id);
		System.out.println(studentArray.toString());
		request.setAttribute("studentArray", studentArray);
		//再此处确定题目数量
		try {
			JSONObject student = studentArray.getJSONObject(0);
			int problem2_id = student.getInt("problem2_id");
			int problem3_id = student.getInt("problem3_id");
			request.setAttribute("problem2_id", problem2_id );
			request.setAttribute("problem3_id", problem3_id );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "view/teacher/examDetail";
	}
	
	public JSONObject reverse(JSONArray stasArray) throws JSONException {

		JSONObject stasJson = new JSONObject();
		int len = stasArray.length();
		int[] timestamp = new int[len];
		double[] lineCount = new double[len];
		int[] noteCount = new int[len];
		int[] methodCount = new int[len];
		int[] varCount = new int[len];
		int[] maxCy = new int[len];
		for (int i = 0; i < len; i++) {
			JSONObject staObj = stasArray.getJSONObject(i);
			timestamp[i] = staObj.getInt("timestamp");
			lineCount[i] = staObj.getInt("lineCount")/10;//除以十
			noteCount[i] = staObj.getInt("noteCount");
			methodCount[i] = staObj.getInt("methodCount");
			varCount[i] = staObj.getInt("varyCount");
			maxCy[i] = staObj.getInt("maxCyclomaticCpl");
		}

		stasJson.put("timestamp", timestamp);
		stasJson.put("lineCount", lineCount);
		stasJson.put("noteCount", noteCount);
		stasJson.put("methodCount", methodCount);
		stasJson.put("varCount", varCount);
		stasJson.put("maxCy", maxCy);
		return stasJson;
	}
	
}
