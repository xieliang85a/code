package cn.com.xl.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpWriteJavaBeanDemo {
	private static void writeObj(DatagramSocket send, Demo demo) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bos);
			os.writeObject(demo);
			byte[] b = bos.toByteArray();
			try {
				DatagramPacket dp = new DatagramPacket(b, b.length,
						InetAddress.getByName("127.0.0.1"), 5555);
				send.send(dp);
				bos.close();
				os.close();
			} catch (UnknownHostException e) {

			}
		} catch (Exception e) {
			System.out.println("数据发送失败");
		}
	}

	private static void readObj(DatagramSocket receive) {
		byte[] b = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b, 0, b.length);
		try {
			receive.receive(dp);
			ByteArrayInputStream bis = new ByteArrayInputStream(dp.getData());
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object obj = ois.readObject();
			bis.close();
			ois.close();
			Demo demo = (Demo) obj;
			demo.say();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		final DatagramSocket send = new DatagramSocket(6666);// 指定本发送方端口号
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i < 5; i++) {
					Demo demo = new Demo("aaa" + i);
					writeObj(send, demo);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		// 接收
		final DatagramSocket receive = new DatagramSocket(5555);// 设置接收方端口号
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					readObj(receive);
				}
			}
		}).start();
	}
}

class Demo implements Serializable {
	Demo(){}
	Demo(String name){
		this.name = name;
	}
	private String name;
	void say() {
		System.out.println(this.name);
	}

}
