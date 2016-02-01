package org.nevp.herbarium.view2;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.sharedata.TransferData8;
import org.nevp.herbarium.util.ImageFactory;
import org.nevp.herbarium.wsclient.Collector;
import org.nevp.herbarium.wsclient.SpecCollectorMap;


import org.nevp.herbarium.dialog.StatisticsDialog;
import org.nevp.herbarium.editors.ImageEditor;
import org.nevp.herbarium.editors.input.MultiEditorInput;
import org.nevp.herbarium.editors.input.XMLEditorInput;
import org.nevp.herbarium.editors.xml.XMLEditor;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Folder;
import org.nevp.herbarium.wsclient.Specimen;

public class View extends ViewPart {
	public static final String ID = "TestView.view";
	private Gallery gallery;
	private Action statisticsAction;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public static void updateGallery(List<Folder> folders, final Gallery gallery,
			String type,final DataUtilsDelegate delegate){
		gallery.removeAll();
		int tag = 0;
		
		for(Folder f : folders){
			
			int number = delegate.getSpecimenNumberByFolder(f.getFolderId());
			if(number!=0){
				final GalleryItem item = new GalleryItem(gallery, SWT.NONE);
				item.setItemCount(number);
				item.setText("#"+f.getFolderId()+" "+f.getFolderName());
				item.setData("folder",f);
			}
//			if(type.equals("missing")){
//				if(spec.getMissingInfo()==null||spec.getMissingInfo()!=1){
//					continue;
//				}
//			}
//			else if(type.equals("error")){
//				if(spec.getImageError()==null||spec.getImageError()!=1){
//					continue;
//				}
//			}
			
//			boolean added = false;
//			Folder f = delegate.getParent(spec.getSpecimenId());
//			spec.setUrl(spec.getFolderName()+"/"+spec.getBarcode());
//			for(GalleryItem item : gallery.getItems()){
//				String itemQR = item.getText();
//				if(itemQR.equals(spec.getFolderName())){
//					item.setExpanded(true);
//					GalleryItem imageItem = new GalleryItem(item,0, SWT.NONE);
//					if(spec.getHandler()!=null&&spec.getHandler().length!=0)
//					imageItem.setImage( new Image(Display.getCurrent(),
//							new ByteArrayInputStream(spec.getHandler())));
//					imageItem.setText( spec.getBarcode());
//					if(spec.getMissingInfo()!=null&&spec.getMissingInfo()==1){
//						imageItem.setBackground(new Color(Display.getCurrent(),255,255,200));
//					}
//					if(spec.getImageError()!=null&&spec.getImageError()==1){
//						imageItem.setBackground(new Color(Display.getCurrent(),255,200,200));
//					}
//					System.out.println(spec.getBarcode());
//					System.out.println(spec.getScientificName());
//					imageItem.setData(spec);
//					imageItem.setData("path",spec.getImagePath());
//					
//					GalleryItem [] items = new GalleryItem [] {imageItem};
//					gallery.setSelection(items);
//					added = true;
//					break;
//				}
//			}
//			if(added==false){
//				GalleryItem item = new GalleryItem(gallery, SWT.NONE);
//				System.out.println(item.getText());
//				item.setText(spec.getFolderName());
//				System.out.println("TAG="+tag);
//				tag++;
//				item.setText("DefaultFolder");
//				item.setExpanded(true);
//				GalleryItem imageItem = new GalleryItem(item,0, SWT.NONE);
//				if(spec.getHandler()!=null&&spec.getHandler().length!=0){
//					imageItem.setImage( new Image(Display.getCurrent(),
//							new ByteArrayInputStream(spec.getHandler())));
//				}
//				imageItem.setText( spec.getBarcode());
//				imageItem.setData(spec);
//				if(spec.getMissingInfo()!=null&&spec.getMissingInfo()==1){
//					imageItem.setBackground(new Color(Display.getCurrent(),255,255,200));
//				}
//				if(spec.getImageError()!=null&&spec.getImageError()==1){
//					imageItem.setBackground(new Color(Display.getCurrent(),255,200,200));
//				}
//				imageItem.setData("path",spec.getImagePath());
//				GalleryItem [] items = new GalleryItem [] {imageItem};
//				gallery.setSelection(items);
//			}
		}
		gallery.addTreeListener(new TreeListener(){

			@Override
			public void treeCollapsed(TreeEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void treeExpanded(TreeEvent e) {
				// TODO Auto-generated method stub
				GalleryItem itm = (GalleryItem)e.item;
				Folder f = (Folder)itm.getData("folder");
				int folderID = f.getFolderId();
				List<Specimen> specimenList = delegate.getSpecimensByFolder(folderID);
				for(Specimen s : specimenList){
					GalleryItem imageItem = new GalleryItem(itm,0, SWT.NONE);
					if(s.getHandler()!=null&&s.getHandler().length!=0){
						imageItem.setImage( new Image(Display.getCurrent(),
								new ByteArrayInputStream(s.getHandler())));
					}
					imageItem.setText( s.getBarcode());
					if(s.getMissingInfo()!=null&&s.getMissingInfo()==1){
						imageItem.setBackground(new Color(Display.getCurrent(),255,255,200));
					}
					if(s.getImageError()!=null&&s.getImageError()==1){
						imageItem.setBackground(new Color(Display.getCurrent(),255,200,200));
					}
					System.out.println(s.getBarcode());
					System.out.println(s.getScientificName());
					imageItem.setData(s);
					imageItem.setData("path",s.getImagePath());
				}
				itm.setItemCount(specimenList.size());
			}
			
		});
	}
	
	public void createToolbar(final Composite parent) {
        IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
        statisticsAction = new Action("statistics"){
        	public void run(){
        		StatisticsDialog dialog = new StatisticsDialog(parent.getShell());
        		dialog.open();
        	}
        };
        statisticsAction.setImageDescriptor(
        		ImageDescriptor.createFromImage(
        				ImageFactory.loadImage(Display.getCurrent(), ImageFactory.STATISTICS)));
        mgr.add(statisticsAction);
	}
	
	public void createPartControl(Composite parent) {
		
		createToolbar(parent);
		final Display display = Display.getCurrent();
		parent.setLayout(new GridLayout());
		final GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumHeight = 900;
		data.minimumWidth = 250;
		parent.setLayoutData(data);
		
		final GridData data2 = new GridData();
		data2.grabExcessHorizontalSpace = true;
		data2.grabExcessVerticalSpace = true;
		data2.minimumHeight = 25;
		data2.minimumWidth = 250;
		
		final Combo combo = new Combo(parent,SWT.NONE);
		combo.setLayoutData(data2);
		combo.add("all");
		combo.add("missing information");
		combo.add("image error");
		
		gallery = new Gallery(parent,SWT.BORDER | SWT.V_SCROLL | SWT.VIRTUAL);
		gallery.setSize(210, 550);
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setItemSize(190, 225);
		gr.setAutoMargin(true);
		
		gr.setMinMargin(1);
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		ir.setDropShadows(true);
		ir.setDropShadowsSize(10);

		gallery.setGroupRenderer(gr);
		gallery.setItemRenderer(ir);
		gallery.setVirtualGroups(true);
		gallery.setLayoutData(data);
		
		gallery.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				Point pt = new Point(e.x,e.y);
				final GalleryItem item = gallery.getItem(pt);
				if(item!=null){
					Specimen spec = (Specimen)item.getData();
					MultiEditorInput input = new MultiEditorInput(spec);
					XMLEditorInput input2 = new XMLEditorInput();
					IWorkbenchPage page = getViewSite().getWorkbenchWindow().getActivePage();
					try {
						boolean flag = false;
						for(IEditorReference ref : page.getEditorReferences()){
							if(ref.getEditor(false).getTitle().equals(input.getName())){
								flag = true;
								page.activate(ref.getEditor(false));
								break;
							}
						}
						if(!flag)
							page.openEditor(input, ImageEditor.ID);
						{
//							page.openEditor(input2, XMLEditor.ID);
						}
					} catch (PartInitException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					for(IViewReference p:getViewSite().getWorkbenchWindow().getActivePage().getViewReferences()){
						if(p.getPartName().trim().equals("Panel")){
							IViewPart v = p.getView(false);
							Composite cc = null;
							for(Control c : v.getViewSite().getShell().getChildren()){
								System.out.println(c.getClass().getName());
								if(c.getClass().getName().equals("org.eclipse.swt.widgets.Composite")){
									cc = (Composite)c;
									break;
								}
							}
							if(cc!=null){
								cc.setLayout(new GridLayout());
								final GridData data = new GridData();
								data.grabExcessHorizontalSpace = true;
								data.grabExcessVerticalSpace = true;
								data.minimumHeight = 920;
								data.minimumWidth = 250;
								cc.setLayoutData(data);
								final Table table = new Table(cc, SWT.V_SCROLL
										| SWT.H_SCROLL|SWT.FULL_SELECTION);
								table.setHeaderVisible(true);
								table.setLayoutData(new GridData());
								
								TableColumn column = new TableColumn(table,SWT.NONE);
								column.setWidth(250);
								column.setText("Name");
								TableColumn column2 = new TableColumn(table,SWT.NONE);
								column2.setWidth(300);
								column2.setText("Data");
								TableColumn column3 = new TableColumn(table,SWT.NONE);
								column3.setWidth(200);
	//							table.setLayoutData(gridData);
								final TableItem itemName = new TableItem(table,SWT.NONE);
								itemName.setText(0,"Scientific Name:");
								final TableItem itemFamily = new TableItem(table, SWT.NONE);
								itemFamily.setText(0, "Family:");
								itemFamily.setData("setFamily");
								final TableItem itemGenus = new TableItem(table, SWT.NONE);
								itemGenus.setText(0, "Genus:");
								itemGenus.setData("setGenus");
								final TableItem itemSpecies = new TableItem(table, SWT.NONE);
								itemSpecies.setText(0, "Species:");
								itemSpecies.setData("setSpecies");
								final TableItem itemCollectAt = new TableItem(table, SWT.NONE);
								itemCollectAt.setText(0, "Collect At:");
								itemCollectAt.setData("setCountry");
								final TableItem itemCollectAtDarwin = new TableItem(table, SWT.NONE);
								itemCollectAtDarwin.setText(0, "Name in Darwin's time:");
								itemCollectAtDarwin.setData("setDcountry");
								final TableItem itemSheetNote = new TableItem(table, SWT.NONE);
								itemSheetNote.setText(0, "Sheet Notes:");
								itemSheetNote.setData("setSheetNotes");
								final TableItem itemState = new TableItem(table, SWT.NONE);
								itemState.setText(0, "State:");
								itemState.setData("setStateProvince");
								final TableItem itemTown = new TableItem(table, SWT.NONE);
								itemTown.setText(0, "Town:");
								itemTown.setData("setTown");
								
								itemName.setText(1,spec.getScientificName());
//								itemFamily.setText(1, spec.getFamily());
								itemGenus.setText(1, spec.getGenus());
								itemSpecies.setText(1, spec.getSpecificEpithet());
								itemCollectAt.setText(1, spec.getCountry());
								itemCollectAtDarwin.setText(1, spec.getDarwinCountry());
								itemSheetNote.setText(1, spec.getSheetNotes());
								itemState.setText(1, spec.getStateProvince());
								itemTown.setText(1, spec.getTown());
							}
						}
					}
				}
			}
		});
		
