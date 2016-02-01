package org.nevp.herbarium.utils;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory {

	private ImageFactory() {}
	
	public static final String EMPTY = "empty.png";

	public static final String FOLDER = "folder.png";

	
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
			image = new Image(display, INSTALL_PATH+"images/" + imageName);
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