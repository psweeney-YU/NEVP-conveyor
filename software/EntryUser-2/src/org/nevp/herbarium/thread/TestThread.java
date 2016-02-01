package org.nevp.herbarium.thread;

import java.util.Scanner;

import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ShareData2;
import org.nevp.herbarium.sharedata.ShareData3;

public class TestThread extends Thread{
	private ShareData3 s3;
	private ErrorData de;
	public TestThread(ErrorData de, ShareData3 s3) {
		super();
		this.de = de;
		this.s3 = s3;
	}
	
	public void run(){
		while(true){
			System.out.println("input:");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			System.out.println(line);
			s3.setShareData(line);
		}
	}
	public ErrorData getDe() {
		return de;
	}
	public void setDe(ErrorData de) {
		this.de = de;
	}
	public ShareData3 getS3() {
		return s3;
	}
	public void setS3(ShareData3 s3) {
		this.s3 = s3;
	}

}
