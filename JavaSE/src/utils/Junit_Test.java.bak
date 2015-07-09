package utils;

import java.io.File;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Junit_Test {
	@Test
	public void test1(){
		try {
			Document dom = XmlUtils.getDocument(new File("src//Test.xml"));
			XmlUtils.showAllElement(dom.getChildNodes().item(0));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
