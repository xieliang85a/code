package cn.com.xl.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriterLockTest {
	static String data;
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void getData() {
		try {
			rwl.readLock().lock();
			try{Thread.sleep(new Random().nextInt(5000));}catch(Exception e){}
			System.out.println(Thread.currentThread().getName() + "A" + data);
			if(data == null || data == "") {
				rwl.readLock().unlock();
				System.out.println(Thread.currentThread().getName() + "B"
						+ data);
				try{
					rwl.writeLock().lock();
					if(data==null){
					
						data = new Random().nextInt(1000)+"有数据了";
						
						System.out.println(Thread.currentThread().getName() + "C"
								+ data);
						
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
			System.out.println(Thread.currentThread().getName() + "D" + data);
		}

	}

	public static void main(String[] args) {
		final ReadWriterLockTest rwlt = new ReadWriterLockTest();
		final ReadWriterLockTest rwlt2 = new ReadWriterLockTest();
		for(int i=0;i<5;i++){
			new Thread(
					new Runnable(){
						public void run(){
							
							rwlt.getData();
							
						}
					}
			).start();
		}
		for(int i=0;i<5;i++){
			new Thread(
					new Runnable(){
						public void run(){
							
							rwlt2.getData();
							
						}
					}
			).start();
		}
		/*ExecutorService pool = Executors.newFixedThreadPool(5);
		for(int i = 0;i<5;i++){
			
			pool.execute(new Runnable(){
				public void run(){
					
					rwlt.getData();
					
				}
			} );
		}*/
	}
}
