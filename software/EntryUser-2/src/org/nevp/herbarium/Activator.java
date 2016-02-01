package org.nevp.herbarium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.dialog.LoginDialog;
import org.nevp.herbarium.sharedata.*;
import org.nevp.herbarium.thread.*;
import org.nevp.herbarium.wsclient.SystemSetting;
import org.nevp.herbarium.xml.XmlTool;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
    //nevp-conveyer1.huh.harvard.edu
	public static Integer userId = -1;
	public static Integer cameraPosition = 0;
	public static String userName = "";
	public static Integer systemId = -1;
	public static Integer settingId = -1;
	public static SystemSetting sysSetting = null;
	public static Integer testmode = 0;
	public static String testDirectory = "";
	public static boolean isConveyorActivate = false;
	
//	public static Display consoleDisplay = null;
	
	// The shared instance
	public static CameraAgent ca;
	public static AOAgent ao;
	public static AIAgent ai;
	public static TestThread test;
	public static Integer bufferSize = 0;
	
	public static ShareData1 d1 = new ShareData1();
	public static ShareData3 d3 = new ShareData3();
	public static ShareData5 d5 = new ShareData5();
	public static ShareData6 d6 = new ShareData6();
	public static ShareData7 d7 = new ShareData7();
	public static ShareData8 d8 = new ShareData8();
	public static ShareData9 d9 = new ShareData9();
	public static ShareData10 d10 = new ShareData10();
	public static ShareData11 d11 = new ShareData11();
	public static ShareData12 d12 = new ShareData12();
	public static ErrorData de = new ErrorData();
	public static ErrorData2 de2 = new ErrorData2();
	public static ErrorData3 de3 = new ErrorData3();
	// The plugin ID
	public static final String PLUGIN_ID = "HerbRCPClient"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
//		LoginDialog dialog = new LoginDialog(Display.getDefault().getActiveShell());
//		dialog.open();
//		if(Activator.userId==-1) {
//			this.stop(context);
//			return;
//		}
		
		
		String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
//		
//
//	    File logfile = new File(localDir+"testLogger.log");  
//	    FileOutputStream fis = new FileOutputStream(logfile);  
//	    PrintStream out = new PrintStream(fis);  
//	    System.setOut(out); 
//	    --=== setting messages ===--

//	    
//	    File logfile2 = new File(localDir+"errorLogger.log");  
//	    FileOutputStream fis2 = new FileOutputStream(logfile);  
//	    PrintStream out2 = new PrintStream(fis);  
//	    System.setErr(out2); 
	    
		File configure = new File(localDir+"configure.xml");
		System.out.println(localDir+"configure.xml");
//		MessageDialog.openInformation(Display.getDefault().getActiveShell(), "localDir", localDir);
		XmlTool xml = new XmlTool();
		xml.initialize(configure);
		Element root =xml.get_doc().getDocumentElement();
		try{
			this.bufferSize = Integer.parseInt(root.getAttribute("bufsize"));
		}catch(Exception e){
			
		}
		if(this.bufferSize == null) this.bufferSize=0;
		System.out.println("bufferSize = "+this.bufferSize);
		
//		TestThread test = new TestThread(de,d3);
//		DataThread tdata = new DataThread(d1,d5,de);
//		ServerThread server = new ServerThread(Platform.getInstanceLocation().getURL().getPath(),d1,d3,de,de2);
//		ServerThread2 server2 = new ServerThread2(d3);
//		ImageThread image = new ImageThread(d5,d6,de,de3);
//		FileThread file = new FileThread(de,d6,d7,d9);
//		RemoteThread remote = new RemoteThread(d7,d8,d12);
//		ConveyorThread conveyor = new ConveyorThread(de);
//		SensorThread sensor = new SensorThread(de2);
//		TCPClientThread tcp = new TCPClientThread(d11);
//		CameraError ce = new CameraError();
//		
//		ca = new CameraAgent();
//		ai = new AIAgent();
//		ao = new AOAgent();
//		ce.start();
//		tdata.start();
//		server.start();
//		server2.start();
//		image.start();
//		file.start();
//		remote.start();
//		conveyor.start();
//		sensor.start();
//		test.start();
//		tcp.start();// ---=== DataThread started ===---
////		ca.start();
////		ai.start();
////		ao.start();
//		plugin = this;
		
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
