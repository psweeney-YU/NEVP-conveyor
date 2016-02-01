package org.nevp.herbarium.dialog;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.nevp.herbarium.xml.XmlTool;
import org.nevp.herbarium.xml.XmlToolException;
import org.w3c.dom.Element;


public class ColorDialog  extends Dialog{
	public ColorDialog(Shell parentShell) {
		super(parentShell);
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Color Setting");
		
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
		
		File configureFile = new File(Platform.getInstallLocation().getURL().getPath()+"/configure.xml");

		XmlTool xml = new XmlTool();
		try {
			xml.initialize(configureFile);
			System.out.println(xml.getRoot());
			Element color = xml.selectElement("system/color");
			System.out.println(color.getTagName());
			
			Group group1 = new Group(comp,SWT.NONE);
			group1.setText("");
			group1.setLayoutData(gridData);
			group1.setLayout(new GridLayout(1,false));
			final Table table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
			table.setHeaderVisible(true);
			TableColumn column = new TableColumn(table,SWT.NONE);
			column.setWidth(150);
			column.setText("Name");
			TableColumn column2 = new TableColumn(table,SWT.NONE);
			column2.setWidth(100);
			column2.setText("Coordinate X");
			TableColumn column3 = new TableColumn(table,SWT.NONE);
			column3.setWidth(100);
			column3.setText("Coordinate Y");
			TableColumn column4 = new TableColumn(table,SWT.NONE);
			column4.setWidth(75);
			column4.setText("Red");
			TableColumn column5 = new TableColumn(table,SWT.NONE);
			column5.setWidth(75);
			column5.setText("Blue");
			TableColumn column6 = new TableColumn(table,SWT.NONE);
			column6.setWidth(75);
			column6.setText("Green");
			
			TableColumn column7 = new TableColumn(table,SWT.NONE);
			column7.setWidth(50);
			column7.setText("");
			
			for(int i=0;i<color.getChildNodes().getLength();i++){
				if(color.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
					Element target = (Element)color.getChildNodes().item(i);
					TableItem item = new TableItem(table,SWT.NONE);
					item.setText(0, target.getAttribute("name"));
					item.setText(1, target.getAttribute("coordinatex"));
					item.setText(2, target.getAttribute("coordinatey"));
					item.setText(3, target.getAttribute("red"));
					item.setText(4, target.getAttribute("green"));
					item.setText(5, target.getAttribute("blue"));
				}
			}

			table.setLayoutData(gridData1);
			
		} catch (XmlToolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comp;
	}
	
}
