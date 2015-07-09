package cn.com.xl.reflect;

import java.beans.BeanInfo;
import java.lang.reflect.Method;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.Paranamer;
/**
 * 获取方法中参数名称
 * @author xieliang
 *
 */
public class ParamName {
	public void func(String name,int age){}
	
	public static void main(String []args)throws Exception{
		BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(ParamName.class);
//		MethodDescriptor [] mds = beanInfo.getMethodDescriptors();
//		MethodDescriptor [] mds = beanInfo.getMethodDescriptors();
		Method [] mds = ParamName.class.getDeclaredMethods();
		Paranamer paranamer = new BytecodeReadingParanamer();
//		for(MethodDescriptor md : mds){
		for(Method m : mds){
//			Method m = md.getMethod();
			System.out.print(m.getName() + "(");
			String [] strs = paranamer.lookupParameterNames(m);
			for(String s : strs){
				System.out.print(s+",");
			}
			System.out.println(")");
			
		}
	}
}
