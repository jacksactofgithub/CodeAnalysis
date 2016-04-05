package pkg.dao;

import java.util.List;

import pkg.entity.Run;

public interface RunDAO {

	public Run addRun(String proName , int student_id , int run_second);
	
	public List<Run> queryRuns(int student_id , String proName);
	
}
