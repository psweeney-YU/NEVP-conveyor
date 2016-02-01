package ou.edu.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import ou.edu.herbarium.dialog.InstituteDialog;

public class InstituteAction  extends Action{

	public InstituteAction() {
		super("Institute",null);
		this.setId("InstituteAction");
	}

	public void run(){
		InstituteDialog d = new InstituteDialog(Display.getDefault().getActiveShell());
		d.open();
	}
}
