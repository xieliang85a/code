package cn.com.xl.lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

class LockTest extends TestTemplate{     
	ReentrantLock lock = new ReentrantLock();

	public LockTest(String _id, int _round, int _threadNum, CyclicBarrier _cb) {
		super(_id, _round, _threadNum, _cb);
	}

	/**   
	 * synchronized关键字不在方法签名里面，所以不涉及重载问题   
	 */
	@Override
	long getValue() {
		try {
			lock.lock();
			return super.countValue;
		} finally {
			lock.unlock();
		}
	}

	@Override
	void sumValue() {
		try {
			lock.lock();
			super.countValue += preInit[index++ % round];
		} finally {
			lock.unlock();
		}
	}
} 