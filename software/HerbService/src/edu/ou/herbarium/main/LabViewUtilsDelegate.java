package edu.ou.herbarium.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@javax.jws.WebService(targetNamespace = "http://main.herbarium.ou.edu/", serviceName = "LabViewUtilsService", portName = "LabViewUtilsPort", wsdlLocation = "WEB-INF/wsdl/LabViewUtilsService.wsdl")
public class LabViewUtilsDelegate {

	edu.ou.herbarium.main.LabViewUtils labViewUtils = new edu.ou.herbarium.main.LabViewUtils();

	public void viTest1() {
		labViewUtils.viTest1();
	}

	public void viTest2() {
		labViewUtils.viTest2();
	}

	public void viTest3() {
		labViewUtils.viTest3();
	}

}