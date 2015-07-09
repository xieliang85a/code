package cn.com.xl.test;

import java.lang.reflect.Type;

public class Test2{
	static{
		System.out.println("static");
	}
	String name;
	public Test2(String name){
		
		System.out.println(name);
		new Runnable(){
			public void run(){
				System.out.println(Test2.this.name);
			}
		};
	}
	public static void main(String[] args) throws Exception{
		//Test2 t;
		//System.out.println("aaa");
		//t = new Test2("nnn");
		/*AbsClass ac = new AbsClass();
		ac.setStr("abc");
		ac.say();*/
	}
}
final class AbsClass{
	private String str = "abc";
	public void say(){
		System.out.println(this.str);
	}
	public void setStr(String s){
		this.str = s;
	}
}
