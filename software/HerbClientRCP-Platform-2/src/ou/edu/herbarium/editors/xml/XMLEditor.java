package ou.edu.herbarium.editors.xml;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.EditorPart;

public class XMLEditor extends EditorPart {
	
	public static final String ID = "ou.edu.herbarium.XMLEditor";
	
	private String fileUrl;
	private String type = "File";
	private String xmlContent;

	public SourceViewer viewer;
	private XMLColorManager colorManager;
	private PersistentDocument doc;
	private XMLConfiguration configuration;
	/**
	 * 
	 */
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName("Export Metadata");
		fileUrl = input.getName();
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		viewer = new SourceViewer(parent, new VerticalRuler(10), SWT.V_SCROLL | SWT.H_SCROLL);
		colorManager = new XMLColorManager();
		connectContent();
		viewer.setEditable(true);
	}
	/**
	 * 
	 */
	public void connectContent(){
		if(type.equals("File")){
			doc = new PersistentDocument(fileUrl,type);
		}
		else{
			doc = new PersistentDocument(fileUrl,type,xmlContent);
		}
		IDocumentPartitioner partitioner =
			new FastPartitioner(
				new XMLPartitionScanner(),
				new String[] {
					XMLPartitionScanner.XML_TAG,
					XMLPartitionScanner.XML_COMMENT });
		partitioner.connect(doc);
		doc.setDocumentPartitioner(partitioner);
		configuration = new XMLConfiguration(colorManager);
		viewer.setDocument(doc);
		viewer.configure(configuration);
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 
	 * @return
	 */
	public String getXmlContent() {
		return xmlContent;
	}
	/**
	 * 
	 * @param xmlContent
	 */
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}
	/**
	 * 
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
