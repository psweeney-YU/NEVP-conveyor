package org.nevp.herbarium.views;


import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;//TestTab
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.nevp.herbarium.Activator;
import org.nevp.herbarium.dialog.LoginDialog;
import org.nevp.herbarium.dialog.SystemIns;
import org.nevp.herbarium.util.ImageFactory;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Institute;
import org.nevp.herbarium.wsclient.User;
import org.nevp.herbarium.thread.*;
public class View extends ViewPart {

	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Charset ISO_8859_1 = Charset.forName("cp1252");
	public static final String ID = "HerbRCPClient.view";// all_instances
	private static final String LCL = "abcdefghijklmnopqrstuvwxyz";
	private static final String UCL = LCL.toUpperCase();
	private static final String NUMS = "0123456789";
	public StatusLineManager slm = new StatusLineManager();

	public static ArrayList<ImgItem> arrays = new ArrayList<ImgItem>();
	
	public static Socket socket;
	public static OutputStream outputStream;
	public static InputStream inputStream;
	public static int sendingCount = 0;
	public static int sentCount = -1;
	public int lastNext = 0;
	
	public static int updatePosition = Activator.cameraPosition;

	public static boolean timerEnabled = true;

	public ArrayList<DataItem> dataItems = new ArrayList<DataItem>();
	public ArrayList<TownItem> townItems = new ArrayList<TownItem>();
	public ArrayList<String> collectorItems = new ArrayList<String>();
	Group group1;
	Group [] group1x = new Group [8];

	public Group[] getGroup1x() {
		return group1x;
	}

	public void setGroup1x(Group[] group1x) {
		this.group1x = group1x;
	}

	public Group getGroup1() {
		return group1;
	}

	public void setGroup1(Group group1) {
		this.group1 = group1;
	}

	/**
	 * The text control that's displaying the content of the email message.
	 * 
	 * @throws IOException
	 */

	public void processNameList(File file) throws IOException {
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String nextLine = "";
	}
	
	final public int isNumber(String text){
		int value = -1;
		try{
			value = Integer.parseInt(text);
		}catch(Exception e){
			return -1;
		}
		return value;
	}

	final public String isMonth(String month) {
		if (month.toLowerCase().startsWith("jan") ) return "01";
		else if(month.toLowerCase().startsWith("feb") ) return "02";
		else if(month.toLowerCase().startsWith("mar") ) return "03";
		else if(month.toLowerCase().startsWith("apr") ) return "04";
		else if(month.toLowerCase().startsWith("may") ) return "05";
		else if(month.toLowerCase().startsWith("jun") ) return "06";
		else if(month.toLowerCase().startsWith("jul") ) return "07";
		else if(month.toLowerCase().startsWith("aug") ) return "08";
		else if(month.toLowerCase().startsWith("sep") ) return "09";
		else if(month.toLowerCase().startsWith("oct") ) return "10";
		else if(month.toLowerCase().startsWith("nov") ) return "11";
		else if(month.toLowerCase().startsWith("dec") ) return "12";
		else return null;
	}

