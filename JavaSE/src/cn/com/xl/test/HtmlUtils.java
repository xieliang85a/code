package cn.com.xl.test;
/** 
 * <p>对HTML中的保留字符和一些特殊字符进行转换</p> 
 * @author tw 2009-06-05 
 * 
 */  
public class HtmlUtils {  
      
    public static String toHtml(String str){  
        if (str == null)  
            return null;  
        StringBuffer sb = new StringBuffer();  
        int len = str.length();  
        for (int i = 0; i < len; i++){  
            char c = str.charAt(i);  
            switch (c){  
           /* case ' ':  
                sb.append(" ");  
                break;  */
            case '\n':  
                sb.append("</br>");  
                break;  
            case '\r':  
                break;  
            case '\'':  
                sb.append("'");  
                break;  
            /*case '<':  
                sb.append("<");  
                break;  
            case '>':  
                sb.append(">");  
                break;  
            case '&':  
                sb.append("&");  
                break;  
            case '"':  
                sb.append('"');  
                break;  
            case '/':  
                sb.append("/");  
                break;  */
            default:  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }  
    public static void main(String arg[]){  
        String str = "<tt>sdfdf<''s''d//s";  
        str = toHtml(str);  
        System.out.println("-----------str:"+str);  
    }  
  
} 