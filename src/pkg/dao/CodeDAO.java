package pkg.dao;

import java.util.List;

import pkg.entity.Code;

public interface CodeDAO {

	public int addCode(int student, String subject, int second, int line_count
			, int note_count, int method_count,int var_count, int max_cyclomatic);
	
	public int addCode(Code attendence);

	public List<Code> queryAttendences(int stu_id, String proName);

}
