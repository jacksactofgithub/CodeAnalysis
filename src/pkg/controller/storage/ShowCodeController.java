package pkg.controller.storage;

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
	 * @param stu_id 学生学号
	 * @param time 时间点
	 * @param exam_id 考试号
	 * @param problem_name 题目名称
	 * @return 所有代码(String)
	 */
	
	@ResponseBody
	@RequestMapping(value = "/showCode", method=RequestMethod.POST)
	public String showResult(@RequestParam("stu_id")int stu_id,@RequestParam("time")int time,@RequestParam("exam_id")int exam_id,
			@RequestParam("problem_name")String problem_name,@RequestParam("file_name")String file_name, HttpServletRequest request, HttpSession session) {
		//参数 学生学号,时间点,考试号,考题名
		String code = service.getStuCode(stu_id, time, exam_id, problem_name,file_name);
		return code;
	}
	
	@ResponseBody
	@RequestMapping(value = "/showTestCase", method=RequestMethod.POST)
	public String showTestCase(@RequestParam("exam_id")int exam_id,	@RequestParam("problem_name")String problem_name,
			HttpServletRequest request, HttpSession session) {
		//参数 学生学号,时间点,考试号,考题名
		//String code = service.getStuCode(stu_id, time, exam_id, problem_name);
		String testCase = "success2";
		return testCase;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCodeStas", method=RequestMethod.POST)
	public String getCodeStas(@RequestParam("stu_id")int stu_id,@RequestParam("exam_id")int exam_id,	@RequestParam("problem_name")String problem_name,
			@RequestParam("file_name")String file_name,HttpServletRequest request, HttpSession session) {
		//参数 学生学号,考试号,考题名,文件名
		//String code = service.getStuCode(stu_id, time, exam_id, problem_name);
		JSONArray codeJson = service.getCodeRecord(stu_id, problem_name, exam_id,file_name);//代码统计
		
		JSONObject stasJson = null;
		try {
			stasJson = reverse(codeJson);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println(stu_id);
		System.out.println(problem_name);
		System.out.println(exam_id);
		System.out.println(file_name);
		System.out.println("=====================================================");
		System.out.println(stasJson.toString());
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
