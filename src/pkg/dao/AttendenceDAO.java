package pkg.dao;

import java.util.List;

import pkg.entity.Attendence;

public interface AttendenceDAO {

	public int addAttendence(int student, int subject, int second, int line_count
			, int note_count, int method_count,int var_count, int max_cyclomatic);

	public List<Attendence> queryAttendences(int stu_id, int subject_id);

}
