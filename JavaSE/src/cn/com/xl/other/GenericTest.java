package cn.com.xl.other;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeSet;

/*
 * 得到泛型实际参数(String)
 * ArrayList<String> array = new ArrayList<String>();
 * 由于无法通过array得到,但可以用方法得到
 */

public class GenericTest {
	public static void main(String [] args)throws Exception{
//		System.out
//				.println(System.class.getClassLoader());
		ClassLoader loader = GenericTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader= loader.getParent();
		}
		System.out.println(loader);
//		Method m = GenericTest.class.getMethod("applyGenericParam", TreeSet.class);
//		System.out.println(m.getParameterTypes()[0].getName());
//		System.out.println(m.getGenericReturnType());
//		System.out.println(m.getGenericParameterTypes()[0]);
//		Type [] types = m.getGenericParameterTypes();
//		ParameterizedType ptype = (ParameterizedType)types[0];
//		System.out.println(ptype.getActualTypeArguments()[0]);
//		System.out.println(ptype.getClass().getName());
//		System.out.println(ptype.getOwnerType());
//		System.out.println(ptype.getRawType());
		
//		Method m = GenericTest.class.getMethod("applyGenericParam", Map.class,double.class);
//		System.out.println(m.getParameterTypes()[0].getName());
//		System.out.println(m.getGenericReturnType());
//		System.out.println(m.getGenericParameterTypes()[0]);
//		Type [] types = m.getGenericParameterTypes();//得到泛型参数类型信息
//		ParameterizedType ptype = (ParameterizedType)types[0];
//		System.out.println(ptype.getActualTypeArguments()[0]+"..."+ptype.getActualTypeArguments()[1]);
////------------------------------------------------
//		Object obj = ptype.getActualTypeArguments()[0];		
//		String s = obj.toString();
//		System.out.println(s.substring(s.lastIndexOf(".") + 1));
////------------------------------------------------		
//		System.out.println(ptype.getClass().getName());
//		System.out.println(ptype.getOwnerType());
//		System.out.println(ptype.getRawType());
	}
	public static void applyGenericParam(TreeSet<Integer> ts){//得到Integer
		
	}
public static void applyGenericParam(Map<Integer,String> ts,double d){//得到Integer,String
		
	}
}
