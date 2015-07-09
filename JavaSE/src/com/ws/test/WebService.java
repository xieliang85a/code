package com.ws.test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@javax.jws.WebService(name="cmms_Handler")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebService {
    
    
    //调用Endpoint.publish发布此WebService接口服务
    public static void main (String[] args) {
    	
        Endpoint.publish("http://localhost:8888/webservice/cmms_Handler", new WebService());
    }
    
    
    public String getDataInfo(String user, String password,String params) {
    	SAXReader reader = new SAXReader();
    	try{
    		ByteArrayInputStream bais = new ByteArrayInputStream(params.getBytes());
    		InputStreamReader isr = new InputStreamReader(bais);
    		Document document = reader.read(isr);
    		Element root = document.getRootElement();
    		read(root);
    		String id = "1";
    		String name = "AB";
    		Node node = root.selectSingleNode("//中国/*[@id='"+id+"' and @name='"+name+"']");
    		if(node==null){
    			System.out.println("没有找到匹配的内容!");
    		}else{
    			System.out.println(node.getName());
    		}
    	}catch(Exception ex){
    		
    	}
    	return "<xml>abc</xml>";
    }
    
    private void read(Element e) {
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
}
