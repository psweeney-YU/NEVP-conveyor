package ou.edu.herbarium.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
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

public class TestModeDialog  extends Dialog{
	private Text testDirectory;
	public static final int OK_ID = 0;
	public static final int CANCEL_ID = 1;
	private Button buttonCmapare;
	private Button buttonCancel;
	public TestModeDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("MODE");
		Group group = new Group(comp,SWT.NONE);
		group.setText("Activate Test Mode");
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
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 20;
		gridData3.minimumWidth = 50;
		new Label(group,SWT.NONE).setText("Test Directory:");
		testDirectory = new Text(group,SWT.BORDER | SWT.SINGLE);
		testDirectory.setLayoutData(gridData);
		testDirectory.setText("C:/test/image/");
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,this.OK_ID,"OK",true);
		buttonCancel = createButton(parent,this.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(InstituteNewDialog.SUBMIT_ID == buttonId){
			Activator.testDirectory = testDirectory.getText();
			close();
		}else if(InstituteNewDialog.CANCEL_ID == buttonId){
			close();
		}
	}
}
