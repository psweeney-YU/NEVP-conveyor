package org.nevp.herbarium.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.nevp.herbarium.Activator;
import org.nevp.herbarium.application.ICommandIds;
import org.nevp.herbarium.application.Perspective;
import org.nevp.herbarium.dialog.CameraPositionDialog;
import org.nevp.herbarium.views.View;


public class SwitchPerspectiveAction extends Action {

    private final IWorkbenchWindow window;
    private int currentStatus = 0;
    public SwitchPerspectiveAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_SWITCH);
    }

    public void run() {
    	if(Activator.userId!=-1){

    		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	    	IViewPart page = window.getPages()[0].getViews()[0];
	    	CameraPositionDialog dialog = new CameraPositionDialog(page.getViewSite().getShell());
	    	dialog.open();
	    	View view = (View)page;
	    	for(Group g : view.getGroup1x()){
	    		g.setText("");
	    	}
	    	view.getGroup1x()[Activator.cameraPosition].setText("Camera");
	    	
    	}
    	else{
    		MessageDialog.openError(Display.getCurrent().getActiveShell(),
    				"Error", "Please Login First");
    	}
    }
}