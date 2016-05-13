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
	 * @param request
	 * @return
	 */
	@RequestMapping("/examDetail")
	public String studentAnalysis(HttpServletRequest request,HttpSession session) {
		
		int exam_id =Integer.parseInt(request.getParameter("exam_id")); 
		JSONObject exam = service.getExamInfo(exam_id);
		request.setAttribute("exam", exam);
		
		JSONArray studentArray = service.getExamPapers(exam_id);
		try {
			request.setAttribute("studentArray", deleteUnexam(studentArray));
		} catch (JSONException e) {
			e.printStackTrace();
		} 

		return "view/teacher/examDetail";
	}
	
	public JSONArray deleteUnexam(JSONArray array) throws JSONException{
    	JSONArray newArray = new JSONArray();
    	for(int i=0;i<array.length();i++){
    		JSONObject student = array.getJSONObject(i);
    		if(student.getDouble("score")!=-1){
    			newArray.put(student);
    		}
    	}
    	return newArray;
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
			lineCount[i] = staObj.getInt("lineCount"); 
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
