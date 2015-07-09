package cn.com.xl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@SuppressWarnings("all")
public class CountDownLatchTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		final CountDownLatch c1 = new CountDownLatch(1);
		final CountDownLatch c2 = new CountDownLatch(3);
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable() {
			
				@Override
				public void run() {
					try{
						System.out.println("线程"+Thread.currentThread().getName()+"正准备接受命令");
						c1.await();
						System.out.println("线程"+Thread.currentThread().getName()+"已接受命令");
						Thread.sleep((long)Math.random()*10000);
						System.out.println("线程"+Thread.currentThread().getName()+"已回应处理");
						c2.countDown();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		
		try{
			Thread.sleep((long)Math.random()*10000);
			System.out.println("线程"+Thread.currentThread().getName()+"即将发布命令");
			c1.countDown();
			System.out.println("线程"+Thread.currentThread().getName()+"已发送命令,待会结果...");
			c2.await();
			System.out.println("结果已全部收到");
		}catch(Exception e){
			e.printStackTrace();
		}
		service.shutdown();
		
		
	}
}
