package pkg.service;


import org.json.JSONArray;
import org.json.JSONObject;

public interface ExamService {

	public JSONArray getStudentExams(int stuId);
	
	/**
	 * @param exam_id
	 * @return
	 */
	public JSONObject getExamInfo(int exam_id);
	
	/**
	 * @param teaId
	 * @return
	 */
	public JSONArray getTeacherFinishedExams(int teaId);
	
	/**
	 * @param exam_id
	 * @return
	 */
	public JSONArray getExamPapers(int exam_id);
	
	public int getClassMemberId(int stuId , int examId);
	
}
