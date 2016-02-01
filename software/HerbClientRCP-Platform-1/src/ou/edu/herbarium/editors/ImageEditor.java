package ou.edu.herbarium.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import ou.edu.herbarium.editors.input.MultiEditorInput;
import ou.edu.herbarium.editors.xml.XMLEditor;

public class ImageEditor extends MultiPageEditorPart{

	public static final String ID = "ou.edu.herbarium.ImageEditor";
	MultiEditorInput input;
	ImageViewer editor1;
	MetaDataEditor editor2;
//	private XMLEditor editor3;
	String specimenId;
	@Override
	protected void createPages() {
		// TODO Auto-generated method stub
		MultiEditorInput input = (MultiEditorInput)getEditorInput();
		this.input = input;
		editor1 = new ImageViewer() ;
		editor2 = new MetaDataEditor();
		try {
			addPage( editor1 , input);
			setPageText(0,"image");
			addPage( editor2 , input);
			setPageText(1,"metadata");
			this.setPartName(input.getName());//Create User
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public MultiEditorInput getInput() {
		return input;
	}


	public String getSpecimenId() {
		return specimenId;
	}

	public void setInput(MultiEditorInput input) {
		this.input = input;
	}


	public void setSpecimenId(String specimenId) {
		this.specimenId = specimenId;
	}

	
	public ImageViewer getEditor1() {
		return editor1;
	}

	public MetaDataEditor getEditor2() {
		return editor2;
	}

	public void setEditor1(ImageViewer editor1) {
		this.editor1 = editor1;
	}

	public void setEditor2(MetaDataEditor editor2) {
		this.editor2 = editor2;
	}

//	public XMLEditor getEditor3() {
//		return editor3;
//	}
//
//	public void setEditor3(XMLEditor editor3) {
//		this.editor3 = editor3;
//	}
}
