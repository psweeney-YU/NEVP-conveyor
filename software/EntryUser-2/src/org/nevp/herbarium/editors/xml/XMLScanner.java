/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XMLScanner.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          
 *                             
 */
package org.nevp.herbarium.editors.xml;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class XMLScanner extends RuleBasedScanner {

	public XMLScanner(XMLColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IXMLColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new XMLWhitespaceDetector());

		setRules(rules);
	}
}
