package cn.com.xl.thread;

import java.util.Timer;
import java.util.TimerTask;
/*
 * 实现交替打印(各两秒)
 */
public class TimerTaskTest {

	public static void main(String[] args) {
		
		new TimerTaskTest().test();
		
		while(true){
			System.out.println(new java.util.Date().getSeconds());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
	public void test(){
		new Timer().schedule(new MyTask(), 2000);
	}
	private int count;//
	 class MyTask extends TimerTask {
		public void run() {
			count = (count+1)%2;//
			System.out.println("bombing....!");
			System.out.println("count"+count);
			new Timer().schedule(new MyTask(),2000+2000*count);
		}
	}
}
