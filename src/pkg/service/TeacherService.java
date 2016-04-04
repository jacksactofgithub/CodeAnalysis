package pkg.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	
	public JSONArray getExams(String tea_account){
		try {
			return new JSONArray("[{'id':1,'exam_name':'测试考试1','teacher_name':'陈振宇','exam_begin_time':'2016-4-1 14:00:01',"
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_duration':120,'score':87},{'id':2,'exam_name':'测试考试2','teacher_name':'陈振宇','exam_begin_time':'2016-4-1 14:00:01',"
					+ "'exam_end_time':'2016-4-1 16:00:01','exam_duration':120,'score':89}]");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray getExamStudent(int exam_id,String tea_account){
		String s = "[{'student_name':'刘睿','stu_no':'121250087','uni_name':'南京大学','score':85,'problem1_name':'ArrayPartition','problem1_score':85,'problem1_id':1,"
				+ " 'problem2_name':'Sorting','problem2_id':2,'problem2_score':85, 'problem3_name':'Schedule','problem3_id':3,'problem3_score':100},{'student_name':'刘硕','stu_no':'121250088','uni_name':'南京大学','score':85,'problem1_name':'ArrayPartition','problem1_score':85,'problem1_id':1,"
				+ " 'problem2_name':'Sorting','problem2_id':2,'problem2_score':85, 'problem3_name':'Schedule','problem3_id':3,'problem3_score':100},]";
		try {
			return new JSONArray(s);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
