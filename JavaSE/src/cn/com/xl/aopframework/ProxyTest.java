package cn.com.xl.aopframework;

import java.io.InputStream;
import java.util.Collection;
/**
 * 测试类
 * @author admin
 *
 */

public class ProxyTest {
	public static void main(String[] args) throws Exception{
		InputStream is = ProxyTest.class.getResourceAsStream("config.properties");
		Collection obj = (Collection) (new BeanFactory(is).getBean("bean"));//在类型转换时,切记不能用本类转换(要用接口转换)
		obj.add("abc");
		obj.add("ced");
		obj.add("aaa");
		for (Object object : obj) {
			System.out.println(object);
		}
		System.out.println("-----"+obj.getClass().getName()+"------");
	}
}
