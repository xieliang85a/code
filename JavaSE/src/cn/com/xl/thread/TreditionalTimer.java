package cn.com.xl.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TreditionalTimer {
public static void main(String[] args) {
	Timer timer = new Timer();
	
//	timer.schedule(new MyTimer()/*new TimerTask() {
//		
//		
//		public void run() {
////			try {
////				Thread.sleep(500);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			System.out.println("bombing");
//			
//		}
//	}, 10000*/, 4000);
//	ExecutorService es = Executors.newFixedThreadPool(3);
//	es.execute(new Runnable() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			
//		}
//	});
	
	//两秒后bombing一次结束
	/*Executors.newScheduledThreadPool(3).schedule(new Runnable(){
		@Override
		public void run() {
			System.out.println("bombing");
			
		}
	}, 2, TimeUnit.SECONDS);*/

	//伊始2秒bombing,而后每隔5秒bombing一次
	Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable(){
		@Override
		public void run() {
			System.out.println("bombing");
			
		}
	}, 2, 5, TimeUnit.SECONDS);
}
	
}
class MyTimer extends TimerTask{
	public void run(){
		System.out.println("bombing");
		new Timer().schedule(new MyTimer(), 2000);
	}
}
