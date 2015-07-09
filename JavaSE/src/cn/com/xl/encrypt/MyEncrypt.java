package cn.com.xl.encrypt;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MyEncrypt {
	/**
	 * 公钥加密/解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] cryptByPublicKey(byte[] data, String key, Integer cryptMode)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加/解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(cryptMode, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 私钥加密/解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] cryptByPrivateKey(byte[] data, String key, Integer cryptMode)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加/解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(cryptMode, privateKey);

		return cipher.doFinal(data);
	}

	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";
	public static final String KEY_ALGORITHM = "RSA";

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		kpg.initialize(1024);
		KeyPair kp = kpg.generateKeyPair();
		PrivateKey privateKey = kp.getPrivate();
		PublicKey publicKey = kp.getPublic();
		Map<String, Object> mapKey = new HashMap();
		mapKey.put(PUBLIC_KEY, publicKey);
		mapKey.put(PRIVATE_KEY, privateKey);
		return mapKey;
	}

	/**
	 * 取得公钥(经过BASE64编码)
	 * 
	 * @param keyMap
	 * @return
	 */
	public static Object getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());

	}

	/**
	 * 取得私钥(经过BASE64编码)
	 * 
	 * @param keyMap
	 * @return
	 */
	public static Object getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());

	}

	/**
	 * BASE64编码
	 * 
	 * @param data
	 * @return
	 */
	private static String encryptBASE64(byte[] data) {
		return new BASE64Encoder().encodeBuffer(data);
	}

	/**
	 * BASE64解码
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptBASE64(String data) throws Exception {
		return new BASE64Decoder().decodeBuffer(data);
	}
}
