package cn.com.xl.encrypt;

public class Encrypt {
	public static void main(String[] args) {
		String str = "中华人民123好了";
		byte [] b = str.getBytes();
		System.out.println(new String(b));
		for(int i = 0 , len = b.length; i < len; i++){
			b[i]^=18;
		}
		System.out.println(new String(b));
		str = new String(b);
		b = str.getBytes();
		for(int i = 0 , len = b.length; i < len; i++){
			b[i]^=18;
		}
		System.out.println(new String(b));
	}
}
