package org.nevp.herbarium.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.nevp.herbarium.utils.ImageFactory;

public class NavigationView extends ViewPart {
	public static final String ID = "HerbRCPClient.navigationView";
	public static final int ITEM_NUMBER = 10;
	public static int CAMERA_POSITION = 4;
	private Gallery gallery;
	

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		gallery = new Gallery(parent,SWT.BORDER | SWT.V_SCROLL );
		gallery.setSize(600, 300);
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setItemSize(160, 210);
		gr.setAutoMargin(false);
		gr.setAlwaysExpanded(false);
		gr.setMinMargin(1);
		gr.setExpanded(false);
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		ir.setDropShadows(true);
		ir.setDropShadowsSize(10);
		gallery.setGroupRenderer(gr);
		gallery.setItemRenderer(ir);
		
		
		GalleryItem item = new GalleryItem(gallery, SWT.NONE | SWT.H_SCROLL);
		item.setItemCount(0);
		item.setExpanded(true);
		
		ArrayList<GalleryItem> items = new ArrayList<GalleryItem>();
		
		for(int i=1;i<ITEM_NUMBER;i++){
			GalleryItem imageItem = new GalleryItem(item,SWT.NONE);
			if(i==CAMERA_POSITION){
				imageItem.setBackground(new Color(Display.getCurrent(),200,200,200));
			}
			items.add(imageItem);
		}
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}