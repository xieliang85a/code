package cn.com.xl.socket;
import java.net.*;
import java.io.*;
class MyServer{
	public static void main(String[] args) throws Exception{
		//writeImg();
		writeHtml();
	}
	public static void writeImg()throws Exception{
		ServerSocket server = new ServerSocket(80);
		Socket socket = server.accept();
		OutputStream os = socket.getOutputStream();
		File f = new File("c:\\users\\admin\\desktop\\a.jpg");
		InputStream is = new FileInputStream(f);
		byte [] b = new byte[1024];
		int len = 0;
		while((len = is.read(b,0,b.length)) != -1){
			os.write(b, 0, len);
		}
		os.close();
		is.close();
		socket.close();
	}
	public static void writeHtml()throws Exception{
		ServerSocket server = new ServerSocket(80);
		Socket socket = server.accept();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("<font color='red' size='16'>您好</font>");
		out.close();
		socket.close();
	}
}