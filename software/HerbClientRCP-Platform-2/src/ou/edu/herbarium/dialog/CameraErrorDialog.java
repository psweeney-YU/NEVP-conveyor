package ou.edu.herbarium.dialog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.views.Monitor;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;

public class CameraErrorDialog extends Dialog {
	
	public static final int SKIP_ID = 0;
	public static final int RETRY_ID = 1;
	public static final int CANCEL_ID = 1;

	private Button buttonSkip;
	private Button buttonRetry;
	
	public CameraErrorDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(final Composite parent) {
		Composite comp = (Composite)super.createDialogArea(parent);
		comp.getShell().setText("Camera Error");
		Label label = new Label(parent, SWT.NONE);
		label.setText("      The camera encounters a problem, please select ");
		return parent;
	}
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonSkip = createButton(parent,this.SKIP_ID,"Skip",true);
		buttonRetry = createButton(parent,this.RETRY_ID,"Retry",false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(this.SKIP_ID == buttonId){
			Activator.de3.setShareData("0");
			if(Monitor.cameraErrorFlag!=null){
				Monitor.cameraErrorFlag.dispose();
				Monitor.cameraErrorFlag = null;
			}
			close();
		}else if(this.RETRY_ID == buttonId){
			Activator.de3.setShareData("1");
			if(Monitor.cameraErrorFlag!=null){
				Monitor.cameraErrorFlag.dispose();
				Monitor.cameraErrorFlag = null;
			}
			close();
		}
	}
}
