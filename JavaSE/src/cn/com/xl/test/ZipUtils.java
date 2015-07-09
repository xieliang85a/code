package cn.com.xl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 文件解压缩类
 * 
 * @author tw
 * 
 */
public class ZipUtils {
	private static final String tt = "";

	/**
	 * 主方法
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String arg[]) throws Exception {
		ZipUtils.unZip_java("F:/TDDOWNLOAD/eclipse-jee-juno-win32.zip");// 测试OK

	}

	/**
	 * <p>
	 * 文件解缩方法2 java本身自带的解压包。 只会解压到当前目录下。
	 * </p>
	 * 
	 * @param zipFileName
	 *            要解压文件的全路径
	 */
	public static void unZip_java(String zipFileName) {
		File infile = new File(zipFileName);

		try {
			// 检查是否是ZIP文件
			ZipFile zip = new ZipFile(infile);
			zip.close();

			// 建立与目标文件的输入连接
			ZipInputStream in = new ZipInputStream(new FileInputStream(infile));
			ZipEntry file = in.getNextEntry();

			int i = infile.getAbsolutePath().lastIndexOf('.');
			String dirname = new String();
			if (i != -1)
				dirname = infile.getAbsolutePath().substring(0, i);
			else
				dirname = infile.getAbsolutePath();

			File newdir = new File(dirname);
			newdir.mkdir();

			byte[] c = new byte[1024];
			int len;
			int slen;

			while (file != null) {
				i = make8859toGB(file.getName()).replace("/", "//")
						.lastIndexOf("//");
				if (i != -1) {
					File dirs = new File(dirname
							+ File.separator
							+ make8859toGB(file.getName()).replace("/", "//")
									.substring(0, i));
					dirs.mkdirs();
					dirs = null;
				}

				System.out.print("Extract "
						+ make8859toGB(file.getName()).replace("/", "//")
						+ " ........ ");

				if (file.isDirectory()) {
					File dirs = new File(make8859toGB(file.getName()).replace(
							"/", "//"));
					dirs.mkdir();
					dirs = null;
				} else {
					FileOutputStream out = new FileOutputStream(dirname
							+ File.separator
							+ make8859toGB(file.getName()).replace("/", "//"));
					while ((slen = in.read(c, 0, c.length)) != -1)
						out.write(c, 0, slen);
					out.close();
				}
				System.out.print("O.K./n");
				file = in.getNextEntry();
			}
			in.close();
		} catch (ZipException zipe) {
			System.out.println(infile.getName() + "不是一个ZIP文件！");
		} catch (IOException ioe) {
			System.out.println("读取文件时错误！");
		} catch (Exception i) {
			System.out.println("over");
		}

	}

	public static String make8859toGB(String str) {
		try {
			String str8859 = new String(str.getBytes("8859-1"), "GB2312");
			return str8859;
		} catch (UnsupportedEncodingException ioe) {
			return str;
		}
	}

}