package ou.edu.herbarium.dialog;

import java.util.UUID;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ou.edu.herbarium.wsclient.Collector;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;

public class CollectorNewDialog extends Dialog{

	public static final int SUBMIT_ID = 0;
	public static final int CANCEL_ID = 1;
	private Text collectorName;
	private Text collectorInfo;
	private Collector col;
	private Button buttonCmapare;
	private Button buttonCancel;
	
	public CollectorNewDialog(Shell parentShell, Collector col) {
		super(parentShell);
		this.col = col;
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Create Collector");//delete
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
		
		new Label(group,SWT.NONE).setText("Collector Name:");
		collectorName = new Text(group,SWT.BORDER | SWT.SINGLE);
		collectorName.setLayoutData(gridData);
		new Label(group,SWT.NONE).setText("Collector Information:");
		collectorInfo = new Text(group,SWT.BORDER  | SWT.SINGLE);
		collectorInfo.setLayoutData(gridData2);
		
		if(this.col!=null){
			if(col.getCollectorFullName()!=null)
				collectorName.setText(col.getCollectorFullName());
			if(col.getCollectorInfo()!=null)
				collectorInfo.setText(col.getCollectorInfo());
		}
		
		
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
			if(this.col!=null){
				if(collectorName.getText()!=null&&collectorName.getText().equals("")==false)
					col.setCollectorFullName(collectorName.getText());
				if(collectorInfo.getText()!=null&&collectorInfo.getText().equals("")==false)
					col.setCollectorInfo(collectorInfo.getText());
				delegate.updateCollector(col);
			}
			else{
				Collector collector = new Collector();
				if(collectorInfo.getText()!=null&&collectorInfo.getText().equals("")==false)
					collector.setCollectorInfo(collectorInfo.getText());
				if(collectorName.getText()!=null&&collectorName.getText().equals("")==false)
					collector.setCollectorFullName(collectorName.getText());
				collector.setGuid(UUID.randomUUID().toString());
				delegate.insertCollector2(collector);
			}
			close();
		}else if(InstituteNewDialog.CANCEL_ID == buttonId){
			close();
		}
	}

	public Text getCollectorName() {
		return collectorName;
	}

	public void setCollectorName(Text collectorName) {
		this.collectorName = collectorName;
	}

	public Text getCollectorInfo() {
		return collectorInfo;
	}

	public void setCollectorInfo(Text collectorInfo) {
		this.collectorInfo = collectorInfo;
	}

}
