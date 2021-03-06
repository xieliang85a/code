package cn.com.xl.soul;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class SemaphoreTest {

	public static void main(String[] args) {
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		final Semaphore s = new Semaphore(1);//每次只能一个人走,走完接着第二个人走
		
		for(int i=0;i<10;i++){
			new Thread(
					new Runnable(){
						public void run(){
							try {
								s.acquire();
								String input = queue.take();
								String output = TestDo.doSomething(input);
								System.out.println(Thread.currentThread().getName()+"\t"+output);
								s.release();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
			).start();
		}
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000) );
		for(int i=1;i<=10;i++){
			String input = ""+i;			
//			String output = TestDo.doSomething(input);
//			System.out.println(Thread.currentThread().getName()+output);
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class TestDo{
	public static String doSomething(String input){
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		input = input + ":" + (System.currentTimeMillis()/1000);
		return input;
	}
}
