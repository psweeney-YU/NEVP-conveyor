package ou.edu.herbarium.util;

import java.io.File;

import ou.edu.herbarium.xml.XmlTool;
import ou.edu.herbarium.xml.XmlToolException;

public class XMLRemoval {
	public static void main(String args[]) throws XmlToolException{
		File file = new File ("C:/workspace/runtime-TestView.product"+"/buffer.xml");
		XmlTool xml = new XmlTool();
		xml.initialize(file);
		xml.removeElemnt("items/item[1]");
		xml.toStream(file);
	}
}
