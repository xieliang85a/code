package cn.com.xl.encrypt;
import java.security.MessageDigest;

public class ByteUtils
{
  public static final String MD5(String data)
  {
    MessageDigest digest;
    try
    {
      digest = MessageDigest.getInstance("MD5");
      digest.update(data.getBytes("utf-8"));
      return encodeHex(digest.digest());
    }
    catch (Exception e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }

  public static final String encodeHex(byte[] bytes) {
    StringBuffer buf = new StringBuffer(bytes.length * 2);
    for (int i = 0; i < bytes.length; ++i) {
      if ((bytes[i] & 0xFF) < 16)
        buf.append("0");
      buf.append(Long.toString(bytes[i] & 0xFF, 16));
    }

    return buf.toString();
  }

  public static final byte[] decodeHex(String hex) {
    char[] chars = hex.toCharArray();
    byte[] bytes = new byte[chars.length / 2];
    int byteCount = 0;
    for (int i = 0; i < chars.length; i += 2) {
      int newByte = 0;
      newByte |= hexCharToByte(chars[i]);
      newByte <<= 4;
      newByte |= hexCharToByte(chars[(i + 1)]);
      bytes[byteCount] = (byte)newByte;
      ++byteCount;
    }

    return bytes;
  }

  private static final byte hexCharToByte(char ch) {
    switch (ch)
    {
    case '0':
      return 0;
    case '1':
      return 1;
    case '2':
      return 2;
    case '3':
      return 3;
    case '4':
      return 4;
    case '5':
      return 5;
    case '6':
      return 6;
    case '7':
      return 7;
    case '8':
      return 8;
    case '9':
      return 9;
    case 'a':
      return 10;
    case 'b':
      return 11;
    case 'c':
      return 12;
    case 'd':
      return 13;
    case 'e':
      return 14;
    case 'f':
      return 15;
    case ':':
    case ';':
    case '<':
    case '=':
    case '>':
    case '?':
    case '@':
    case 'A':
    case 'B':
    case 'C':
    case 'D':
    case 'E':
    case 'F':
    case 'G':
    case 'H':
    case 'I':
    case 'J':
    case 'K':
    case 'L':
    case 'M':
    case 'N':
    case 'O':
    case 'P':
    case 'Q':
    case 'R':
    case 'S':
    case 'T':
    case 'U':
    case 'V':
    case 'W':
    case 'X':
    case 'Y':
    case 'Z':
    case '[':
    case '\\':
    case ']':
    case '^':
    case '_':
    case '`':
    }

    return 0;
  }

  public static void main(String[] argc)
    throws Exception
  {
    String han = "hanqiuming";

    MessageDigest digest = MessageDigest.getInstance("MD5");
    digest.update(han.getBytes("utf-8"));
    byte[] original = digest.digest();

    String encodedString = encodeHex(original);
    System.out.println(encodedString);
    byte[] copy = decodeHex(encodedString);

    for (int i = 0; i < original.length; ++i)
      System.out.print(original[i]);

    System.out.println();
    for (int i = 0; i < copy.length; ++i)
      System.out.print(copy[i]);
  }
}