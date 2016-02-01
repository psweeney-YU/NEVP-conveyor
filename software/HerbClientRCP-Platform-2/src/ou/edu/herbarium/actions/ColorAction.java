package ou.edu.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import ou.edu.herbarium.dialog.CollectorDialog;
import ou.edu.herbarium.dialog.ColorDialog;

public class ColorAction extends Action{
	public ColorAction() {
		super("Color Setting",null);
		this.setId("ColorAction");
	}

	public void run(){
		ColorDialog d = new ColorDialog(Display.getDefault().getActiveShell());
		d.open();
	}
}
