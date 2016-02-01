package ou.edu.herbarium.editors.contributor;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorActionBarContributor;

public class ImageEditorContributor extends EditorActionBarContributor{
	private Action action2 ;
	private IWorkbenchWindow window;
	
	public void makeActions() {
		window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		action2 = new Action(){
			public void run() {
				System.out.println("111111");
			}
		};
	}
}
