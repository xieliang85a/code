package cn.com.xl.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PicClient {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1", 9999);
		InputStream is = new FileInputStream(new File("d:\\b.jpg"));
		OutputStream os = socket.getOutputStream();
		
		byte [] b = new byte[1024];
		int len = 0;
		while((len = is.read(b, 0, b.length)) != -1)
			os.write(b, 0, len);
		
		socket.shutdownOutput();
		InputStream in = socket.getInputStream();
		len = in.read(b, 0, b.length);
		System.out.println(new String(b,0,len));
		os.close();
		is.close();
		in.close();
		socket.close();
	}
}
