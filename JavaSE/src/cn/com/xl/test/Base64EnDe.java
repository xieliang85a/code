package cn.com.xl.test;

import java.io.IOException;

public class Base64EnDe {

	/**
	 * 编码
	 * 
	 * @param filecontent
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param filecontent
	 * @return string
	 */
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Base64EnDe te = new Base64EnDe();
		String aa = "更多更多";
		aa = te.encode(aa.getBytes());
		System.out.println("----aa:" + aa);
		String str = aa;
		String str2 = new String(te.decode(str));
		System.out.println("-----str2:" + str2);
	}
}
