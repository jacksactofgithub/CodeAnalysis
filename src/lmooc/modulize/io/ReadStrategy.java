package lmooc.modulize.io;

import java.util.Iterator;

public interface ReadStrategy {

	/**
	 * 根据path进行数据读取
	 * @param path
	 * @return
	 */
	public Iterator<String> read(String path);
	
}
