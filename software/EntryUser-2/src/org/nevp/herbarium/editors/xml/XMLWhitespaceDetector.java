/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XMLWhitespaceDetector.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          
 *                             
 */
package org.nevp.herbarium.editors.xml;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class XMLWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
