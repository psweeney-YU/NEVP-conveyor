package org.nevp.herbarium.view2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class Panel  extends ViewPart {

	
	public static final String ID = "TestView.panel";
	@Override
	public void createPartControl(Composite parent) {
		
		// TODO Auto-generated method stub
		final Display display = Display.getCurrent();
		parent.setLayout(new GridLayout());
		final GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumHeight = 320;
		data.minimumWidth = 250;
		
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.minimumHeight = 250;
		gridData1.minimumWidth = 400;
		
		parent.setLayoutData(data);
		Table table = new Table(parent, SWT.NONE);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(150);
		column.setText("Setting");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(50);
		column2.setText("Value");
		table.setLayoutData(gridData1);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
