package cn.com.xl.aopframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
	Properties p = new Properties();
	public BeanFactory(InputStream is){
		try {
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据配置文件获取实例
	public Object getBean(String className){
		Object obj = null;
		try {
			Class clazz = Class.forName(p.getProperty(className));//获取代理工厂
			obj = clazz.newInstance();
			if(obj instanceof ProxyFactoryBean){
				MyAdvice myAdvice = (MyAdvice)Class.forName(p.getProperty("advice")).newInstance();
				Object target = Class.forName(p.getProperty("target")).newInstance();
				System.out.println("用于测试输出(代理的实现类)"+p.getProperty("target"));//为了方便测试用
				ProxyFactoryBean proxy = (ProxyFactoryBean)obj;
				proxy.setAdvice(myAdvice);
				proxy.setTarget(target);
				return proxy.getProxy();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
