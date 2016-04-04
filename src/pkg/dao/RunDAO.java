package pkg.dao;

import java.util.List;

import pkg.entity.Run;

public interface RunDAO {

	public Run addRun(String proName , int student_id);
	
	public List<Run> queryRuns(int student_id , String proName);
	
}
