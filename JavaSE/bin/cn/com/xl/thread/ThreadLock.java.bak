package cn.com.xl.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
	public static void main(String[] args) {
		final Business business = new Business();
		while (true) {
			new Thread(new Runnable() {
				public void run() {
					business.output("xieliang");
				}
			}).start();

			new Thread(new Runnable() {
				public void run() {
					business.output("lixiaoxia");
				}
			}).start();
		}

	}

	static class Business {
		Lock lock = new ReentrantLock();

		public void output(String name) {
			try {
				lock.lock();
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
		}
	}
}
