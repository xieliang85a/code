package cn.com.xl.thread;

import java.util.concurrent.CompletionService;

public class TreditionThread {
	
	public static void main(String[] args) {
//		new Thread(new Runnable() {
//			public void run() {
//				System.out.println("Runnable"+Thread.currentThread().getName());
//			}
//		})/*{
//			public void run(){
//				System.out.println("Thread"+Thread.currentThread().getName());
//			}
//			
//		}*/.start();
		
//		CompletionService<V>
		
		 final Business business = new Business();
		 new Thread(
				 new Runnable(){
					 public void run(){
						 for(int i=1;i<=50;i++){
							 business.sub(i);
						 }
					 }
				 }
		 ).start();
		 

		 for(int i=1;i<=50;i++)
			 business.main(i);
	 	}
				 
	
	
	
}
class Business{
	private boolean sub = true;
	public synchronized void sub(int i){
		while(!sub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		for(int j=1;j<=10;j++){
			System.out.println("sub"+j+" loop"+i);
		}
		sub = false;
		this.notify();
	}
	public synchronized void main(int i){
		while(sub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=1;j<=100;j++){
			System.out.println("main"+j+" loop"+i);
		}
		sub = true;
		this.notify();
	}
	
}
