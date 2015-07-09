package cn.com.xl.queue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpDemo {
	public static void main(String[] args) throws Exception {
		final DatagramSocket client = new DatagramSocket(6666);
		new Thread(new Runnable(){
			public void run(){
				for (int i = 0; i < 4; i++) {
					try{
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(bos);
						oos.writeObject(new Demo(i));
						byte[] b = bos.toByteArray();
//						DatagramPacket datagramPacket = new DatagramPacket(/*b, 0, b.length*/"123".getBytes(),0,"123".getBytes().length,InetAddress./*getLocalHost()*/getByName("127.0.0.1"), 5555);
						DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length,InetAddress./*getLocalHost()*/getByName("127.0.0.1"), 5555);
						client.send(datagramPacket);
						bos.close();
						oos.close();
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			}
			
		}).start();
		final DatagramSocket service = new DatagramSocket(5555);
		new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						byte [] b_service = new byte[1024];
						DatagramPacket datagramPacket_service = new DatagramPacket(b_service,0,b_service.length);
						service.receive(datagramPacket_service);
						byte [] b_data = datagramPacket_service.getData();
//						System.out.println(new String(b_data,0,datagramPacket_service.getLength()));
						ByteArrayInputStream bais = new ByteArrayInputStream(b_data);
						ObjectInputStream ois = new ObjectInputStream(bais);
						Object obj = ois.readObject();
						if (obj instanceof Demo)
							((Demo)obj).say();
						else
							System.out.println("null");
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					
				}
			}
		}).start();

	}
}

class Demo implements Serializable {
	private String[] names = { "李","张","谢","陆" };

	private String name;

	Demo(int i) {
		this.name = names[i];
	}

	void say() {
		System.out.println(this.name);
	}
}
