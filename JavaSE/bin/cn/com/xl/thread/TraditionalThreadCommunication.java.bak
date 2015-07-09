package cn.com.xl.thread;

public class TraditionalThreadCommunication {
	public static void main(String[] args) {
		final Business b = new TraditionalThreadCommunication().new Business();
		new Thread(
			new Runnable(){
				public void run(){
					for(int j = 1; j<=5; j++){
						b.sub(j);
					}
				}
			}
		).start();
		
		for (int i = 1; i <=5; i++) {
			b.main(i);
		}
	}
	class Business{
		boolean flag = true;
		public synchronized void main(int i ){
			while(flag){
				try{this.wait();}catch(Exception e){}
			}
			for(int j = 1; j<=100; j++){
				try{Thread.sleep(10);}catch(Exception e){}
				System.out.println("main---loop"+i+":"+j);
			}
			flag = true;
			this.notify();
		}
		
public synchronized void sub(int i ){
			while(!flag){
				try{this.wait();}catch(Exception e){}
			}
			for(int j = 1; j<=10; j++){
				try{Thread.sleep(10);}catch(Exception e){}
				System.out.println("sub---loop"+i+":"+j);
			}
			flag = false;
			this.notify();
		}
	}
}
