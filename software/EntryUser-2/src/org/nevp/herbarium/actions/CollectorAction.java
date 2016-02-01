package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import org.nevp.herbarium.dialog.CollectorDialog;

public class CollectorAction  extends Action{

	public CollectorAction() {
		super("Collector",null);
		this.setId("CollectorAction");//8081
	}

	public void run(){
		CollectorDialog d = new CollectorDialog(Display.getDefault().getActiveShell());
		d.open();
	}
}