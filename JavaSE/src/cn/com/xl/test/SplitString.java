package cn.com.xl.test;

public class SplitString {
	String SplitStr;
	int SplitByte;

	public SplitString(String str, int bytes) {
		SplitStr = str;
		SplitByte = bytes;
		System.out.println("The String is:′" + SplitStr + "′;SplitBytes="
				+ SplitByte);
	}

	public static void main(String[] args) {
		SplitString ss = new SplitString("test中dd文fasjaslkjdfalsjf师的看见发lkjd", 6);
		ss.split();
	}

	public void split() {
		for (int i = 0, len = SplitStr.length(); i < len; i = i + SplitByte) {
			if (i + SplitByte < len) {
				System.out.println(SplitStr.substring(i, i + SplitByte));
			} else {
				System.out.println(SplitStr.substring(i, len));
			}
		}
	}

}