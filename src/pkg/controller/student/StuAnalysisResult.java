package pkg.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.CodeService;
import pkg.service.StudentService;

/**
 * Servlet implementation class AnalysisResult
 */
@Controller
public class StuAnalysisResult {

	@Autowired
	StudentService service;
	@Autowired
	CodeService codeService;

	public StuAnalysisResult() {
		super();
	}

	@SuppressWarnings("unused")
	@RequestMapping("/stuAnalysisResult")
	public String showResult(HttpServletRequest request, HttpSession session) {

		String stu_account = (String) session.getAttribute("stu_account");
//		int stu_id = (int) session.getAttribute("stu_id");
		int stu_id = 12289;
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		String problem_name = request.getParameter("problem_name");
		JSONObject exam = service.getExamDetail(stu_account, exam_id);
		request.setAttribute("exam", exam);
		try {
			String exam_name = exam.getString("exam_name");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		String obj1 = "{'stuid':121250088,'questionName':'Triangle','caseNum':6,'caseName':[triangle1,triangle2,triangle3,triangle4,triangle5,triangle6],'result':[{'time':1,'passNo':[1,]},"
				+ "{'time':2,'passNo':[1,2]},{'time':3,'passNo':[1,2]},{'time':4,'passNo':[1,2,3]},{'time':5,'passNo':[1,2,3]},{'time':6,'passNo':[1,2,3]},"
				+ "{'time':7,'passNo':[1,2,3]},{'time':8,'passNo':[1,2,3,4]},{'time':9,'passNo':[1,2,3,4,5]},{'time':10,'passNo':[1,2,3,4]},{'time':11,'passNo':[1,2,3,4]},{'time':12,'passNo':[1,2,3,4]},"
				+ "{'time':13,'passNo':[1,2,3,4,5]},{'time':14,'passNo':[1,2,3,4,5,6]},{'time':15,'passNo':[1,2,3,4,5,6]}]}";
		JSONObject runResultJson=null;
		try {
			runResultJson = new JSONObject(obj1);
		} catch (JSONException e) {
			e.printStackTrace();
		}// 暂无数据

		request.setAttribute("runResultJson", runResultJson);//
		
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

		return "view/student/stuAnalysisResult";
	}

	public JSONObject reverse(JSONArray stasArray) throws JSONException {//

		JSONObject stasJson = new JSONObject();
		int len = stasArray.length();
//		int[] timestamp = new int[len];
//		double[] lineCount = new double[len];
//		int[] noteCount = new int[len];
//		int[] methodCount = new int[len];
//		int[] varCount = new int[len];
//		int[] maxCy = new int[len];
		
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
//			timestamp[i] = staObj.getInt("timestamp");
//			lineCount[i] = staObj.getInt("lineCount")/10;//除以十
//			noteCount[i] = staObj.getInt("noteCount");
//			methodCount[i] = staObj.getInt("methodCount");
//			varCount[i] = staObj.getInt("varyCount");
//			maxCy[i] = staObj.getInt("maxCyclomaticCpl");
		}

		stasJson.put("timestamp", timeArray);
		stasJson.put("lineCount", lineCount);
		stasJson.put("noteCount", noteCount);
		stasJson.put("methodCount", methodCount);
		stasJson.put("varCount", varCount);
		stasJson.put("maxCy", maxCy);
		return stasJson;
	}

	public String getProblemName(JSONObject exam, int id) {// 通过题目id得到题目名称
		return null;
	}

}
