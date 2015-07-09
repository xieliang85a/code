package cn.com.xl.thread;

public class MulitThreadShareData {
	static int j = 10;
	
	
//	static MyShareData myShareData = new MyShareData();
	public static void main(String[] args) {
		final MyShareData myShareData = new MulitThreadShareData().new MyShareData();
		for(int i=0;i<2;i++){
			new Thread(
					new Runnable(){
						public void run(){
							myShareData.increment();
						}
					}
			).start();
			new Thread(
					new Runnable(){
						public void run(){
							myShareData.decrement();
						}
					}
			).start();
		}
		try {
			Thread.currentThread().join(10);
			System.out.println(j);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	class MyShareData{
		
		public synchronized void increment(){
			j++;
			System.out.println(Thread.currentThread().getName()+"++"+j);
		}
		
		public synchronized void decrement(){
			j--;
			System.out.println(Thread.currentThread().getName()+"--"+j);
		}
	}
}

