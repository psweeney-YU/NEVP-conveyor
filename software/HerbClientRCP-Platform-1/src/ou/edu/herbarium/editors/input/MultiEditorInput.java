package ou.edu.herbarium.editors.input;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import ou.edu.herbarium.wsclient.Specimen;

public class MultiEditorInput implements IEditorInput{
	private Specimen input ;
	
	/**
	 * 
	 * @param input
	 */
	public MultiEditorInput ( Specimen input ){
		this.setInput(input) ;
	}
	
	/**
	 * 
	 */
	public boolean exists() {
		return true;
	}
	
	/**
	 * 
	 */
	public String getName() {
		return getInput().getBarcode();
	}
	
	/**
	 * 
	 */
	public IPersistableElement getPersistable() {
		return null;
	}
	
	/**
	 * 
	 */
	public String getToolTipText() {
		String ret = getInput().getGeneralNotes();
		if(ret==null) ret = "";
		return ret;
	}
	
	/**
	 * 
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Specimen getInput() {
		return input;
	}

	public void setInput(Specimen input) {
		this.input = input;
	}
}
