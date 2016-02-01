package ou.edu.herbarium.editors;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.part.EditorPart;
import org.w3c.dom.Element;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.actions.SyncIDropAction;
import ou.edu.herbarium.editors.input.MultiEditorInput;
import ou.edu.herbarium.util.ImageFactory;
import ou.edu.herbarium.views.View;
import ou.edu.herbarium.wsclient.Collection;
import ou.edu.herbarium.wsclient.Collector;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.SpecCollectorMap;
import ou.edu.herbarium.wsclient.Specimen;
import ou.edu.herbarium.xml.XmlTool;
import ou.edu.herbarium.xml.XmlToolException;
import ou.edu.herbarium.dialog.CollectorModDialog;

public class MetaDataEditor extends EditorPart{

	public static final String ID = "ou.edu.herbarium.MetaDataEditor";
	private FormToolkit toolkit;
	private Form form;
	Specimen spec;
	String specimenBarcode = "";
	private Table table;
	private SelectionListener tableSelectionListener = null;
	private SelectionListener collectionSelectionListener = null;
	private SelectionListener buttonSelectionListener = null;
	private Listener tableListener = null;
	private Listener tableListener2 = null;
	private XmlTool configXml;
	
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		File configureFile = new File(Platform.getInstallLocation().getURL().getPath()+"/configure.xml");
		this.configXml = new XmlTool();
		try {
			this.configXml.initialize(configureFile);
		} catch (XmlToolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
		MultiEditorInput mi = (MultiEditorInput)input;
		this.spec = mi.getInput();
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createForm(parent);
		form.setText("MetaData Editor:");
		
		toolkit.decorateFormHeading(form);
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.minimumWidth = 200;
//		FormLayout gridLayout = new FormLayout();
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = 5;
		form.getBody().setLayout(gridLayout);
		
		
		FormData formDate = new FormData();
		formDate.height = 250;
		formDate.width = 300;
		
		FormData formDate0 = new FormData();
		formDate0.height = 150;
		formDate0.width = 300;
		FormData formDate2 = new FormData();
		formDate2.height = 550;
		formDate2.width = 550;
		
		Section section = toolkit.createSection(form.getBody(),  Section.TITLE_BAR);
		section.setText("Attribute Modifier");
		Composite sectionClient = toolkit.createComposite(section);
		sectionClient.setLayout(new FormLayout());
		section.setClient(sectionClient);
		
		GridData gridData2 = new GridData();
		gridData2.verticalSpan = 2;
		section.setLayoutData(gridData2);
		
		Section section2 = toolkit.createSection(form.getBody(),  Section.TITLE_BAR);
		section2.setText("Collector");
		Composite sectionClient2 = toolkit.createComposite(section2);
		sectionClient2.setLayout(new FormLayout());
		section2.setClient(sectionClient2);
		
		Section section3 = toolkit.createSection(form.getBody(),  Section.TITLE_BAR);//printStackTrace
		section3.setText("Collection Code");
		Composite sectionClient3 = toolkit.createComposite(section3);
		sectionClient3.setLayout(new FormLayout());
		section3.setClient(sectionClient3);
		
		final Table table2 = toolkit.createTable(sectionClient2, SWT.V_SCROLL
				| SWT.H_SCROLL|SWT.FULL_SELECTION | SWT.NONE);
		table2.setHeaderVisible(true);
		
		final Button submitCollectionCode = toolkit.createButton(sectionClient2, "submit", SWT.None);
		
		
		TableColumn column01 = new TableColumn(table2,SWT.NONE);
		column01.setWidth(25);
		column01.setText("#");
		TableColumn column02 = new TableColumn(table2,SWT.NONE);
		column02.setWidth(100);
		column02.setText("Name");
		TableColumn column03 = new TableColumn(table2,SWT.NONE);
		column03.setWidth(100);
		column03.setText("Collection No");
		table2.setLayoutData(formDate);
		int index = 1;
		for(SpecCollectorMap map : spec.getSpecCollectorMaps()){
			Collector c = map.getCollector();
			TableItem item = new TableItem(table2, SWT.FULL_SELECTION);
			item.setData("collector",c);
			item.setText(0, index+"");
			index++;
			item.setText(1,c.getCollectorFullName());//c.exe
			if(spec.getRecordNumber()==null){
				item.setText(2,"");
			}
			else item.setText(2,spec.getRecordNumber());
			
			table2.setSelection(index);
			
		}
		
		table2.addListener(SWT.MouseDoubleClick, new Listener(){
			@Override
			public void handleEvent(Event arg0) {
				Point pt = new Point(arg0.x,arg0.y);
				int ret;
				for(final TableItem item : table2.getItems()){
					
					for (int i=0; i<table2.getColumnCount (); i++) {
						Rectangle rect = item.getBounds (i);
						if(rect.contains(pt)){
							Collector c = (Collector)item.getData("collector");
							CollectorModDialog dialog = new CollectorModDialog(Display.getDefault().getActiveShell(),c);
							ret = dialog.open();
						}
					}
				}
			}
		});
		final Table table3 = toolkit.createTable(sectionClient3, SWT.V_SCROLL
				| SWT.H_SCROLL|SWT.FULL_SELECTION | SWT.NONE);
		table3.setHeaderVisible(true);
		
		TableColumn column31 = new TableColumn(table3,SWT.NONE);
		column31.setWidth(25);
		column31.setText("#");
		TableColumn column32 = new TableColumn(table3,SWT.NONE);
		column32.setWidth(50);
		column32.setText("Collection Code");
		TableColumn column33 = new TableColumn(table3,SWT.NONE);
		column33.setWidth(150);
		column33.setText("Collection Info");
		table3.setLayoutData(formDate0);
		
		Collection ccc = this.spec.getCollection();
		
		TableItem item31 = new TableItem(table3, SWT.NONE);
		item31.setText(0,"1");
		if(ccc!=null){
			if(ccc.getCollectionCode()==null) ccc.setCollectionCode("");
			if(ccc.getCollectionInfo()==null) ccc.setCollectionInfo("");
			item31.setText(1,ccc.getCollectionCode());
			item31.setText(2,ccc.getCollectionInfo());
		}
		
		
		final TableEditor collectionEditor = new TableEditor(table3);
		collectionEditor.horizontalAlignment = SWT.LEFT;
		collectionEditor.grabHorizontal = true;
		collectionEditor.minimumWidth = 50;
		collectionSelectionListener = new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Control oldEditor = collectionEditor.getEditor();
				if (oldEditor != null) oldEditor.dispose();
			}
		};
		
		table3.addSelectionListener(collectionSelectionListener);
		tableListener2 = new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
				Point pt = new Point(arg0.x,arg0.y);
				for(final TableItem item : table.getItems()){
					String editable = (String)item.getData("editable");
					if(editable.equals("true")){
					}
				}
			}
		};
		
		
		tableListener2 = new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Point pt = new Point(arg0.x,arg0.y);
				for(final TableItem item : table3.getItems()){
					
					for (int i=0; i<table3.getColumnCount (); i++) {
						Rectangle rect = item.getBounds (i);
						if(rect.contains(pt)){
							Control oldEditor = collectionEditor.getEditor();
							if (oldEditor != null) oldEditor.dispose();
							
							final Combo  newEditor = new Combo (table3, SWT.NONE);
							DataUtilsService service = new DataUtilsService();
							DataUtilsDelegate delegate = service.getDataUtilsPort();
							final List<Collection> collections = delegate.getCollections();
							for(Collection col : collections){
								newEditor.add(col.getCollectionCode());
							}
							
							newEditor.addDisposeListener(new DisposeListener(){
								@Override
								public void widgetDisposed(DisposeEvent e) {
									// TODO Auto-generated method stub
									Combo combo = (Combo)collectionEditor.getEditor();
									Collection collection = collections.get(combo.getSelectionIndex());
									collectionEditor.getItem().setText(1, collection.getCollectionCode());
									collectionEditor.getItem().setText(2, collection.getCollectionInfo());
									collectionEditor.getItem().setBackground(new Color(Display.getCurrent(),255,250,160));
									
									String methodName = (String)item.getData();
								}
							});
							
							newEditor.setFocus();
							collectionEditor.setEditor(newEditor, item, 1);
						}
					}
				}
			}
		};
		table3.addListener(SWT.MouseDoubleClick, tableListener2);
		
		final Table table = toolkit.createTable(sectionClient, SWT.V_SCROLL
				| SWT.H_SCROLL|SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLayoutData(formDate2);
		TableColumn column = new TableColumn(table,SWT.NONE);
		column.setWidth(200);
		column.setText("Name");
		TableColumn column2 = new TableColumn(table,SWT.NONE);
		column2.setWidth(250);
		column2.setText("Data");
		TableColumn column3 = new TableColumn(table,SWT.NONE);
		column3.setWidth(100);
		
		
		
//		table.setLayoutData(gridData);
/*
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
		itemFamily.setText(1, spec.getFamily());
		itemGenus.setText(1, spec.getGenus());
		itemSpecies.setText(1, spec.getSpecificEpithet());
		itemCollectAt.setText(1, spec.getCountry());
		itemCollectAtDarwin.setText(1, spec.getDarwinCountry());
		itemSheetNote.setText(1, spec.getSheetNotes());
		itemState.setText(1, spec.getStateProvince());
		itemTown.setText(1, spec.getTown());
*/
	
		try {
			Element root = this.configXml.selectElement("system/editor");
			for(int i=0;i<root.getChildNodes().getLength();i++){
				if(root.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
					Element child = (Element)root.getChildNodes().item(i);
					System.out.println("child name = "+child.getAttribute("field"));
					if(child.getAttribute("display").equals("true")){
						final TableItem itemName = new TableItem(table,SWT.NONE);
						System.out.println(itemName);
						itemName.setData("set"+child.getAttribute("field"));
						itemName.setData("editable", child.getAttribute("editable"));
						itemName.setData("type", child.getAttribute("type"));
						itemName.setText(0,child.getAttribute("name"));
						if(child.getAttribute("field").equals("RecordNumber")){
							System.out.println(child.getAttribute("editable"));
						}
						Method m;
						
						try {
							m = spec.getClass().getMethod("get"+child.getAttribute("field"));
							System.out.println("function name = "+m.getName());
							if(child.getAttribute("type").equals("date")){
								XMLGregorianCalendar cal = (XMLGregorianCalendar)m.invoke(spec);
								
								String calstr = "";
								if(cal!=null) {
									calstr = cal.toString();
									itemName.setText(1,calstr);
									itemName.setData("date",cal);
									
								}
							}
							else if(child.getAttribute("type").equals("string")){
								String str = (String)m.invoke(spec);
								if(str==null){
									str = "";
								}
								itemName.setText(1,str);
							}
							else if(child.getAttribute("type").equals("int")){
								Integer inte = (Integer)m.invoke(spec);
								itemName.setText(1,inte+"");
							}
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							System.out.println("field=="+child.getAttribute("field"));
//						e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		} catch (XmlToolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final TableEditor editor = new TableEditor(table);
		//The editor must have the same size as the cell and must
		//not be any smaller than 50 pixels.
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN = 1;
		tableSelectionListener = new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) oldEditor.dispose();
			}
		};
		
		
		
		final Button submitButton = toolkit.createButton(form.getBody(), "submit", SWT.None);
		submitButton.setEnabled(false);
		final Label label2 = new Label(form.getBody(),SWT.NONE);
		label2.setData("name", "label2");
		label2.setText("Modification Save Successfully");
		label2.setFont( new Font(Display.getCurrent(),"Arial", 10, SWT.BOLD ) );
		label2.setForeground(new Color(Display.getCurrent(),0,128,64));
		label2.setVisible(false);
		
		
		
		final Label label3 = new Label(form.getBody(),SWT.NONE);
		label3.setData("name", "label2");
		label3.setText("Modification Save Error!");
		label3.setFont( new Font(Display.getCurrent(),"Arial", 10, SWT.BOLD ) );
		label3.setForeground(new Color(Display.getCurrent(),128,0,0));
		label3.setVisible(false);
		
		final Label label4 = new Label(form.getBody(),SWT.NONE);
		label4.setData("name", "label4");
		label4.setFont( new Font(Display.getCurrent(),"Arial", 10, SWT.BOLD ) );
		label4.setForeground(new Color(Display.getCurrent(),200,0,0));
		label4.setVisible(false);
		
		final TableEditor edit = new TableEditor(table);
		//The editor must have the same size as the cell and must
		//not be any smaller than 50 pixels.
		edit.horizontalAlignment = SWT.LEFT;
		edit.grabHorizontal = true;
		edit.minimumWidth = 50;
		// editing the second column
		tableSelectionListener = new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) oldEditor.dispose();
			}
		};
		table.addSelectionListener(tableSelectionListener);
		tableListener = new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
				Point pt = new Point(arg0.x,arg0.y);
				for(final TableItem item : table.getItems()){
					String editable = (String)item.getData("editable");
					if(editable.equals("true")){
						for (int i=0; i<table.getColumnCount (); i++) {
							Rectangle rect = item.getBounds (i);
							if(rect.contains(pt)){
								Control oldEditor = editor.getEditor();
								if (oldEditor != null) oldEditor.dispose();
								
								if(item.getData("type").equals("int")){
									Text newEditor = new Text(table, SWT.NONE);
									newEditor.setText(item.getText(EDITABLECOLUMN));
									newEditor.addDisposeListener(new DisposeListener(){
										@Override
										public void widgetDisposed(
												DisposeEvent arg0) {
											// TODO Auto-generated method stub
											Text text = (Text)editor.getEditor();
											editor.getItem().setText(EDITABLECOLUMN, text.getText());
											String methodName = (String)item.getData();
											for(Method m : spec.getClass().getMethods()){
												if(m.getName().equals(methodName)){
													try {
														m.invoke(spec, new Object[] {  Integer.parseInt(text.getText()) });
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													break;
												}
											}
											
											item.setBackground(new Color(Display.getCurrent(),255,250,160));
											item.setData("modified","true");
											submitButton.setEnabled(true);
										}
									});
									newEditor.selectAll();
									newEditor.setFocus();
									editor.setEditor(newEditor, item, EDITABLECOLUMN);
									break;
								}
								
								if(item.getData("type").equals("string")){
									Text newEditor = new Text(table, SWT.NONE);
									newEditor.setText(item.getText(EDITABLECOLUMN));
									newEditor.addDisposeListener(new DisposeListener(){
										@Override
										public void widgetDisposed(
												DisposeEvent arg0) {
											// TODO Auto-generated method stub
											Text text = (Text)editor.getEditor();
											editor.getItem().setText(EDITABLECOLUMN, text.getText());
											String methodName = (String)item.getData();
											for(Method m : spec.getClass().getMethods()){
												if(m.getName().equals(methodName)){
													try {
														m.invoke(spec, new Object[] {  text.getText() });
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													break;
												}
											}
											
											item.setBackground(new Color(Display.getCurrent(),255,250,160));
											item.setData("modified","true");
											submitButton.setEnabled(true);
										}
									});
									newEditor.selectAll();
									newEditor.setFocus();
									editor.setEditor(newEditor, item, EDITABLECOLUMN);
									break;
								}
								else if(item.getData("type").equals("date")){
									CalendarCombo calendar = new CalendarCombo (table, SWT.CALENDAR);
									XMLGregorianCalendar cal = (XMLGregorianCalendar)item.getData("date");
									if(cal==null){
										calendar.setDate(new Date(System.currentTimeMillis()));
									}
									else{
										GregorianCalendar ca = cal.toGregorianCalendar();
										calendar.setDate(ca.getTime());
									}
//									
									calendar.addDisposeListener(new DisposeListener(){
										@Override
										public void widgetDisposed(
												DisposeEvent arg0) {
											// TODO Auto-generated method stub
											CalendarCombo cc = (CalendarCombo)editor.getEditor();
											editor.getItem().setText(EDITABLECOLUMN, cc.getDateAsString());
											
											String methodName = (String)item.getData();
											for(Method m : spec.getClass().getMethods()){
												if(m.getName().equals(methodName)){
													try {
														GregorianCalendar gc = new GregorianCalendar();
														gc.setTime(cc.getDate().getTime());
														XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
														m.invoke(spec, new Object[] {  date2 });
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (DatatypeConfigurationException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													break;
												}
											}
											
											item.setBackground(new Color(Display.getCurrent(),255,250,160));
											item.setData("modified","true");
											submitButton.setEnabled(true);
										}
									});
									
									calendar.setFocus();
									editor.setEditor(calendar, item, EDITABLECOLUMN);
									break;
								}
								
							}
						}//for
					}
				}
			}
		};
		table.addListener(SWT.MouseDoubleClick, tableListener);
		submitButton.setLayoutData(gridData);
		buttonSelectionListener = new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				try{
					DataUtilsService service = new DataUtilsService();//Missing
					DataUtilsDelegate delegate = service.getDataUtilsPort();
					Specimen ss = delegate.getSpecimenById(spec.getSpecimenId());
					List<SpecCollectorMap> scms = delegate.getScms(spec.getSpecimenId());
//					System.out.println(ss.getSpecimenId()+"***"+scms.size());
					if(spec==null){
						System.out.println("NULL ID!");
					}
					if(scms==null){
						System.out.println("NULL SCMS!");
					}
					String scmIds = "";
					int index = 0;
					for(SpecCollectorMap scm : scms){
						if(index==0){
							scmIds+=scm.getSpecCollectorMapId();
						}
						else{
							scmIds+=("-"+scm.getSpecCollectorMapId());
						}
						index++;
					}
					System.out.println("scmIds = "+scmIds);
					System.out.println("specimen Id = "+spec.getSpecimenId());
					if(scmIds.equals("")) scmIds = "0";
					Specimen updatedSpecimen = delegate.updateSpecimen(spec,scmIds);
					for(TableItem item : table.getItems()){
						item.setBackground(new Color(Display.getCurrent(),255,255,255));
						submitButton.setEnabled(false);
					}
					IWorkbenchPage pages[] = getEditorSite().getWorkbenchWindow().getPages();
					for(IWorkbenchPage p : pages){
						IViewReference ivrs [] = p.getViewReferences(); 
						for(IViewReference ivr : ivrs){
							if(ivr.getId().equals("TestView.view")){
								View v = (View)ivr.getView(true);
								v.getGallery().getSelection()[0].setData("spec", spec);
								if(spec.getMissingInfo()==0){
									v.getGallery().getSelection()[0].setBackground(new Color(Display.getCurrent(), 255,255,255));
								}
								break;
							}
						}
					}
					label2.setVisible(true);
					label3.setVisible(false);
					label4.setVisible(false);
				}catch(Exception e){
					label2.setVisible(false);
					label3.setVisible(true);
//					label4.setText(e.getMessage());
					label4.setVisible(true);
					e.printStackTrace();
				}
			}
		};//scientific name inserting
		submitButton.addSelectionListener(buttonSelectionListener);
		System.out.println(Platform.getInstallLocation().getURL().getPath());
		System.out.println(Platform.getInstanceLocation().getURL().getPath());
		Image image = ImageFactory.loadImage(Display.getCurrent(), ImageFactory.ACTION_SYNC);
		IStatusLineManager manager = this.getEditorSite().getActionBars().getStatusLineManager();
		Action toggleBotton = new SyncIDropAction("Sync with iDrop", ImageDescriptor.createFromImage(image),manager);
	    if(spec.getIdropSync()==null||spec.getIdropSync()==0){
	    	toggleBotton.setEnabled(true);
	    }
	    else toggleBotton.setEnabled(true);
	    form.getToolBarManager().add(toggleBotton);
		form.getToolBarManager().update(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
