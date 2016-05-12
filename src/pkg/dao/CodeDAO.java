package pkg.dao;

import java.util.List;

import pkg.entity.Code;

public interface CodeDAO {

	public int addCode(int student,int examID , String subject, int second, int line_count
			, int note_count, int method_count,int var_count
			, int max_cyclomatic , long timestamp , String file_name);
	
	public int addCode(Code attendence);

	public List<Code> queryCode(int stu_id, String proName , int exam);
	
	public List<Code> queryAvg(int exam , String proName);
	
	public List<String> queryAllProNames(int examId);
	
	public List<String> queryStuFileNames(int examId , int stu_id , String proName);
	
	/**
	 * 获取code记录的总量
	 * @param examId
	 * @return
	 */
	public long getCodeCount(int examId);

}
