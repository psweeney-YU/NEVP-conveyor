package org.nevp.herbarium.thread;

import java.io.IOException;
import java.util.Scanner;

import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ShareData2;
import org.nevp.herbarium.sharedata.ShareData3;
import org.nevp.herbarium.util.TCPClient;

public class TCPConnection{
	String server;
	TCPClient client;
	String userId = "-1";
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TCPClient getClient() {
		return client;
	}

	public void setClient(TCPClient client) {
		this.client = client;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public TCPConnection(String server) {
		super();
		this.server = server;
		this.client = null;
	}
	
	public void run(){
		TCPClient client = new TCPClient(this.server);
		client.connect();
		if(client.isConnected()==false){
			System.out.println("CONNECT FAILL");
			this.client = null;
			return;
		}
		else this.client = client;
	}
	
	public String getUser (String username, String password){
		userId = client.getUser(username, password);
		return userId;
	}
}
