package org.nevp.herbarium.thread;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ErrorData2;
import org.nevp.herbarium.sharedata.ShareData1;
import org.nevp.herbarium.sharedata.ShareData2;
import org.nevp.herbarium.sharedata.ShareData3;

public class ServerThread2 extends Thread{
	private ShareData3 s3;
	private ArrayList<String> portBuffer = new ArrayList<String>();
	public static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public ServerThread2(ShareData3 s3){
		this.setS3(s3);
	}
	
	public void run(){
		ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(60790/*, 0, InetAddress.getByName("127.0.0.1")*/);
	        while (listening)
	        new MultiServerThread(serverSocket.accept(),getS3()).start();
	        serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 6789.");//camera
            System.exit(-1);
        }
	}

	public ShareData3 getS3() {
		return s3;
	}

	public void setS3(ShareData3 s3) {
		this.s3 = s3;
	}


	
}
