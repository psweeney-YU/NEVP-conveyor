package org.nevp.herbarium.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.nevp.herbarium.editors.input.MultiEditorInput;

import org.nevp.herbarium.wsclient.Specimen;

public class ImageViewer extends EditorPart {

	private Specimen spec;
	private Table table;
	public static final String ID = "org.nevp.herbarium.ImageView";
	public static String SERVER_ADDR = "http://127.0.0.1:8081/HerbService/";
	
	public ImageViewer() {
		super();
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
		this.setSpec(((MultiEditorInput)input).getInput());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Browser browser = new Browser(parent, SWT.NONE);
		String specPath = "";
		if(getSpec().getImageRaw()!=null)
			specPath = getSpec().getImageRaw().getImageRawPath();
		else specPath = spec.getFolderPath();
		System.out.println(specPath);
		specPath = specPath.replaceAll(specPath.split("/")[0],"");
		specPath = specPath.substring(1,specPath.length()-1);
		System.out.println("specPath"+specPath);
		browser.setUrl(SERVER_ADDR+"test.html?file="+specPath);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	public Specimen getSpec() {
		return spec;
	}

	public void setSpec(Specimen spec) {
		this.spec = spec;
	}

}
