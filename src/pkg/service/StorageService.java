package pkg.service;

public interface StorageService {

	public void startStore(int examID , int tea_id);
	
	public void storeOne(int examID , int classMemberId , int stuID);
	
}
