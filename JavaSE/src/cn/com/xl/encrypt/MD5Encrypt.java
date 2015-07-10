package cn.com.xl.encrypt;
/**
 *	md5加密
 */
import java.security.MessageDigest;

class MD5Encrypt
{
	public static void main(String[] args) throws Exception{
		//encrypt(args);//加密
		encrypt(new String[]{"明文"});
	}
	/*
	 * 加密
	 * 加密串
	 * 加密前的文件
	 * 加密后的文件
	 */
	public static void encrypt(String [] args) throws Exception{
		String encryptStr = args[0];
		try{
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] bytes = md5.digest(encryptStr.getBytes("utf-8"));
			StringBuffer buf = new StringBuffer(bytes.length * 2);
			for(int i = 0; i < bytes.length; i++)
			{	
				if((bytes[i] & 255) < 16)
					buf.append("0");
				buf.append(Long.toString(bytes[i] & 255, 16));
			}

			String data = buf.toString();
			System.out.println(data);
		}catch(Exception e){
			
		}
	}
}