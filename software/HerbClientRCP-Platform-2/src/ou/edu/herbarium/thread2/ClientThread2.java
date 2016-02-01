package ou.edu.herbarium.thread2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Scanner;

import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ShareData2;
public class ClientThread2 extends Thread{

	private Socket connectionSocket = null;
//	private ShareData1 s1;
	private ShareData2 s2;
	private ErrorData de;
	private int clientId;
	private int spanCounter = 0;
	
	public ClientThread2(ShareData2 s2, Socket socket, ErrorData de, Integer clientId) {
		this.connectionSocket = socket;
		this.s2 = s2;
		this.de = de;
		this.clientId = clientId;
	}
	
	public byte [] convertString(String data){
		byte [] ret = new byte[data.length()];
		for(int i=0;i<data.length();i++){
			ret[i] = (byte) data.charAt(i);
		}
		return ret;
	}
	
	public void run(){
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			while(true){
				String readLine = s2.getShareData();
//				String readLine = "C:/full_2.jpg";
//				while((readLine=br.readLine())!=null){
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
				
				String type = "00000001";
				System.out.println(type+" * "+length);
				outToClient.write(convertString(type));
				outToClient.write(convertString(length));
				System.out.println("data sended1");
				Path path = Paths.get(readLine);
				System.out.println("data sended2");
				byte[] data = Files.readAllBytes(path);
				System.out.println("data sended3");
				outToClient.write(data);
				
				System.out.println("data sended4");
			}
//			}
//		}
				

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
			
		}
			
			
//			while (true) {
//				System.out.println("waiting...");
//				String fileName = s2.getShareData();
//				System.out.println("file name = "+fileName);
//				File file = new File(fileName);
//				if(file.exists()==false)
//					de.setShareData("ERROR_FILE_0::Request file \""+ fileName +"\" not exist! ");
//				else{
//					String length = file.length()+"";
//					int siz = length.length();
//					if(siz<8){
//						for(int i=0;i<8-siz;i++){
//							length = "0"+length;
//						}
//					}
//					
//					System.out.println("00000001"+length);
//					outToClient.writeChars("00000001"+length);
//					String resp = inFromClient.readLine();
//					System.out.println(resp);
//					RandomAccessFile f = new RandomAccessFile(fileName, "r");
//					byte[] b = new byte[(int)f.length()];
//					f.read(b);
//					outToClient.write(b);
//					resp = inFromClient.readLine();
//					System.out.println(resp);
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
}
