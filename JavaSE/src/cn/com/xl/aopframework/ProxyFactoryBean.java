package cn.com.xl.aopframework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * .本类类似于springframework代理中的org.springframework.aop.framework.ProxyFactoryBean
 * .可以将target.getClass().getInterfaces()也定义个成员变量,也通过注入的方式
 * .这样就类似于spring中的代理(advice)
 * .代理接口,目标,事务都有了,也都是通过注入的方式
 */
public class ProxyFactoryBean {
	private Object target;
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public MyAdvice getAdvice() {
		return advice;
	}
	public void setAdvice(MyAdvice advice) {
		this.advice = advice;
	}
	private MyAdvice advice;//事务
	//动态创建代理
	public Object getProxy(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),//目标
									  target.getClass().getInterfaces(),//接口
					new InvocationHandler(){
						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							advice.before(method);
							Object retVal = method.invoke(target, args);
							advice.after(method);
							return retVal;
						}
			
		});
		
	}
	
}
