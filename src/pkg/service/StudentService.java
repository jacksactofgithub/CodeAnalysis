package pkg.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	public JSONArray getExams(String stu_account){
		try {
			return new JSONArray("[{'id':1,'exam_name':'测试考试1','teacher_name':'陈振宇','exam_begin_time':'2016-4-1 14:00:01',"
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_duration':120,'score':87},{'id':2,'exam_name':'测试考试2','teacher_name':'陈振宇','exam_begin_time':'2016-4-1 14:00:01',"
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_duration':120,'score':89}]");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getExamDetail(String stu_account,int id){
		JSONArray examArray = getExams(stu_account);
		int len = examArray.length();
		for (int i = 0; i < len; i++) {
			try {
				if (examArray.getJSONObject(i).getInt("id") == id) {
					return examArray.getJSONObject(i);
				}
			} catch (Exception e) {
				System.out.println("获取exam_name失败");
			}
		}
		return null;
	}

	public JSONArray getProblems(int exam_id,String stu_account){
		try {
			return new JSONArray("[{'problem_name':'题目一','problem_id':2, 'difficulty':1,'score':80,'ave_score':85,"
					+ "'description':'题目一',},{'problem_name':'题目二','problem_id':3, 'difficulty':1,'score':80,'ave_score':85,"
					+ "'description':'题目二',},{'problem_name':'题目三','problem_id':5, 'difficulty':1,'score':82,'ave_score':87,"
					+ "'description':'题目三',}]");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
