package pkg.service;

import org.json.JSONArray;

/**
 * @author Ray Liu
 *
 */
public interface ProblemService {

	/**
	 * @return
	 */
	public JSONArray getExamProblems(int stu_id , int exam_id);
	
}
