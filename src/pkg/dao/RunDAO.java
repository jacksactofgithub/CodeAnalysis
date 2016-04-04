package pkg.dao;

import java.util.List;

import pkg.entity.Run;

public interface RunDAO {

	public int addRun(int subject_id , int student_id);
	
	public List<Run> queryRuns(int student_id , int subject_id);
	
}
