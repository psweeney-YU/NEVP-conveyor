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
import org.eclipse.swt.widgets.Combo;
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
import ou.edu.herbarium.wsclient.Institute;
import ou.edu.herbarium.wsclient.System;
public class PlatformDialog extends Dialog{
	public PlatformDialog(Shell parent) {
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
		
		final DataUtilsService service = new DataUtilsService();
		final DataUtilsDelegate delegate = service.getDataUtilsPort();
		final Combo combo = new Combo(comp,SWT.BORDER);
		combo.setLayoutData(gridData0);
		List<Institute> ins = delegate.getInstitutes();
		for(Institute inss : ins){
			combo.add(inss.getInstituteName()+"(#"+inss.getInstituteId()+")");
		}
		Group group1 = new Group(comp,SWT.NONE);
		group1.setText("System Information");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(1,false));
		final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		combo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = combo.getSelectionIndex();
				String itemText = combo.getItem(index);
				itemText = itemText.split("\\(")[1];
				itemText = itemText.replaceAll("\\)", "");
				itemText = itemText.replaceAll("#", "").trim();
				int insId = Integer.parseInt(itemText);
				List<System> syss =  delegate.getSystems(insId);
				table.removeAll();
				for(System sys : syss){
					if(sys.getDeleted()==null||sys.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, sys.getSystemId()+"");
						item.setText(1, sys.getSystemDescription());
						item.setData("System",sys);
						item.setData("Institute",insId);
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
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
				int index = combo.getSelectionIndex();
				String itemText = combo.getItem(index);
				String insName = itemText.split("\\(")[0];
				itemText = itemText.split("\\(")[1];
				itemText = itemText.replaceAll("\\)", "");
				itemText = itemText.replaceAll("#", "").trim();
				int insId = Integer.parseInt(itemText);
				SystemNewDialog dialog = new SystemNewDialog(Display.getDefault().getActiveShell(),insId, insName,null);
				dialog.open();
				table.removeAll();
				List<System> syss =  delegate.getSystems(insId);
				table.removeAll();
				for(System sys : syss){
					if(sys.getDeleted()==null||sys.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, sys.getSystemId()+"");
						item.setText(1, sys.getSystemDescription());
						item.setData("System",sys);
						item.setData("Institute",insId);
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
				int index = combo.getSelectionIndex();
				String itemText = combo.getItem(index);
				String insName = itemText.split("\\(")[0];
				itemText = itemText.split("\\(")[1];
				itemText = itemText.replaceAll("\\)", "");
				itemText = itemText.replaceAll("#", "").trim();
				int insId = Integer.parseInt(itemText);
				if(table.getSelection().length==0){
					MessageDialog.openError(parent.getShell(), 
							"Error", "Please select a item first");
					return;
				}
				System sys = (System)table.getSelection()[0].getData("System");
				SystemNewDialog dialog = new SystemNewDialog(Display.getDefault().getActiveShell(),insId, insName,sys);
				dialog.open();
				table.removeAll();
				List<System> syss =  delegate.getSystems(insId);
				table.removeAll();
				for(System sys2 : syss){
					if(sys2.getDeleted()==null||sys2.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, sys2.getSystemId()+"");
						item.setText(1, sys2.getSystemDescription());
						item.setData("System",sys2);
						item.setData("Institute",insId);
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button b4 = new Button(group2,SWT.NONE);
		b4.setLayoutData(gridData3);
		b4.setText("Settings....");
		b4.addSelectionListener(new SelectionListener(){
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelection().length==0){
					MessageDialog.openError(parent.getShell(), 
							"Error", "Please select a item first");
					return;
				}
				System sys = (System) table.getSelection()[0].getData("System");
				SettingDialog d = new SettingDialog(Display.getDefault().getActiveShell(),sys);
				d.open();
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
				int index = combo.getSelectionIndex();
				String itemText = combo.getItem(index);
				String insName = itemText.split("\\(")[0];
				itemText = itemText.split("\\(")[1];
				itemText = itemText.replaceAll("\\)", "");
				itemText = itemText.replaceAll("#", "").trim();
				int insId = Integer.parseInt(itemText);
				TableItem select = table.getSelection()[0];
				System selectIns = (System)select.getData("System");
				boolean b = MessageDialog.openConfirm(parent.getShell(), "Confirm delete", 
						"Delete select item, and could not be recovered.");
				if(b==true){
					selectIns.setDeleted(1);
					delegate.updateSystem(selectIns, insId);
					table.removeAll();
					List<System> syss =  delegate.getSystems(insId);
					table.removeAll();
					for(System sys2 : syss){
						if(sys2.getDeleted()==null||sys2.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0, sys2.getSystemId()+"");
							item.setText(1, sys2.getSystemDescription());
							item.setData("System",sys2);
							item.setData("Institute",insId);
						}
					}
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
