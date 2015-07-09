package cn.com.xl.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 实现文件压缩
 * </p>
 * 
 * @author tangw 2010-12-27
 * 
 */
public class ZipManager {
	private static ZipManager instance;
	private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

	/**
	 * get ZipManager
	 * 
	 * @return ZipManager
	 */
	public synchronized static ZipManager getInstance() {
		if (instance == null) {
			instance = new ZipManager();
		}
		return instance;
	}

	/**
	 * <p>
	 * 批量或单个压缩文件
	 * </p>
	 * 
	 * @author tangw 2010-12-27
	 * @param resFileList
	 *            源文件列表
	 * @param zipFile
	 *            压缩文件
	 * @throws IOException
	 */
	public static void batchZipFiles(Collection<File> resFileList, File zipFile)
			throws IOException {
		// 创建压缩文件
		ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipFile), BUFF_SIZE));
		for (File resFile : resFileList) {
			byte buffer[] = new byte[BUFF_SIZE];
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(resFile), BUFF_SIZE);
			// 新增zip输出流
			zipout.putNextEntry(new ZipEntry(resFile.getName()));
			int realLength;
			// 写入
			while ((realLength = in.read(buffer)) != -1) {
				zipout.write(buffer, 0, realLength);
			}
			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
		zipout.close();
	}

	/**
	 * <p>
	 * 测试方法
	 * </p>
	 * 
	 * @author tangw 2010-12-27
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<File> resFileList = new ArrayList<File>();
		resFileList.add(new File("E://im//deps_last.xml"));
		// resFileList.add(new File("E://im//user_last.xml"));
		// File zipFile = new File("e://im//depuser.zip");
		File zipFile = new File("e://im//deps_last.zip");
		try {
			batchZipFiles(resFileList, zipFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}