		DataUtilsService service = new DataUtilsService();
		DataUtilsDelegate delegate = service.getDataUtilsPort();
		final List<Folder> folders = delegate.getFolders();
		System.out.println("folder="+folders.size());
		final GalleryItem currentSession = new GalleryItem(gallery, SWT.NONE);
		
		
		updateGallery(folders, gallery, "",delegate);
		combo.addSelectionListener(new SelectionListener(){
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
//				if(combo.getSelectionIndex()==1){
//					updateGallery(specimens,gallery,"missing");
//				}
//				else if(combo.getSelectionIndex()==2){
//					updateGallery(specimens,gallery,"error");
//				}
			}
			
		});
		
		Thread monitorPicture = new Thread() {
			//Acquire insert specimen
			public void run () {
				while(true){
					if (gallery.isDisposed())
						return;
//					final TransferData td = sdata.getShareChar();
					final TransferData8 data8 = Activator.d8.getShareData();
					display.asyncExec(new Runnable() {
						public void run() {
							boolean added = false;
							Specimen spec = data8.getSpec();
							spec.setFolderPath(data8.getOriginalFilePath());
							Collector collector = new Collector();
							String [] tmpArray = spec.getTempData().split("#");
							String name = tmpArray[0];
							String RecordNum = "";
							if(tmpArray.length>1){
								RecordNum = spec.getTempData().split("#")[1];
							}
							collector.setCollectorNo(RecordNum);
							collector.setCollectorFullName(name);
							SpecCollectorMap scm = new SpecCollectorMap();
							scm.setCollector(collector);
							spec.getSpecCollectorMaps().add(scm);
							String qrcode = data8.getQrCode();
							currentSession.setExpanded(true);
							GalleryItem imageItem = new GalleryItem(currentSession,0, SWT.NONE);
							imageItem.setImage( new Image(Display.getCurrent(),
									new ByteArrayInputStream(data8.getByte4())));
							imageItem.setText( spec.getBarcode());
							imageItem.setData(spec);
							if(spec.getMissingInfo()!=null&&spec.getMissingInfo()==1){
								
								imageItem.setBackground(new Color(Display.getCurrent(),255,255,200));
							}
							if(spec.getImageError()!=null&&spec.getImageError()==1){
								imageItem.setBackground(new Color(Display.getCurrent(),255,200,200));
							}
							imageItem.setData("path",spec.getImagePath());
//								}
//							}
//							if(added==false){
//								GalleryItem item = new GalleryItem(gallery, SWT.NONE);
//								item.setText(qrcode);
//								item.setExpanded(true);
//								GalleryItem imageItem = new GalleryItem(item, 0,SWT.NONE);
//								imageItem.setImage( new Image(Display.getCurrent(),
//										new ByteArrayInputStream(data8.getByte4())));
//								imageItem.setText( spec.getBarcode());
//								imageItem.setData(spec);
//								if(spec.getMissingInfo()!=null&&spec.getMissingInfo()==1){
//									
//									imageItem.setBackground(new Color(Display.getCurrent(),255,255,200));
//								}
//								if(spec.getImageError()!=null&&spec.getImageError()==1){
//									imageItem.setBackground(new Color(Display.getCurrent(),255,200,200));
//								}
//								imageItem.setData("path",spec.getImagePath());
//								GalleryItem [] items = new GalleryItem [] {imageItem};
//								gallery.setSelection(items);
//							}
						}
					});
				}
			}
		};
		monitorPicture.start();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}



	public Gallery getGallery() {
		return gallery;
	}



	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
}