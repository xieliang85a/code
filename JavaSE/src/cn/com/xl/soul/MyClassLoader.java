package cn.com.xl.soul;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
@SuppressWarnings(value="all")
public class MyClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		name = "d://"+name.substring(name.lastIndexOf(".")+1) +".class";
		try {
			FileInputStream fis = new FileInputStream(name);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			cypher(fis, os);
			fis.close();
			
			byte [] b = os.toByteArray();
			System.out.println("这是经过自定义类加载的");
			return defineClass("ClassLoaderTest",b, 0, b.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static void cypher(InputStream in, OutputStream out)throws Exception{
		int line = -1;
		while((line=in.read())!=-1){
			out.write(line)/*^ 0xff)*/;
			out.flush();
		}
		in.close();
		out.close();
	}
	
	public static void main(String[] args) throws Exception{
//		FileInputStream fis = new FileInputStream("D://MyWork//SE//JavaSE//bin//cn//com//xl//soul//ClassLoaderTest.class");
//		FileOutputStream fos = new FileOutputStream("d://ClassLoaderTest.class");
//		cypher(fis,fos);
//		fis.close();fos.close();
		//------------以上是进行加密-------------------
		
		
		//Class clazz = Class.forName("cn.com.xl.soul.ClassLoaderTest");
		//使用自己的类加载器
		String filePath = "cn.com.xl.soul.ClassLoaderTest";/*"d://ClassLoaderTest";*//*"bin//cn//com//xl//soul//ClassLoaderTest";*/
		Class clazz = new MyClassLoader().loadClass(filePath);
		System.out.println(clazz.getClassLoader().getClass().getName());
		Method m = clazz.getMethod("main", String[].class);

		m.invoke(null,new Object[]{new String[]{}});
	}
}


