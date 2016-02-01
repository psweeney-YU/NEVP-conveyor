package ou.edu.herbarium;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import ou.edu.herbarium.views.Monitor;
import ou.edu.herbarium.views.Panel;
import ou.edu.herbarium.views.View;


public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
//		IFolderLayout Navigate = layout.createFolder("Navigate",
//		IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
//		Navigate.addPlaceholder(View.ID + ":*");
//		Navigate.addView(View.ID);
//		
//		
//		
//		IFolderLayout Panels = layout.createFolder("Panels",
//		IPageLayout.RIGHT, 0.8f, IPageLayout.ID_EDITOR_AREA);
//		Panels.addPlaceholder(Panel.ID + ":*");
//		Panels.addView(Panel.ID);
//		
//		IFolderLayout monitor = layout.createFolder("Monitors",
//		IPageLayout.BOTTOM, 0.8f, IPageLayout.RIGHT);
//		monitor.addPlaceholder(Monitor.ID + ":*");
//		monitor.addView(Monitor.ID);
		
		
	}
}
