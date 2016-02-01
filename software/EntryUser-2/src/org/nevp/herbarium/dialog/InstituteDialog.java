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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Institute;

public class InstituteDialog extends Dialog{
	public InstituteDialog(Shell parent) {
		super(parent);
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Institute");
		comp.setLayout(new GridLayout(2,false));//setLayoutData
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.minimumHeight = 300;
		gridData.minimumWidth = 400;
		
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
		
		Group group1 = new Group(comp,SWT.NONE);
		group1.setText("Institute Information");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(1,false));
		final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		final DataUtilsService service = new DataUtilsService();
		final DataUtilsDelegate delegate = service.getDataUtilsPort();
		List<Institute> inss =  delegate.getInstitutes();
		for(Institute i : inss){
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(0, i.getInstituteName());
			item.setText(1, i.getInstituteInfo());
			item.setData("Institute",i);
		}
		table.setLayoutData(gridData1);
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
				InstituteNewDialog dialog = new InstituteNewDialog(Display.getDefault().getActiveShell(),null);
				dialog.open();
				table.removeAll();
				List<Institute> inss =  delegate.getInstitutes();
				for(Institute i : inss){
					TableItem item = new TableItem(table,SWT.NONE);
					item.setText(0, i.getInstituteName());
					item.setText(1, i.getInstituteInfo());
					item.setData("Institute",i);
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
				if(table.getSelection().length==0){
					MessageDialog.openError(parent.getShell(), 
							"Error", "Please select a item first");
					return;
				}
				TableItem select = table.getSelection()[0];
				Institute selectIns = (Institute)select.getData("Institute");
				InstituteNewDialog dialog = new InstituteNewDialog(Display.getDefault().getActiveShell(),selectIns);
				dialog.open();
				table.removeAll();
				List<Institute> inss =  delegate.getInstitutes();
				for(Institute i : inss){
					TableItem item = new TableItem(table,SWT.NONE);
					item.setText(0, i.getInstituteName());
					item.setText(1, i.getInstituteInfo());
					item.setData("Institute",i);
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
				TableItem select = table.getSelection()[0];
				Institute selectIns = (Institute)select.getData("Institute");
				MessageDialog.openConfirm(parent.getShell(), "Confirm delete", 
						"Delete select item, and could not be recovered.");
				delegate.deleteInstitute(selectIns);
				table.removeAll();
				List<Institute> inss =  delegate.getInstitutes();
				for(Institute i : inss){
					TableItem item = new TableItem(table,SWT.NONE);
					item.setText(0, i.getInstituteName());
					item.setText(1, i.getInstituteInfo());
					item.setData("Institute",i);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return parent;
	}
}