	public void createPartControl(Composite parent) {

		String localDir = System.getProperty("user.dir")
				.replaceAll("\\\\", "/") + "/";
		File nameList = new File(localDir + "nameList.txt");
		File townList = new File(localDir + "townList.txt");

		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = 2;
		parent.setLayout(topLayout);

		arrays.add(new ImgItem());
//		for(int i=0;i<8;i++){
//			arrays.add(new ArrayList<StringItem>());
//		}
		
		final GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalSpan = 1;
		parent.setLayoutData(data);

		GridData data1 = new GridData();
		data1.minimumHeight = 25;
		data1.minimumWidth = 250;
		data1.horizontalAlignment = SWT.CENTER;
		data1.verticalAlignment = SWT.CENTER;
		data1.grabExcessHorizontalSpace = true;
		data1.grabExcessVerticalSpace = true;

		
		GridData data2 = new GridData();
		data2.horizontalAlignment = GridData.FILL;
		// data2.verticalAlignment = GridData.FILL;
		data2.grabExcessHorizontalSpace = true;
		data2.grabExcessVerticalSpace = true;
		data2.minimumHeight = 35;
		data2.minimumWidth = 150;
		// data2.horizontalIndent = 10;
		// data2.verticalIndent = 10;

		GridData data3 = new GridData();
		data3.grabExcessHorizontalSpace = true;
		data3.grabExcessVerticalSpace = true;
		data3.minimumWidth = 150;
		data3.minimumHeight = 45;
		data3.horizontalAlignment = GridData.END;

		GridData data4 = new GridData();
		data4.grabExcessHorizontalSpace = true;
		data4.grabExcessVerticalSpace = true;
		data4.minimumHeight = 45;
		// data4.horizontalAlignment = GridData.BEGINNING;
		// data4.verticalAlignment = GridData.BEGINNING;

		GridData gridData0 = new GridData();
		gridData0.horizontalAlignment = GridData.FILL;
		gridData0.grabExcessHorizontalSpace = true;
		gridData0.grabExcessVerticalSpace = true;
		gridData0.minimumHeight = 70;
		gridData0.minimumWidth = 900;
		gridData0.horizontalSpan = 2;

		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.minimumHeight = 350;
		gridData1.minimumWidth = 900;
		gridData1.horizontalSpan = 2;

		GridData gridData10 = new GridData();
		gridData10.horizontalAlignment = GridData.FILL;
		gridData10.verticalAlignment = GridData.FILL;
		gridData10.grabExcessHorizontalSpace = true;
		gridData10.grabExcessVerticalSpace = true;
		gridData10.minimumHeight = 30;
		gridData10.minimumWidth = 110;

		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = GridData.FILL;
		gridData11.verticalAlignment = GridData.FILL;
		gridData11.grabExcessHorizontalSpace = true;
		gridData11.grabExcessVerticalSpace = true;
		gridData11.minimumHeight = 300;
		gridData11.minimumWidth = 110;

		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.minimumHeight = 300;
		gridData2.minimumWidth = 1200;

		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.minimumHeight = 100;
		gridData4.minimumWidth = 900;
		
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = GridData.FILL;
		gridData5.grabExcessHorizontalSpace = true;
		gridData5.minimumHeight = 80;
		gridData5.minimumWidth = 100;

		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.minimumHeight = 610;
		gridData3.minimumWidth = 150;
		gridData3.verticalSpan = 4;

		final Group group0 = new Group(parent, SWT.NONE);
		group0.setLayoutData(gridData0);
		group1 = new Group(parent, SWT.NONE);
		group1.setLayoutData(gridData1);
		final Group group2 = new Group(parent, SWT.NONE);
		group2.setLayoutData(gridData2);
		final Group group3 = new Group(parent, SWT.NONE);
		group3.setLayoutData(gridData3);
		final Group group4 = new Group(parent, SWT.NONE);
		group4.setLayoutData(gridData4);

		group1.setVisible(false);
		group2.setVisible(false);
		group3.setVisible(false);
		group4.setVisible(false);
		timerEnabled = false;

		GridLayout gl = new GridLayout();
		gl.numColumns = 8;
		gl.makeColumnsEqualWidth = true;
		GridLayout g2 = new GridLayout();
		g2.numColumns = 1;

		group0.setLayout(gl);
		group1.setLayout(gl);

		final Button ciButton5 = new Button(group0, SWT.BORDER);
		ciButton5.setText("login      ");
		ciButton5.setLayoutData(gridData10);
		final Label ciLabel1 = new Label(group0, SWT.READ_ONLY);
		ciLabel1.setText("  Username:  ");
		ciLabel1.setLayoutData(gridData10);
		final Text username = new Text(group0, SWT.BORDER);
		username.setLayoutData(gridData10);
		username.setText("");
		final Label ciLabel2 = new Label(group0, SWT.READ_ONLY);
		ciLabel2.setText("  password:  ");
		ciLabel2.setLayoutData(gridData10);
		final Text password = new Text(group0, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(gridData10);
		password.setText("111111");
		final Label ciLabel33 = new Label(group0, SWT.READ_ONLY);
		ciLabel33.setText("  Server IP:  ");
		ciLabel33.setLayoutData(gridData10);
		final Text serveraddr = new Text(group0, SWT.BORDER);
		serveraddr.setLayoutData(gridData10);
		serveraddr.setText("140.247.98.169");
		
		final Label ciLabel3 = new Label(group0, SWT.READ_ONLY);
		ciLabel3.setText("  Login Fail...  ");
		ciLabel3.setLayoutData(gridData10);
		ciLabel3.setVisible(false);
// ou.edu.herbarium.thread2.FileEvent
		
		
		final Thread imageUpdate = new Thread() {
			long currentMilli;
			long index = 0;
			// Acquire insert specimen
			public void run() {
				System.out.println("secondCount start");

				currentMilli = System.currentTimeMillis();
				while (true) {
					try {
						byte[] typeAr = new byte[4];
						inputStream.read(typeAr);
				        int type = ByteBuffer.wrap(typeAr).asIntBuffer().get();
				        if(type==1){
				        	index++;
				        	byte[] sizeAr = new byte[4];
				            inputStream.read(sizeAr);
				            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
				            System.out.println("size="+size);
				            byte[] imageAr = new byte[size];
				            
				            FileOutputStream fos = new FileOutputStream("C:/temp/test_"+index+".jpg");
				            BufferedOutputStream bos = new BufferedOutputStream(fos);
				            int bytesRead = inputStream.read(imageAr, 0, imageAr.length);
				            bos.write(imageAr, 0, bytesRead);
				            bos.close();
				        }
				        else if(type==2){
				        	String info = "";
				        	byte[] sizeAr = new byte[4];
				        	inputStream.read(sizeAr);
				            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
				            
				            byte[] stringAr = new byte[size];
				            inputStream.read(stringAr);
				            info = new String(stringAr);
				            System.out.println("String received = "+info);
				          //update UI
				            if(info.equals("false")){
				            	Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										
										System.out.println("update!!!!!! " );
										View thisView = (org.nevp.herbarium.views.View) getViewSite()
												.getPage().getActivePart();
										if(thisView!=null){
											Control cs[] = thisView.group1.getChildren();
//											int updatepose = Activator.cameraPosition>sentCount ? Activator.cameraPosition : sentCount;
//											Group g = (Group) cs[updatepose];
											ImageLoader imageLoader = new ImageLoader();
											ImageData ida = imageLoader.load("C:/temp/test_"+index+".jpg")[0];
											ida = ida.scaledTo(230, 322);
											Image img = new Image(Display.getCurrent(), ida);
											for(int i=arrays.size()-1;i>0;i--){
												if(arrays.get(i).image==null){
													arrays.get(i).image = img;
													break;
												}
											}
											for(int i=0;i<8;i++){
												Control c1 [] = group1x[i].getChildren();
												for(int j=0;j<8;j++){
													Label l1 = (Label)c1[j];
													l1.setText("");
													l1.setData("done","");
												}
												group1x[i].setBackgroundImage(null);
											}
											
											for(int i=0;i<8;i++){
												Control c1 [] = group1x[i].getChildren();
												if(i>=arrays.size()) break;
												for(int j=0;j<8;j++){
													if(j>=arrays.get(i).strings.size()) break;
													Label l1 = (Label)c1[j];
													l1.setText(arrays.get(i).strings.get(j).barcode);
													l1.setForeground(arrays.get(i).strings.get(j).color);
												}
												group1x[i].setBackgroundImage(arrays.get(i).image);
											}
										}
									}
								});
				            }
				            else{
				            	Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										
										View thisView = (org.nevp.herbarium.views.View) getViewSite()
												.getPage().getActivePart();
										if(thisView!=null){
											Control cs[] = thisView.group1.getChildren();
											int updatepose = Activator.cameraPosition>sentCount ? Activator.cameraPosition : sentCount;
											Group g = (Group) cs[updatepose];
											System.out.println("update2!!!!!! " +updatepose);
											Control c1 [] = g.getChildren();
											for(int j=0;j<7;j++){
												Label l1 = (Label)c1[j];
												String done = (String)l1.getData("done");
												if(done==null||done.equals("done")==false){
													l1.setForeground(new Color(Display.getDefault(),0,128,64));
													l1.setData("done","done");
													break;
												}
											}
											
										}
									}
								});
//				            	for(int i=7;i>=0;i--){
//									Control c = g.getChildren()[i];
//									Label l = (Label)c;
//									if(l.getText().equals("")==false){
//										l.setText("");
//										break;
//									}
//								}
				            }
				        }
	////					////////////////////////////////////////////////////////////////
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		for(int i=0;i<8;i++){
			group1x[i] = new Group(group1, SWT.NONE);
			group1x[i].setLayout(g2);
			group1x[i].setLayoutData(gridData11);
			group1x[i].setBackground(new Color(Display.getDefault(),255,255,255));
			Label [] label1s = new Label [8];
			for(Label l : label1s){
				l = new Label(group1x[i], SWT.READ_ONLY);
				l.setLayoutData(gridData10);
			}
		}
			
