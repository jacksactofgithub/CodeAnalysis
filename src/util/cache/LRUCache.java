package util.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K , V> extends AbstractCache<K, V>{

	public LRUCache(int cacheSize, long defaultExpire) {
		super(cacheSize, defaultExpire);
		// TODO Auto-generated constructor stub
		this.cacheMap = new LinkedHashMap<K , CacheObject<K, V>>(cacheSize+1 , 1f , true){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest){
				
				return LRUCache.this.removeEldestEntry(eldest);
			}
			
		};
	}
	
	private boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {
		 
        if (cacheSize == 0)
            return false;
 
        return size() > cacheSize;
    }

	/**
	 */
	@Override
	public int eliminateCache() {
		// TODO Auto-generated method stub
		if(!isNeedClearExpiredObject()){
			return 0;
		}
		
		Iterator<CacheObject<K , V>> iterator = cacheMap.values().iterator();
		
		int count = 0;
		
		while(iterator.hasNext()){
			CacheObject<K , V> cacheObject = iterator.next();
			
			if(cacheObject.isExpired()){
				iterator.remove();
				count++;
			}
			
		}
		
		return count;
	}

}
