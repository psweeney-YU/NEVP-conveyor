package ou.edu.herbarium;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;



import ou.edu.herbarium.dialog.InstituteDialog;
import ou.edu.herbarium.dialog.LoginDialog;
import ou.edu.herbarium.sharedata.*;
import ou.edu.herbarium.thread2.*;
import ou.edu.herbarium.wsclient.SystemSetting;
import ou.edu.herbarium.xml.XmlTool;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "TestView"; //$NON-NLS-1$//TestView
	public static Integer userId = -1;
	public static Integer systemId = -1;
	public static Integer settingId = -1;
	public static SystemSetting sysSetting = null;
	public static Integer testmode = 0;
	public static String testDirectory = "";
	
	
	// The shared instance
	private static Activator plugin;
	public static CameraAgent ca;
	public static AOAgent ao;
	public static AIAgent ai;
	public static TestThread test;
	public static Integer bufferSize = 0;
	public static Integer initBufferSize = 0;
	public static Integer nextSize = 0;
	public static ShareData1 d1 = new ShareData1();
	public static ShareData3 d3 = new ShareData3();
	public static ShareData5 d5 = new ShareData5();
	public static ShareData6 d6 = new ShareData6();
	public static ShareData7 d7 = new ShareData7();
	public static ShareData8 d8 = new ShareData8();
	public static ShareData9 d9 = new ShareData9();
	public static ShareData10 d10 = new ShareData10();
	public static ErrorData de = new ErrorData();
	public static ErrorData2 de2 = new ErrorData2();
	public static ErrorData3 de3 = new ErrorData3();
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
		
		LoginDialog dialog = new LoginDialog(Display.getDefault().getActiveShell());
		dialog.open();
		if(Activator.userId==-1) {
			this.stop(context);
			return;
		}
		String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
		File configure = new File(localDir+"configure.xml");
		XmlTool xml = new XmlTool();
		xml.initialize(configure);
		Element root =xml.get_doc().getDocumentElement();
		try{
			this.bufferSize = Integer.parseInt(root.getAttribute("bufsize"));
		}catch(Exception e){
			
		}
		if(this.bufferSize == null) this.bufferSize=0;
		
		TestThread test = new TestThread(de,d3);
		DataThread tdata = new DataThread(d1,d5,de,de2);
		ServerThread server = new ServerThread(Platform.getInstanceLocation().getURL().getPath(),d1,d3,de,de2);
		ServerThread2 server2 = new ServerThread2(d3);
		ImageThread image = new ImageThread(d5,d6,de,de3);
		FileThread file = new FileThread(de,d6,d7,d9);
		RemoteThread remote = new RemoteThread(d7,d8);
		ConveyorThread conveyor = new ConveyorThread(de);
		SensorThread sensor = new SensorThread(de2);
		CameraError ce = new CameraError();
		ca = new CameraAgent();
		ai = new AIAgent();
		ao = new AOAgent();
		ce.start();
		tdata.start();
		server.start();
		server2.start();
		image.start();
		file.start();
		remote.start();
		conveyor.start();
		sensor.start();
		test.start();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		System.out.println("stopped");
		if(ai!=null&&ai.p!=null)
			ai.p.destroy();
		if(ao!=null&&ao.p!=null)
			ao.p.destroy();
		
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
