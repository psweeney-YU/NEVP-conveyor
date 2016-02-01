package ou.edu.herbarium.splash;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.splash.EclipseSplashHandler;

import ou.edu.herbarium.util.ImageFactory;
 
@SuppressWarnings("restriction")
public class SplashHandler extends EclipseSplashHandler {
 
	private static final int BORDER = 10;
	private Image image;
 
	public SplashHandler() {
		super();
	}
 
	@Override
	public void init(Shell splash) {
		super.init(splash);
 
		//here you could check some condition on which decoration to show
 
		
		image = ImageFactory.loadImage(Display.getCurrent(), ImageFactory.SPLASH);
		if (image !=null) {
			System.out.println("loading spalsh");
			final int xposition = splash.getSize().x - image.getImageData().width - BORDER;
			final int yposition = BORDER;
			getContent().addPaintListener (new PaintListener () {
				public void paintControl (PaintEvent e) {
					e.gc.drawImage (image, xposition, yposition);
				}
			});
		}
	}
 
	@Override
	public void dispose() {
		super.dispose();
		if (image != null)
			image.dispose();
	}
 
}