package org.nevp.herbarium.util;
import java.io.*;
import java.net.Socket;

import ou.edu.herbarium.thread2.FileEvent;

public class TCPClient {
	private int index = 0;
    private Socket socket = null;
//    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private String server;
    private boolean isConnected = false;
    private FileEvent fileEvent = null;
    private File dstFile = null;
    private FileOutputStream fileOutputStream = null;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToServer = null;
//        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    private DataOutputStream outToClient = null;
//    		 new DataOutputStream(connectionSocket.getOutputStream());

    public TCPClient(String server) {
    	this.server = server;
    }

    /**
    * Connect with server code running in local host or in any other host
    */
    public void connect() {
        while (!isConnected) {
            try {
            	System.out.println(server);
                socket = new Socket(server, 60789);
                
//              outputStream = new ObjectOutputStream(socket.getOutputStream());
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getUser(String username, String password){
    	sendString("Username="+username+",Password="+password);
    	return recvString();
    }
    
    public String recvString(){
    	try {
			inFromClient = 
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientSentence = inFromClient.readLine();
			System.out.println(clientSentence);
			return clientSentence;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
    
    public void sendString(String str){
    	try {
			outToServer = new DataOutputStream(socket.getOutputStream());
			outToServer.writeBytes(str + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
    * Sending FileEvent object.
    */
    public void downloadFile() {
        try {
        	inputStream = new ObjectInputStream(socket.getInputStream());
        	System.out.println("downloading");
            fileEvent = (FileEvent) inputStream.readObject();
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
            	return;
            }
            String outputFile = fileEvent.getDestinationDirectory() +"/"+ fileEvent.getFilename().split("\\.")[0]+index+"."+fileEvent.getFilename().split("\\.")[1];
            System.out.println(outputFile);
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }
            dstFile = new File(outputFile);
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("Output file : " + outputFile + " is successfully saved ");
//            Thread.sleep(3000);
//            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        TCPClient client = new TCPClient();
//        client.connect();
//        while(true){
//        	Thread.sleep(10000);
//        	client.sendString();
//        	client.downloadFile();
//        }
//    }

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}

