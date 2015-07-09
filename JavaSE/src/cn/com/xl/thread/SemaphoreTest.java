package cn.com.xl.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore semap = new Semaphore(3);//最多3个
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try{
						semap.acquire();
					}catch(Exception e){
						e.printStackTrace();
					}
					System.out.println("线程"+Thread.currentThread().getName()+"进入,已有"+(3-semap.availablePermits())+"并发");
					
					
					try{
						Thread.sleep(new Random().nextInt(10000));
					}catch(Exception e){
						e.printStackTrace();
					}
					System.out.println("线程"+Thread.currentThread().getName()+"准备离开");
					semap.release();
					System.out.println("线程"+Thread.currentThread().getName()+"已离开 ,已有"+(3-semap.availablePermits())+"并发");
				}
			};
			service.execute(runnable);
		}
		
	}
}
