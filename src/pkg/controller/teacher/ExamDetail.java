package pkg.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.TeacherService;

@Controller
public class ExamDetail {
	

	@Autowired
	TeacherService service;

	/**
	 * 教师查看一次考试的学生答题情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/examDetail")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
		
		String id =request.getParameter("id");//考试id
		String tea_account = (String)session.getAttribute("tea_account");
		service.getExamStudent(Integer.parseInt(id), tea_account);
		JSONObject exam = service.getExamDetails(Integer.parseInt(id),tea_account);
		request.setAttribute("exam", exam);
		
		JSONArray studentArray = service.getExamStudent(Integer.parseInt(id), tea_account);
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
		
		/**
		 * ------------------------------------------------------------
		 */
		JSONObject runResultJson = null;
		try {
			String obj1 = "{'stuid':121250088,'examNo':1,'questionNo':'1','caseNum':6,'caseName':[triangle1,triangle2,triangle3,triangle4,triangle5,triangle6],'result':[{'time':1,'passNo':[1,2]},"
					+ "{'time':3,'passNo':[1,2,3]},{'time':8,'passNo':[1,2,3,4]},{'time':11,'passNo':[1,2,3,4,5]},"
					+ "{'time':14,'passNo':[1,2,3,4,5,6]}]}";
			runResultJson = new JSONObject(obj1);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		request.setAttribute("runResultJson", runResultJson);

		String obj3 = "[{'timestamp': 1, 'source':'q1' , 'lineCount':5 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 2, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 3, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 4, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 5, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 6, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 7, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 8, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1},"
				+ "{'timestamp': 9, 'source':'q1' , 'lineCount':6 , 'noteCount':0 , 'methodCount': 1 , 'varyCount': 2 , 'maxCyclomaticCpl': 1}]";
		try {
			reverse(new JSONArray(obj3));
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		String obj4 = "{'source':'q1','timestamp':[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],'lineCount':[0,2,7,8,15,16,20,25,27,28,35,36,30,35,37],"
				+ "'noteCount':[0,1,3,3,5,6,6,8,10,3,5,6,6,8,10],'methodCount':[0,1,1,1,1,2,2,2,2,1,1,2,2,3,3],"
				+ "'varCount':[0,1,3,4,5,5,5,6,6,4,5,5,5,6,6],'maxCy':[1,1,1,2,2,2,3,3,3,2,2,2,3,3,3]}";
		request.setAttribute("staJsonstr", obj4);
		try {
			request.setAttribute("staJson", new JSONObject(obj4));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/**
		 * ------------------------------------------------------------
		 */
		
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