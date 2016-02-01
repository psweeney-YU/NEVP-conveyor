package org.nevp.herbarium.dialog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.framework.internal.core.BundleContextImpl;
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
import org.eclipse.ui.PlatformUI;


public class LoginDialog extends Dialog{

	public static final int LOGIN_ID = 0;
	public static final int CANCEL_ID = 1;
	private Text username;
	private Text password;
	private Text workspace;
	private Button buttonCmapare;
	private Button buttonCancel;
	private Button login;
	private Button browse2;
	private int focusID = -1;;
	private static final long serialVersionUID = 1L;
	
	public LoginDialog(Shell parent) {
		super(parent);
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
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 20;
		gridData3.minimumWidth = 50;
		new Label(group,SWT.NONE).setText("Username:");
		username = new Text(group,SWT.BORDER | SWT.SINGLE);
		username.setLayoutData(gridData);
		username.setText("root");
		new Label(group,SWT.NONE).setText("Password:");
		password = new Text(group,SWT.BORDER |SWT.PASSWORD | SWT.SINGLE);
		password.setText("111111");
		password.setLayoutData(gridData2);
		workspace = new Text(group,SWT.BORDER | SWT.SINGLE);
		workspace.setText("C:/test");
		workspace.setVisible(false);
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,LoginDialog.LOGIN_ID,"Login",true);
		buttonCancel = createButton(parent,LoginDialog.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(LoginDialog.LOGIN_ID == buttonId){
			
    		close();
		}else if(LoginDialog.CANCEL_ID == buttonId){
			close();
		}
	}

}


