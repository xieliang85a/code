
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class FileConcurrent {
	public static void main(String[] args) throws Exception{
		final File f = new File("C:\\Users\\xieliang\\Desktop\\r.txt");
			new Thread(){
				public void run(){
					RandomAccessFile raf = null;
					long pos = 0l;//最新位置维护到库（文件）中
					int lineNum = 0;//最新行数维护到库（文件）中
					//////pos = 200;
					//lineNum = 8;
					int errorLineNum = -1;
					boolean isNullLine = false;
					try{
						raf = new RandomAccessFile(f, "r");
						raf.seek(pos);//设置从哪开始读取
					}catch(Exception ex){
						ex.printStackTrace();
					}
					if(raf == null)return;
					while(true){
						try {
							String str = null;
							while( (str = raf.readLine())!=null){
								str = new String(str.getBytes("ISO8859-1"),"UTF-8");
								String tmpStr = str.replaceAll("^\\s+|\\s+$", "");
								if(isNullLine = tmpStr.equals("")){
//									isNullLine = true;
									raf.seek(pos);
									break;
								}
								//isNullLine = false;
								lineNum++;
								System.out.println(str);
								pos = raf.getFilePointer();
							}
							if(null == str){
								continue;
							}
							/*
							 * 如果有空行就停止一会，再运行
							 */
							if(isNullLine){
								if(errorLineNum > 0 && errorLineNum == (lineNum + 1));
								else{
									
									
									//文件末尾空行
									if(raf.length() <= raf.getFilePointer() + str.length()+"\r\n".length()){//UTF-8
										//errorLineNum = 
									}else{
										errorLineNum = lineNum + 1;
										Thread.currentThread().sleep(1*1000);
										raf.seek(pos);
										System.out.println("现在读取到第" + (lineNum + 1) +"行有空串，请删除。");
										//System.out.println("第" + errorLineNum + "行有空串，请删除。");//log
										//System.out.println("现在读取到第"+pos + "偏移量");
										//System.out.println(raf.getFilePointer());
									}
									//raf.seek(pos);
								}
							}
						} catch (Exception e){
							e.printStackTrace();
						}finally{
						}
					}
					
				}
			}.start();
	}
	public static void main2(String[] args) throws Exception{
		//System.out.println(Math.log(32)/Math.log(2));
		/*File fff = new File("C:\\Users\\xieliang\\Desktop\\ddddddddd");
		
		
		FileOutputStream fos = new FileOutputStream(fff,true);
		fos.write("haha".getBytes());
		fos.write("你好".getBytes("utf-8"));
		fos.close();*/
		
		
		
		final String filepath = "C:\\Users\\xieliang\\Desktop\\r.txt";
		final File f = new File(filepath);
		if(!f.exists()){
			System.out.println("CCCCCCCCCCCCCC");
			f.createNewFile();
		}
		for(int i = 0; i < 5; i++){
			new Thread(){
				long stime = System.currentTimeMillis();
				public void run(){
					while(true){
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(f,true);
							fos.write((/*Thread.currentThread().getName() +*/ new Date().toLocaleString() + "\r\n").getBytes());
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}catch(IOException e){
							e.printStackTrace();
						}finally{
							if(fos!=null){
								try {
									fos.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						try{
							Thread.sleep(1000);
						}catch(InterruptedException ie){
							ie.printStackTrace();
						}
						
						if(System.currentTimeMillis() - stime >20000)
							break;
					}
					System.out.println("写结束了");
				}
			}.start();
			
		}
		
		final File f2 = new File("C:\\Users\\xieliang\\Desktop\\write.txt");
		if(!(f2.exists())){
			System.out.println("###create#######");
			f2.createNewFile();
		}
		new Thread(){
			int j = 0;
			long stime = System.currentTimeMillis();
			public void run(){
				int pos = 0;
				FileOutputStream fos = null;
				RandomAccessFile raf = null;
				try{
					fos = new FileOutputStream(f2,true);
					raf = new RandomAccessFile(f, "r");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				while(true){
					FileInputStream fis = null;
					try {
						if(pos>0)
							raf.seek(pos);
						//fis = new FileInputStream(f);
						//BufferedReader br = new BufferedReader(new InputStreamReader(fis));
						String str = null;
						int len = 0;
						byte[] bytes = new byte[1];
						while((len = raf.read(bytes,0,bytes.length))!= -1){
							fos.write(bytes,0, len);
							fos.flush();
							pos += len;
						}
						
						/*while((str = raf.readLine()br.readLine()) != null){
								System.out.println(++j + Thread.currentThread().getName() + "::" + str);
							}*/
					} catch (Exception e){
						e.printStackTrace();
					}finally{
					}
					if(System.currentTimeMillis() - stime >50000)
						break;
				}
				System.out.println("读结束了");
				
			}
		}.start();
	}
}
