package cn.com.xl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TreditionalThread2 {
	//private static int data = 0;
	static Map<Thread,Integer> threadData= new HashMap<Thread,Integer>();
	
	public static void main(String[] args) {
		for (int i=0;i<2;i++)
			new Thread(
					new Runnable(){
						public void run(){
							int data = new Random().nextInt();
							System.out.println(Thread.currentThread().getName()+data);
							threadData.put(Thread.currentThread(), data);
							new A().get();
							new B().get();
						}
					}
			).start();
	}
	static class A{
		 
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A thread "+Thread.currentThread().getName()+"data"+data);
		}
	}
	static class B{
		 
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("B thread "+Thread.currentThread().getName()+"data"+data);
		}
	}
}
