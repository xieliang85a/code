package cn.com.xl.io;

import java.io.RandomAccessFile;




public class RandomAccessFileTest {

	public static void main(String[] args) throws Exception{
		RandomAccessFile raf = new RandomAccessFile("d://1.txt","rw");
		raf.write("谢亮".getBytes());
		raf.write(26);
		raf.write("靖轩".getBytes());
		raf.write(1);
		raf.write("lisisi".getBytes());
		raf.writeInt(12);
		raf.close();
		
		RandomAccessFile raf2 = new RandomAccessFile("d://1.txt","rw");
		byte [] b = new byte[4];
		int len = raf2.read(b);
		System.out.println(new String(b,0,len));
		int data = raf2.read();
		System.out.println(data);
		
		raf2.seek(10);
		byte [] b2 = new byte[6];
		len = raf2.read(b2);
		System.out.println(new String(b2,0,len));
		data = raf2.readInt();
		System.out.println(data);
		
		
		raf2.seek(5);
		len = raf2.read(b);
		System.out.println(new String(b,0,len));
		data = raf2.read();
		System.out.println(data);
		
		raf2.close();
		
		/*final RandomAccessFileTest r = new RandomAccessFileTest();
		new Thread(new Runnable(){
			public void run(){
				while(true){
				System.out.print("AA");
				r.sayHello3();}
			}
		}).start();
		new Thread(new Runnable(){
			public void run(){
				while(true){
				System.out.print("二");r.sayHello3();}
			}
		}).start();*/

	}
	/*synchronized void sayHello3()
	{
		System.out.println("abc");
	}*/	
}

