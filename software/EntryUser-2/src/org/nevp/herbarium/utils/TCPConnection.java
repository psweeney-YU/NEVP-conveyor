package org.nevp.herbarium.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPConnection extends Thread{
	public void run(){
		try {
			String sentence;
			Socket clientSocket = new Socket("127.0.0.1", 60789);
			int index = 0;
			while (true) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
