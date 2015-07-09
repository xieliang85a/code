package cn.com.xl.thread;
@SuppressWarnings("all")
public class ThreadLocalTest {
	private ThreadLocal/*<Thread>*/ data = new ThreadLocal();
	
	public static void main(String[] args) throws Exception{
		MyRunnable myRunnable = new ThreadLocalTest().new MyRunnable();
		for(int i = 0; i < 2; i++)
			new Thread(myRunnable).start();
	}
	
	class MyRunnable implements Runnable{
		@Override
		public void run() {
			while(true){
				if(data.get() == null){
					data.set(/*Thread.currentThread()*/Math.random());
				}
				else
					System.out.println(Thread.currentThread().getName() + ":data.get:"+data.get());
				try {Thread.sleep(1000);} catch (InterruptedException e) { }
			}
		}
	}
}