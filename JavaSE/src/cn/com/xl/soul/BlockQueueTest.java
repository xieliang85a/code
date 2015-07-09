package cn.com.xl.soul;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {
	public static void main(String[] args) {
		final BlockingQueue queue = new ArrayBlockingQueue(16);
		for(int i=0;i<4;i++){ //锟斤拷锟斤拷锟侥革拷锟竭筹拷
			new Thread(
					new Runnable(){
						public void run(){ 
							while(true){
								try {
									String log = (String)queue.take();
									BlockQueueTest.parseLog(log);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
			).start();
		}
		
		System.out.println("begin:"+System.currentTimeMillis()/1000);
	
		for(int i=0;i<16;i++){		//一锟斤拷16锟斤拷锟斤拷息,要锟斤拷锟斤拷锟侥革拷锟竭筹拷,锟斤拷锟斤拷锟斤拷锟斤拷锟?
			final String log = i+1+"";
			{
				try {
					
					queue.put(log);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Test.parseLog(log);
			}
		}
		
		
		
	}
	
	public static void parseLog(String log){
		System.out.println(log+":"+System.currentTimeMillis()/1000);
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}