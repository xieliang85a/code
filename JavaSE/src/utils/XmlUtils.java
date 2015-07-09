package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtils {
	/**
	 * 
	 * @param src源文件(.xml)
	 * @return(document对象)
	 * @throws Exception
	 */
	public static Document getDocument(File src) throws Exception{
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = documentFactory.newDocumentBuilder();
		
		return builder.parse(src);
	}

	/**
	 * 
	 * @param dom要更新的document对象
	 * @param desc更新后保存的文件位置
	 * @throws Exception
	 */
	public static void write2Xml(Document dom,File desc) throws Exception{
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.transform(new DOMSource(dom), new StreamResult(desc));
	}
	
	/**
	 * 提供查看所有元素
	 */
	public static void showAllElement(Node node){
		System.out.println(node.getNodeName());
//		System.out.println(node.getTextContent());
		NodeList nodeList = node.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			if(nodeList.item(i) instanceof Element){
				showAllElement(nodeList.item(i));
				
			}
		}
	}
}