		group1x[4].setText("Camera");
		Activator.cameraPosition = 3;

		GridLayout group2Layout = new GridLayout();
		group2Layout.numColumns = 4;
		group2Layout.makeColumnsEqualWidth = false;
		group2.setLayout(group2Layout);

		GridLayout group4Layout = new GridLayout();
		group4.setLayout(group4Layout);
		final Group group5 = new Group(group4, SWT.NONE);
		GridLayout group5Layout = new GridLayout();
		group5Layout.numColumns = 3;
		group5.setLayout(group5Layout);
		group5.setLayoutData(data3);
		Label label1 = new Label(group2, SWT.NONE);
		label1.setText("Scientific Name");
		FontData fontData = new FontData("Arial", 16, SWT.BOLD);
		FontData fontData2 = new FontData("Arial", 14, SWT.NORMAL);
		label1.setFont(new Font(Display.getCurrent(), fontData));
		final Text scientific_name = new Text(group2, SWT.BORDER);
		scientific_name.setFont(new Font(Display.getCurrent(), fontData2));
		label1.setLayoutData(data1);
		scientific_name.setLayoutData(data2);
		scientific_name.setData("json", "sn");
		Label label2 = new Label(group2, SWT.NONE);
		label2.setText("Barcode");
		label2.setFont(new Font(Display.getCurrent(), fontData));
		final Text barcode = new Text(group2, SWT.BORDER);
		label2.setLayoutData(data1);
		barcode.setLayoutData(data2);
		barcode.setData("json", "Barcode");
		barcode.setFont(new Font(Display.getCurrent(), fontData2));
		barcode.setText("");
		Label label3 = new Label(group2, SWT.NONE);
		label3.setText("Genus");
		label3.setFont(new Font(Display.getCurrent(), fontData));
		final Text genus = new Text(group2, SWT.BORDER);
		label3.setLayoutData(data1);
		genus.setLayoutData(data2);
		genus.setData("json", "g");
		genus.setText("");
		genus.setFont(new Font(Display.getCurrent(), fontData2));

		
		

		
		Label label4 = new Label(group2, SWT.NONE);
		label4.setText("Collector");
		label4.setFont(new Font(Display.getCurrent(), fontData));
		final Text collector = new Text(group2, SWT.BORDER);
		label4.setLayoutData(data1);
		collector.setLayoutData(data2);
		collector.setData("json", "cl");
		collector.setText("");
		collector.setFont(new Font(Display.getCurrent(), fontData2));
		
		Label label5 = new Label(group2, SWT.NONE);
		label5.setText("Species");
		label5.setFont(new Font(Display.getCurrent(), fontData));
		final Text species = new Text(group2, SWT.BORDER);
		label5.setLayoutData(data1);
		species.setLayoutData(data2);
		species.setData("json", "s");
		species.setText("");
		species.setFont(new Font(Display.getCurrent(), fontData2));

		Label label6 = new Label(group2, SWT.NONE);
		label6.setText("Collector Number");
		
		final Text collectorNumber = new Text(group2, SWT.BORDER);
		label6.setLayoutData(data1);
		label6.setFont(new Font(Display.getCurrent(), fontData));
		collectorNumber.setLayoutData(data2);
		collectorNumber.setData("json", "record");
		collectorNumber.setText("");
		collectorNumber.setFont(new Font(Display.getCurrent(), fontData2));

		Label label7 = new Label(group2, SWT.NONE);
		label7.setText("Infraspecific Rank");
		label7.setFont(new Font(Display.getCurrent(), fontData));
		final Text infraspecific_rank = new Text(group2, SWT.BORDER);
		label7.setLayoutData(data1);
		infraspecific_rank.setLayoutData(data2);
		infraspecific_rank.setData("json", "ir");
		infraspecific_rank.setText("");
		infraspecific_rank.setFont(new Font(Display.getCurrent(), fontData2));

		Label label8 = new Label(group2, SWT.NONE);
		label8.setText("Verbartim Collection Date");
		label8.setFont(new Font(Display.getCurrent(), fontData));
		final Text Verbartim = new Text(group2, SWT.BORDER);
		label8.setLayoutData(data1);
		Verbartim.setLayoutData(data2);
		Verbartim.setData("json", "Verbatim");
		Verbartim.setText("");
		Verbartim.setFont(new Font(Display.getCurrent(), fontData2));

		Label label9 = new Label(group2, SWT.NONE);
		label9.setText("Infraspecific Epithet");
		label9.setFont(new Font(Display.getCurrent(), fontData));
		final Text infraspecific_epithet = new Text(group2, SWT.BORDER);
		label9.setLayoutData(data1);
		infraspecific_epithet.setLayoutData(data2);
		infraspecific_epithet.setData("json", "i");
		infraspecific_epithet.setText("");
		infraspecific_epithet.setFont(new Font(Display.getCurrent(), fontData2));

