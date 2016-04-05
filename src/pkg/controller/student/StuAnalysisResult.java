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
		int stu_id = (int) session.getAttribute("stu_id");
		String exam_id = request.getParameter("exam_id");
		String problem_id = request.getParameter("problem_id");
		JSONObject exam = service.getExamDetail(stu_account, Integer.parseInt(exam_id));
		request.setAttribute("exam", exam);
		try {
			String exam_name = exam.getString("exam_name");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		JSONObject runResultJson = null;
		try {
			String obj1 = "{'stuid':121250088,'examNo':1,'questionNo':'1','caseNum':6,'caseName':[triangle1,triangle2,triangle3,triangle4,triangle5,triangle6],'result':[{'time':1,'passNo':[1,2]},"
					+ "{'time':3,'passNo':[1,2,3]},{'time':8,'passNo':[1,2,3,4]},{'time':11,'passNo':[1,2,3,4,5]},"
					+ "{'time':14,'passNo':[1,2,3,4,5,6]}]}";
			runResultJson = new JSONObject(obj1);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		request.setAttribute("runResultJson", runResultJson);//

		String obj3 = "[{'timestamp': 1, 'source':'q1' , 'lineCount':5 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 2, 'source':'q1' , 'lineCount':6 , 'noteCount':1 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 3, 'source':'q1' , 'lineCount':7 , 'noteCount':3 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 4, 'source':'q1' , 'lineCount':9 , 'noteCount':5 , 'methodCount': 2 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 5, 'source':'q1' , 'lineCount':11 , 'noteCount':5 , 'methodCount': 3 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 6, 'source':'q1' , 'lineCount':14 , 'noteCount':4 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 7, 'source':'q1' , 'lineCount':15 , 'noteCount':6 , 'methodCount': 3 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 8, 'source':'q1' , 'lineCount':12 , 'noteCount':9 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 9, 'source':'q1' , 'lineCount':18 , 'noteCount':11 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 10, 'source':'q1' , 'lineCount':22 , 'noteCount':9 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 11, 'source':'q1' , 'lineCount':30 , 'noteCount':14 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 12, 'source':'q1' , 'lineCount':40 , 'noteCount':15 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 13, 'source':'q1' , 'lineCount':45 , 'noteCount':12 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 14, 'source':'q1' , 'lineCount':55 , 'noteCount':11 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 15, 'source':'q1' , 'lineCount':65 , 'noteCount':16 , 'methodCount': 4 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},]";
		JSONObject stasJson = null;
		try {
			stasJson = reverse(new JSONArray(obj3));
			System.out.println(stasJson.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		String obj4 = "{'source':'q1','timestamp':[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],'lineCount':[0,2,7,8,15,16,20,25,27,28,35,36,30,35,37],"
				+ "'noteCount':[0,1,3,3,5,6,6,8,10,3,5,6,6,8,10],'methodCount':[0,1,1,1,1,2,2,2,2,1,1,2,2,3,3],"
				+ "'varCount':[0,1,3,4,5,5,5,6,6,4,5,5,5,6,6],'maxCy':[1,1,1,2,2,2,3,3,3,2,2,2,3,3,3]}";
		// request.setAttribute("staJsonstr", obj4);
		request.setAttribute("staJsonstr", stasJson.toString());
		try {
			request.setAttribute("staJson", new JSONObject(obj4));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return "view/student/stuAnalysisResult";
	}

	public JSONObject reverse(JSONArray stasArray) throws JSONException {//

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

	public String getProblemName(JSONObject exam, int id) {// 通过题目id得到题目名称
		return null;
	}

}
