package pkg.controller.teacher;

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
public class TeaAnalysisResult {

	@Autowired
	ExamService examService;
	@Autowired
	RunService runService;
	@Autowired
	CodeService codeService;

	public TeaAnalysisResult() {
		super();
	}

	@RequestMapping("/teaAnalysisResult")
	public String showResult(HttpServletRequest request, HttpSession session) {

		int stu_id = Integer.parseInt(request.getParameter("stu_id"));
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		String problem_name = request.getParameter("problem_name");
		JSONObject exam = examService.getExamInfo(exam_id);
		request.setAttribute("exam", exam);

		JSONObject runResultJson = null;
		try {
			runResultJson = runService.getRuns(stu_id, problem_name, exam_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		request.setAttribute("runResultJson", runResultJson);

		JSONArray codeJson = codeService.getCodeRecord(stu_id, problem_name, exam_id);		
		JSONObject stasJson = null;
		try {
			stasJson = reverse(codeJson);
			System.out.println(stasJson.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		request.setAttribute("staJsonstr", stasJson.toString());
		request.setAttribute("staJson", stasJson);

		return "view/teacher/teaAnalysisResult";
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
