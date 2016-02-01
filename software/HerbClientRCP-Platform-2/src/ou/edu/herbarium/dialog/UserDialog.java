package ou.edu.herbarium.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ou.edu.herbarium.wizards.NewUserWizard;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.SystemSetting;
import ou.edu.herbarium.wsclient.User;

public class UserDialog extends Dialog{
	public UserDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("User");
		comp.setLayout(new GridLayout(2,false));//setLayoutData
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.minimumHeight = 500;
		gridData.minimumWidth = 400;
		
		GridData gridData0 = new GridData();
		gridData0.horizontalAlignment = GridData.FILL;
		gridData0.horizontalSpan = 2;
		gridData0.grabExcessHorizontalSpace = true;
		gridData0.grabExcessVerticalSpace = true;
		gridData0.minimumHeight = 25;
		gridData0.minimumWidth = 500;
		
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.minimumHeight = 300;
		gridData1.minimumWidth = 400;
		
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.minimumHeight = 500;
		gridData2.minimumWidth = 100;
		
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.BEGINNING;
		gridData3.verticalAlignment = GridData.BEGINNING;
		gridData3.grabExcessHorizontalSpace = true;
//		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 25;
		gridData3.minimumWidth = 100;
		
		final DataUtilsService service = new DataUtilsService();
		final DataUtilsDelegate delegate = service.getDataUtilsPort();
		
		Group group1 = new Group(comp,SWT.NONE);
		group1.setText("");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(1,false));
		final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		column.setText("UserID");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		column2.setText("UserName");
		TableColumn column3 = new TableColumn(table,SWT.NONE);
		column3.setWidth(250);
		column3.setText("Create Date");
		TableColumn column4 = new TableColumn(table,SWT.NONE);
		column4.setWidth(250);
		column4.setText("Last Login");
		

		table.setLayoutData(gridData1);
		
		List<User> userList = delegate.getUsers();
		for(User user : userList){
			if(user.getDeleted()==null||user.getDeleted()==0){
				TableItem item = new TableItem(table,SWT.NONE);
				item.setText(0, user.getUserId()+"");
				item.setText(1, user.getUsername()+"");
				if(user.getCreateTime()!=null){
					item.setText(2,user.getCreateTime().toString() );
				}
				if(user.getLastLoginTime()!=null){
					item.setText(2,user.getLastLoginTime().toString() );
				}
				item.setData("user",user);
			}
		}
		
		Group group2 = new Group(comp,SWT.NONE);
		group2.setText("");
		group2.setLayoutData(gridData2);
		group2.setLayout(new GridLayout(1,false));
		Button b1 = new Button(group2,SWT.NONE);
		b1.setLayoutData(gridData3);
		b1.setText("New");
		b1.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				WizardDialog dlg = new WizardDialog(Display.getCurrent().getActiveShell(), new NewUserWizard());
				dlg.open();
				table.removeAll();
				List<User> users =  delegate.getUsers();
				for(User user : users){
					if(user.getDeleted()==null||user.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, user.getUserId()+"");
						item.setText(1, user.getUsername()+"");
						item.setText(2, user.getCreateTime().toString());
						item.setText(3, user.getLastLoginTime().toString());
						item.setData("user",user);
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		Button b3 = new Button(group2,SWT.NONE);
		b3.setLayoutData(gridData3);
		b3.setText("Delete");
		b3.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelection().length==0){
					MessageDialog.openError(parent.getShell(), 
							"Error", "Please select a item first");
					return;
				}
				boolean b = MessageDialog.openConfirm(parent.getShell(), 
						"Confirm Delete Item",
						"Confirm to delete the item and can not be recovered");
				if(b){
					User usr = (User) table.getSelection()[0].getData("user");
					usr.setDeleted(1);
					delegate.updateUser(usr);
					table.removeAll();
					List<User> users =  delegate.getUsers();
					for(User user : users){
						if(user.getDeleted()==null||user.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0, user.getUserId()+"");
							item.setText(1, user.getUsername()+"");
							item.setText(2, user.getCreateTime().toString());
							item.setText(3, user.getLastLoginTime().toString());
							item.setData("user",user);
						}
					}
				}
				else return;
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		return parent;
	}

}
