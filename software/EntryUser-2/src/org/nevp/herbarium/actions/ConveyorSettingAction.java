package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.nevp.herbarium.Activator;

public class ConveyorSettingAction extends Action{
	public ConveyorSettingAction() {
		super("Conveyor Enabled",null);
		this.setId("ConveyorAction");//8081
	}

	public void run(){
		if(Activator.isConveyorActivate) Activator.isConveyorActivate = false;
		else Activator.isConveyorActivate = true;
		if(this.isChecked()==false) this.setChecked(true);
		else this.setChecked(true);
	}
}
