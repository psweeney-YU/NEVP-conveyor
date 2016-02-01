package ou.edu.herbarium.thread2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServerThread2 extends Thread{

	public void run() {
		ServerSocket servsock;
		try {
			servsock = new ServerSocket(4444);
		
			byte [] fileName  = new byte[1024];
			
			FileInputStream fis = null;
			OutputStream os = null;
			InputStream is = null;
			while (true) {
				Socket sock = servsock.accept();
				try {
					byte [] chars  = new byte[1024];
					byte[] mybytearray = new byte[1024];
					os = sock.getOutputStream();
					is = sock.getInputStream();
					int count;
					is.read(chars);
					String revcStr = new String(chars, "UTF-8");
					if(revcStr.startsWith("FILE")){
						String fileNameStr = revcStr.split("&&&")[1];
						File myFile = new File(fileNameStr);
						fis = new FileInputStream(myFile);
						System.out.println(fileNameStr);
						while ((count = fis.read(mybytearray)) >= 0) {
							os.write(mybytearray, 0, count);
						}
						os.flush();
					}
					else if(revcStr.startsWith("METADATA")){
						String fileNameStr = revcStr.split("&&&")[1];
						System.out.println(fileNameStr);
					}
					else if(revcStr.startsWith("STATUS")){
						String fileNameStr = revcStr.split("&&&")[1];
						System.out.println(fileNameStr);
					}
				} finally {
					os.close();
					sock.close();
					System.out.println("Socket closed");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
