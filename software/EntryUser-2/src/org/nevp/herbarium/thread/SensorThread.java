package org.nevp.herbarium.thread;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.nevp.herbarium.image.ImageUtils;

import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ErrorData2;
import org.nevp.herbarium.sharedata.TransferData6;
import org.nevp.herbarium.sharedata.TransferData7;
import org.nevp.herbarium.sharedata.TransferData9;

public class SensorThread extends Thread{
	private ErrorData2 de2;
	
	public SensorThread(ErrorData2 de) {
		super();
		this.de2 = de;
	}
	
	public void run(){
		try{
			System.out.println("1111");
			DatagramSocket serverSocket = new DatagramSocket(60107);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while(true){
				for (int i = 0; i < receiveData.length; i++)
					receiveData[i] = 0;
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
//				System.out.println("link"+sentence);
				de2.setShareData("INFO_SENSOR_0::run");
			}
		}
		catch(Exception e){
			
		}
	}
	
}
