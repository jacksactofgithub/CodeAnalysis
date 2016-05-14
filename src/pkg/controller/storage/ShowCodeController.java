package pkg.controller.storage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pkg.service.CodeService;

@Controller
public class ShowCodeController {
	
	@Autowired
	CodeService service;
	
	/**
	 * 
	 */
	
	@ResponseBody
	@RequestMapping(value = "/showCode", method=RequestMethod.POST)
	public String showResult(@RequestParam("stu_id")int stu_id,@RequestParam("time")int time,@RequestParam("exam_id")int exam_id,
			@RequestParam("problem_name")String problem_name,@RequestParam("file_name")String file_name, HttpServletRequest request, HttpSession session) {
		String code = service.getStuCode(stu_id, time, exam_id, problem_name,file_name);
		try {
			byte[] bytes = code.getBytes("UTF-8");
			
			code = new String(bytes , "ISO-8859-1");
			
			code = new String(code.getBytes("ISO-8859-1") , "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			code = URLEncoder.encode(code, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return code;
	}
	
	@ResponseBody
	@RequestMapping(value = "/showCodeByClassMemId", method=RequestMethod.POST)
	public String showResultByClassMemId(@RequestParam("class_member_id")int class_member_id,@RequestParam("time")int time,@RequestParam("exam_id")int exam_id,
			@RequestParam("problem_name")String problem_name,@RequestParam("file_name")String file_name, HttpServletRequest request, HttpSession session) {
		String code = service.getStuCodeByClassMemId(class_member_id, time, exam_id, problem_name,file_name);
//		try {
//			byte[] bytes = code.getBytes("UTF-8");
//			
//			code = new String(bytes , "ISO-8859-1");
//			
//			code = new String(code.getBytes("ISO-8859-1") , "UTF-8");
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		try {
//			code = URLEncoder.encode(code, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return code;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCodeStas", method=RequestMethod.POST)
	public String getCodeStas(@RequestParam("stu_id")int stu_id,@RequestParam("exam_id")int exam_id,	@RequestParam("problem_name")String problem_name,
			@RequestParam("file_name")String file_name,HttpServletRequest request, HttpSession session) {
		//String code = service.getStuCode(stu_id, time, exam_id, problem_name);
		JSONArray codeJson = service.getCodeRecordByClassMemId(stu_id, problem_name, exam_id,file_name); 
		
		JSONObject stasJson = null;
		try {
			stasJson = reverse(codeJson);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return stasJson.toString();
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
