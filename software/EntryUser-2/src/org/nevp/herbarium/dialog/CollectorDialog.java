package org.nevp.herbarium.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import org.nevp.herbarium.wizards.NewUserWizard;
import org.nevp.herbarium.wsclient.Collector;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.User;

public class CollectorDialog  extends Dialog{

	public CollectorDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Collector");
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
		gridData1.minimumHeight = 400;
		gridData1.minimumWidth = 400;
		gridData1.horizontalSpan = 9;
		
		GridData gridData00 = new GridData();
		gridData00.horizontalAlignment = GridData.FILL;
		gridData00.grabExcessHorizontalSpace = true;
		gridData00.grabExcessVerticalSpace = true;
		gridData00.minimumHeight = 25;
		gridData00.minimumWidth = 400;
		gridData00.horizontalSpan = 9;
		
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
		group1.setText("Collector Information");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(9,false));
		final Text searchBar = new Text(group1,SWT.BORDER);
		searchBar.setLayoutData(gridData00);
		
		final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		column.setText("CollectorID");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		column2.setText("GUID");
		TableColumn column3 = new TableColumn(table,SWT.NONE);
		column3.setWidth(250);
		column3.setText("Name");
		
		TableColumn column4 = new TableColumn(table,SWT.NONE);
		column4.setWidth(250);
		column4.setText("Information");
		
		List<Collector> collectorList = delegate.getCollectorsByPage(1, 5);
		for(Collector collector : collectorList){
			if(collector.getDeleted()==null||collector.getDeleted()==0){
				TableItem item = new TableItem(table,SWT.NONE);
				item.setText(0, collector.getCollectorId()+"");
				item.setText(1, collector.getGuid()+"");
				item.setText(2, collector.getCollectorFullName()+"");
				if(collector.getCollectorInfo()!=null)
					item.setText(3, collector.getCollectorInfo()+"");
				item.setData("collector",collector);
			}
		}
		
		searchBar.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				table.removeAll();
				List<Collector> collectorList = delegate.getCollectorsByName(searchBar.getText());
				for(Collector collector : collectorList){
					if(collector.getDeleted()==null||collector.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, collector.getCollectorId()+"");
						item.setText(1, collector.getGuid()+"");
						item.setText(2, collector.getCollectorFullName()+"");
						if(collector.getCollectorInfo()!=null)
							item.setText(3, collector.getCollectorInfo()+"");
						item.setData("collector",collector);
					}
				}
			}
		});

		table.setLayoutData(gridData1);
//		
//		Button leftMost = new Button(group1,SWT.NONE);
//		leftMost.setText("|<");
//		Button left = new Button(group1,SWT.NONE);
//		left.setText("<");
		
		Label labelPage = new Label(group1,SWT.NONE);
		labelPage.setText("Page:");//logo
		final Text text = new Text(group1,SWT.BORDER);
		text.setSize(100, 25);
		text.setText("1");
		
