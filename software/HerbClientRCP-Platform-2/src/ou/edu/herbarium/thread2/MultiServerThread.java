
package ou.edu.herbarium.thread2;
import java.net.*;
import java.util.Scanner;
import java.io.*;

import ou.edu.herbarium.sharedata.ShareData3;

public class MultiServerThread extends Thread {
	private Socket connectionSocket = null;
	private ShareData3 s3;
	public MultiServerThread(Socket socket,ShareData3 s3) {
		super("MultiServerThread");
		this.connectionSocket = socket;
		this.s3 = s3;
		System.out.println(socket.getPort());
		System.out.println("client connected.");
	}

	
	public static String bytesToHex(byte[] bytes) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public byte [] convertString(String data){
		byte [] ret = new byte[data.length()];
		for(int i=0;i<data.length();i++){
			ret[i] = (byte) data.charAt(i);
		}
		return ret;
	}
	public void run() {
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			String readLine = "";
			
			while(true){
				readLine = s3.getShareData();
				File file = new File(readLine);
				if(file.exists()==false) {
					System.out.println("File not exist: "+readLine);
					continue;
				}
				String length = file.length()+"";
				int max = 8-length.length();
				if(length.length()<8){
					for(int i=0;i<max;i++){
						length = "0"+length;
					}
				}
				System.out.println(length);
				String type = "00000001";
//				outToClient.write(convertString(start));
				outToClient.write(convertString(type));
				outToClient.write(convertString(length));
				Thread.sleep(500);
//				Path path = Paths.get(readLine);
//				byte[] data = Files.readAllBytes(path);
//				System.out.println("data.length="+data.length);
//				outToClient.write(data);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				outToClient.close();
				inFromClient.close();
				connectionSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}