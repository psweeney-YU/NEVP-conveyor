package org.nevp.herbarium.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.eclipse.swt.widgets.Display;
import org.nevp.herbarium.Activator;
import org.nevp.herbarium.sharedata.ShareData11;
import org.nevp.herbarium.sharedata.ShareData7;
import org.nevp.herbarium.sharedata.TransferData7;

public class TCPClientThread  extends Thread {
	ShareData11 s11;
	public TCPClientThread(ShareData11 s11) {
		super();
		this.s11 = s11;
	}
	public void run() {
		try {
		Socket clientSocket = new Socket("127.0.0.1", 60789);
		while (true) {
			
				String  text = s11.getShareData();
				System.out.println("---==== TCPClientThread start====---");
				DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());
				if (text.equals("bye"))
					break;
				outToServer.writeBytes(text + '\n');
				outToServer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
