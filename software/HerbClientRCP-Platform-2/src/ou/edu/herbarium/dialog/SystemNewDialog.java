package ou.edu.herbarium.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.Institute;
import ou.edu.herbarium.wsclient.System;

public class SystemNewDialog extends Dialog{

	public static final int SUBMIT_ID = 0;
	public static final int CANCEL_ID = 1;
	public int insId;
	public String insName;
	public System system;
	public Text systemDescription;
	
	private Button buttonCmapare;
	private Button buttonCancel;
	protected SystemNewDialog(Shell parentShell, int insId, String insName, System system ) {
		super(parentShell);
		this.insId = insId;
		this.insName = insName;
		this.system = system;
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Create platform for Institute "+this.insName);
		
		Group group = new Group(comp,SWT.NONE);
		group.setText("Input System Description");
		GridLayout layout = new GridLayout();
		
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		layout.verticalSpacing = 10;
		group.setLayout(layout);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.minimumHeight = 20;
		gridData.minimumWidth = 150;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.minimumHeight = 20;
		gridData2.minimumWidth = 350;
		
		new Label(group,SWT.NONE).setText("System Description:");
		systemDescription = new Text(group,SWT.BORDER | SWT.SINGLE);
		if(this.system!=null){
			systemDescription.setText(system.getSystemDescription());
		}
		systemDescription.setLayoutData(gridData);
		
		return parent;
	}

	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,InstituteNewDialog.SUBMIT_ID,"Submit",true);
		buttonCancel = createButton(parent,InstituteNewDialog.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(InstituteNewDialog.SUBMIT_ID == buttonId){
			DataUtilsService service = new DataUtilsService();
			DataUtilsDelegate delegate = service.getDataUtilsPort();
			if(this.system==null)
				delegate.insertSystem(systemDescription.getText(), insId);
			else{
				this.system.setSystemDescription(systemDescription.getText());
				delegate.updateSystem(this.system,insId);
			}
			close();
		}else if(InstituteNewDialog.CANCEL_ID == buttonId){
			close();
		}
	}
}
