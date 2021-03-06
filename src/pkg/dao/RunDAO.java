package pkg.dao;

import java.util.List;

import pkg.entity.ProblemResult;
import pkg.entity.Run;

public interface RunDAO {

	public Run addRun(String proName , int student_id , int run_second , int examID , long timestamp);
	
	public List<Run> queryRuns(int student_id , String proName , int exam);
	
	/**
	 * @param proName
	 * @return
	 */
	public List<Integer> queryStudentID(String proName , int exam);
	
	public List<ProblemResult> queryAvgRun(String proName , int exam);
	
}
