package org.nevp.herbarium.thread;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.nevp.herbarium.image.ImageUtils;
import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.TransferData6;
import org.nevp.herbarium.sharedata.TransferData7;
import org.nevp.herbarium.sharedata.TransferData9;

public class ConveyorThread extends Thread{
	private ErrorData de;

	public ConveyorThread(ErrorData de) {
		super();
		this.de = de;
	}
	
	public void run(){
		try{
			DatagramSocket serverSocket = new DatagramSocket(60105);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while(true){
				for (int i = 0; i < receiveData.length; i++)
					receiveData[i] = 0;
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
//				de.setShareData("INFO_CONVEYOR_0::run");
			}
		}
		catch(Exception e){
			
		}
	}
	
}
