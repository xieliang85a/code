package cn.com.xl.test;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TmpTest {
	public static void main(String[] args) {
		File file = new File("d:\\abc/aa");
		//file.delete();
//		file.mkdirs();
//		System.out.println(file.getParentFile().exists());
		Properties p = System.getProperties();
		Set<Map.Entry<Object, Object>> entry= p.entrySet();
		for(Map.Entry<Object, Object> me : entry){
			System.out.println(me.getKey() + ":" + me.getValue());
		}
	}
}
