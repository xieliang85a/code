package cn.com.xl.test;

import java.lang.reflect.Array;

public class Test {
	private String a;

	// @Override
	// public int hashCode(){
	// //return 2;
	// }
	@Override
	public boolean equals(Object obj) {
		Test t = (Test) obj;
		if (t.a.equals(this.a))
			return true;
		return false;
	}

	static void changeStr(String s) {
		s = "abc";
	}

	public static void main(String[] args) throws Exception {
		String s  = new String("123");
		changeStr(s);
		System.out.println(s);
		// final BlockingQueue queue = new ArrayBlockingQueue(16);
		// for(int i=0;i<4;i++){ //开启四个线程
		// new Thread(
		// new Runnable(){
		// public void run(){
		// while(true){
		// try {
		// String log = (String)queue.take();
		// Test.parseLog(log);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		// }
		// ).start();
		// }
		//
		// System.out.println("begin:"+System.currentTimeMillis()/1000);
		//
		// for(int i=0;i<16;i++){ //一共16条信息,要求开启四个线程,四秒钟输出
		// final String log = i+1+"";
		// {
		// try {
		//
		// queue.put(log);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// //Test.parseLog(log);
		// }
		// }

	}

	private static void printArray(Object obj) {
		// TODO Auto-generated method stub
		Class clazz = obj.getClass();
		if (clazz.isArray()) {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				if (Array.get(obj, i).getClass().isArray()) {
					Object obj1 = Array.get(obj, i);
					int length = Array.getLength(obj1);
					for (int j = 0; j < length; j++) {
						System.out.println(Array.get(obj1, j));
					}
				} else {
					System.out.println(Array.get(obj, i));
				}

			}
		} else {
			System.out.println(obj);
		}

	}

	public static void parseLog(String log) {
		System.out.println(log + ":" + System.currentTimeMillis() / 1000);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
