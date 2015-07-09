package cn.com.xl.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger changer = new Exchanger();
		
		service.execute(new Runnable(){
			public void run(){
				try{
					String data1 = "xieliang";
					System.out.println("线程"+Thread.currentThread().getName()+"正在把数据"+data1+"交换出去!");
					Thread.sleep(5000);
					String data2 = (String)changer.exchange(data1);
					System.out.println("线程"+Thread.currentThread().getName()+"交换后的数据"+data2);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		service.execute(new Runnable(){
			public void run(){
				try{
					String data1 = "xiehai";
					System.out.println("线程"+Thread.currentThread().getName()+"正在把数据"+data1+"交换出去!");
					Thread.sleep(5000);
					String data2 = (String)changer.exchange(data1);
					System.out.println("线程"+Thread.currentThread().getName()+"交换后的数据"+data2);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		service.shutdown();
	}
}
