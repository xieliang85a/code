package cn.com.xl.other;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.zip.GZIPOutputStream;

public class GZIPDemo {
	public static void main(String[] args) throws Exception{
		String data = "CCCCCCCCCCCCCCCCCCCCCCCCCCCC";
		System.out.println("原始大小" + data.getBytes().length);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		gos.write(data.getBytes());
		byte [] bytes = bos.toByteArray();
		gos.close();
		System.out.println("压缩后大小" + bytes.length);
			
	}
}
