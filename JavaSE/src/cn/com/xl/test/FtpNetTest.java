package cn.com.xl.test;

/**
 * <p>
 * 使用apache commons-net-1.4.1 实现ftp上传功能
 * </p>
 * 
 * @author tangw 2010-12-26
 * 
 */
public class FtpNetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FtpConManager.getInstance().login("ftp服务地址", "登陆名", "密码");
		// boolean flag =
		// FtpConManager.getInstance().uploadFile("d://use_1.zip", "3.zip");
		boolean flag = FtpConManager.getInstance().removeFile("/ImData/3.zip");
		System.out.println("flag:" + flag);
		// FtpConManager.getInstance().closeCon();

	}

}