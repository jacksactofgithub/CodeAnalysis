package pkg.util;


public class SystemContext {

	private static ThreadLocal<Integer> START_ROW=new ThreadLocal<Integer>();
	
	private static ThreadLocal<Integer> PAGE_SIZE=new ThreadLocal<Integer>();
	
	public static void setStartRow(int startRow){
		START_ROW.set(startRow);
	}
	public static void setPageSize(int pageSize){
		PAGE_SIZE.set(pageSize);
	}
	
	public static int getStartRow(){
		try {
			if(START_ROW.get()!=null){
				return START_ROW.get();
			}else{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int getPageSize(){
		try {
			if(PAGE_SIZE.get()!=null){
				return PAGE_SIZE.get();
			}else{
				return 10;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 10;
		}
	}
}
