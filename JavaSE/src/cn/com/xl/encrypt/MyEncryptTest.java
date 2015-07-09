package cn.com.xl.encrypt;

import static org.junit.Assert.*;

import java.util.Map;

import javax.crypto.Cipher;

import org.junit.Test;

public class MyEncryptTest {

	@Test
	public void test() throws Exception{
		String data = "上海地铁电科";
		System.out.println("原始内容：\r\n" + data);
		Map <String,Object> keyMap = MyEncrypt.initKey();
		String privateKey = (String)MyEncrypt.getPrivateKey(keyMap);
		String publicKey = (String)MyEncrypt.getPublicKey(keyMap);
		System.out.println("公钥\r\n" + publicKey);
		System.out.println("私钥\r\n" + privateKey);
		System.out.println("----------------公钥加密，私钥解密-----------------------");
		byte [] endata = MyEncrypt.cryptByPublicKey(data.getBytes(), publicKey, Cipher.ENCRYPT_MODE);
		System.out.println("公钥加密后的内容：\r\n" + new String(endata));
		
		byte [] dedata = MyEncrypt.cryptByPrivateKey(endata, privateKey, Cipher.DECRYPT_MODE);
		System.out.println("私钥解密后的内容：\r\n" + new String(dedata));
		
		
		System.out.println("----------------私钥加密，公钥解密-----------------------");
		endata = MyEncrypt.cryptByPrivateKey(data.getBytes(), privateKey, Cipher.ENCRYPT_MODE);
		System.out.println("私钥加密后的内容：\r\n" + new String(endata));
		dedata = MyEncrypt.cryptByPublicKey(endata, publicKey, Cipher.DECRYPT_MODE);
		System.out.println("公钥解密后的内容：\r\n" + new String(dedata));
		
	}

}
