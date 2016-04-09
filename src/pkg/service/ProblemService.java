package pkg.service;

import org.json.JSONArray;

/**
 * 处理与problem相关的业务
 * @author Ray Liu
 *
 */
public interface ProblemService {

	/**
	 * 查看一个学生参加的一次考试的所有problem
	 * @return
	 */
	public JSONArray getExamProblems(int stu_id , int exam_id);
	
}
