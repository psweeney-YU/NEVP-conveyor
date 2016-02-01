package org.nevp.herbarium.thread;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.FileChannel;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jface.dialogs.MessageDialog;

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ErrorData3;
import org.nevp.herbarium.sharedata.ShareData5;
import org.nevp.herbarium.sharedata.ShareData6;
import org.nevp.herbarium.sharedata.TransferData5;
import org.nevp.herbarium.sharedata.TransferData6;
import org.nevp.herbarium.util.ASyncUDPSvr;

public class ImageThread extends Thread{
	ShareData5 s5;
	ShareData6 s6;
	ErrorData de;
	ErrorData3 de3;
	//Socket
	int index = 0;
	
	public ImageThread(ShareData5 s5,ShareData6 s6,ErrorData de, ErrorData3 de3){
		this.s5 = s5;
		this.s6 = s6;
		this.de = de;
		this.de3 = de3;
	}
	
	public void run(){
		try {
		String sentence = "";//Display
//		DatagramSocket serverSocket = new DatagramSocket(60005);
			while (true) {
				final TransferData5 d5 = s5.getShareData();
				System.out.println(" ---=== ImageThread started ===---");
				if(d5.getSpec().getIsFolder()==false){
					String ret = "";
					if(Activator.testmode == 0){
						String host = "127.0.0.1";
						int port = 60002;
						
						System.out.println( d5.getOriginalFileName());
						String tempDir = "C:/ImageStorage";
						File tempDirFile = new File(tempDir);
						if(tempDirFile.exists()==false){
							tempDirFile.mkdir();
						}
						byte[] message = tempDir.getBytes();
						InetAddress address = InetAddress.getByName("127.0.0.1");
						long currentMilli = System.currentTimeMillis();
						DatagramPacket packet = new DatagramPacket(message,
								message.length, address, 60002);
						DatagramSocket dsocket = new DatagramSocket();
						dsocket.send(packet);//copyFile
						dsocket.close();
						
						ASyncUDPSvr svr = new ASyncUDPSvr(60005);
						ret = svr.process();
						System.out.println("RET="+ret+" "+(System.currentTimeMillis()-currentMilli)+"*");
						if(ret==null){
							File tempFolder = new File(tempDir);
							System.out.println("current milli = "+System.currentTimeMillis());
							
							for(File f : tempFolder.listFiles()){
								if(f.getName().endsWith(".cr2")&&System.currentTimeMillis()-f.lastModified()<18000){
									ret = f.getAbsolutePath().replaceAll("test", "full").replaceAll("cr2", "jpg");
									
									System.out.println("Did not receive ret");
									break;
								}
							}
						}
						if(ret==null||ret.startsWith("ERROR")){
							java.awt.Toolkit.getDefaultToolkit().beep();
							de.setShareData("ERROR_CAMERA_5::ERROR");
							Thread.sleep(1000);
							String buttonReply = de3.getShareData();
							if(buttonReply.equals("0")){
								svr.close();
								continue;
							}
							else if(buttonReply.equals("1")){
								s5.setShareFirstData(d5);
								svr.close();
								continue;
							}
							else if(buttonReply.equals("2")){
								
							}
						}
						svr.close();
					}
					else{
						ret = Activator.testDirectory+"full_0.jpg";
					}
					
					
					String path = d5.getOriginalFileName();
					String [] pathArray = path.split("/");
					String pathSentence = "";
					for(int i=0;i<pathArray.length;i++){
						pathSentence+=pathArray[i]+"/";
						File tempDirFile = new File(pathSentence);//converter
						if(tempDirFile.exists()==false){
							tempDirFile.mkdir();
						}
					}
					ret = ret.trim().replaceAll("\\\\", "/");
					File temp = new File(ret);
					copyFile(ret,d5.getOriginalFileName()+"full.jpg");
					File oldJPG = new File(ret);
					if(Activator.testmode == 0){
						//oldJPG.delete();
					}
					
					ret = ret.replaceAll("jpg", "cr2");
					ret = ret.replaceAll("full", "test");
					temp = new File(ret);
					copyFile(ret,d5.getOriginalFileName()+"full.cr2");
					File oldCR2 = new File(ret);
					if(Activator.testmode == 0){
						//oldCR2.delete();
					}
					DNGAgent agent = new DNGAgent(d5.getOriginalFileName()+"full.cr2");
					agent.run();
					File dngFile = new File(d5.getOriginalFileName()+"full.dng");
					boolean convertflag = true;
					long currentMilli = System.currentTimeMillis();
					while(dngFile.exists()==false){
						if(System.currentTimeMillis()>currentMilli+40000){
							System.out.println("DNG FILE MISSING");
							convertflag = false;
							break;
						}
					}
					if(convertflag==true){
						File cr2File = new File(d5.getOriginalFileName()+"full.cr2");
						cr2File.delete();
						if(Activator.testmode == 0){
							oldJPG.delete();
							oldCR2.delete();
						}
					}
					File ff = new File(d5.getOriginalFileName()+"full.dng");
					FileInputStream fis = new FileInputStream(ff);
					String md5 = DigestUtils.md5Hex(fis);
					d5.getSpec().setChecksum(md5);
					
					TransferData6 d6 = new TransferData6(d5.getSpec(),d5.getS2(),d5.getS3(),d5.getOriginalFileName(),"");
					this.s6.setShareData(d6);
					Thread.sleep(1000);
				}
				else{
					//create the folder and return 
				}
					//stub
			}
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream(new File("errorLog.log"));
				PrintStream ps = new PrintStream(fos);
				e.printStackTrace(ps);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	public long copyFile(String srcFileName, String newFileName) {  
		File srcFile = new File(srcFileName);//Date
        long copySizes = 0;  
        if (!srcFile.exists()) {  
            copySizes = -1;  
        } else if (newFileName == null) {  
            copySizes = -1;  
        } else {  
            try {  
                FileChannel fcin = new FileInputStream(srcFile).getChannel();  
                FileChannel fcout = new FileOutputStream(new File(newFileName)).getChannel();  
                long size = fcin.size();  
                fcin.transferTo(0, fcin.size(), fcout);  
                fcin.close();  
                fcout.close();  
                copySizes = size;  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return copySizes;  
    }  
}
