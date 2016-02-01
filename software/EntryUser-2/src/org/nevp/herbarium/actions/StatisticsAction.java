package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import org.nevp.herbarium.dialog.StatisticsDialog;

public class StatisticsAction  extends Action{
	
	public StatisticsAction(){
		super("Statistics",null);
		this.setId("StatisticsAction");
	}
	
	public void run(){
		StatisticsDialog dialog = new StatisticsDialog(Display.getDefault().getActiveShell());
		dialog.open();
	}
}
