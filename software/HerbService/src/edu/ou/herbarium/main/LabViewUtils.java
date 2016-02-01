package edu.ou.herbarium.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LabViewUtils {

	public void viTest1(){
		try {
			String host = "127.0.0.1";
			System.out.println("viTest1");
			byte[] message = "vitest1-message".getBytes();
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(message, message.length,
        			address,65500);
        	DatagramSocket dsocket = new DatagramSocket();
        	dsocket.send(packet);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viTest2(){
		try {
			String host = "127.0.0.1";
			System.out.println("viTest2");
			byte[] message = "vitest2-message".getBytes();
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(message, message.length,
        			address,65500);
        	DatagramSocket dsocket = new DatagramSocket();
        	dsocket.send(packet);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viTest3(){
		try {
			String host = "127.0.0.1";
			System.out.println("viTest3");
			byte[] message = "vitest3-message".getBytes();
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(message, message.length,
        			address,65500);
        	DatagramSocket dsocket = new DatagramSocket();
        	dsocket.send(packet);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
