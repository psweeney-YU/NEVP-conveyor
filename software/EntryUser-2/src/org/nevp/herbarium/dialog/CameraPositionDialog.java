package org.nevp.herbarium.dialog;

import java.util.List;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.nevp.herbarium.Activator;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Institute;
import org.nevp.herbarium.wsclient.System;
public class CameraPositionDialog extends Dialog{
	public static final int LOGIN_ID = 0;
	public static final int CANCEL_ID = 1;
	private Button buttonCmapare;
	private Button buttonCancel;
	Combo combo;
	
	public CameraPositionDialog(Shell parent) {
		super(parent);
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Platform");
		comp.setLayout(new GridLayout(2,false));//setLayoutData
		
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
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
		
		combo = new Combo(comp,SWT.BORDER);
		combo.setLayoutData(gridData0);
		combo.add(" ");
		combo.add("1");
		combo.add("2");
		combo.add("3");
		combo.add("4");
		combo.add("5");
		combo.add("6");
		combo.add("7");
		combo.add("8");
		combo.select(5);
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,CameraPositionDialog.LOGIN_ID,"Set",true);
		buttonCancel = createButton(parent,CameraPositionDialog.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(CameraPositionDialog.LOGIN_ID == buttonId){
			Activator.cameraPosition = combo.getSelectionIndex();
    		close();
		}else if(CameraPositionDialog.CANCEL_ID == buttonId){
			close();
		}
	}
}
