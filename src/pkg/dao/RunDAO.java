package pkg.dao;

import java.util.List;

import pkg.entity.Run;

public interface RunDAO {

	public Run addRun(String proName , int student_id , int run_second , int examID);
	
	public List<Run> queryRuns(int student_id , String proName , int exam);
	
	/**
	 * 查询做了某一题的所有学生的id
	 * @param proName
	 * @return
	 */
	public List<Integer> queryStudentID(String proName , int exam);
	
}
