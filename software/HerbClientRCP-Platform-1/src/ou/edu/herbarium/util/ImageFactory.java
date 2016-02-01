package ou.edu.herbarium.util;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory {

	private ImageFactory() {}
	
	public static final String ACTION_SYNC = "synced.gif";
	public static final String ANNOTATE = "annotate.gif";
	public static final String ACTIVITY = "activity.gif";
	public static final String MONITOR = "contended_monitor_obj.gif";
	public static final String SERVICE_RUN = "osprc_obj.gif";
	public static final String SERVICE_STOP = "osprct_obj.gif";
	public static final String REFRESH = "refresh_tab.gif";
	public static final String SPLASH = "splash.gif";
	public static final String STATISTICS = "all_instances.gif";
	
	public static final String INFO =  "alert_obj.gif";
	public static final String WARNING =  "warning.gif";
	public static final String ERROR =  "hprio_tsk.gif";
	public static final String PVIEW = "pview.gif";
	
    //use Hashtable to save image resources
	private static Hashtable htImage = new Hashtable();
	/**
	 * load image.get image object from htImage first
	 * if lose£¬load new image and put it into htImage
	 * @param display
	 * @param imageName
	 * @return
	 */
	
	public static Image loadImage(Display display, String imageName) {
		String INSTALL_PATH = Platform.getInstallLocation().getURL().getPath();
		Image image = (Image) htImage.get(imageName.toUpperCase());
		if (image == null) {
			image = new Image(display, INSTALL_PATH+"icons/" + imageName);
			htImage.put(imageName.toUpperCase(), image);
		}
		return image;
	}
	/**
	 * release all image resources from htImage
	 * 
	 */
	
	public static void dispose() {
		Enumeration e = htImage.elements();
		while (e.hasMoreElements()) {
			Image image = (Image) e.nextElement();
			if (!image.isDisposed())
				image.dispose();
		}
	}
}