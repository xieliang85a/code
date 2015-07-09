package cn.com.xl.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(5);
		
		for(int i=0;i<5;i++){
			Runnable runnable = new Runnable() {
				
				
				public void run() {
					try{
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"已到达集合地1,已有"+(cb.getNumberWaiting()+1)+"等候");
						cb.await();
						
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"已到达集合地2,已有"+(cb.getNumberWaiting()+1)+"等候");
						cb.await();
					}catch(Exception e){
						e.printStackTrace();
					}
					System.out.println("run结束");
				}
			};
			service.execute(runnable);
			
		}
		System.out.println("程序结束");
	}
}
