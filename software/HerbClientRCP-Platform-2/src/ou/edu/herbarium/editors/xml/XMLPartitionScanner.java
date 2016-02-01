/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XMLPartitionScanner.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          
 *                             
 */
package ou.edu.herbarium.editors.xml;

import org.eclipse.jface.text.rules.*;

public class XMLPartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_TAG = "__xml_tag";

	public XMLPartitionScanner() {
		IToken xmlComment = new Token(XML_COMMENT);
		IToken tag = new Token(XML_TAG);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new XMLTagRule(tag);

		setPredicateRules(rules);
	}
}
