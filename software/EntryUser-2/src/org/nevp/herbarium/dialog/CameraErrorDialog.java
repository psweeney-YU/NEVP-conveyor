package org.nevp.herbarium.dialog;

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

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.view2.Monitor;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;

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
		byte[] message = "steing".getBytes();
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			DatagramPacket packet = new DatagramPacket(message,
					message.length, address, 60888);
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);//copyFile
			dsocket.close();//		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
