package org.nevp.herbarium.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class CameraError extends Thread{
	public void run(){
		try{
			DatagramSocket serverSocket = new DatagramSocket(60701);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while(true){
				for (int i = 0; i < receiveData.length; i++)
					receiveData[i] = 0;
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				System.out.println("---==== camera error ====---"+sentence);
			}
		}
		catch(Exception e){
			
		}
	}
}
