package ou.edu.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import ou.edu.herbarium.dialog.InstituteDialog;
import ou.edu.herbarium.dialog.PlatformDialog;

public class PlatformAction  extends Action{

	public PlatformAction() {
		super("Platform",null);
		this.setId("PlatformAction");
	}

	public void run(){
		PlatformDialog d = new PlatformDialog(Display.getDefault().getActiveShell());
		d.open();
	}
}
