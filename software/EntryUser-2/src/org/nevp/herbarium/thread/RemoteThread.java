package org.nevp.herbarium.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.nevp.herbarium.xml.XmlTool;

import org.nevp.herbarium.sharedata.ShareData12;
import org.nevp.herbarium.sharedata.ShareData7;
import org.nevp.herbarium.sharedata.ShareData8;
import org.nevp.herbarium.sharedata.TransferData7;
import org.nevp.herbarium.sharedata.TransferData8;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Specimen;

public class RemoteThread extends Thread{
	ShareData7 s7;
	ShareData8 s8;
	ShareData12 s12;
	
	public RemoteThread(ShareData7 s7, ShareData8 s8, ShareData12 s12){
		this.s7 = s7;
		this.s8 = s8;
		this.s12 = s12;
	}
	
	public void run(){
		while (true) {
			TransferData7 d7 = s7.getShareData();
			Specimen spec = d7.getSpec();
			String split [] = d7.getOriginalFilePath().split("/");
			String sub1 = split[split.length-1];
			String sub2 = split[split.length-2];
			DataUtilsService service = new DataUtilsService();
			DataUtilsDelegate delegate = service.getDataUtilsPort();
			System.out.println("scientific name inserting = "+d7.getSpec().getScientificName());
			try{
				Specimen name = delegate.insertSpecimen(d7.getOriginalFilePath(), d7.getSpec().getTempData()
					, d7.getSpec(), d7.getByte1(), d7.getByte2(), d7.getByte3(), d7.getByte4());
				/*
				File file = new File (d7.getSpec().getWorkspace()+"/buffer.xml");
				XmlTool xml = new XmlTool();
				xml.initialize(file);//ERROR_CAMERA_5::ERROR
				xml.removeElemnt("items/item[1]");
				xml.toStream(fil
				*/
				System.out.println("d7.getOriginalFilePath()="+d7.getOriginalFilePath());
				TransferData8 d8 = new TransferData8(spec,d7.getByte4(),d7.getOriginalFilePath(),sub2);
				s8.setShareData(d8);
				s12.setShareData(d7.getOriginalFilePath());
			}catch(Exception e){
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
	}
}
