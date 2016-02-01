/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XPathFactory.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          getXPath
 *                             
 */
package edu.ou.herbarium.xml;

import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;


public class XPathFactory {

	/**
	 * create <code>XPath</code> instance from context.
	 * 
	 * @param expr
	 * @return
	 * @throws JaxenException
	 */
	public static XPath getXPath(String expr) throws JaxenException {
		// TODO
		return new DOMXPath(expr);
	}
}
