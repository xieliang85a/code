package cn.com.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.xml.internal.ws.util.StringUtils;

public class IntrospectorTest {
	static IntrospectorTest it = new IntrospectorTest();
	public static void main(String[] args) {
//		it.introspector1();
		
		//it.propertyDescriptor();
		List personLst = new ArrayList();
		Person p1 = new Person();
		p1.setAge(0);
		p1.setName("");
		personLst.add(p1);
		
		System.out.println(((Person)personLst.get(0)).getName());
		
		parseLst(personLst);
		System.out.println(((Person)personLst.get(0)).getName());
	}
	private void propertyDescriptor() {
		Class clazz = Person.class;
		Object person = null;
		try {
			person = clazz.newInstance();
		
			Field [] field = clazz.getDeclaredFields();
			int count = 1;
			PropertyDescriptor pd = null;
			for(Field f : field){
				pd = new PropertyDescriptor(f.getName(), Person.class);
				Method m = pd.getWriteMethod();
				int param_type=param_type(f);
				switch(param_type){
					case 0:
						m.invoke(person, (count++));
						break;
					case 1:
						m.invoke(person, m.getName()+"........" + (count++));
				}
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{ 
			System.out.println(((Person)person).getAge() + "\r\n" + ((Person)person).getName());
		}
	}
	private void introspector1(){
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
			PropertyDescriptor pd[] = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor :pd) {
				//System.out.println(propertyDescriptor.getWriteMethod());
				System.out.println(propertyDescriptor.getName());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int param_type(Field f){
		String [] ints = {"int","string"};
		Class clazz  = f.getType();
		return ints[0].equals(clazz.getName())?0:1;
	}
	
	public static void parseLst(List<Object> lst){
		if(lst == null || lst.isEmpty())
			return;
		Map<Object,Object> map = new HashMap();
		Object obj = null;
		Entry<Object, Object> entry = null;
		String value = null;
		String key = null;
		PropertyDescriptor pd = null;
		for(int i = 0,size = lst.size(); i < size; i++){
			obj = lst.get(i);
			Class clazz = obj.getClass();
			try {
				map = BeanUtils.describe(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			for(Iterator<Entry<Object, Object>> it = map.entrySet().iterator();it.hasNext();){
				entry = it.next();
				key = String.valueOf(entry.getKey());
				value = String.valueOf(entry.getValue());
				
				if(null == value || "".equals(value.trim()) || "null".equals(value)){
					try {
						pd = new PropertyDescriptor(key, clazz);
						pd.getWriteMethod().invoke(obj, new Object[]{"空"});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
			}
		}
	}
}
class Person{
	
	private int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
