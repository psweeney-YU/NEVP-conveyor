package org.nevp.herbarium.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;
import org.nevp.herbarium.views.NavigationView;
import org.nevp.herbarium.views.View;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "HerbRCPClient.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(View.ID + ":*");
		folder.addView(View.ID);
//		folder.addPlaceholder(Console.ID + ":*");
//		folder.addView(Console.ID);
		
		IViewLayout vLayout = layout.getViewLayout(View.ID);  
		vLayout.setCloseable(false);
		
	}
}
