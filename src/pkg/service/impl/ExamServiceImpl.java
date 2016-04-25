package pkg.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import pkg.service.ExamService;
import util.HttpHandler;
import util.URLNameFormat;

@Service
public class ExamServiceImpl implements ExamService{

	private HttpHandler http = new HttpHandler();
	
	@Override
	public JSONObject getExamInfo(int exam_id) {
		// TODO Auto-generated method stub
		Map<String , String> params = new HashMap<String , String>();
		params.put(URLNameFormat.EXAM_ID , exam_id+"");
		
		try {
			String info = http.postHttpInvocation(URLNameFormat.GET_EXAM_INFO, params);
			JSONObject json = new JSONObject(info);
			
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONObject();
		}
		
	}

	@Override
	public JSONArray getTeacherFinishedExams(int teaId) {
		// TODO Auto-generated method stub
		Map<String , String> params = new HashMap<String , String>();
		params.put(URLNameFormat.TEACHER_ID , teaId+"");
		
		try {
			String exams = http.postHttpInvocation(URLNameFormat.GET_TEACHER_FINISHED_EXAMS, params);
			JSONArray array = new JSONArray(exams);
			
			return array;
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONArray();
		}
		
	}

	@Override
	public JSONArray getExamPapers(int exam_id) {
		// TODO Auto-generated method stub
		Map<String , String> params = new HashMap<String , String>();
		params.put(URLNameFormat.EXAM_ID , exam_id+"");
		
		try {
			String papers = http.postHttpInvocation(URLNameFormat.GET_EXAM_RESULT, params);
			JSONArray array = new JSONArray(papers);
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		}
		
	}

	@Override
	public JSONArray getStudentExams(int stuId) {
		// TODO Auto-generated method stub
		
		Map<String , String> params = new HashMap<String, String>();
		params.put(URLNameFormat.STUDENT_ID, stuId+"");
		
		try {
			String exams = http.postHttpInvocation(URLNameFormat.GET_STUDENT_EXAMS, params);
			JSONArray array = new JSONArray(exams);
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		}
	}

	@Override
	public int getClassMemberId(int stuId, int examId) {
		// TODO Auto-generated method stub
		
		Map<String , String> params = new HashMap<String, String>();
		params.put(URLNameFormat.STUDENT_ID, stuId+"");
		params.put(URLNameFormat.EXAM_ID, examId+"");
		
		String id;
		try {
			id = http.postHttpInvocation(URLNameFormat.GET_CLASS_MEMBER_ID , params);
			
			int classMemberId = Integer.parseInt(id);
			return classMemberId;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
