package ou.edu.herbarium.thread2;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import ou.edu.herbarium.image.ImageUtils;
import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ErrorData2;
import ou.edu.herbarium.sharedata.TransferData6;
import ou.edu.herbarium.sharedata.TransferData7;
import ou.edu.herbarium.sharedata.TransferData9;

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
