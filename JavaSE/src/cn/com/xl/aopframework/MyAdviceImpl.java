package cn.com.xl.aopframework;

import java.lang.reflect.Method;
/**
 * 类似于spring中的代理通知
 * @author admin
 *
 */
public class MyAdviceImpl implements MyAdvice {

	
	@Override
	public void before(Method method) {
		System.out.println("调用"+method.getName()+"方法,代理通知开始");

	}

	@Override
	public void after(Method method) {
		System.out.println("调用"+method.getName()+"方法,代理通知结束");
	}

}
