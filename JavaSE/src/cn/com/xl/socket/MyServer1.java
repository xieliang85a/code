package cn.com.xl.socket;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer1 {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(9999);
		Socket socket = server.accept();
		OutputStream os = socket.getOutputStream();
		
		String text = "我是服务器传过来的文字";
		
		ByteArrayInputStream bis = new ByteArrayInputStream(text.getBytes());
		byte [] b = new byte[1024];
		int len = 0;
		while((len = bis.read(b,0,b.length))!=-1){
			os.write(b,0,len);
		}
		os.close();
		bis.close();
		socket.close();
		server.close();
	}
}
