/**
 * Copyright(c) 2008 by Alcatel-Lucent. 
 *        All rights reserved.
 *        
 * File name:            XmlToolException.java
 * 
 * Originally developed: Yan Xiaofeng & Xu Yiming
 * 
 * Create date:          Sep 23, 2008
 *        
 * Description:          Some XmlTool Exceptions
 *                             
 */
package ou.edu.herbarium.xml;

import java.io.PrintStream;
import java.io.PrintWriter;


public class XmlToolException extends Exception {

	private Throwable _exception;
	
	/**
	 * 
	 */
	public XmlToolException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public XmlToolException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public XmlToolException(String message, Throwable cause) {
		this(message);
		_exception = cause;
	}

	/**
	 * 
	 * @param cause
	 */
	public XmlToolException(Throwable cause) {
		_exception = cause;
	}

	/**
	 * 
	 * @return
	 */
	public Throwable getException() {
		return _exception;
	}

	/**
	 * 
	 */
	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			if (_exception != null) {
				s.print(this.getClass().toString());
				_exception.printStackTrace(s);
			}
			else {
				super.printStackTrace(s);
			}
		}
	}

	/**
	 * 
	 */
	public void printStackTrace(PrintWriter s) {
		synchronized (s) {
			if (_exception != null) {
				s.print(this.getClass().toString());
				_exception.printStackTrace(s);
			}
			else {
				super.printStackTrace(s);
			}
		}
	}
}
