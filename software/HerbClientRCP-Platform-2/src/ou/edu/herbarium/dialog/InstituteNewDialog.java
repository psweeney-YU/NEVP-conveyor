package ou.edu.herbarium.dialog;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
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

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.Institute;
import ou.edu.herbarium.wsclient.User;

public class InstituteNewDialog  extends Dialog{

	public static final int SUBMIT_ID = 0;
	public static final int CANCEL_ID = 1;
	
	private Text insName;
	private Text insInfo;
	private Institute ins;
	private Button buttonCmapare;
	private Button buttonCancel;
	protected InstituteNewDialog(Shell parentShell, Institute ins) {
		super(parentShell);
		this.ins = ins;
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("login");
		Group group = new Group(comp,SWT.NONE);
		group.setText("Authority Required");
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
		
		new Label(group,SWT.NONE).setText("Institute Name:");
		insName = new Text(group,SWT.BORDER | SWT.SINGLE);
		insName.setLayoutData(gridData);
		new Label(group,SWT.NONE).setText("nstitute Information:");
		insInfo = new Text(group,SWT.BORDER  | SWT.SINGLE);
		insInfo.setLayoutData(gridData2);
		
		if(this.ins!=null){
			insName.setText(ins.getInstituteName());
			insInfo.setText(ins.getInstituteInfo());
		}
		
		
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,InstituteNewDialog.SUBMIT_ID,"OK",true);
		buttonCancel = createButton(parent,InstituteNewDialog.CANCEL_ID,"Cancel",false);
		buttonCmapare = createButton(parent,InstituteNewDialog.SUBMIT_ID,"Submit",true);
		buttonCancel = createButton(parent,InstituteNewDialog.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(InstituteNewDialog.SUBMIT_ID == buttonId){
			DataUtilsService service = new DataUtilsService();
			DataUtilsDelegate delegate = service.getDataUtilsPort();
			if(this.ins!=null){
				ins.setInstituteInfo(insInfo.getText());
				ins.setInstituteName(insName.getText());
				delegate.updateInstitute(ins);
			}
			else{
				delegate.insertInstitute(insName.getText(), insInfo.getText());
			}
			close();
		}else if(InstituteNewDialog.CANCEL_ID == buttonId){
			close();
		}
	}

	public Text getInsName() {
		return insName;
	}

	public void setInsName(Text insName) {
		this.insName = insName;
	}

	public Text getInsInfo() {
		return insInfo;
	}

	public void setInsInfo(Text insInfo) {
		this.insInfo = insInfo;
	}

}
