package org.nevp.herbarium.editors.xml;

import java.io.*;

import org.eclipse.jface.text.*;

public class PersistentDocument extends Document implements IDocumentListener {
	private String fileName;
	private boolean dirty;
	private String type;
	private String xmlContent;
	
	/**
	 * 
	 * @param fileName
	 * @param type
	 */
	public PersistentDocument(String fileName, String type) {
		this.type = type;
		this.fileName = fileName;
		this.addDocumentListener(this);
		try {
			open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param fileName
	 * @param type
	 * @param content
	 */
	public PersistentDocument(String fileName, String type, String content){
		this.type = type;
		this.xmlContent = content;
		this.fileName = fileName;
		this.addDocumentListener(this);
		try {
			open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		if (fileName == null)
			throw new IllegalStateException("");
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			out.write(get());
			dirty = false;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException {
		if (fileName == null)
			throw new IllegalStateException("");
		BufferedReader in = null;
		try {
			if(type.equals("File")){
				in = new BufferedReader(new FileReader(fileName));
				StringBuffer buf = new StringBuffer();
				int n;
				while ((n = in.read()) != -1) {
					buf.append((char) n);
				}
				set(buf.toString());
				dirty = false;
			}
			else if(type.equals("String")){
				in = new BufferedReader(new StringReader(xmlContent));
				StringBuffer buf = new StringBuffer();
				int n;
				while ((n = in.read()) != -1) {
					buf.append((char) n);
				}
				set(buf.toString());
				dirty = false;
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 
	 * @return
	 */
	public boolean isDirty() {
		return dirty;
	}
	/**
	 * 
	 * @param dirty
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	/**
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 */
	public void documentAboutToBeChanged(DocumentEvent arg0) {

	}
	/**
	 * 
	 */
	public void documentChanged(DocumentEvent arg0) {
		dirty = true;
	}
}
