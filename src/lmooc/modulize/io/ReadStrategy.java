package lmooc.modulize.io;

import java.util.Iterator;

public interface ReadStrategy {

	/**
	 * @param path
	 * @return
	 */
	public Iterator<String> read(String path);
	
}
