package utils;

import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4JTest {
	
	//xpath
	@Test
	public void test_xpath() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("src//Test.xml");
		Element root = document.getRootElement();
		String id = "1";
		String name = "AB";
		Node node = root.selectSingleNode("//中国/*[@id='"+id+"' and @name='"+name+"']");
		if(node==null){
			System.out.println("没有找到匹配的内容!");
		}else{
			System.out.println(node.getName());
		}
	}
	
	@Test
	public void test() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("src//Test.xml");
		Element root = document.getRootElement();
		read(root);
		//----------默认形式加入元素----------------------
//		Element element = root.addElement("广州");
//		element.addElement(QName.get("白云")).setText("1");
//		element.addElement("天河").setText("2");
		//--------指定位置添加元素--------------------
//		List list = root.elements();
//		list.add(1, addElement());
		//--------------------------
		//write(document);

	}
	
	
	//在指定的位置上添加
	public Branch addElement(){
		Element e = DocumentHelper.createElement("浙江");
		e.addElement("拱墅").setText("北站");
		return e;
	}
	public void write(Document document)throws Exception{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		/**
		 * 1.当XMLWriter的实参为字节流时,则可以不用传入编码字集,但要传一个格式化编码(format)
		 * 这样系统将文件以字节流的形式写入,写入时将依据指定的格式化编码进行查表保存.
		 * 2.若用字符流写入的话,则要指定一个编码字集
		 */		
		XMLWriter writer = new XMLWriter(/*new OutputStreamWriter(*/new FileOutputStream("src//Test.xml")/*,"UTF-8")*/,format);
		writer.write(document);
		writer.flush();
		writer.close();
	}
	
	
	public void read(Element e) {
		System.out.println(e.getName());
		System.out.println(e.getTextTrim());
		for (Iterator elements = e.elementIterator(); elements.hasNext();) {
			Object obj = elements.next();
			if (obj instanceof Element){
				read((Element) obj);
			}
		}

	}
}
