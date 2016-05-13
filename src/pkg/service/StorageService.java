package pkg.service;

public interface StorageService {

	public void startStore(int examID);
	
	public void storeOne(int examID , int classMemberId , int stuID);
	
	/**
	 * @param examId
	 * @return
	 */
	public int getAnalyseState(int examId);
	
}
