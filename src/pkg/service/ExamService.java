package pkg.service;


import org.json.JSONArray;
import org.json.JSONObject;

public interface ExamService {

	/**
	 * 获取某一场考试的具体信息
	 * @param exam_id
	 * @return
	 */
	public JSONObject getExamInfo(int exam_id);
	
	/**
	 * 教师查看自己开设的已结束的考试
	 * @param tea_account
	 * @return
	 */
	public JSONArray getTeacherFinishedExams(String tea_account);
	
	/**
	 * 查看参与一次考试的所有的学生列表，以及每个学生的考试情况
	 * 考试情况包括pro_name，pro_id，pro_score
	 * @param exam_id
	 * @return
	 */
	public JSONArray getExamPapers(int exam_id);
	
	
	
}
