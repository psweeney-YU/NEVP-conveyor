package ou.edu.herbarium.dialog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.Institute;
import ou.edu.herbarium.wsclient.SystemSetting;
import ou.edu.herbarium.wsclient.System;

public class SettingNewDialog extends Dialog{
	private SelectionListener tableSelectionListener = null;
	private Listener tableListener = null;
	SystemSetting setting;
	System system;
	public static final int SUBMIT_ID = 0;
	public static final int CANCEL_ID = 1;
	private Button buttonCmapare;
	private Button buttonCancel;
	private Table table;
	
	protected SettingNewDialog(Shell parentShell, System system,SystemSetting setting) {
		super(parentShell);
		this.setting = setting;
		this.system = system;
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Setting");
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
		gridData1.minimumHeight = 450;
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
		
		Group group1 = new Group(comp,SWT.NONE);
		group1.setText("");
		group1.setLayoutData(gridData);
		group1.setLayout(new GridLayout(1,false));
		table = new Table(group1, SWT.FULL_SELECTION | SWT.BORDER);
		TableColumn tc0 = new TableColumn(table,SWT.NONE);
		tc0.setText("Name");
		tc0.setWidth(200);
		TableColumn tc1 = new TableColumn(table,SWT.NONE);
		tc1.setText("Value");
		tc1.setWidth(400);
		final TableItem installationUrl = new TableItem(table, SWT.FULL_SELECTION);
		installationUrl.setData("name","installationUrl");
		installationUrl.setText(0,"Install Directory");
		
		final TableItem workspace = new TableItem(table, SWT.FULL_SELECTION);
		workspace.setData("name","workspace");
		workspace.setText(0,"Workspace");
		
		final TableItem cameraName = new TableItem(table, SWT.FULL_SELECTION);
		cameraName.setData("name","cameraName");
		cameraName.setText(0,"Camera Name");
		
		final TableItem expoTime = new TableItem(table, SWT.FULL_SELECTION);
		expoTime.setData("name","expoTime");
		expoTime.setText(0,"Camera ExpoTime");
		
		final TableItem cameraAperture = new TableItem(table, SWT.FULL_SELECTION);
		cameraAperture.setData("name","cameraAperture");
		cameraAperture.setText(0,"Camera Aperture");
		
		final TableItem cameraISO = new TableItem(table, SWT.FULL_SELECTION);
		cameraISO.setData("name","cameraISO");
		cameraISO.setText(0,"Camera ISO");
		
		final TableItem positionX = new TableItem(table, SWT.FULL_SELECTION);
		positionX.setData("name","positionX");
		positionX.setText(0,"Color Target Position X");
		
		final TableItem positionY = new TableItem(table, SWT.FULL_SELECTION);
		positionY.setData("name","positionY");
		positionY.setText(0,"Color Target Position Y");
		
		final TableItem conveyorVoltage = new TableItem(table, SWT.FULL_SELECTION);
		conveyorVoltage.setData("name","conveyorVoltage");
		conveyorVoltage.setText(0,"Conveyor Voltage Threshold:");
		
		final TableItem sensorVoltage = new TableItem(table, SWT.FULL_SELECTION);
		sensorVoltage.setData("name","sensorVoltage");
		sensorVoltage.setText(0,"Sensor Voltage Threshold:");
		installationUrl.setText(1,Platform.getInstallLocation().getURL().toString());
		workspace.setText(1,Platform.getInstanceLocation().getURL().toString());
		if(this.setting!=null){
			cameraName.setText(1,setting.getCameraName());
			expoTime.setText(1,setting.getCameraExpoTime());
			cameraAperture.setText(1,setting.getCameraAperture()+"");
			cameraISO.setText(1,setting.getCameraIso()+"");
			positionX.setText(1,setting.getColorTargetPositionX()+"");
			positionY.setText(1,setting.getColorTargetPositionY()+"");
			conveyorVoltage.setText(1,setting.getConveyorVoltage()+"");
			sensorVoltage.setText(1,setting.getSensorVoltage()+"");
		}
		
		final TableEditor editor = new TableEditor(table);
		//The editor must have the same size as the cell and must
		//not be any smaller than 50 pixels.
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN = 1;
		tableSelectionListener = new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) oldEditor.dispose();
			}
		};
		
		table.addSelectionListener(tableSelectionListener);
		tableListener = new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
				Point pt = new Point(arg0.x,arg0.y);
				for(final TableItem item : table.getItems()){
					for (int i=0; i<table.getColumnCount (); i++) {
						Rectangle rect = item.getBounds (i);
						if(rect.contains(pt)){
							Control oldEditor = editor.getEditor();
							if (oldEditor != null) oldEditor.dispose();
							final Text newEditor = new Text(table, SWT.NONE);
							newEditor.setText(item.getText(EDITABLECOLUMN));
							newEditor.addDisposeListener(new DisposeListener(){
								@Override
								public void widgetDisposed(
										DisposeEvent arg0) {
									// TODO Auto-generated method stub
									item.setText(1,newEditor.getText());
									item.setBackground(new Color(Display.getCurrent(),255,250,160));
									item.setData("modified","true");
								}
							});
							
							newEditor.selectAll();
							newEditor.setFocus();
							editor.setEditor(newEditor, item, EDITABLECOLUMN);
							break;
						}
					}
				}
			}
		};
		
		table.addListener(SWT.MouseDoubleClick, tableListener);
		return parent;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonCmapare = createButton(parent,InstituteNewDialog.SUBMIT_ID,"Submit",true);
		buttonCancel = createButton(parent,InstituteNewDialog.CANCEL_ID,"Cancel",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(InstituteNewDialog.SUBMIT_ID == buttonId){
			DataUtilsService service = new DataUtilsService();
			DataUtilsDelegate delegate = service.getDataUtilsPort();
			String cameraName = null;
			String cameraExpoTime = null;
			String cameraAperture = null;
			String cameraISO = null;
			String colorTargetPositionX = null;
			String colorTargetPositionY = null;
			String conveyorVoltage = null;
			String sensorVoltage = null;
			String workspace = null;
			
			
			for(TableItem item : table.getItems()){
				if(item.getData("name").equals("cameraName")){
					cameraName = item.getText(1);
				}
				if(item.getData("name").equals("cameraExpoTime")){
					cameraExpoTime = item.getText(1);
				}
				if(item.getData("name").equals("cameraAperture")){
					cameraAperture = item.getText(1);
				}
				if(item.getData("name").equals("cameraISO")){
					cameraISO = item.getText(1);
				}
				if(item.getData("name").equals("positionX")){
					colorTargetPositionX = item.getText(1);
				}
				if(item.getData("name").equals("positionY")){
					colorTargetPositionY = item.getText(1);
				}
				if(item.getData("name").equals("conveyorVoltage")){
					conveyorVoltage = item.getText(1);
				}
				if(item.getData("name").equals("sensorVoltage")){
					sensorVoltage = item.getText(1);
				}
				if(item.getData("name").equals("workspace")){
					workspace = item.getText(1);
				}
			}
			int intCameraISO = 0;
			if(cameraISO!=null&&cameraISO.equals("")==false){
				intCameraISO = Integer.parseInt(cameraISO);
			}
			int intCameraAperture = 0;
			if(cameraAperture!=null&&cameraAperture.equals("")==false){
				intCameraAperture = Integer.parseInt(cameraAperture);
			}
			int intColorTargetPositionX = 0;
			if(colorTargetPositionX!=null&&colorTargetPositionX.equals("")==false){
				intColorTargetPositionX = Integer.parseInt(colorTargetPositionX);
			}
			int intColorTargetPositionY = 0;
			if(colorTargetPositionY!=null&&colorTargetPositionY.equals("")==false){
				intColorTargetPositionY = Integer.parseInt(colorTargetPositionY);
			}
			
			int intConveyorVoltage = 0;
			if(conveyorVoltage!=null&&conveyorVoltage.equals("")==false){
				intConveyorVoltage = Integer.parseInt(conveyorVoltage);
			}
			
			int intSensorVoltage = 0;
			if(sensorVoltage!=null&&sensorVoltage.equals("")==false){
				intSensorVoltage = Integer.parseInt(sensorVoltage);
			}
			if(cameraExpoTime==null) cameraExpoTime = "";
			if(cameraName==null) cameraName = "";
			
			if(this.setting==null){
				delegate.insertSystemSetting(
						system.getSystemId(),//TimestampAdapter
						cameraName,
						cameraExpoTime,
						intCameraISO,
						intCameraAperture,
						intColorTargetPositionX,
						intColorTargetPositionY,
						intConveyorVoltage,
						intSensorVoltage,
						workspace
					);
			}
			else{
				setting.setCameraAperture(intCameraAperture);
				setting.setCameraExpoTime(cameraExpoTime);
				setting.setCameraIso(intCameraISO);
				setting.setCameraName(cameraName);
				setting.setColorTargetPositionX(intColorTargetPositionX);
				setting.setColorTargetPositionY(intColorTargetPositionY);
				setting.setConveyorVoltage(intConveyorVoltage);
				setting.setSensorVoltage(intSensorVoltage);
				setting.setWorkspacePath(workspace);
				delegate.updateSystemSetting(setting,system);
			}
			close();
		}else if(InstituteNewDialog.CANCEL_ID == buttonId){
			close();
		}
	}

}
