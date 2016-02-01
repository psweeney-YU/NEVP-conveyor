package ou.edu.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import ou.edu.herbarium.dialog.LoginDialog;

public class NewSessionAction  extends Action  {
	public NewSessionAction() {
		super("Log out",null);
		this.setId("NewSessionAction");
	}

	public void run(){
		PlatformUI.getWorkbench().restart();
	}
}
