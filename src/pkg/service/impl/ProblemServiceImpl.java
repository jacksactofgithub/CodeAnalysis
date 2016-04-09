package pkg.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import pkg.service.ProblemService;
import util.HttpHandler;
import util.URLNameFormat;

@Service
public class ProblemServiceImpl implements ProblemService{

	private HttpHandler http = new HttpHandler();
	
	@Override
	public JSONArray getExamProblems(int stu_id, int exam_id) {
		// TODO Auto-generated method stub
		Map<String , String> params = new HashMap<String , String>();
		try {
			String problems = http.postHttpInvocation(URLNameFormat.GET_STUDENT_EXAM_PROBLEMS, params);
			JSONArray array = new JSONArray(problems);
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		}
		
	}

}
