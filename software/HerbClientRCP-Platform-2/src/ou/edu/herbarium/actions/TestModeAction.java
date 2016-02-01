package ou.edu.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.dialog.PlatformDialog;
import ou.edu.herbarium.dialog.TestModeDialog;

public class TestModeAction  extends Action {
	
	public TestModeAction() {
		super("Test Mode",null);
		this.setId("PlatformAction");
		this.setChecked(false);
		Activator.testmode = 0;
	}
	
	public void run(){
		TestModeDialog dialog = new TestModeDialog(Display.getDefault().getActiveShell());
		int ret = dialog.open();
		if(ret==0){
			if(Activator.testmode==1) Activator.testmode = 0;
			else Activator.testmode = 1;
		}
	}
}
