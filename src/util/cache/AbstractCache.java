package util.cache;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractCache<K,V> implements Cache<K,V>{
	
	protected Map<K , CacheObject<K , V>> cacheMap;
	
	private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
	private final Lock readLock = cacheLock.readLock();
	private final Lock writeLock = cacheLock.writeLock();
	
	protected int cacheSize;
	protected long defaultExpire;
	protected boolean existCustomExpire;
	
	class CacheObject<K2 , V2>{
		
		final K2 key;
		final V2 value;
		long lastAccess;
		long accessCount;
		long ttl;		//time to live
		
		CacheObject(K2 key , V2 value , long ttl){
			this.key = key;
			this.value = value;
			this.ttl = ttl;
			this.lastAccess = System.currentTimeMillis();
		}
		
		boolean isExpired(){
			if(ttl == 0){
				return false;
			}else{
				return (lastAccess + ttl) < System.currentTimeMillis();
			}
		}
		
		V2 getObject(){
			lastAccess = System.currentTimeMillis();
			accessCount++;
			return value;
		}
		
	}
	
	public AbstractCache(int cacheSize , long defaultExpire){
		this.cacheSize = cacheSize;
		this.defaultExpire = defaultExpire;
	}
	
	public int getCacheSize(){
		return cacheSize;
	}
	
	public long getDefaultExpire(){
		return defaultExpire;
	}
	
	protected boolean isNeedClearExpiredObject(){
		return (defaultExpire>0) || existCustomExpire;
	}
	
	public void put(K key , V value){
		put(key , value , defaultExpire);
	}
	
	public void put(K key , V value , long expire){
		writeLock.lock();
		
		try{
			CacheObject<K,V> co = new CacheObject<K, V>(key, value, expire);
			if(expire!=0){
				existCustomExpire = true;
			}
			if(isFull()){
				eliminate();
			}
			cacheMap.put(key , co);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
		
	}
	
	public boolean containsKey(K key){
		return cacheMap.containsKey(key);
	}
	
	public V get(K key){
		readLock.lock();
		
		try{
			CacheObject<K, V> co = cacheMap.get(key);
			if(co == null){
				return null;
			}
			if(co.isExpired()){
				cacheMap.remove(key);
				return null;
			}
			
			return co.getObject();
		}finally{
			readLock.unlock();
		}
		
	}
	
	public final int eliminate(){
		writeLock.lock();
		try{
			return eliminateCache();
		}finally{
			writeLock.unlock();
		}
		
	}
	
	public abstract int eliminateCache();

	public boolean isFull(){
		if(cacheSize == 0){		//0代表无cache大小
			return false;
		}
		
		return cacheMap.size() >= cacheSize;
	}
	
	public void remove(K key){
		writeLock.lock();
		try{
			cacheMap.remove(key);
		}finally{
			writeLock.unlock();
		}
	}
	
	public void clear(){
		writeLock.lock();
		try{
			cacheMap.clear();
		}finally{
			writeLock.unlock();
		}
	}
	
	public int size(){
		return cacheMap.size();
	}
	
	public boolean isEmpty(){
		return cacheMap.size()==0;
	}
	
}


