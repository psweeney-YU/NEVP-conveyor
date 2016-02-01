package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import org.nevp.herbarium.dialog.UserDialog;
import org.nevp.herbarium.wizards.NewUserWizard;



public class NewUserAction  extends Action {
	private IWorkbenchWindow window;
	/**
	 * 
	 * @param image
	 */
	public NewUserAction() {
		super("User",null);
		this.setId("NewUserAction");
	}
	/**
	 * 
	 */
	public void run() {
		Shell s = Display.getCurrent().getActiveShell();
		System.out.println(s);
		UserDialog dlg = new UserDialog(Display.getCurrent().getActiveShell());
//		WizardDialog dlg = new WizardDialog(Display.getCurrent().getActiveShell(), new NewUserWizard());
		dlg.open();
	}
}
