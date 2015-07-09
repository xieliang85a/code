package cn.com.xl.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Vector;

/**
 * 取出泛型的实际类型参数
 * @author admin
 *
 */
public class GenericTest {

	public static void main(String[] args) throws Exception {
		Method m = GenericTest.class.getMethod("applyVector", Vector.class);
		Type [] type = m.getGenericParameterTypes();
		ParameterizedType pt= (ParameterizedType)type[0];
		System.out.println(pt.getActualTypeArguments()[0]);
		System.out.println(((Class)pt.getActualTypeArguments()[0]).newInstance());
	}
	Vector<Date> v = new Vector<Date>();
	public void applyVector(Vector<Date> v){
		
	}
}
