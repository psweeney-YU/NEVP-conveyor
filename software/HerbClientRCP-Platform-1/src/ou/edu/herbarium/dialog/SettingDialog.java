package ou.edu.herbarium.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
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

import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.System;
import ou.edu.herbarium.wsclient.SystemSetting;
public class SettingDialog extends Dialog{

	System sys;
	protected SettingDialog(Shell parentShell,System sys) {
		super(parentShell);
		this.sys = sys;
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Setting");
		comp.setLayout(new GridLayout(2,false));//setLayoutData
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;//Activate
		gridData.grabExcessVerticalSpace = true;
		gridData.minimumHeight = 300;
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
		gridData1.minimumHeight = 250;
		gridData1.minimumWidth = 400;
		
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.minimumHeight = 300;
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
		group1.setText("Setting Information");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(1,false));
		final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		column.setText("Setting");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		column2.setText("Date");
		
		table.setLayoutData(gridData1);
		
		List<SystemSetting> settingList = delegate.getSettings(sys.getSystemId());
		for(SystemSetting setting : settingList){
			if(setting.getDeleted()==null||setting.getDeleted()==0){
				TableItem item = new TableItem(table,SWT.NONE);
				item.setText(0,"setting "+setting.getSettingId());
				item.setText(1,setting.getSettingDate().toString());
				item.setData("setting",setting);
			}
		}
		
		Group group2 = new Group(comp,SWT.NONE);
		group2.setText("Operation");
		group2.setLayoutData(gridData2);
		group2.setLayout(new GridLayout(1,false));
		Button b1 = new Button(group2,SWT.NONE);
		b1.setLayoutData(gridData3);
		b1.setText("New");
		b1.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				SettingNewDialog dialog = new SettingNewDialog(Display.getDefault().getActiveShell(),sys,null);
				dialog.open();
				table.removeAll();
				List<SystemSetting> syss =  delegate.getSettings(sys.getSystemId());
				table.removeAll();
				for(SystemSetting sys : syss){
					if(sys.getDeleted()==null||sys.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0,"setting "+sys.getSettingId());
						item.setText(1,sys.getSettingDate().toString());
						item.setData("setting",sys);
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		Button b2 = new Button(group2,SWT.NONE);
		b2.setLayoutData(gridData3);
		b2.setText("Edit");
		b2.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				TableItem selectedItem = table.getSelection()[0];
				SystemSetting setting = (SystemSetting)selectedItem.getData("setting");
				SettingNewDialog dialog = new SettingNewDialog(Display.getDefault().getActiveShell(),sys,setting);
				dialog.open();
				table.removeAll();
				List<SystemSetting> syss =  delegate.getSettings(sys.getSystemId());
				table.removeAll();
				for(SystemSetting sys : syss){
					if(sys.getDeleted()==null||sys.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0,"setting "+sys.getSettingId());
						item.setText(1,sys.getSettingDate().toString());
						item.setData("setting",sys);
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
					SystemSetting sys1 = (SystemSetting) table.getSelection()[0].getData("setting");
					sys1.setDeleted(1);
					delegate.updateSystemSetting(sys1, sys);
					table.removeAll();
					List<SystemSetting> syss =  delegate.getSettings(sys.getSystemId());
					table.removeAll();
					for(SystemSetting sys : syss){
						if(sys.getDeleted()==null||sys.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0,"setting "+sys.getSettingId());
							item.setText(1,sys.getSettingDate().toString());
							item.setData("setting",sys);
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
