package pkg.service;

public interface StorageService {

	public void startStore(int examID);
	
	public void storeOne(int examID , int classMemberId , int stuID);
	
	/**
	 * 获取一次考试的分析状态，若分析过，返回1，正在进行分析返回2，尚未分析或不存在考试返回0
	 * @param examId
	 * @return
	 */
	public int getAnalyseState(int examId);
	
}
