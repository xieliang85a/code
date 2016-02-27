package cn.com.xl.encrypt;

  import static org.junit.Assert.assertTrue;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.Map;

import org.junit.Test;

public class DSACoderTest {
    @Test
    public void test() throws Exception {
        String inputStr = "iplat";
        byte[] data = inputStr.getBytes();
        // 构建密钥
        Map keyMap = DSACoder.initKey();
        // 获得密钥
        String publicKey = DSACoder.getPublicKey(keyMap);
        String privateKey = DSACoder.getPrivateKey(keyMap);
        System.err.println("公钥:\r" + publicKey);
        System.err.println("私钥:\r" + privateKey);
        // 产生签名
        String sign = DSACoder.sign(data, privateKey);
        System.err.println("签名:\r" + sign);
        //data = "1".getBytes();
        // 验证签名
        boolean status = DSACoder.verify(data, publicKey, sign);
        System.err.println("状态:\r" + status);
        assertTrue(status);
        
    }
    @Test
    public void testTurst(){
    	KeyPairGenerator keygen;
		try {
			keygen = KeyPairGenerator.getInstance("DSA");
			// 初始化随机产生器
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.setSeed("0f22507a10bbddd07d8a3082122966e3".getBytes());
			keygen.initialize(1024, secureRandom);
			KeyPair keys = keygen.genKeyPair();
			DSAPublicKey publicKey2 = (DSAPublicKey) keys.getPublic();
			DSAPrivateKey privateKey2 = (DSAPrivateKey) keys.getPrivate();
			// 获得密钥
			Key privateK = (Key) privateKey2;
			Key publicK = (Key) publicKey2;
			//encryptBASE64(privateK.getEncoded());
			System.err.println("公钥:\r" + encodeHex(privateK.getEncoded()));
			System.err.println("私钥:\r" +encodeHex(publicK.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    }
    public static final String encodeHex(byte bytes[])
    {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for(int i = 0; i < bytes.length; i++)
        {
            if((bytes[i] & 255) < 16)
                buf.append("0");
            buf.append(Long.toString(bytes[i] & 255, 16));
        }

        return buf.toString();
    }
}