//		Button go = new Button(group1,SWT.NONE);
//		go.setText("Go");
//		Button right = new Button(group1,SWT.NONE);
//		right.setText(">");
//		Button rightMost = new Button(group1,SWT.NONE);
//		rightMost.setText(">|");
		Label labelNo = new Label(group1,SWT.NONE);
		labelNo.setText("Item number per page: ");
		final Text text2 = new Text(group1,SWT.BORDER);
		text2.setText("5");
		text2.setSize(100, 25);
		
		Button b = new Button(group1,SWT.NONE);
		b.setText("GO");
		
		text.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				Integer pageNo = 0;
				Integer pageSize = 0;
				try{
					pageNo = Integer.parseInt(text.getText());
					pageSize = Integer.parseInt(text2.getText());
				}catch(Exception ex){
//					ex.printStackTrace();
					MessageDialog.openError(parent.getShell(), "ERROR", "Digital format error");
					return;
				}
				if(pageNo>0&&pageSize>0){
					table.removeAll();
					List<Collector> collectorList = delegate.getCollectorsByPage(pageNo, pageSize);
					for(Collector collector : collectorList){
						if(collector.getDeleted()==null||collector.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0, collector.getCollectorId()+"");
							item.setText(1, collector.getGuid()+"");
							item.setText(2, collector.getCollectorFullName()+"");
							if(collector.getCollectorInfo()!=null)
								item.setText(3, collector.getCollectorInfo()+"");
							item.setData("collector",collector);
						}
					}
				}
				else{
					MessageDialog.openError(parent.getShell(), "ERROR", "Digital format error");
					return;
				}
			}
		});
		
		text2.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				Integer pageNo = 0;
				Integer pageSize = 0;
				try{
					pageNo = Integer.parseInt(text.getText());
					pageSize = Integer.parseInt(text2.getText());
				}catch(Exception ex){
//					ex.printStackTrace();
					MessageDialog.openError(parent.getShell(), "ERROR", "Digital format error");
					return;
				}
				if(pageNo>0&&pageSize>0){
					table.removeAll();
					List<Collector> collectorList = delegate.getCollectorsByPage(pageNo, pageSize);
					for(Collector collector : collectorList){
						if(collector.getDeleted()==null||collector.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0, collector.getCollectorId()+"");
							item.setText(1, collector.getGuid()+"");
							item.setText(2, collector.getCollectorFullName()+"");
							if(collector.getCollectorInfo()!=null)
								item.setText(3, collector.getCollectorInfo()+"");
							item.setData("collector",collector);
						}
					}
				}
				else{
					MessageDialog.openError(parent.getShell(), "ERROR", "Digital format error");
					return;
				}
			}
		});
		Group group2 = new Group(comp,SWT.NONE);
		group2.setText("Opration");
		group2.setLayoutData(gridData2);
		group2.setLayout(new GridLayout(1,false));
		Button b1 = new Button(group2,SWT.NONE);
		b1.setLayoutData(gridData3);
		b1.setText("New");
		b1.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				CollectorNewDialog dlg = new CollectorNewDialog(Display.getCurrent().getActiveShell(),null);
				dlg.open();
				table.removeAll();
				List<Collector> collectorList = delegate.getCollectors();
				for(Collector collector : collectorList){
					if(collector.getDeleted()==null||collector.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, collector.getCollectorId()+"");
						item.setText(1, collector.getGuid()+"");
						item.setText(2, collector.getCollectorFullName()+"");
						if(collector.getCollectorInfo()!=null)
							item.setText(3, collector.getCollectorInfo()+"");
						item.setData("collector",collector);
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
				if(table.getSelection().length==0){
					MessageDialog.openError(parent.getShell(), 
							"Error", "Please select a item first");
					return;
				}
				
				TableItem selected = table.getSelection()[0];
				Collector coll = (Collector)selected.getData("collector");
				CollectorNewDialog dlg = new CollectorNewDialog(Display.getCurrent().getActiveShell(),coll);
				dlg.open();
				table.removeAll();
				List<Collector> collectorList = delegate.getCollectors();
				for(Collector collector : collectorList){
					if(collector.getDeleted()==null||collector.getDeleted()==0){
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(0, collector.getCollectorId()+"");
						item.setText(1, collector.getGuid()+"");
						item.setText(2, collector.getCollectorFullName()+"");
						if(collector.getCollectorInfo()!=null)
							item.setText(3, collector.getCollectorInfo()+"");
						item.setData("collector",collector);
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
					Collector col = (Collector) table.getSelection()[0].getData("collector");
					col.setDeleted(1);
					delegate.updateCollector(col);
					table.removeAll();
					List<Collector> collectorList = delegate.getCollectors();
					for(Collector collector : collectorList){
						if(collector.getDeleted()==null||collector.getDeleted()==0){
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(0, collector.getCollectorId()+"");
							item.setText(1, collector.getGuid()+"");
							item.setText(2, collector.getCollectorFullName()+"");
							if(collector.getCollectorInfo()!=null)
								item.setText(3, collector.getCollectorInfo());
							item.setData("collector",collector);
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
