package pkg.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.service.ExamService;
import pkg.service.ProblemService;
import util.HttpHandler;
import util.URLNameFormat;

@Service
public class ProblemServiceImpl implements ProblemService{

	@Autowired
	private ExamService examService;
	
	private HttpHandler http = new HttpHandler();
	
	@Override
	public JSONArray getExamProblems(int stu_id, int exam_id) {
		// TODO Auto-generated method stub
		Map<String , String> params = new HashMap<String , String>();
		
		int classMemberId = examService.getClassMemberId(stu_id, exam_id);
		params.put(URLNameFormat.CLASS_MEMBER_ID, classMemberId+"");
		params.put(URLNameFormat.EXAM_ID, exam_id+"");
		
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
