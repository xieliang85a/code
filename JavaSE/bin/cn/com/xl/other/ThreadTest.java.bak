package cn.com.xl.other;
 public class ThreadTest implements Runnable {
	 public void run() {
		    for (int k = 0; k < 5; k++) {
		    	
		      System.out.println("Thread:" + Thread.currentThread().getName() + "  " + k);
		    }
		  }

		  public static void main(String[] args) throws Exception {
		    Runnable r = new ThreadTest();
		    Thread t1 = new Thread(r);
		    Thread t2 = new Thread(r);
		    t1.start();
		    //t1.join();
		    //t2.start();
		    
		   
		  }
}