package cn.com.xl.test;

import java.lang.reflect.Method;

public class ReflectMain {

	/**
	 * 通过此mian方法执行其它类的main方法
	 * 给此main方法添加参数(要执行的类的字节码的全包名)
	 */
	public static void main(String[] args) {
		String startClassName = args[0];
		try {
			Class c = Class.forName(startClassName);
			Method m = c.getMethod("main", String[].class);
			m.invoke(null, new Object[]{new String[]{"a","b"}});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

class ExecuteTest{
	public static void main(String [] args) {
		System.out.println("我是通过反射调用执行的main方法!!参数:"+args[0]);
	}
}
