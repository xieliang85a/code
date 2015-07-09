package cn.com.xl.soul;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
  实现当key值相同是则退后一秒执行
 */

public class SynchronizedTest extends Thread {

	private Test_Do test_Do;
	private String value;
	private String key;
	public SynchronizedTest(String key, String key2, String value) {
			test_Do = Test_Do.getInstance();
			this.key = key+key2;//故意设置(key)
			this.value = value;
	}
	
	public static void main(String[] args) {
		/*
		 a和b的key值相等
		 */
		SynchronizedTest a = new SynchronizedTest("1", "", "1");
		SynchronizedTest b = new SynchronizedTest("1", "", "4");
		SynchronizedTest c = new SynchronizedTest("2", "", "2");
		SynchronizedTest d = new SynchronizedTest("3", "", "3");
		
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		a.start();
		b.start();
		c.start();
		d.start();
	}

	public void run() {
		test_Do.doSome(key, value);
	}
}

class Test_Do {
	private Test_Do() {
	}

	private static Test_Do _instance = new Test_Do();

	public static Test_Do getInstance() {
		return _instance;
	}

	
	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();//同步集合
	//private ArrayList keys = new ArrayList();//若用此集合则会出错(添加和迭带不能同时操作)
	public void doSome(String key, String value) {//key+key2,"1"+""
		Object o = key;
		if(!keys.contains(o)){
			keys.add(o);
		}else{
			for(Iterator iter = keys.iterator();iter.hasNext();){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object oo = iter.next();
				if(oo.equals(o)){
					o = oo;
				}
			}
			
		}
		
		/*
		 若直接用参数key做同步(互斥)则要求构造方法内的key+key2改为key即可.
		 */
		synchronized (o){
			try {
				Thread.sleep(1000);
				System.out.println(key + ":" + value + "\t"
						+ (System.currentTimeMillis() / 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
