package cn.com.xl.test;

import java.io.File;

/**
 * <p>
 * 递归删除目录和文件的程序
 * </p>
 * 
 * @author tw 2009-07-16
 * 
 */
public class DelFileUtils {

	public static void main(String[] args) {
		// 则试OK
		String rootDir = "E://delDir";
		String delDirName = "a.txt";// 要删除的文件夹
		DelFileUtils del = new DelFileUtils();
		System.out.println("-------------del ---start----");
		del.FindDirectory(rootDir, delDirName);
		System.out.println("-------------del ---end----");
	}

	/**
	 * 删除目录下指定类型文件或目录
	 * 
	 * @param root
	 *            是根目录的绝对路径
	 * @author tw 2009-07-16
	 * @param delName
	 *            要删的类型
	 */
	public static void FindDirectory(String root, String delName) {
		// root是根目录的绝对路径
		File directory = new File(root);
		File[] files = directory.listFiles();
		// 根目录为空
		if (files.length == 0) {
			System.out.println(root + "根目录为空！");
			return;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					System.out.println("--------name:" + files[i].getName());
					// 根目录的子文件夹就是要删除的文件夹
					if (files[i].getName().equals(delName)) {
						System.out.println(files[i].getAbsolutePath());
						DelDirectory(files[i]);
					}
					// 根目录的子文件夹不是要删除的文件夹
					else {
						FindDirectory(files[i].getAbsolutePath(), delName);
					}
				}
			}
		}
	}

	/**
	 * 删除目录以及目录下的所有文件
	 * 
	 * @author tw 2009-07-16
	 * @param directory
	 *            文件名
	 */
	public static void DelDirectory(File directory) {
		File[] children = directory.listFiles();
		System.out.println("---------------method del---------------------");
		if (children.length == 0) {
			directory.delete();
			System.out.println("删除目录" + directory.getName());
		} else {
			for (int i = 0; i < children.length; i++) {
				if (children[i].isFile()) {
					children[i].delete();
					System.out.println("删除文件" + children[i].getName());
				} else if (children[i].isDirectory()) {
					DelDirectory(children[i]);
					System.out.println("" + children[i].getAbsolutePath());
				}
			}
			directory.delete();
			System.out.println("删除目录" + directory.getName());
		}
	}
}
