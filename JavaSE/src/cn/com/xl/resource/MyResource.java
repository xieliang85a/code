package cn.com.xl.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyResource {
	public static void main(String[] args) {
		
		/*Locale.setDefault(new Locale("en","US"));*/
		Locale locale = Locale.getDefault();
//		System.out.println(locale.getDisplayCountry()+"kk");
		ResourceBundle rb = ResourceBundle.getBundle("mess", locale);
		String sb = rb.getString("hello");
		System.out.println(MessageFormat.format(sb,/*"xieliang"*/"谢亮"));
	}
}
