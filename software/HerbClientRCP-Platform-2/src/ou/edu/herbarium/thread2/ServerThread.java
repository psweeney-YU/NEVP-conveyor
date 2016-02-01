package ou.edu.herbarium.thread2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ErrorData2;
import ou.edu.herbarium.sharedata.ShareData1;
import ou.edu.herbarium.sharedata.ShareData2;
import ou.edu.herbarium.sharedata.ShareData3;

public class ServerThread extends Thread{
	private ShareData1 s1;
	private ShareData2 s2;
	private ShareData3 s3;
	private ErrorData de;
	private ErrorData2 de2;
	private static int clientId = 100;
	private ArrayList<String> portBuffer = new ArrayList<String>();
	private String workspaceDir;
	public static ServerSocket serverSocket;
	public static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public ServerThread(String workspaceDir,ShareData1 s1,ShareData3 s3,  ErrorData de,ErrorData2 de2){
		this.workspaceDir = workspaceDir;
		this.s1 = s1;
		this.s3 = s3;
		this.de = de;
		this.de2 = de2;
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(60789);
			while(true){
				Socket client = serverSocket.accept();
				String address = new String(client.getInetAddress().getHostAddress());
				boolean flag = true;
				String port = client.getPort()+"";
//				for(String str : portBuffer){
//					if(str.equals((client.getPort()-1)+"")){
//						flag = false;
//						portBuffer.remove(str);
//						break;
//					}
//				}
				if(flag){
					String info = address+":"+port;
					de.setShareData("INFO_CLIENT_"+clientId+"::"+info);//8082
					
					
					ClientThread loader = new ClientThread(this.s1,this.s3,client,de,clientId,workspaceDir,de2);
					clients.add(loader);
					portBuffer.add(client.getPort()+"");
					loader.start();
					clientId++;
				}
//				else{
//					ShareData2 s2 = null;
//					for(ClientThread c : clients){
//						if(((c.getConnectionSocket().getPort()+1)+"").equals(port)){
//						if(c.getConnectionSocket().getPort()==65500){
//							System.out.println("65500!");
//							s2 = c.getS2();
//							break;
//						}
//					}
//					if(s2!=null){
//						ClientThread2 loader = new ClientThread2(s2,client,de,clientId-1);
//						TestThread tth = new TestThread(de,s2);
//						loader.start();
//						tth.start();
//					}Could not listen on port
//				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ShareData2 getS2() {
		return s2;
	}

	public void setS2(ShareData2 s2) {
		this.s2 = s2;
	}

	
}
