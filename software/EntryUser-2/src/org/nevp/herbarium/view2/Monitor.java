package org.nevp.herbarium.view2;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;


import org.nevp.herbarium.Activator;
import org.nevp.herbarium.dialog.CameraErrorDialog;
import org.nevp.herbarium.util.ImageFactory;

public class Monitor extends ViewPart{

	public static final String ID = "TestView.monitor";
	public static TableItem cameraErrorFlag = null;
	public static Table table2;
	@Override
	public void createPartControl(final Composite parent) {
		GridLayout g = new GridLayout();
		g.numColumns = 1;
		parent.setLayout(g);
		
		final GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumHeight = 100;
		data.minimumWidth = 310;
		parent.setLayoutData(data);
		
		final GridData data2 = new GridData();
		data2.grabExcessHorizontalSpace = true;
		data2.grabExcessVerticalSpace = true;
		data2.minimumHeight = 150;
		data2.minimumWidth = 310;
		
		final GridData data3 = new GridData();
		data3.grabExcessHorizontalSpace = true;
		data3.grabExcessVerticalSpace = true;
		data3.minimumHeight = 130;
		data3.minimumWidth = 310;
		
		final GridData data33 = new GridData();
		data33.grabExcessHorizontalSpace = true;
		data33.grabExcessVerticalSpace = true;
		data33.minimumHeight = 100;
		data33.minimumWidth = 300;
		
		// TODO Auto-generated method stub
		Group group = new Group (parent, SWT.NONE);
		group.setLayout(new GridLayout());
		group.setLayoutData(data2);
		group.setText("Information");
		group.setLayoutData(data);
		table2 = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table2.setLayoutData(data33);
		table2.setHeaderVisible(false);
		
		TableColumn c01 = new TableColumn(table2,SWT.NONE);
		c01.setWidth(100);
		TableColumn c02 = new TableColumn(table2,SWT.NONE);
		c02.setWidth(200);
//		TableItem ti0 = new TableItem(table2, SWT.NONE);
//		ti0.setText(0,"Camera");
		
		
		Thread monitorError = new Thread() {
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			int id = 0;
			//Acquire insert specimen
			public void run () {
				while(true){
					final String info = Activator.de.getShareData();
//					if (table2.isDisposed())
//						return;
					
					System.out.println(info);
					final String type = info.split("::")[0].split("_")[0];
					final String name = info.split("::")[0].split("_")[1];
					final int number = Integer.parseInt(info.split("::")[0].split("_")[2]);
					if(info.startsWith("ERROR_CAMERA")){
						parent.getDisplay().asyncExec(new Runnable() {
							public void run() {
								if(cameraErrorFlag==null){
									cameraErrorFlag = new TableItem(table2,SWT.FULL_SELECTION);
									cameraErrorFlag.setImage(0,ImageFactory.loadImage(parent.getDisplay(), ImageFactory.ERROR));
									cameraErrorFlag.setText(1, "Camera Error:"+info.split("::")[1]);
								}
								CameraErrorDialog dialog = new CameraErrorDialog(Display.getCurrent().getActiveShell());
								dialog.open();
							}
						});
						continue;
					}
					else{
						if(number>1000){
							try{
								for(int i=0;i<list.size();i++){
									if(list.get(i)==number-1000){
										RemoveRunnable rr = new RemoveRunnable(i+1,table2);
										Display.getCurrent().asyncExec(rr);
										list.remove(i);
										continue;
									}
								}
							}catch(Exception e){
								e.printStackTrace();
								continue;
							}
						}
						else{
							boolean flag = false;
							for(Integer num : list){
								if(num==number&&number!=5){
									flag = true;
									break;
								}
							}
							if(flag==false){
								if(type.equals("INFO")){
									if(name.equals("CLIENT")){
										parent.getDisplay().asyncExec(new Runnable() {
											public void run() {
												TableItem ti = new TableItem(table2,SWT.FULL_SELECTION);
												ti.setImage(0,ImageFactory.loadImage(parent.getDisplay(), ImageFactory.INFO));
												ti.setText(1, "Client Connected:"+info.split("::")[1]);
												list.add(0, number);
											}
										});
									}
									else if(name.equals("CONVEYOR")){
										parent.getDisplay().asyncExec(new Runnable() {
											public void run() {
//												TableItem ti = table2.getItem(0);
//												ti.setImage(0,ImageFactory.loadImage(parent.getDisplay(), ImageFactory.PVIEW));
//												id++;
//												if(id>19) id=0;
											}
										});
										continue;
									}
								}
								else if(type.equals("ERROR")){
									if(name.equals("COLOR")){
										parent.getDisplay().asyncExec(new Runnable() {
											public void run() {
												TableItem ti = new TableItem(table2,SWT.FULL_SELECTION);
												ti.setImage(0,ImageFactory.loadImage(parent.getDisplay(), ImageFactory.WARNING));
												ti.setText(1, "Color Error:"+info.split("::")[1]);
												list.add(0, number);
											}
										});
										continue;
									}
								}
							}
						}
					}
					
				}
			}
		};
		monitorError.start();
		
		Group group2 = new Group (parent, SWT.NONE);
		group2.setLayout(new GridLayout());
		group2.setLayoutData(data2);
		Table table1 = new Table(group2, SWT.BORDER | SWT.FULL_SELECTION);
		table1.setLayoutData(data3);
		table1.setHeaderVisible(true);
//		table.setLinesVisible(false);
		TableEditor editor0 = new TableEditor (table1);
		editor0.grabHorizontal = editor0.grabVertical = true;
		TableEditor editor1 = new TableEditor (table1);
		editor1.grabHorizontal = editor1.grabVertical = true;
		TableEditor editor2 = new TableEditor (table1);
		editor2.grabHorizontal = editor2.grabVertical = true;
		
		TableEditor editor3 = new TableEditor (table1);
		editor3.grabHorizontal = editor3.grabVertical = true;
		TableEditor editor4 = new TableEditor (table1);
		editor4.grabHorizontal = editor4.grabVertical = true;
		TableEditor editor5 = new TableEditor (table1);
		editor5.grabHorizontal = editor5.grabVertical = true;
		
		TableColumn c = new TableColumn(table1,SWT.NONE);
		c.setWidth(100);
		c.setText("Agent");
		TableColumn c1 = new TableColumn(table1,SWT.NONE);
		c1.setWidth(140);
		c1.setText("Status");
		TableColumn c2 = new TableColumn(table1,SWT.NONE);
		c2.setWidth(25);
		TableColumn c3 = new TableColumn(table1,SWT.NONE);
		c3.setWidth(25);
		
		TableItem ti = new TableItem(table1, SWT.NONE);
		ti.setText(0,"Camera");
		TableItem ti1 = new TableItem(table1, SWT.NONE);
		ti1.setText(0,"DAQ Output");
		TableItem ti2 = new TableItem(table1, SWT.NONE);
		ti2.setText(0,"DAQ Input");

		Button b0start = new Button(table1,SWT.NONE);
		b0start.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.SERVICE_RUN));
		editor0.setEditor (b0start, ti, 2);
		Button b1start = new Button(table1,SWT.NONE);
		b1start.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.SERVICE_RUN));
		editor1.setEditor (b1start, ti1, 2);
		Button b2start = new Button(table1,SWT.NONE);
		b2start.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.SERVICE_RUN));
		editor2.setEditor (b2start, ti2, 2);
		
		Button b0restart = new Button(table1,SWT.NONE);
		b0restart.setBackground(new Color(Display.getCurrent(),255,255,255));
		b0restart.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.REFRESH));
		b0restart.setToolTipText("restart");
		editor3.setEditor (b0restart, ti, 3);
		
		Button b1restart = new Button(table1,SWT.NONE);
		b1restart.setBackground(new Color(Display.getCurrent(),255,255,255));
		b1restart.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.REFRESH));
		b1restart.setToolTipText("restart");
		editor4.setEditor (b1restart, ti1, 3);
		
		Button b2restart = new Button(table1,SWT.NONE);
		b2restart.setBackground(new Color(Display.getCurrent(),255,255,255));
		b2restart.setImage(ImageFactory.loadImage(Display.getCurrent(), ImageFactory.REFRESH));
		b2restart.setToolTipText("restart");
		editor5.setEditor (b2restart, ti2, 3);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}

class RemoveRunnable implements Runnable{
	
	int index;
	Table table;
	public RemoveRunnable (int i,Table t){
		this.index = i;
		this.table = t;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		table.remove(index);
	}
}

