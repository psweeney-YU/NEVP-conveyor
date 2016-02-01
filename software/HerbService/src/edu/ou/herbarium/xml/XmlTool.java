/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XmlTool.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          DOM's superior encapsulation
 *                             
 */
package edu.ou.herbarium.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




public class XmlTool {

	private static final String STYLE_XSLT = "format.xslt";

	private Document _doc;
	private EntityResolver entityRevolver;
	private Map outputProperties = new HashMap();

	
	/**
	 * 
	 * @return
	 */
	public Document get_doc() {
		return _doc;
	}

	/**
	 * 
	 */
	public XmlTool() {
	}

	/**
	 * 
	 * @throws XmlToolException
	 */
	public void createDocument() throws XmlToolException {
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder build = dfactory.newDocumentBuilder();
			_doc = build.newDocument();
		}
		catch (ParserConfigurationException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param reader
	 * @throws XmlToolException
	 */
	public void initialize(Reader reader) throws XmlToolException {
		// Set up a DOM tree to query.
		InputSource in = new InputSource(reader);
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		dfactory.setNamespaceAware(true);
		try {
			DocumentBuilder parser = dfactory.newDocumentBuilder();
			if (entityRevolver != null)
				parser.setEntityResolver(entityRevolver);
			_doc = parser.parse(in);
		}
		catch (SAXException e) {
			throw new XmlToolException(e);
		}
		catch (IOException e) {
			throw new XmlToolException(e);
		}
		catch (ParserConfigurationException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param input
	 * @throws XmlToolException
	 */
	public void initialize(InputStream input) throws XmlToolException {
		// Set up a DOM tree to query.
		InputSource in = new InputSource(input);
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		dfactory.setNamespaceAware(true);
		try {
			DocumentBuilder parser = dfactory.newDocumentBuilder();
			if (entityRevolver != null)
				parser.setEntityResolver(entityRevolver);
			_doc = parser.parse(in);
		}
		catch (SAXException e) {
			throw new XmlToolException(e);
		}
		catch (IOException e) {
			throw new XmlToolException(e);
		}
		catch (ParserConfigurationException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param file
	 * @throws XmlToolException
	 */
	public void initialize(File file) throws XmlToolException {
		try {
			initialize(new FileReader(file));
		}
		catch (FileNotFoundException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param url
	 * @throws XmlToolException
	 */
	public void initialize(URL url) throws XmlToolException {
		try {
			initialize(url.openStream());
		}
		catch (IOException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xmlString
	 * @throws XmlToolException
	 */
	public void initialize(String xmlString) throws XmlToolException {
		initialize(new StringReader(xmlString));
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public int count(String xpath) throws XmlToolException {
		String cntExpr = "count(" + xpath + ")";
		try {
			XPath xp = XPathFactory.getXPath(cntExpr);
			return ((Double) xp.evaluate(_doc)).intValue();
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public String selectElementValue(String xpath) throws XmlToolException {
		try {
			XPath xp = new DOMXPath(xpath);
			List eleList = xp.selectNodes(_doc);
			/*if (eleList.size() > 1)
				throw new XmlToolException("Specified xpath not unique!"
						+ " xpath: " + xpath);*/

			Element ele = (Element) eleList.get(0);
			NodeList nl = ele.getChildNodes();
			for (int i = 0, n = nl.getLength(); i < n; i++) {
				Node node = nl.item(i);
				if (node.getNodeType() == Node.TEXT_NODE) {
					return node.getNodeValue();
				}
			}
			return "";
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public String selectElementAttr(String xpath) throws XmlToolException {
		if (xpath.lastIndexOf("@") < 0)
			throw new XmlToolException("No attribute name specified!"
					+ " xpath: " + xpath);
		String attrname = xpath.substring(xpath.lastIndexOf("@") + 1, xpath
				.length());
		attrname = attrname.trim();
		if (attrname.equals(""))
			throw new XmlToolException("No attribute name specified!"
					+ " xpath: " + xpath);

		String sPath = xpath.substring(0, xpath.lastIndexOf("/"));
		try {
			XPath xp = XPathFactory.getXPath(sPath);

			List eleList = xp.selectNodes(_doc);
			if (eleList.size() == 0)
				throw new XmlToolException("Specified attribute not exist!"
						+ " xpath: " + xpath);
			/*if (eleList.size() > 1)
				throw new XmlToolException("Specified xpath not unique!"
						+ " xpath: " + xpath);
*/
			return ((Element) eleList.get(0)).getAttribute(attrname);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param elevalue
	 * @throws XmlToolException
	 */
	public void setElementValue(String xpath, String elevalue)
			throws XmlToolException {
		try {
			XPath xp = XPathFactory.getXPath(xpath);
			List eleList = xp.selectNodes(_doc);
			if (eleList.size() == 0)
				throw new XmlToolException("Specified xpath not exist!"
						+ " xpath: " + xpath);

			Iterator itrEle = (eleList).iterator();
			Text txt = _doc.createTextNode(elevalue);
			for (; itrEle.hasNext();) {
				Element Ele = (Element) itrEle.next();
				Ele.appendChild(txt.cloneNode(false));
			}
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param attrvalue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, String attrvalue)
			throws XmlToolException {
		if (xpath.lastIndexOf("@") < 0)
			throw new XmlToolException("No attribute name specified!"
					+ " xpath: " + xpath);
		String attrname = xpath.substring(xpath.lastIndexOf("@") + 1, xpath
				.length());
		attrname = attrname.trim();
		if (attrname.equals(""))
			throw new XmlToolException("No attribute name specified!"
					+ " xpath: " + xpath);

		String sPath = xpath.substring(0, xpath.lastIndexOf("/"));
		try {
			XPath xp = XPathFactory.getXPath(sPath);
			List eleList = xp.selectNodes(_doc);
			if (eleList.size() == 0)
				throw new XmlToolException("Specified xpath not exist!"
						+ " xpath: " + xpath);
//			if (eleList.size() > 1)
//				throw new XmlToolException("Specified xpath not unique!"
//						+ " xpath: " + xpath);

			Iterator itrEle = eleList.iterator();
			for (; itrEle.hasNext();)
				((Element) itrEle.next()).setAttribute(attrname, attrvalue);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param iValue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, int iValue)
			throws XmlToolException {
		setElementAttr(xpath, String.valueOf(iValue));
	}

	/**
	 * 
	 * @param xpath
	 * @param bValue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, boolean bValue)
			throws XmlToolException {
		setElementAttr(xpath, String.valueOf(bValue));
	}

	/**
	 * 
	 * @param xpath
	 * @param fValue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, float fValue)
			throws XmlToolException {
		setElementAttr(xpath, String.valueOf(fValue));
	}

	/**
	 * 
	 * @param xpath
	 * @param dValue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, double dValue)
			throws XmlToolException {
		setElementAttr(xpath, String.valueOf(dValue));
	}

	/**
	 * 
	 * @param xpath
	 * @param lValue
	 * @throws XmlToolException
	 */
	public void setElementAttr(String xpath, long lValue)
			throws XmlToolException {
		setElementAttr(xpath, String.valueOf(lValue));
	}

	/**
	 * Add child node under existed parent anyway. e.g.
	 * "/deviceconfig[1]/serial/port[7]
	 * 
	 * @param xpath The xpath ends with element name
	 * @throws XmlToolException invalid xpath which no <code>"/"</code> in the
	 *             param <code>xpath</code>
	 * @throws XmlToolException parent not existed
	 * @throws XmlToolException multi parent error
	 */
	public void addElement(String xpath) throws XmlToolException {
		String parent = null;
		int idx = xpath.lastIndexOf("/");
		if (idx < 0)
			throw new XmlToolException("Invalid xpath!" + " xpath: " + xpath);
		else if (idx == 0) {
			parent = "/";
		}
		else {
			parent = xpath.substring(0, idx);
		}
		String name = xpath.substring(idx + 1);
		name = getNameWithoutQualifier(name);
		try {
			XPath xp = XPathFactory.getXPath(parent);
			List parentNodes = xp.selectNodes(_doc);
			if (parentNodes.size() == 0)
				throw new XmlToolException("Specified parent xpath not exist!"
						+ " xpath: " + parent);
//			if (parentNodes.size() > 1)
//				throw new XmlToolException("Specified parent xpath not unique!"
//						+ " xpath: " + parent);

			Node ele = (Node) parentNodes.get(0);
			ele.appendChild(_doc.createElement(name));
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * non-existed node in the <code>xpath</code> will be created! e.g.
	 * "/deviceconfig[1]/serial[1]/port[2]"
	 * 
	 * @param xpath The xpath ends with element name
	 * @throws XmlToolException
	 */
	public void appendElement(String xpath) throws XmlToolException {
		String[] sPaths = StringUtil.getTokens(xpath, "/");
		StringBuffer subPath = new StringBuffer();
		for (int i = 0; i < sPaths.length; i++) {
			subPath.append("/");
			subPath.append(sPaths[i]);
			String sPath = subPath.toString();
			int cnt = count(sPath);
			if ((i < (sPaths.length - 1) && cnt == 0)
					|| (i == sPaths.length - 1)) {
				addElement(sPath);
			}
		}
	}

	/**
	 * @param xpath The xpath ends with attribute name
	 * @param attrvalue
	 * @throws XmlToolException
	 */
	public void appendElementAttr(String xpath, String attrvalue)
			throws XmlToolException {
		int idx = xpath.lastIndexOf("/@");
		if (idx < 0)
			throw new XmlToolException("Invalid xpath!" + " xpath: " + xpath);

		String sPath = xpath.substring(0, idx);
		String attrname = xpath.substring(idx + 2);

		String path = sPath;
		int cnt = count(path);
		if (cnt == 0) {
			appendElement(path);
		}
		setElementAttr(path + "/@" + attrname, attrvalue);
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public Element selectElement(String xpath) throws XmlToolException {
		try {
			XPath xp = XPathFactory.getXPath(xpath);
			List eleList = xp.selectNodes(_doc);
			if (eleList.size() == 0)
				throw new XmlToolException("Specified xpath not exist!"
						+ " xpath: " + xpath);
//			if (eleList.size() > 1)
//				throw new XmlToolException("Specified xpath not unique!"
//						+ " xpath: " + xpath);

			return (Element) eleList.get(0);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public Element removeElemnt(String xpath) throws XmlToolException {
		Element ele = selectElement(xpath);
		Node parent = ele.getParentNode();
		Element oldEle = (Element) parent.removeChild(ele);
		return oldEle;
	}

	/**
	 * 
	 * @param xpath
	 * @param element
	 * @throws XmlToolException
	 */
	public void replaceElement(String xpath, Element element)
			throws XmlToolException {
		try {
			Element oldElement = selectElement(xpath);
			Node newElement = _doc.importNode(element, true);
			Node parent = oldElement.getParentNode();
			parent.replaceChild(newElement, oldElement);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param element
	 * @throws XmlToolException
	 */
	public void appendElement(String xpath, Element element)
			throws XmlToolException {
		try {
			Element parent = selectElement(xpath);
			Node newElement = _doc.importNode(element, true);
			parent.appendChild(newElement);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 */
	public String toString() {
		if (_doc == null)
			return "";

		StringWriter write = new StringWriter();
		try {
			Source source = new StreamSource(getClass().getResourceAsStream(
					STYLE_XSLT));
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(source);
			for (Iterator it = outputProperties.entrySet().iterator(); it
					.hasNext();) {
				Entry entry = (Entry) it.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				transformer.setOutputProperty(key, val);
			}
			DOMSource src = new DOMSource(_doc);
			StreamResult des = new StreamResult(write);
			transformer.transform(src, des);
		}
		catch (Exception e) {
		}

		return write.toString();
	}

	/**
	 * 
	 * @param file
	 * @throws XmlToolException
	 */
	public void toStream(File file) throws XmlToolException {
		try {
			Source source = new StreamSource(getClass().getResourceAsStream(
					STYLE_XSLT));
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(source);
			for (Iterator it = outputProperties.entrySet().iterator(); it
					.hasNext();) {
				Entry entry = (Entry) it.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				transformer.setOutputProperty(key, val);
			}
			DOMSource src = new DOMSource(_doc);
			StreamResult des = new StreamResult(file);
			transformer.transform(src, des);
		}
		catch (TransformerConfigurationException e) {
			throw new XmlToolException(e);
		}
		catch (TransformerFactoryConfigurationError e) {
			throw new XmlToolException(e);
		}
		catch (TransformerException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param sysmtemId Must be a String that conforms to the URI syntax.
	 * @throws XmlToolException
	 */
	public void toStream(String sysmtemId) throws XmlToolException {
		try {
			Source source = new StreamSource(getClass().getResourceAsStream(
					STYLE_XSLT));
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(source);
			for (Iterator it = outputProperties.entrySet().iterator(); it
					.hasNext();) {
				Entry entry = (Entry) it.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				transformer.setOutputProperty(key, val);
			}
			DOMSource src = new DOMSource(_doc);
			StreamResult des = new StreamResult(sysmtemId);
			transformer.transform(src, des);
		}
		catch (TransformerConfigurationException e) {
			throw new XmlToolException(e);
		}
		catch (TransformerFactoryConfigurationError e) {
			throw new XmlToolException(e);
		}
		catch (TransformerException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param writer
	 * @throws XmlToolException
	 */
	public void toStream(Writer writer) throws XmlToolException {
		try {
			Source source = new StreamSource(getClass().getResourceAsStream(
					STYLE_XSLT));
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(source);
			for (Iterator it = outputProperties.entrySet().iterator(); it
					.hasNext();) {
				Entry entry = (Entry) it.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				transformer.setOutputProperty(key, val);
			}
			DOMSource src = new DOMSource(_doc);
			StreamResult des = new StreamResult(writer);
			transformer.transform(src, des);
		}
		catch (TransformerConfigurationException e) {
			throw new XmlToolException(e);
		}
		catch (TransformerFactoryConfigurationError e) {
			throw new XmlToolException(e);
		}
		catch (TransformerException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param output
	 * @throws XmlToolException
	 */
	public void toStream(OutputStream output) throws XmlToolException {
		try {
			Source source = new StreamSource(getClass().getResourceAsStream(
					STYLE_XSLT));
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(source);
			for (Iterator it = outputProperties.entrySet().iterator(); it
					.hasNext();) {
				Entry entry = (Entry) it.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				transformer.setOutputProperty(key, val);
			}
			DOMSource src = new DOMSource(_doc);
			StreamResult des = new StreamResult(output);
			transformer.transform(src, des);
		}
		catch (TransformerConfigurationException e) {
			throw new XmlToolException(e);
		}
		catch (TransformerFactoryConfigurationError e) {
			throw new XmlToolException(e);
		}
		catch (TransformerException e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param xslSource
	 * @param outputTarget
	 * @throws XmlToolException
	 */
	public void transform(Source xslSource, Result outputTarget)
			throws XmlToolException {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(xslSource);
			DOMSource src = new DOMSource(_doc);
			transformer.transform(src, outputTarget);
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
	}

	/**
	 * 
	 * @param holdRoot
	 * @throws XmlToolException
	 */
	public void clear(boolean holdRoot) throws XmlToolException {
		if (_doc == null)
			throw new XmlToolException("Document root node not exist!");

		Element root = _doc.getDocumentElement();
		if (root == null)
			throw new XmlToolException("Document root node not exist!");

		try {
			Node n;
			while ((n = root.getFirstChild()) != null)
				root.removeChild(n);
			if (!holdRoot)
				_doc.removeChild(root);
		}
		catch (Exception e) {
			throw new XmlToolException("Clear xml structure failed!", e);
		}
	}

	/**
	 * 
	 */
	public void destroy() {
		_doc = null;
		entityRevolver = null;
	}

	/**
	 * @return the entityRevolver
	 */
	public EntityResolver getEntityRevolver() {
		return entityRevolver;
	}

	/**
	 * @param entityRevolver the entityRevolver to set
	 */
	public void setEntityRevolver(EntityResolver entityRevolver) {
		this.entityRevolver = entityRevolver;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setOutputProperty(String name, String value) {
		outputProperties.put(name, value);
	}

	/**
	 * Get the element name
	 * 
	 * @param xpath The xpath ends with element name
	 * @return
	 */
	public static String getName(String xpath) {
		String name = xpath;
		int idx = xpath.lastIndexOf("/");
		if (idx >= 0) {
			name = xpath.substring(idx + 1);
		}
		return name;
	}

	/**
	 * Get the parent path
	 * 
	 * @param xpath The xpath ends with element name
	 * @return <code>null</code> if no parent.
	 */
	public static String getParent(String xpath) {
		String parent = null;
		int idx = xpath.lastIndexOf("/");
		if (idx >= 0) {
			parent = xpath.substring(0, idx);
		}
		return parent;
	}

	/**
	 * Get the name with out index.
	 * 
	 * @param name The element name, or xpath ends with element name.
	 * @return
	 */
	public static String getNameWithoutQualifier(String name) {
		String sName = name;
		int idx = name.lastIndexOf("/");
		if (idx >= 0) {
			sName = getName(name);
		}

		int index = sName.lastIndexOf("[");
		if (index > 0) {
			sName = sName.substring(0, index);
		}
		return sName;
	}
	
	/**
	 * 
	 * @return
	 * @throws XmlToolException
	 */
	public String getRoot() throws XmlToolException{
		if (_doc == null)
			throw new XmlToolException("Document root node not exist!");

		Element root = _doc.getDocumentElement();
		if (root == null)
			throw new XmlToolException("Document root node not exist!");
		return root.getNodeName();
	}
	
	/**
	 * 
	 * @param xpath
	 * @return
	 * @throws XmlToolException
	 */
	public String[] getSonList(String xpath) throws XmlToolException{
		String ret[] = null;
		ArrayList<String> ret_array = new ArrayList<String>();
		try {
			Element parent = selectElement(xpath);
			if(!parent.hasChildNodes()){
				ret = new String[0];
				return ret;
			}
			int numberOfSon = parent.getChildNodes().getLength();
			for(int i = 0 ;i<numberOfSon;i++){
				if(parent.getChildNodes().item(i).getNodeName()!=null&&!parent.getChildNodes().item(i).getNodeName().equalsIgnoreCase("#text")){
					ret_array.add(parent.getChildNodes().item(i).getNodeName());
				}
			}
			ret = new String[ret_array.size()];
			for(int i = 0 ;i<ret_array.size();i++){
				ret[i]=ret_array.get(i);
			}
			
		}
		catch (Exception e) {
			throw new XmlToolException(e);
		}
		return ret;
	}
	
	/**
	 * 
	 * @param node
	 */
	public void addNodetoDocument(Node node){
		this._doc.importNode(node, true);
	}
	
	/**
	 * 
	 */
	public XmlTool clone(){
		XmlTool ret = new XmlTool();
		try {
			ret.initialize(this.toString());
		} catch (XmlToolException e) {
			return null;
		}
		return ret;
	}
	
}
