package cn.com.xl.encrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
  
/** 
 * 消息摘要是一种算法：无论原始数据多长，消息摘要的结果都是固定长度的；是一种不可逆的算法 
 * 原始数据任意bit位的变化，都会导致消息摘要的结果有很大的不同，且根据结果推算出原始数据的概率极低。 
 * 消息摘要可以看作原始数据的指纹，指纹不同则原始数据不同。 
 * @author stone 
 * @date 2014-03-11 15:55:48 
 */  
public class MD5 {  
      
      
    public static void main(String[] args) throws Exception {  
        encodeByMAC("中国oP……&*（）…&802134…");  
          
        encodeByMd5("中国oP……&*（）…&802134…");  
          
        md5File();  
    }  
      
    /** 
     * 使用MAC 算法的 消息摘要 
     * @param data 
     * @throws Exception 
     */  
    public static void encodeByMAC(String data) throws Exception{  
//      KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");  
//      SecretKey key = keyGen.generateKey(); //这个每次生成的key不一样, 此处不能使用  
          
        PBEKeySpec keySpec = new PBEKeySpec("randomkey^(^&*^%$".toCharArray());  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
        SecretKey key = keyFactory.generateSecret(keySpec);  
          
        /* 
         *  此类提供“消息验证码”（Message Authentication Code，MAC）算法的功能。 
         *  MAC 基于秘密密钥提供一种方式来检查在不可靠介质上进行传输或存储的信息的完整性。 
         *  通常，消息验证码在共享秘密密钥的两个参与者之间使用，以验证这两者之间传输的信息。 
         *  基于加密哈希函数的 MAC 机制也叫作 HMAC。结合秘密共享密钥， 
         *  HMAC 可以用于任何加密哈希函数（如 MD5 或 SHA-1） 
         */  
        Mac mac = Mac.getInstance("HmacMD5");  
        mac.init(key);  
        byte[] dest = mac.doFinal(data.getBytes());  
        //System.out.println(dest.length);  
        System.out.println("MAC摘要：" + Arrays.toString(dest));  
    }  
      
    /** 
     * md5加密  使用消息摘要MessageDigest 处理 
     * @throws Exception  
     */  
    public static String encodeByMd5(String str) throws Exception{  
        MessageDigest md5;  
        md5 = MessageDigest.getInstance("MD5");  
          
        md5.update(str.getBytes()); //先更新摘要  
        byte[] digest = md5.digest(); //再通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。  
          
        /* 
         * 使用指定的 byte 数组对摘要进行最后更新，然后完成摘要计算。 
         * 也就是说，此方法首先调用 update(input)， 
         * 向 update 方法传递 input 数组，然后调用 digest()。 
         */  
//      byte[] digest = md5.digest(str.getBytes());  
          
        String hex = toHex(digest);  
        System.out.println("MD5摘要:" + hex);  
        return hex;  
    }  
      
    /** 
     * 文件数据摘要 
     * @throws Exception 
     */  
    public static void md5File() throws Exception {  
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
        DigestOutputStream dos = new DigestOutputStream(new FileOutputStream(new File("D:\\abc.txt")), messageDigest);  
        dos.write("中华人民……&（））f*（214）admin*".getBytes());  
        dos.close();  
        byte[] digest = messageDigest.digest();  
        System.out.println("使用流写文件，该文件的摘要为:" + toHex(digest));  
          
          
        DigestInputStream dis = new DigestInputStream(new FileInputStream(new File("D:\\abc.txt")), messageDigest);  
        byte[] buf = new byte[100];  
        int len;  
        while ((len = dis.read(buf)) != -1) {  
            System.out.println("读取到的数据为：" + new String(buf, 0, len));  
        }  
        dis.close();  
        byte[] digest2 = messageDigest.digest();  
        //当流读取完毕，即将文件读完了， 这时的摘要 才与 写入时的 一样  
        System.out.println("使用流读文件，该文件的摘要为:" + toHex(digest2));  
    }  
      
    /** 
     * md5 摘要转16进制 
     * @param digest 
     * @return 
     */  
    private static String toHex(byte[] digest) {  
        StringBuilder sb = new StringBuilder();  
        int len = digest.length;  
          
        String out = null;  
        for (int i = 0; i < len; i++) {  
//          out = Integer.toHexString(0xFF & digest[i] + 0xABCDEF); //加任意 salt  
            out = Integer.toHexString(0xFF & digest[i]);//原始方法  
            if (out.length() == 1) {  
                sb.append("0");//如果为1位 前面补个0  
            }  
            sb.append(out);  
        }  
        return sb.toString();  
    }  
  
}  