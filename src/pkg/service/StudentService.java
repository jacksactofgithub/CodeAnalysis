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
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_Duration':120,'score':87},{'id':2,'exam_name':'测试考试2','teacher_name':'陈振宇','exam_begin_time':'2016-4-1 14:00:01',"
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_Duration':120,'score':89}]");
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

	public JSONArray getProblems(int exam_id){
		try {
			return new JSONArray("[]");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
