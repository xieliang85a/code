package cn.com.xl.encrypt;

public class Encrypt {
	public static void main(String[] args) throws Exception {
		String str = "中华人民123好了";
		byte [] b = str.getBytes("utf-8");
		System.out.println(new String(b,"utf-8"));
		StringBuilder sb = new StringBuilder();
		for(int i = 0 , len = b.length; i < len; i++){
			b[i]^=18;
			if ((b[i] & 0xFF) < 16)
				sb.append("0");
			sb.append(Long.toString(b[i] & 0xFF, 16));
		}
		
		System.out.println("___________________");
		str = sb.toString();
		int j = 0;
		System.out.println(str);
		char c1 = '0';
		char c2 = '0';
		for(int i = 0 , len = str.length(); i < len; i++){
			c1 = str.charAt(i++);
			c2 = str.charAt(i);
			byte l = (byte) Long.parseLong("" + c1+c2, 16);
			b[j++]=(byte) (l^18);
		}
		System.out.println(new String(b,"utf-8"));
	}
}
