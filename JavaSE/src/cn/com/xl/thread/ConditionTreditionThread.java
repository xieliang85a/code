package cn.com.xl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTreditionThread {

	public static void main(String[] args) {
		final Business business = new Business();
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++)
			business.main(i);
	}

	static class Business {
		private Lock lock = new ReentrantLock();
		private Condition cond = lock.newCondition();
		private boolean sub = true;

		public void sub(int i) {
			lock.lock();
			try {
				while (!sub) {
					try {
						cond.await();
						// this.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub" + j + " loop" + i);
				}
				sub = false;
				cond.signal();
			} finally {
				lock.unlock();
			}
			
			// this.notify();
		}

		public void main(int i) {
			lock.lock();
			try {
				while (sub) {
					try {
						cond.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main" + j + " loop" + i);
				}
				sub = true;
				cond.signal();
			} finally {
				lock.unlock();
			}
			
			// this.notify();
		}

	}

}
