package cn.com.xl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final ReadWriteLockTest rwlt = new ReadWriteLockTest();
		final ReadWriteLockTest rwlt2 = new ReadWriteLockTest();
		new Thread(
				new Runnable(){
					public void run(){
						System.out.println(rwlt.getData("key"));
					}
				}
		).start();
		
		new Thread(
				new Runnable(){
					public void run(){
						System.out.println(rwlt2.getData("key"));
					}
				}
		).start();
		
	}
	Map<String, Object> map = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = map.get(key);
			
			if(value==null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(value==null){
						value = "aaaa";
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
