package org.nevp.herbarium.util;

import java.io.File;

import org.nevp.herbarium.xml.XmlTool;
import org.nevp.herbarium.xml.XmlToolException;

public class XMLRemoval {
	public static void main(String args[]) throws XmlToolException{
		File file = new File ("C:/workspace/runtime-TestView.product"+"/buffer.xml");
		XmlTool xml = new XmlTool();
		xml.initialize(file);
		xml.removeElemnt("items/item[1]");
		xml.toStream(file);
	}
}
