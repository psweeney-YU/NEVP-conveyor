package org.nevp.herbarium.utils;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Test2 {
	public int port = 0;
	public static void main(String argv[]) throws Exception {
		String sentence;
		Socket clientSocket = new Socket("127.0.0.1", 60789);
//		TCPClient2 tcp2 = new TCPClient2(clientSocket.getLocalPort()+1);
//		tcp2.run();
		int index = 0;
		while (true) {
			Scanner sc = new Scanner(System.in);
			String text = sc.nextLine();
//			Thread.sleep(18000);
//			String text = "asdasd";
			if(text.equals("test"))
				sentence = "{\"m1p\":\"[NEVP TCN]\"$\"m2v\":\"1.1\"$\"sn\":\"Diphasiastrum sabinifolium\"$\"g\":\"Diphasiastrum\"$\"s\":\"sabinifolium\"$\"ir\":\"1\"$\"i\":\"\"$\"a\":\"(Willd.) Holub\"$\"st\":\"Vermont\"$\"ct\":\"Addison\"$\"cl\":\"Yiming Xu - for test\"$\"record\":\"10010\"$\"cc\":\"YU\"$\"RecordEnteredBy\":\"Aimee\"$\"Town\":\"Norman\"$\"IDqual\":\"Wade\"$\"BeginDate\":\"2003-11-09\"$\"EndDate\":\"2003-11-20\"$\"Verbatim\":\"Nov. 9 2003\"$\"Barcode\":\"YU."+index+"\"$\"MI\":\"0\"$\"IllInfo\":\"0\"$\"UR\":\"0\"$\"UD\":\"0\"$\"Emp\":\"0\"$\"Fol\":\"1\"$\"Oth\":\"0\"$#4}";
			else sentence = "{\"m1p\":\"[NEVP TCN]\"$\"m2v\":\"1.1\"$\"sn\":\"Diphasiastrum sabinifolium\"$\"g\":\"Diphasiastrum\"$\"s\":\"sabinifolium\"$\"ir\":\"1\"$\"i\":\"\"$\"a\":\"(Willd.) Holub\"$\"st\":\"Vermont\"$\"ct\":\"Addison\"$\"cl\":\"Yiming Xu - for test\"$\"record\":\"10010\"$\"cc\":\"YU\"$\"RecordEnteredBy\":\"Aimee\"$\"Town\":\"Norman\"$\"IDqual\":\"Wade\"$\"BeginDate\":\"2003-11-09\"$\"EndDate\":\"2003-11-20\"$\"Verbatim\":\"Nov. 9 2003\"$\"Barcode\":\"YU."+index+"\"$\"MI\":\"1\"$\"IllInfo\":\"0\"$\"UR\":\"0\"$\"UD\":\"0\"$\"Emp\":\"0\"$\"Fol\":\"0\"$\"Oth\":\"0\"$#4}";
			DataOutputStream outToServer = new DataOutputStream(
					clientSocket.getOutputStream());
			if (sentence.equals("bye"))
				break;
			outToServer.writeBytes(sentence + '\n');
			index++;
			System.out.println(index);
		}
		clientSocket.close();
	}
}

