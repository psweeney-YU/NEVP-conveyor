package org.nevp.herbarium.dialog;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import 	org.eclipse.swt.custom.CCombo;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Institute;
import org.nevp.herbarium.wsclient.System;
import org.nevp.herbarium.wsclient.SystemSetting;
import org.nevp.herbarium.wsclient.User;

public class SystemSelectDialog extends Dialog{

	ArrayList<SystemIns> ps;
	Combo combo;
	Combo combo2;
	ArrayList<SystemSetting> settingList;
	String workspace = "";
	public static final int LOGIN_ID = 0;
	public static final int CANCEL_ID = 1;
	private Button buttonCmapare;
	protected SystemSelectDialog(Shell parentShell, ArrayList<SystemIns> ps) {
		super(parentShell);
		this.ps = ps;
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
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 20;
		gridData3.minimumWidth = 50;
		new Label(group,SWT.NONE).setText("Platform:");
		combo = new Combo(group,SWT.NONE);
		combo.setLayoutData(gridData);
		new Label(group,SWT.NONE).setText("Setting:");
		combo2 = new Combo(group,SWT.NONE);
		combo2.setLayoutData(gridData);
		final DataUtilsService service = new DataUtilsService();
		final DataUtilsDelegate delegate = service.getDataUtilsPort();
		for(SystemIns ins : ps){
			combo.add(ins.getInstituteName()+ "system #"+ins.getSystem().getSystemId());
		}
		combo.setLayoutData(gridData);
		combo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = combo.getSelectionIndex();
				String itemstr = combo.getItem(index);
				int systemId = Integer.parseInt(itemstr.split("\\#")[1]);
				Activator.systemId = systemId;
				
				combo2.removeAll();
				settingList = new ArrayList<SystemSetting>();
				List<SystemSetting> settings = delegate.getSettings(systemId);
				
				for(SystemSetting set :settings){
					settingList.add(set);
					combo2.add(set.getSettingDate().toString()+" #"+set.getSettingId());
					
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		combo2.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = combo2.getSelectionIndex();
				String itemstr = combo2.getItem(index);
				Integer settingId = Integer.parseInt(itemstr.split("#")[1].trim());
				Activator.settingId = settingId;
				final DataUtilsService service = new DataUtilsService();
				final DataUtilsDelegate delegate = service.getDataUtilsPort();
				Activator.sysSetting = delegate.getSettings(settingId).get(0);
				workspace = settingList.get(index).getWorkspacePath();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,LoginDialog.LOGIN_ID,"select",true);//MessageDialog
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(LoginDialog.LOGIN_ID == buttonId){
    		close();
		}else if(LoginDialog.CANCEL_ID == buttonId){
			close();
		}
		//get workspace from setting ID
		
		try {
			Platform.getInstanceLocation().release();
			Platform.getInstanceLocation().set(new URL("file", null, workspace), false);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
