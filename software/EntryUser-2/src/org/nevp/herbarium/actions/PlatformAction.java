package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import org.nevp.herbarium.dialog.InstituteDialog;
import org.nevp.herbarium.dialog.PlatformDialog;

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
