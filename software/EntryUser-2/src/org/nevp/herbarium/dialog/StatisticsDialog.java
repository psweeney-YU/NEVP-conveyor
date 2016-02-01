package org.nevp.herbarium.dialog;

import java.sql.Timestamp;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.swtchart.Chart;
import org.swtchart.IBarSeries;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;

import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Institute;

public class StatisticsDialog  extends Dialog{

	int start = 0;
	public static final int SUBMIT_ID = 0;
	public static final int CANCEL_ID = 1;
	public static final int PRE_ID = 2;
	public static final int NEXT_ID = 3;
	private Text insName;
	private Text insInfo;
	private Table table;
	private Institute ins;
	private Button buttonCmapare;
	private Button buttonCancel;
	private Button buttonPre;
	private Button buttonNext;
	private Group group1;
	private Group group2;
	public StatisticsDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Statistics");
		comp.setLayout(new GridLayout(3,false));//setLayoutData
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.minimumHeight = 300;
		gridData.minimumWidth = 400;
		
		
		
		
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.minimumHeight = 300;
		gridData2.minimumWidth = 320;
		
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.BEGINNING;
		gridData3.verticalAlignment = GridData.BEGINNING;
		gridData3.grabExcessHorizontalSpace = true;
//		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 25;
		gridData3.minimumWidth = 100;
		
		
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.minimumHeight = 250;
		gridData1.minimumWidth = 400;
		
		
		group1 = new Group(comp,SWT.NONE);
		group1.setText("Chart");
		group1.setLayoutData(gridData);
		group1.setLayout(new FillLayout());
		
		
		
		group2 = new Group(comp,SWT.NONE);
		group2.setText("Information");
		group2.setLayoutData(gridData2);
		group2.setLayout(new GridLayout(1,false));
		
		table = new Table(group2, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(350);
		column.setText("Week");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(70);
		column2.setText("Number");
		table.setLayoutData(gridData1);
		
		createChart(start);
		
		return parent;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,SUBMIT_ID,"OK",true);
		buttonCancel = createButton(parent,CANCEL_ID,"Cancel",false);
		buttonPre = createButton(parent,PRE_ID,"Previous",true);
		buttonNext = createButton(parent,NEXT_ID,"Next",false);
		if(start==0) buttonNext.setEnabled(false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(NEXT_ID == buttonId){
			start--;
			createChart(start);
			if(start==0){
				buttonNext.setEnabled(false);
			}
		}
		else if(PRE_ID == buttonId){
			start++;
			createChart(start);
			buttonNext.setEnabled(true);
		}
		else if(SUBMIT_ID == buttonId){
			
		}else if(CANCEL_ID == buttonId){
			close();
		}
	}
	
	public void createChart(int start){
		
		
		Control [] ctrls1 = group1.getChildren();
		for(Control c : ctrls1){
			c.dispose();
		}
		
		final DataUtilsService service = new DataUtilsService();
		final DataUtilsDelegate delegate = service.getDataUtilsPort();
		double [] values = new double[7];
		String [] tags = new String [7];
		java.util.Date date= new java.util.Date();
		for(int i=start; i<start+7; i++){
			java.util.Date date1 = new java.util.Date(System.currentTimeMillis()-(7*(i)*24*3600*1000));
			java.util.Date date2 = new java.util.Date(System.currentTimeMillis()-(7*(i+1)*24*3600*1000));
			tags[i-start] = date1.toLocaleString()+" ~ "+date2.toLocaleString();
			values[i-start] = delegate.getSpecimensByWeek(i, i+1).size();
		}
		
		
		
		Chart chart = new Chart(group1, SWT.NONE);

        // set titles
        chart.getTitle().setText("Line Chart");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Data Points");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Amplitude");

        // create line series
        IBarSeries lineSeries = (IBarSeries) chart.getSeriesSet()
                .createSeries(SeriesType.BAR, "line series");
        lineSeries.setYSeries(values);

        // adjust the axis range
        chart.getAxisSet().adjustRange();
        
        //table
        
        table.removeAll();
		
        
		for(int i=0;i<7;i++){
			TableItem item = new TableItem(table,SWT.FULL_SELECTION);
			item.setText(0, tags[i]);
			item.setText(1,values[i]+"");
//			if(delegate.getSpecimensByWeek(i, i+1).size()==0) break;
		}

		
	}
}