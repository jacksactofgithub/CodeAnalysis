package pkg.service;


import org.json.JSONArray;
import org.json.JSONObject;

public interface ExamService {

	public JSONArray getStudentExams(int stuId);
	
	/**
	 * 获取某一场考试的具体信息
	 * @param exam_id
	 * @return
	 */
	public JSONObject getExamInfo(int exam_id);
	
	/**
	 * 教师查看自己开设的已结束的考试
	 * @param teaId
	 * @return
	 */
	public JSONArray getTeacherFinishedExams(int teaId);
	
	/**
	 * 查看参与一次考试的所有的学生列表，以及每个学生的考试情况
	 * 考试情况包括pro_name，pro_id，pro_score
	 * @param exam_id
	 * @return
	 */
	public JSONArray getExamPapers(int exam_id);
	
	public int getClassMemberId(int stuId , int examId);
	
}
