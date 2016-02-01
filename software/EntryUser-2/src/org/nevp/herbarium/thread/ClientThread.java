package org.nevp.herbarium.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.nevp.herbarium.Activator;
import org.nevp.herbarium.xml.XmlTool;
import org.nevp.herbarium.xml.XmlToolException;
import org.w3c.dom.Element;

import org.nevp.herbarium.sharedata.ErrorData;
import org.nevp.herbarium.sharedata.ErrorData2;
import org.nevp.herbarium.sharedata.ShareData;
import org.nevp.herbarium.sharedata.ShareData1;
import org.nevp.herbarium.sharedata.ShareData2;
import org.nevp.herbarium.sharedata.ShareData3;
import org.nevp.herbarium.sharedata.TransferData1;
import org.nevp.herbarium.wsclient.DataUtilsDelegate;
import org.nevp.herbarium.wsclient.DataUtilsService;
import org.nevp.herbarium.wsclient.Folder;
import org.nevp.herbarium.wsclient.Specimen;
import org.nevp.herbarium.wsclient.User;
//imagePath
public class ClientThread extends Thread {
	private Socket connectionSocket = null;
	private ShareData1 s1;
	private ShareData2 s2;
	private ShareData3 s3;
	private ErrorData de;
	private ErrorData2 de2;
	private int systemId;
	private int clientId;
	private int spanCounter = 0;// **INFO_CLIENT_100
	private String workspaceDir;
	private String currentFolder = "DefaultFolder";//Username=
	private int currentFolderId = 1;
	private XmlTool buffer;
	private DataUtilsService service;
	private DataUtilsDelegate delegate;
	class Con {
		ByteBuffer req;
		ByteBuffer resp;
		SocketAddress sa;

		public Con() {
			req = ByteBuffer.allocate(1024);
		}
	}

	public ClientThread(ShareData1 s1, ShareData3 s3, Socket socket,
			ErrorData de, Integer clientId, String workspaceDir, ErrorData2 de2) {
		this.setConnectionSocket(socket);
		this.s1 = s1;
		this.s3 = s3;
		this.setS2(new ShareData2());
		this.de = de;
		this.clientId = clientId;
		this.workspaceDir = workspaceDir;
		this.buffer = new XmlTool();
		this.de2 = de2;
		service = new DataUtilsService();
		delegate = service.getDataUtilsPort();
	}