		Label label10 = new Label(group2, SWT.NONE);
		label10.setText("Beginning Date");
		label10.setFont(new Font(Display.getCurrent(), fontData));
		final Text beginDate = new Text(group2, SWT.BORDER);
		label10.setLayoutData(data1);
		beginDate.setLayoutData(data2);
		beginDate.setData("json", "BeginDate");
		beginDate.setText("");
		beginDate.setFont(new Font(Display.getCurrent(), fontData2));

		Label label11 = new Label(group2, SWT.NONE);
		label11.setText("Scientific Name Author");
		label11.setFont(new Font(Display.getCurrent(), fontData));
		Text sna = new Text(group2, SWT.BORDER);
		label11.setLayoutData(data1);
		sna.setLayoutData(data2);
		sna.setData("json", "a");
		sna.setText("");
		sna.setFont(new Font(Display.getCurrent(), fontData2));
		Verbartim.addFocusListener(new FocusListener() {
			
			public String convertVerbatim(String verbartimDate){
				
				
				
				int month = 0;
				ArrayList list = new ArrayList();
				
				verbartimDate = verbartimDate.replaceAll("/"," ");
				verbartimDate = verbartimDate.replaceAll("\\,"," ");
				verbartimDate = verbartimDate.replaceAll("\\'"," ");
				verbartimDate = verbartimDate.replaceAll("th", "");
				verbartimDate = verbartimDate.replaceAll("rd", "");
				verbartimDate = verbartimDate.replaceAll("st", "");
				verbartimDate = verbartimDate.replaceAll("nd", "");
				
				String[] arrays = verbartimDate.split(" ");
				if(arrays.length==3&&arrays[0].contains("-")==false
						&&arrays[1].contains("-")==true&&arrays[2].contains("-")==false){
					verbartimDate = arrays[0]+" "+arrays[1].split("-")[1]+" "+arrays[2];
				}
				if(arrays.length==3&&arrays[0].contains("-")==true
						&&arrays[1].contains("-")==false&&arrays[2].contains("-")==false){
					verbartimDate = arrays[0].split("-")[1]+" "+arrays[1]+" "+arrays[2];
				}
				if(arrays.length==3&&arrays[0].contains("-")==false
						&&arrays[1].contains("-")==false&&arrays[2].contains("-")==true){
					verbartimDate = arrays[0]+" "+arrays[1]+" "+arrays[2].split("-")[1];
				}
				
				verbartimDate = verbartimDate.replaceAll("-"," ");
				String[] array = verbartimDate.split(" ");
				String realDate = "";
				for (int i = 0; i < array.length; i++) {
					if (array[i].trim().equals("") == false) {
						if(isMonth(array[i].trim())!=null){
							list.add(array[i].trim());
						}
						else if(romanToDecimal(array[i].trim()).equals("0")==false){
							list.add(romanToDecimal(array[i].trim()));
						}
						else{
							list.add(array[i]);
						}
					}
				}
				if (list.size() == 3) {
					if(isMonth((String) list.get(0))!=null){
						//MM-DD-YYYY
						int value1 = isNumber((String) list.get(1));
						int value2 = isNumber((String) list.get(2));
						if(value1==-1||value2==-1) {
							//Do Nothing
							return "";
						}
						else if(value1>31){
							//Do Nothing
							return "";
						}
						else{
							//Do
							String mm;
							String dd;
							String yyyy;
							mm = isMonth((String) list.get(0));
							if(value1<10) dd = "0"+value1;
							else dd = ""+value1;
							yyyy = value2+"";
							if(yyyy.length()==2)
								yyyy = "19"+yyyy;
							System.out.println("2222");
							return (yyyy+"-"+mm+"-"+dd);
						}
					}
					else if(isMonth((String) list.get(1))!=null){
						//DD-MM-YYYY
						int value1 = isNumber((String) list.get(0));
						int value2 = isNumber((String) list.get(2));
						if(value1==-1||value2==-1) {
							//Do Nothing
							return "";
						}
						else if(value1>31&&value2<31){
							//YYYY-MM-DD
							String mm;
							String dd;
							String yyyy;
							mm = isMonth((String) list.get(1));
							if(value2<10) dd = "0"+value2;
							else dd = ""+value2;
							yyyy = value1+"";
							if(yyyy.length()==2)
								yyyy = "19"+yyyy;
							System.out.println("3333");
							return (yyyy+"-"+mm+"-"+dd);
						}
						else{
							//Do 
							String mm;
							String dd;
							String yyyy;
							mm = isMonth((String) list.get(1));
							if(value1<10) dd = "0"+value1;
							else dd = ""+value1;
							yyyy = value2+"";
							if(yyyy.length()==2)
								yyyy = "19"+yyyy;
							System.out.println("4444");
							return (yyyy+"-"+mm+"-"+dd);
						}
					}
					else{
						int value1 = isNumber((String) list.get(0));
						int value2 = isNumber((String) list.get(1));
						int value3 = isNumber((String) list.get(2));
						if(value1==-1||value2==-1||value3==-1){
							//Do Nothing
							return "";
						}
						else if(value1>31){
							//YYYY-MM-DD
							if(value2>12||value3>31){
								//Do Nothing
								return "";
							}
							else{
								String mm;
								String dd;
								String yyyy;
								if(value2<10) mm = "0"+value2;
								else mm = ""+value2;
								if(value2<10) dd = "0"+value3;
								else dd = ""+value3;
								yyyy = value1+"";
								if(yyyy.length()==2)
									yyyy = "19"+yyyy;
								return (yyyy+"-"+mm+"-"+dd);
							}
						}
						else if(value3>31){
							if(value1>12&&value2>12){
								//Do Nothing
								return "";
							}
							else if(value1>31||value2>31){
								//Do Nothing
								return "";
							}
							else if(value2>12){
								//MM-DD-YYYY
								String mm;
								String dd;
								String yyyy;
								if(value1<10) mm = "0"+value1;
								else mm = ""+value1;
								if(value2<10) dd = "0"+value2;
								else dd = ""+value2;
								yyyy = value3+"";
								if(yyyy.length()==2)
									yyyy = "19"+yyyy;
								return (yyyy+"-"+mm+"-"+dd);
								
							}
							else if(value1>12){
								//DD-MM-YYYY
								String mm;
								String dd;
								String yyyy;
								if(value2<10) mm = "0"+value2;
								else mm = ""+value2;
								if(value1<10) dd = "0"+value1;
								else dd = ""+value1;
								yyyy = value3+"";
								if(yyyy.length()==2)
									yyyy = "19"+yyyy;
								return (yyyy+"-"+mm+"-"+dd);
							}
							else{
								//MM-DD-YYYY
								String mm;
								String dd;
								String yyyy;
								if(value1<10) mm = "0"+value1;
								else mm = ""+value1;
								if(value2<10) dd = "0"+value2;
								else dd = ""+value2;
								yyyy = value3+"";
								if(yyyy.length()==2)
									yyyy = "19"+yyyy;
								return (yyyy+"-"+mm+"-"+dd);
							}
						}
						else{
							String mm;
							String dd;
							String yyyy;
							if(value1<10) mm = "0"+value1;
							else mm = ""+value1;
							if(value2<10) dd = "0"+value2;
							else dd = ""+value2;
							yyyy = value3+"";
							if(yyyy.length()==2)
								yyyy = "19"+yyyy;
							return (yyyy+"-"+mm+"-"+dd);
						}
					}
				}
				else return ("");
			}
			
			String romanToDecimal(java.lang.String romanNumber) {
			    int decimal = 0;
			    int lastNumber = 0;
			    String romanNumeral = romanNumber.toUpperCase();
			         /* operation to be performed on upper cases even if user enters roman values in lower case chars */
			    for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
			        char convertToDecimal = romanNumeral.charAt(x);

			        switch (convertToDecimal) {
			            case 'M':
			                decimal = processDecimal(1000, lastNumber, decimal);
			                lastNumber = 1000;
			                break;

			            case 'D':
			                decimal = processDecimal(500, lastNumber, decimal);
			                lastNumber = 500;
			                break;

			            case 'C':
			                decimal = processDecimal(100, lastNumber, decimal);
			                lastNumber = 100;
			                break;

			            case 'L':
			                decimal = processDecimal(50, lastNumber, decimal);
			                lastNumber = 50;
			                break;

			            case 'X':
			                decimal = processDecimal(10, lastNumber, decimal);
			                lastNumber = 10;
			                break;

			            case 'V':
			                decimal = processDecimal(5, lastNumber, decimal);
			                lastNumber = 5;
			                break;

			            case 'I':
			                decimal = processDecimal(1, lastNumber, decimal);
			                lastNumber = 1;
			                break;
			        }
			    }
			    return decimal+"";
			}

			public int processDecimal(int decimal, int lastNumber, int lastDecimal) {
			    if (lastNumber > decimal) {
			        return lastDecimal - decimal;
			    } else {
			        return lastDecimal + decimal;
			    }
			}


			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String verbartimDate = Verbartim.getText().trim();
				beginDate.setText(convertVerbatim(verbartimDate));
				
			}
		});

		Label label12 = new Label(group2, SWT.NONE);
		label12.setText("Ending Date");
		label12.setFont(new Font(Display.getCurrent(), fontData));
		final Text endingDate = new Text(group2, SWT.BORDER);
		label12.setLayoutData(data1);
		endingDate.setLayoutData(data2);
		endingDate.setData("json", "EndDate");
		endingDate.setText("");
		endingDate.setFont(new Font(Display.getCurrent(), fontData2));

		Label label13 = new Label(group2, SWT.NONE);
		label13.setText("Collection Code");
		label13.setFont(new Font(Display.getCurrent(), fontData));
		final Text collection_code = new Text(group2, SWT.BORDER);
		label13.setLayoutData(data1);
		collection_code.setLayoutData(data2);
		collection_code.setData("json", "cc");
		collection_code.setText("");
		collection_code.setFont(new Font(Display.getCurrent(), fontData2));

		
		scientific_name.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				ArrayList<DataItem> itemlist = new ArrayList<DataItem>();
				// TODO Auto-generated method stub
				String txt = scientific_name.getText();
				if (txt.endsWith("}")) {
					txt = txt.replaceAll("\\{", "");
					txt = txt.replaceAll("\\}", "");
					scientific_name.setText("");
					String items[] = txt.split("\\,");
					for (int index = 0; index < items.length; index++) {
						String item = items[index];
						item = item.replace("{", "");
						item = item.replace("}", "");
						if (item.startsWith("#")) {
							continue;
						}
						DataItem di;
						if(item.split(":").length >1){
							di = new DataItem(item.split(":")[0]
									.replaceAll("\"", ""), item.split(":")[1]
									.replaceAll("\"", ""));
							dataItems.add(di);
						}
						else{
							di = new DataItem(item.split(":")[0]
									.replaceAll("\"", ""),"");
							dataItems.add(di);
						}
						
						Text source = (Text) e.getSource();
						Group p = (Group) source.getParent();
						for (Control c : p.getChildren()) {
							if (c instanceof Text && c.getData("json") != null
									&& c.getData("json").equals(di.getName())) {
								Text t = (Text) c;
								t.setText(di.getData());
							}
						}
					}
					String s_name = "";
					String g_name = "";
					String sci_name = "";
					for(DataItem d : dataItems){
						
						System.out.println("idems:"+d.getName()+" "+d.getData());
						if(d.getName().equals("s")) {
							s_name = d.getData();
						}
						else if(d.getName().equals("g")) {
							g_name = d.getData();
						}
					}
					sci_name = g_name+" "+s_name;
					scientific_name.setText(sci_name);
					collection_code.setFocus();
				}
			}

		});
		
		collection_code.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				String txt = collection_code.getText();
				if(txt.equalsIgnoreCase("A")){
					barcode.setFocus();
				}
				else if(txt.equalsIgnoreCase("ECON")){
					barcode.setFocus();
				}
				else if(txt.equalsIgnoreCase("GH")){
					barcode.setFocus();
				}
				else if(txt.equalsIgnoreCase("NEBC")){
					barcode.setFocus();
				}
			}
		});
		
		barcode.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				String txt = barcode.getText();
				if(txt.equals("MES")){
					collection_code.setText("AMES");
					barcode.setText("");
					barcode.setFocus();
				}
				else if(txt.length()==8){
					collector.setFocus();
				}
			}
		});
		
		
		Label label14 = new Label(group2, SWT.NONE);
		label14.setText("Town");
		label14.setFont(new Font(Display.getCurrent(), fontData));
		final Text town = new Text(group2, SWT.BORDER);
		label14.setLayoutData(data1);
		town.setLayoutData(data2);
		town.setData("json", "Town");
		town.setText("");
		town.setFont(new Font(Display.getCurrent(), fontData2));
		
		

		Label label15 = new Label(group2, SWT.NONE);
		label15.setText("Identification Qualifier");
		label15.setFont(new Font(Display.getCurrent(), fontData));
		Text text15 = new Text(group2, SWT.BORDER);
		label15.setLayoutData(data1);
		text15.setLayoutData(data2);

		Label label16 = new Label(group2, SWT.NONE);
		label16.setText("County");
		label16.setFont(new Font(Display.getCurrent(), fontData));
		final Text county = new Text(group2, SWT.BORDER);
		label16.setLayoutData(data1);
		county.setLayoutData(data2);
		county.setData("json", "ct");
		county.setText("");
		county.setFont(new Font(Display.getCurrent(), fontData2));

		Label label17 = new Label(group2, SWT.NONE);
		label17.setText("Record Entered By");
		label17.setFont(new Font(Display.getCurrent(), fontData));
		final Text text17 = new Text(group2, SWT.BORDER);
		label17.setLayoutData(data1);
		text17.setLayoutData(data2);
		text17.setText(Activator.userName);
		text17.setEditable(false);
		
		ciButton5.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ciButton5.getText().equals("login      ")) {
//					
//					conn.run();
//					if(conn.getClient()==null){
//						System.out.println("connect fail");
//					}
//					else System.out.println("connect start");
//					String userId = conn.getUser(username.getText().trim(), password.getText());
//					
//					if (userId == null||userId.equals("-1")) {
//						ciLabel3.setVisible(true);
//						return;
//					}
					String userId = null;
					try {
						socket = new Socket(serveraddr.getText(),60789);
					
						outputStream = socket.getOutputStream();
						inputStream = socket.getInputStream();
						String loginString = "Username="+username.getText()+",Password="+password.getText();
						
				        byte[] type1 = ByteBuffer.allocate(4).putInt(2).array();
				        byte[] size1 = ByteBuffer.allocate(4).putInt(loginString.length()).array();
				        outputStream.write(type1);
				        outputStream.write(size1);
				        outputStream.write(loginString.getBytes());
						
				        byte[] typeAr = new byte[4];
				        inputStream.read(typeAr);
				        int type = ByteBuffer.wrap(typeAr).asIntBuffer().get();
				        if(type==3){
				        	byte[] sizeAr = new byte[4];
				        	inputStream.read(sizeAr);
				            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
				            
				            byte[] stringAr = new byte[size];
				            inputStream.read(stringAr);
				            userId=new String(stringAr);
//				            outputStream.close();
//				            inputStream.close();
				        }
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        if(userId!=null&&userId.equals("")==false
			        		&&userId.equals("-1")==false){
			        
						Activator.userId = Integer.parseInt(userId);
						Activator.userName = username.getText().trim();
						java.lang.System.out.println("Activator.userId"
								+ Activator.userId);
	//					List<Institute> inss = delegate.getInstitutes();
						ArrayList<SystemIns> ps = new ArrayList<SystemIns>();
						ciLabel1.setVisible(false);
						ciLabel2.setVisible(false);
						username.setVisible(false);
						password.setVisible(false);
						ciLabel3.setVisible(false);
						// TODO Auto-generated method stub
						ciButton5.setText("logout     ");
						group1.setVisible(true);
						group2.setVisible(true);
						group3.setVisible(true);
						group4.setVisible(true);
						timerEnabled = true;
						imageUpdate.start();
						scientific_name.setFocus();
						text17.setText(username.getText());
			        }
			        else{
			        	ciLabel3.setVisible(true);
			        }
				} else {
					Activator.userId = -1;
					ciButton5.setText("login      ");
					group1.setVisible(false);
					group2.setVisible(false);
					group3.setVisible(false);
					group4.setVisible(false);
					ciLabel1.setVisible(true);
					ciLabel2.setVisible(true);
					username.setVisible(true);
					password.setVisible(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		

		Label label18 = new Label(group2, SWT.NONE);
		label18.setText("State");
		label18.setFont(new Font(Display.getCurrent(), fontData));
		final Text state = new Text(group2, SWT.BORDER);
		label18.setLayoutData(data1);
		state.setLayoutData(data2);
		state.setData("json", "st");
		state.setText("");
		state.setFont(new Font(Display.getCurrent(), fontData2));

		group3.setLayout(new GridLayout());
		final Button missing = new Button(group3, SWT.CHECK);
		missing.setText("Flag Specimen");
		missing.setFont(new Font(Display.getCurrent(), fontData));
		// label21.setData("json", "MI");
		Label label19 = new Label(group3, SWT.NONE);
		label19.setText("   ");
		group3.setLayout(new GridLayout());
//		Button label22 = new Button(group3, SWT.CHECK);
//		label22.setText("Uncertain Date");
//		label22.setFont(new Font(Display.getCurrent(), fontData));
//		Label label20 = new Label(group3, SWT.NONE);
//		label20.setText("   ");
//		Button label23 = new Button(group3, SWT.CHECK);
//		label23.setText("Empty Space");
//		label23.setFont(new Font(Display.getCurrent(), fontData));
//		Label label21 = new Label(group3, SWT.NONE);
//		label21.setText("   ");
		final Button label24 = new Button(group3, SWT.CHECK);
		label24.setText("Folder");
		label24.setFont(new Font(Display.getCurrent(), fontData));
		//
		final Button send2 = new Button(group5, SWT.NONE);
		send2.setText("NEXT");
		send2.setLayoutData(data3);
		
		Label labe = new Label(group5, SWT.NONE);
		labe.setText("               ");
		
		
		final Button send = new Button(group5, SWT.NONE);
		send.setText("SEND");
		send.setLayoutData(data3);
		
		File file = new File(localDir+"cities_.txt");
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String content = "";
			String nextLine = "";
			while((nextLine = br.readLine())!=null){
				content+=(nextLine.split(",")[0] + "\n");
			}
			enableContentProposal(town,content.split("\n"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File file3 = new File(localDir+"counties_.txt");
		FileReader reader3;
		try {
			reader3 = new FileReader(file3);
			BufferedReader br = new BufferedReader(reader3);
			String content = "";
			String nextLine = "";
			while((nextLine = br.readLine())!=null){
				content+=(nextLine.split(",")[1] + "\n");
			}
			enableContentProposal(county,content.split("\n"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File file4 = new File(localDir+"states_.txt");
		FileReader reader4;
		try {
			reader4 = new FileReader(file4);
			BufferedReader br = new BufferedReader(reader4);
			String content = "";
			String nextLine = "";
			while((nextLine = br.readLine())!=null){
				content+=(nextLine + "\n");
			}
			enableContentProposal(state,content.split("\n"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File file2 = new File(localDir+"namesonly.txt");
		FileReader reader2;
		try {
			reader2 = new FileReader(file2);
			BufferedReader br2 = new BufferedReader(reader2);
			String nextLine = "";
			ArrayList<String> array = new ArrayList<String>();
			while((nextLine=br2.readLine())!=null){
				nextLine = nextLine.trim();
				boolean exist = false;
				int i=0;
				if(array.size()>100) i = array.size()-100;
				for(;i<array.size();i++){
					if(array.get(i).equals(nextLine)){
						exist = true;
						break;
					}
				}
				if(exist==false){
					boolean inserted = false;
					i=0;
					if(array.size()>100) i = array.size()-100;
					for(;i<array.size();i++){
						if(array.get(i).startsWith(nextLine)){
							array.add(i,nextLine);
							inserted = true;
							break;
						}
					}
					if(inserted==false){
						array.add(nextLine);
					}
				}
			}
			final String[] myArray = array.toArray(new String[array.size()]);
//			enableContentProposal(collector,myArray);
			
			final SimpleContentProposalProvider proposalProvider = new SimpleContentProposalProvider(myArray);;
			final ContentProposalAdapter proposalAdapter = new ContentProposalAdapter(collector,
					new TextContentAdapter(), proposalProvider,
					getActivationKeystroke(), getAutoactivationChars());
				
			proposalProvider.setFiltering(true);
			proposalAdapter.setPropagateKeys(true);
			proposalAdapter
					.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		town.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String test = town.getText();
				if(test.split("/").length==3){
					town.setText(test.split("/")[0].trim());
					county.setText(test.split("/")[1].trim());
					state.setText(test.split("/")[2].trim());
				}
			}
			
		});
		
		send2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				send2.setEnabled(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				send2.setEnabled(true);
				try {
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(lastNext==0){
					
					lastNext=1;
					sentCount++;
				}
				arrays.get(0).strings.add(new StringItem(barcode.getText(),null));
				for(int i=0;i<8;i++){
					Control c1 [] = group1x[i].getChildren();
					for(int j=0;j<8;j++){
						Label l1 = (Label)c1[j];
						l1.setText("");
						l1.setData("done","");
					}
					group1x[i].setBackgroundImage(null);
				}
				
				for(int i=0;i<8;i++){
					Control c1 [] = group1x[i].getChildren();
					if(i>=arrays.size()) break;
					for(int j=0;j<8;j++){
						if(j>=arrays.get(i).strings.size()) break;
						Label l1 = (Label)c1[j];
						l1.setText(arrays.get(i).strings.get(j).barcode);
						l1.setForeground(arrays.get(i).strings.get(j).color);
					}
					group1x[i].setBackgroundImage(arrays.get(i).image);
				}
				
				sendingCount = 0;
				dataItems.add(new DataItem("Next", "1"));
				if (missing.getSelection()) {
					dataItems.add(new DataItem("MI", "1"));
				} else
					dataItems.add(new DataItem("MI", "0"));

				if (label24.getSelection()) {
					dataItems.add(new DataItem("Fol", "1"));
				} else
					dataItems.add(new DataItem("Fol", "0"));
				for (Control c : group2.getChildren()) {
					String s = (String) c.getData("json");

					if (s != null && s.equals("") == false) {
						boolean flag = false;
						Text t = (Text) c;
						for (DataItem di : dataItems) {
							if (di.getName().equals(s)) {
								di.setData(t.getText());
								flag = true;
								break;
							}
						}
						if (flag == false) {
							dataItems.add(new DataItem((String) t
									.getData("json"), t.getText()));
						}

					}

				}
				String sendString = "{";
				for (DataItem item : dataItems) {
					sendString += item.getName() + ":" + item.getData() + "$";
				}
				sendString += ("#" + Activator.userId + "}");
				System.out.println(sendString);
				byte[] type1 = ByteBuffer.allocate(4).putInt(2).array();
		        byte[] size1 = ByteBuffer.allocate(4).putInt(sendString.length()).array();
		        try{
			        outputStream.write(type1);
			        outputStream.write(size1);
			        outputStream.write(sendString.getBytes());
			        outputStream.flush();
		        }catch(Exception ee){
		        	ee.printStackTrace();
		        }
				
				//////////////////////////////////////////////////////////////////
				dataItems.clear();
				barcode.setText("");
				collection_code.setText("");
				collector.setText("");
				collectorNumber.setText("");
				Verbartim.setText("");
				beginDate.setText("");
				endingDate.setText("");
				town.setText("");
				county.setText("");
				missing.setSelection(false);
				label24.setSelection(false);
//				state.setText("");
				collection_code.setFocus();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		send.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				send.setEnabled(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				send.setEnabled(true);
				
				sentCount++;
				if(lastNext==1){
					
					for(int i=0;i<7;i++){
						Control c = group1x[0].getChildren()[i];
						Label l = (Label)c;
						if(l.getText().equals("")) {
							l.setText(barcode.getText());
							break;
						}
					}
					lastNext=0;
					
				}
				group1x[0].setBackgroundImage(null);
				group1x[0].setBackgroundMode(SWT.INHERIT_FORCE);
				arrays.get(0).strings.add(new StringItem(barcode.getText(),null));
				arrays.add(0,new ImgItem());
				
				for(int i=0;i<8;i++){
					Control c1 [] = group1x[i].getChildren();
					for(int j=0;j<8;j++){
						Label l1 = (Label)c1[j];
						l1.setText("");
						l1.setData("done","");
					}
					group1x[i].setBackgroundImage(null);
				}
				
				for(int i=0;i<8;i++){
					Control c1 [] = group1x[i].getChildren();
					if(i>=arrays.size()) break;
					for(int j=0;j<8;j++){
						if(j>=arrays.get(i).strings.size()) break;
						Label l1 = (Label)c1[j];
						l1.setText(arrays.get(i).strings.get(j).barcode);
						l1.setForeground(arrays.get(i).strings.get(j).color);
					}
					group1x[i].setBackgroundImage(arrays.get(i).image);
				}
				
				
				if (missing.getSelection()) {
					dataItems.add(new DataItem("MI", "1"));
				} else
					dataItems.add(new DataItem("MI", "0"));

				if (label24.getSelection()) {
					dataItems.add(new DataItem("Fol", "1"));
				} else
					dataItems.add(new DataItem("Fol", "0"));
				for (Control c : group2.getChildren()) {
					String s = (String) c.getData("json");

					if (s != null && s.equals("") == false) {
						boolean flag = false;
						Text t = (Text) c;
						for (DataItem di : dataItems) {
							if (di.getName().equals(s)) {
								di.setData(t.getText());
								flag = true;
								break;
							}
						}
						if (flag == false) {
							dataItems.add(new DataItem((String) t
									.getData("json"), t.getText()));
						}

					}

				}
				String sendString = "{";
				for (DataItem item : dataItems) {
					sendString += item.getName() + ":" + item.getData() + "$";
				}
				sendString += ("#" + Activator.userId + "}");
				System.out.println(sendString);
				try {
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				byte[] type1 = ByteBuffer.allocate(4).putInt(2).array();
		        byte[] size1 = ByteBuffer.allocate(4).putInt(sendString.length()).array();
		        try{
			        outputStream.write(type1);
			        outputStream.write(size1);
			        outputStream.write(sendString.getBytes());
			        outputStream.flush();
		        }catch(Exception ee){
		        	ee.printStackTrace();
		        }
				
				dataItems.clear();
				barcode.setText("");
				collector.setText("");
				collectorNumber.setText("");
				collection_code.setText("");
				Verbartim.setText("");
				beginDate.setText("");
				endingDate.setText("");
				town.setText("");
				county.setText("");
				missing.setSelection(false);
				label24.setSelection(false);
//				state.setText("");
				sendingCount = 0;
				collection_code.setFocus();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		//
		Thread secondCount = new Thread() {
			long currentMilli;

			// Acquire insert specimen
			public void run() {
				System.out.println("secondCount start");

				currentMilli = System.currentTimeMillis();
				while (true) {
					// System.out.println(timerEnabled);
					if (System.currentTimeMillis() - 1000 > currentMilli
							&& timerEnabled) {
						currentMilli = System.currentTimeMillis();
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								getViewSite().getActionBars()
										.getStatusLineManager()
										.setMessage(sendingCount + "");
								sendingCount++;
							}
						});
					}
				}
			}
		};
		secondCount.start();
		

		
		
		Control ctls[] = new Control[]{scientific_name, collection_code, barcode, collector, collectorNumber, 
				Verbartim,town,county,state};
		group2.setTabList(ctls);
		scientific_name.setFocus();
	}

	void enableContentProposal(Control control, String[] items) {

		SimpleContentProposalProvider proposalProvider = null;
		ContentProposalAdapter proposalAdapter = null;
		if (control instanceof Combo) {
			Combo combo = (Combo) control;
			proposalProvider = new SimpleContentProposalProvider(
					combo.getItems());
			proposalAdapter = new ContentProposalAdapter(combo,
					new ComboContentAdapter(), proposalProvider,
					getActivationKeystroke(), getAutoactivationChars());
		} else if (control instanceof Text) {
			Text text = (Text) control;
			
			proposalProvider = new SimpleContentProposalProvider(items);
			proposalAdapter = new ContentProposalAdapter(text,
					new TextContentAdapter(), proposalProvider,
					getActivationKeystroke(), getAutoactivationChars());
		}
		proposalProvider.setFiltering(true);
		proposalAdapter.setPropagateKeys(true);
		proposalAdapter
				.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
	}

	static char[] getAutoactivationChars() {

		// To enable content proposal on deleting a char

		String delete = new String(new char[] { 8 });
		String allChars = LCL + UCL + NUMS + delete;
		return allChars.toCharArray();
	}

	static KeyStroke getActivationKeystroke() {
		KeyStroke instance = KeyStroke.getInstance(
				new Integer(SWT.CTRL).intValue(), new Integer(' ').intValue());
		return instance;
	}

	public void setFocus() {
	}
}

class DataItem {

	String name = "";
	String data = "";

	public DataItem(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

class ImgItem{
	ArrayList<StringItem> strings;
	Image image;
	public ImgItem() {
		super();
		this.strings = new ArrayList<StringItem>();
		this.image = null;
	}
}

class StringItem{
	String barcode;
	public StringItem(String barcode, Color color) {
		super();
		this.barcode = barcode;
		this.color = color;
	}
	Color color;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}

class TownItem {
	String town;
	String county;
	String State;

	public TownItem(String town, String county, String state) {
		super();
		this.town = town;
		this.county = county;
		State = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

}
