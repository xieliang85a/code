package cn.com.xl.socket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PicServer {
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(9999);
		/*InputStream is = s.getInputStream();
		
		String ip = s.getInetAddress().getHostAddress();
		OutputStream os = new FileOutputStream(new File("c:\\users\\admin\\desktop\\" + ip + ".jpg"));
		byte [] b = new byte[1024];
		int len = 0;
		while((len = is.read(b, 0, b.length)) != -1){
			os.write(b, 0, len);
		}
		os.close();
		os = s.getOutputStream();
		os.write("上传成功".getBytes());
		os.close();
		s.close();
		ss.close();*/
		while(true){
			Socket s = ss.accept();
			new Thread(new PicServer().new PicThread(s)).start();
		}
	}
	class PicThread implements Runnable{
		Socket s;
		PicThread(Socket s){
			this.s = s;
		}
		@Override
		public void run(){
			try{
				InputStream is = s.getInputStream();
				FileOutputStream fos = new FileOutputStream("c:\\users\\admin\\desktop\\" + s.getInetAddress().getHostAddress().replaceAll("\\.", "") + ".jpg");
				byte [] buff = new byte[1024];
				int len = 0;
				while((len = is.read(buff, 0, buff.length)) != -1){
					fos.write(buff, 0, len);
				}
				OutputStream os = s.getOutputStream();
				os.write((s.getInetAddress().getHostAddress() + "..上传成功!").getBytes());
				os.close();
				fos.close();
				is.close();
				s.close();
			}catch(Exception ex){
				System.err.print(s.getInetAddress().getHostAddress() + "上传失败。。");
			}
		}
	}
}
