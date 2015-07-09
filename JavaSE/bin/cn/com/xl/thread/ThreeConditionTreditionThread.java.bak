package cn.com.xl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionTreditionThread {

	public static void main(String[] args) {

		final Business business = new Business();
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub1(i);
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub2(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++)
			business.main(i);
	}

	static class Business {
		private Lock lock = new ReentrantLock();
		private Condition cond1 = lock.newCondition();
		private Condition cond2 = lock.newCondition();
		private Condition cond3 = lock.newCondition();
		private int sub = 1;

		public void sub1(int i) {
			lock.lock();
			try {
				while (sub!=2) {
					try {
						cond2.await();
						// this.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub1" + j + " loop" + i);
				}
				sub = 3;
				cond3.signal();
			} finally {
				lock.unlock();
			}
			
			// this.notify();
		}
		public void sub2(int i) {
			lock.lock();
			try {
				while (sub!=3) {
					try {
						cond3.await();
						// this.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2" + j + " loop" + i);
				}
				sub = 1;
				cond1.signal();
			} finally {
				lock.unlock();
			}
			
			// this.notify();
		}
		public void main(int i) {
			lock.lock();
			try {
				while (sub!=1) {
					try {
						cond1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main" + j + " loop" + i);
				}
				sub = 2;
				cond2.signal();
			} finally {
				lock.unlock();
			}
			
			// this.notify();
		}

	}

}
