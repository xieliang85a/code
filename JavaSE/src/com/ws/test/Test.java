package com.ws.test;

public class Test {
	public static void main(String[] args) {
		WebServiceService ws = new WebServiceService();
		CmmsHandler ch = ws.getCmmsHandlerPort();
		String str = ch.getDataInfo("a", "b", "<xml name=\"abc\">xmlakdjf</xml>");
		System.out.println(str);
	}
}
