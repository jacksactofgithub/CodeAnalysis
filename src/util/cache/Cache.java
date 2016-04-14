package util.cache;

public interface Cache<K , V> {

	public int size();
	
	/**
	 * 返回缓存时间
	 * @return
	 */
	public long getDefaultExpire();
	
	public void put(K key , V value);
	
	public void put(K key , V value , long expire);
	
	public V get(K key);
	
	public boolean isFull();
	
	public void clear();
	
	boolean isEmpty();
	
	public boolean containsKey(K key);
}
