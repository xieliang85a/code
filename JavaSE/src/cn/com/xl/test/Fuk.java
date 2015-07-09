package cn.com.xl.test;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * <p>
 * jar文件操作
 * </p>
 * ??jar???л?????????
 * 
 * @author tw 2010-05-09
 * 
 */
public class Fuk {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			JarFile j = new JarFile(
					"E:/hotchpotch/lib/jxl.jar");
			Enumeration<JarEntry> entries = j.entries();
			while (entries.hasMoreElements()) {
				JarEntry je = entries.nextElement();
				System.out.println(je.getName());
			}
			System.out.println();
			Manifest manifest = j.getManifest();
			Attributes mainAttributes = manifest.getMainAttributes();
			for (Entry<Object, Object> e : mainAttributes.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
			}
			System.out.println();
			System.out.println();
			System.out.println();
			Map<String, Attributes> entries2 = manifest.getEntries();
			for (Entry<String, Attributes> e : entries2.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
			}

			System.out.println();
			URL url = new URL("jar:file:/E:/hotchpotch/lib/jxl.jar!/");
			JarURLConnection jarConnection = (JarURLConnection) url
					.openConnection();
			Manifest m = jarConnection.getManifest();
			Attributes aaa = m.getMainAttributes();
			for (Entry<Object, Object> e : aaa.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
			}

			System.out.println();
			System.out.println();
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}