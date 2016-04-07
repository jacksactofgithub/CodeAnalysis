package pkg.service;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;

import lmooc.modulize.bean.RunStamp;

public interface RunService {

	public int saveRunStamp(Iterator<RunStamp> stamps , int stuID);
	
	public JSONArray getRuns(int stuID , String proName);
	
	/**
	 * 找到某一到题的最小公共测试用例集
	 * 返回测试用例的名称集合
	 * @param proName
	 * @return
	 */
	public List<String> findCommonTestCases(String proName);
}
