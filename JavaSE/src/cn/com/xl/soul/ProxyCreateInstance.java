package cn.com.xl.soul;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class ProxyCreateInstance {
	public static void main(String[] args) throws Exception{
		/*Class clazz =Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		Constructor [] constructors = clazz.getConstructors();
		for(Constructor c : constructors){
			System.out.print(c.getName());
			if(c.getParameterTypes()!=null&&c.getParameterTypes().length!=0)
			for(Class cla :(c.getParameterTypes())){
				System.out.print("("+cla.getName());
			}
			System.out.println(")");
		}
		Collection collection1 = (Collection)clazz.getConstructor(InvocationHandler.class)
											.newInstance(new InvocationHandler(){
			ArrayList target = new ArrayList();
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				System.out.println("调用方法"+method.getName());
				Long beginTime = System.currentTimeMillis();
				Object retVal = method.invoke(target, args);
				Long endTime = System.currentTimeMillis();
				System.out.println("---------"+(endTime-beginTime)+"---------");
				return retVal;
			}
			
		});
		System.out.println(collection1.size());
		collection1.add("第一个数");
		collection1.add("第二个数");
		collection1.add("第三个数");
		System.out.println(collection1.size());
		for (Object object : collection2) {
			System.out.println(object);
		}*/
		
		Collection collection2 = (Collection)Proxy.newProxyInstance(Collection.class.getClassLoader(), new Class[]{Collection.class}, 
				new InvocationHandler(){
					Set target = new TreeSet();
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						System.out.println("调用方法"+method.getName());
						Long beginTime = System.currentTimeMillis();
						Object retVal = method.invoke(target, args);
						Long endTime = System.currentTimeMillis();
						System.out.println("---------"+(endTime-beginTime)+"---------");
						return retVal;
					}
			
			}
		);
		
		System.out.println(collection2.size());
		collection2.add("第一个数");
		collection2.add("第二个数");
		collection2.add("第三个数");
		System.out.println(collection2.size());
		for (Object object : collection2) {
			System.out.println(object);
		}
		
	}
}