	public void run() {

		int index2 = 0;
		try {
//			getConnectionSocket().getInputStream().read(data);
//			String clientSentence = new String(data, "UTF-8");
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(getConnectionSocket()
							.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					getConnectionSocket().getOutputStream());
			

			while (true) {
				final String clientSentence = inFromClient.readLine();
				System.out.println("---==== ClientThread Received Sentence "+clientSentence+" ====---");
				String currentUserId = "0";
				ArrayList<DataItem> itemlist = new ArrayList<DataItem>();
				if (clientSentence == null || clientSentence.equals("")) {
					continue;
				} 
				else if (clientSentence.equals("BYE")) {
					if (spanCounter == ShareData.TIME_SPAN) {
						s1.setShareData(new TransferData1(null, getS2(), s3));
						outToClient.close();
						inFromClient.close();
						getConnectionSocket().close();

						de.setShareData("INFO_CLIENT_" + (1000 + clientId)
								+ "::" + "_");
						return;
					}
					s1.setShareData(new TransferData1(null, getS2(), s3));
					spanCounter++;
					continue;
				} else if (clientSentence.startsWith("Username")) {
					String username = clientSentence.split(",")[0].split("=")[1];
					String password = clientSentence.split(",")[1].split("=")[1];
					User u = delegate.validateLogin(username, password);
					if(u!=null){
						Integer userId = u.getUserId();
						String userIdStr = userId+"";
						String length = userIdStr.length()+"";
						int siz = length.length();
						if(siz<8){
							for(int i=0;i<8-siz;i++){
								length = "0"+length;
							}
						}
						outToClient.writeChars("00000002"+length+userId+"");
//						System.out.println("login success");
					}
					else{
						outToClient.writeChars("0000000200000002"+"-1");
//						System.out.println("login failure");//System.out.println("**
					}
					continue;
				} else {
					// System.out.println(clientSentence.replaceAll("\\$",
					// "*&*"));
					final String trueSentence = clientSentence;
					System.out.println("---==== ClientThread Started ====---");
					System.out.println("trueSentence = "+trueSentence);
					
					String items[] = trueSentence.split("\\$");
					for (int index = 0; index < items.length; index++) {
						String item = items[index];
						item = item.replace("{", "");
						item = item.replace("}", "");
						if (item.startsWith("#") == false) {
							String splitItem [] = item.split(":");
							if(splitItem.length<2){
								DataItem di = new DataItem(
										item.split(":")[0].replaceAll("\"", ""),
										"");
								itemlist.add(di);
							}
							else{
								DataItem di = new DataItem(
										item.split(":")[0].replaceAll("\"", ""),
										item.split(":")[1].replaceAll("\"", ""));
								itemlist.add(di);
							}
							
						}
						else{
							currentUserId = item.replaceAll("#", "").trim();
						}
					}
					/*
					File buf = new File(workspaceDir + "/buffer.xml");
					if (buf.exists() == false) {
						buf.createNewFile();
						String contentStr = "<?xml version=\"1.0\"?>\n<items/>";
						RandomAccessFile mm = null;
						try {
							mm = new RandomAccessFile(buf, "rw");
							mm.writeBytes(contentStr);
						} catch (IOException e1) {
							// TODO �Զ���� catch ��
							e1.printStackTrace();
						} finally {
							if (mm != null) {
								try {
									mm.close();
								} catch (IOException e2) {
									// TODO �Զ���� catch ��
									e2.printStackTrace();
								}
							}
						}
					}
					this.buffer.initialize(buf);
					this.buffer.addElement("items/item");
					int count = this.buffer.count("items/item");
					Element newElement = this.buffer
							.selectElement("items/item[" + count + "]");
					for (DataItem item : itemlist) {
						newElement.setAttribute(item.getName(), item.getData());
					}
					this.buffer
							.toStream(new File(workspaceDir + "/buffer.xml"));
					 */
				}
				boolean isAlert = true;
				Specimen spec = new Specimen();

				// spec.setFolderNumber(value)
				spec.setBarcode(getData(itemlist, "Barcode"));
				String recordNo = "";
				Date currentDate = new Date();
				String curDate = (currentDate.getYear() - 100 + 2000) + "_"
						+ (currentDate.getMonth() + 1) + "_"
						+ currentDate.getDate();
				if (getData(itemlist, "record") == null)
					recordNo = "";
				else if (getData(itemlist, "record").equals(""))
					recordNo = "";
				else
					recordNo = getData(itemlist, "record");
				spec.setTempData(getData(itemlist, "cl") + "#" + recordNo);
				spec.setStateProvince(getData(itemlist, "st"));
				ArrayList<User> users = (ArrayList<User>) delegate.getUsers();
				if(currentUserId!=null&&currentUserId.equals("")==false){
					for(User u : users)
					{
						if(u.getUserId()==Integer.parseInt(currentUserId)){
							spec.setUser(u);
							break;
						}
					}
					
				}
				
				spec.setTown(getData(itemlist, "Town"));
				spec.setCounty(getData(itemlist, "ct"));
				spec.setGenus(getData(itemlist, "g"));
				spec.setSpecificEpithet(getData(itemlist, "s"));
				spec.setInfraspecificEpithet(getData(itemlist, "i"));
				spec.setScientificNameAuthorship(getData(itemlist, "a"));
				spec.setInfraspecificRank(getData(itemlist, "ir"));
				spec.setCollectionCode(getData(itemlist, "cc"));
				spec.setScientificName(getData(itemlist, "sn"));
				spec.setVerbatimEventDate(getData(itemlist, "Verbatim"));
//				spec.setSpecificEpithet(getData(itemlist, "i"));
				spec.setFolderPath(workspaceDir + "/" + currentFolder + "/"
						+ curDate);
				spec.setCountry("");
				spec.setDarwinCountry("");
				spec.setSheetNotes("");
				spec.setMissingInfo(Integer.parseInt(getData(itemlist, "MI")));
//				spec.setRecordEnterBy(value)
				Date nowDate = new Date(System.currentTimeMillis());
				GregorianCalendar cc = new GregorianCalendar();
				cc.setTime(nowDate);
				XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(cc);
				
				spec.setCreationDate(date2);
				spec.setModificationDate(date2);
				spec.setScientificName(getData(itemlist, "sn"));
				spec.setWorkspace(workspaceDir);//CollectionCode
				spec.setRecordDate(DateCurrent());
				if (getData(itemlist, "Fol").equals("0")) {
					spec.setIsFolder(false);
					spec.setFolderNumber(currentFolderId);
				} else {
					spec.setIsFolder(true);
					DataUtilsService service = new DataUtilsService();
					DataUtilsDelegate delegate = service.getDataUtilsPort();
					currentFolder = "folder_" + index2;
					Folder f = delegate.insertFolder(currentFolder,
							workspaceDir + "/" + curDate + "/" + currentFolder,
							spec.getSheetNotes());
					currentFolderId = f.getFolderId();
					index2++;
				}

				if (getData(itemlist, "BeginDate") != null
						&& getData(itemlist, "BeginDate").trim().equals("") == false) {
					spec.setBeginEventDate(DateCreate(getData(itemlist,
							"BeginDate") + " 00:00:00"));
				}
				if (getData(itemlist, "EndDate") != null
						&& getData(itemlist, "EndDate").trim().equals("") == false) {
					spec.setBeginEventDate(DateCreate(getData(itemlist,
							"EndDate") + " 00:00:00"));
				}
				String host2 = "localhost";
				boolean flag1 = false;
				
				
				if(Activator.isConveyorActivate){
				//for conveyor alert
					while (true) {
						String c = this.de2.getShareData();
						if (c == null) {
							break;
						} else {
							if (!flag1) {
								java.awt.Toolkit.getDefaultToolkit().beep();
								Thread.sleep(200);
								java.awt.Toolkit.getDefaultToolkit().beep();
								Thread.sleep(200);
								java.awt.Toolkit.getDefaultToolkit().beep();
								flag1 = true;
							}
						}
					}
					//for conveyor end
	
					int port2 = 60011;
					byte[] message2 = "conveyor".getBytes();
	
					
					String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
					Process p = Runtime.getRuntime().exec(localDir+"AIAO/UDPSend.exe");
					p.waitFor();
	//				InetAddress address2 = InetAddress.getByName(host2);
	//				DatagramPacket packet2 = new DatagramPacket(message2,
	//						message2.length, address2, port2);
	//				DatagramSocket dsocket2 = new DatagramSocket();
	//				dsocket2.send(packet2);
	//				
	//				InetAddress address3 = InetAddress.getByName(host2);
	//				DatagramPacket packet3 = new DatagramPacket(message2,
	//						message2.length, address2, 60088);
	//				DatagramSocket dsocket3 = new DatagramSocket();
	//				dsocket3.send(packet2);
					
//					System.out.println("--=== Conveyor command Sent ===--");
					Thread.sleep(3000);
				}
				s1.setShareData(new TransferData1(spec, getS2(), s3));
			}
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream(new File("errorLog.log"));
				PrintStream ps = new PrintStream(fos);
				e.printStackTrace(ps);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public XMLGregorianCalendar DateCurrent() {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date(System.currentTimeMillis()));
		XMLGregorianCalendar date2 = null;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date2;
	}

	public XMLGregorianCalendar DateCreate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		XMLGregorianCalendar date2 = null;
		try {
			Date date = sdf.parse(dateStr);
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date2;
	}

	public String getData(ArrayList<DataItem> items, String title) {
		String result = "";
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(title)) {
				result = items.get(i).getData();
				break;
			}
		}
		return result;
	}

	public Socket getConnectionSocket() {
		return connectionSocket;
	}

	public void setConnectionSocket(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	public ShareData2 getS2() {
		return s2;
	}

	public void setS2(ShareData2 s2) {
		this.s2 = s2;
	}

	public ShareData3 getS3() {
		return s3;
	}

	public void setS3(ShareData3 s3) {
		this.s3 = s3;
	}
}

class DataItem {

	String name = "";
	String data = "";

	public DataItem(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
