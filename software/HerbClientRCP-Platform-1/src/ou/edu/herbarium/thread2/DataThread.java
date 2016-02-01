package ou.edu.herbarium.thread2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ErrorData2;
import ou.edu.herbarium.sharedata.ShareData1;
import ou.edu.herbarium.sharedata.ShareData2;
import ou.edu.herbarium.sharedata.ShareData5;
import ou.edu.herbarium.sharedata.TransferData1;
import ou.edu.herbarium.sharedata.TransferData5;
import ou.edu.herbarium.wsclient.Specimen;

public class DataThread extends Thread{
	
	private ShareData1 s1;
	private ShareData5 s5;
	private ErrorData  e1;
	private boolean conveyorstatus = true;
	
	private static int flag = 0;
	public DataThread(ShareData1 s1, ShareData5 s5, ErrorData e1, ErrorData2 de2){
		this.s1 = s1;
		this.s5 = s5;
		this.e1 = e1;
	}
	
	public void run(){
		try{
		while(true){
			
			//TODO: 1: connect to conveyor and get status from conveyor
			String feedback = "";
			String originalFilePath = "";
			if(!conveyorstatus){
				this.e1.setShareData("ERROR_CONVEYOR_51:"+feedback);//fake
				continue;
			}
			TransferData1 td1 = this.s1.getShareData();
			
//			if(td1.getSpec().isNext()==true){
//				Activator.bufferSize;
//				this.s1.sync();
//			}
			
			ArrayList<Specimen> specs = td1.getSpec();
			for(Specimen s : specs){
			//TODO: generate original file path and name, base on QR info in metadata
				File dir = new File(s.getFolderPath());//Yiming
				if(dir.exists()==false||dir.isDirectory()==false){
					dir.mkdir();
				}
				File subdir = new File(s.getFolderPath()+"/"+s.getBarcode());
				if(subdir.exists()){
					for(int i=1;i<100000;i++){
						File subdir2 = new File(s.getFolderPath()+"/"+s.getBarcode()+"_"+i);
						if(subdir2.exists()==false){
							subdir2.mkdir();
							originalFilePath = subdir2.getAbsolutePath()+"/";
							break;
						}
					}
				}
				else originalFilePath = subdir.getAbsolutePath()+"/";
				originalFilePath = originalFilePath.replaceAll("\\\\", "/");
				File newdir = new File(originalFilePath);
				newdir.mkdir();
				TransferData5 d5 = new TransferData5(s,td1.getSocket(),td1.getS2(),td1.getS3(),originalFilePath);
				s5.setShareData(d5);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}


