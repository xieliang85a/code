package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestDom4j {

	
	public static String getDataInfo(String user, String password,String params) {
    	SAXReader reader = new SAXReader();
    	try{
    		
    		ByteArrayInputStream bais = new ByteArrayInputStream(params.getBytes());
    		InputStreamReader isr = new InputStreamReader(bais);
    		Document document = reader.read(isr);
//    		Document document = reader.read(params);//Test.class.getClassLoader().getResource("a.xml"));
    		Element root = document.getRootElement();
    		read(root);
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    	return "<xml>abc</xml>";
    }
    
    public static void read(Element e) {
		System.out.println(e.getName());
		System.out.println(e.attribute("name").getText());
		System.out.println(e.getTextTrim());
		for (Iterator elements = e.elementIterator(); elements.hasNext();) {
			Object obj = elements.next();
			if (obj instanceof Element){
				read((Element) obj);
			}
		}

	}
	
	public static void main(String[] args) throws Exception{
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String soapRequestData = ""
			+ "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">"
			+ "  <soap:Body>"
			+ "   <setDataInfo xmlns=\"http://dataExchange.webservice.stpt.wondersgroup.com/\">"
			+ "  <user>USER_NAME </user>" + "<password>PASSWORD</password>" + "<date>" + date + "</date>" + "<content>"
			+ "" + "</content>"
			+ "   </setDataInfo>" 
			+ "  </soap:Body>"
			+ "</soap:Envelope>";
		
		soapRequestData = "<xml name=\"abc\"><p name=\"你\">haha</p></xml>";
		getDataInfo("a","b",soapRequestData);
		// TODO Auto-generated method stub

	}

}
