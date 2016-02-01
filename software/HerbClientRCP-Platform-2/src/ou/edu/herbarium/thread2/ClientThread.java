package ou.edu.herbarium.thread2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

import org.w3c.dom.Element;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ErrorData2;
import ou.edu.herbarium.sharedata.ShareData;
import ou.edu.herbarium.sharedata.ShareData1;
import ou.edu.herbarium.sharedata.ShareData2;
import ou.edu.herbarium.sharedata.ShareData3;
import ou.edu.herbarium.sharedata.TransferData1;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.Folder;
import ou.edu.herbarium.wsclient.Specimen;
import ou.edu.herbarium.wsclient.User;
import ou.edu.herbarium.xml.XmlTool;
import ou.edu.herbarium.xml.XmlToolException;
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
			ErrorData de, Integer clientId, String workspaceDir,ErrorData2 de2) {
		this.setConnectionSocket(socket);
		this.s1 = s1;
		this.s3 = s3;
		this.setS2(new ShareData2());
		this.de = de;
		this.de2 = de2;
		this.clientId = clientId;
		this.workspaceDir = workspaceDir;
		this.buffer = new XmlTool();
		service = new DataUtilsService();
		delegate = service.getDataUtilsPort();
	}

	public void run() {

		String clientSentence;
		int index2 = 0;
		try {
			
//			BufferedReader inFromClient =
//               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			ArrayList<Specimen> specs = new ArrayList<Specimen>();
			InputStream inputStream = connectionSocket.getInputStream();
			OutputStream outputStream = connectionSocket.getOutputStream();
			while (true) {
				String currentUserId = "0";
				ArrayList<DataItem> itemlist = new ArrayList<DataItem>();
				//03112015
				
				byte[] typeAr = new byte[4];
		        inputStream.read(typeAr);
		        int type = ByteBuffer.wrap(typeAr).asIntBuffer().get();
		        
		        if(type!=2){
		        	continue;
		        }
		        byte[] lengthAr = new byte[4];
		        inputStream.read(lengthAr);
		        int length = ByteBuffer.wrap(lengthAr).asIntBuffer().get();
		        if (length<=0){
		        	continue;
		        }
		        byte[] stringAr = new byte[length];
		        inputStream.read(stringAr);
		        clientSentence = new String(stringAr);
		        System.out.println("clientSentence="+clientSentence);
				
				if (clientSentence == null || clientSentence.equals("")) {
					continue;
				} 
				
				else if (clientSentence.equals("BYE")) {
					if (spanCounter == ShareData.TIME_SPAN) {
						System.out.println("data loader exit");
						s1.setShareData(new TransferData1(null,connectionSocket, getS2(), s3));
						inputStream.close();
						getConnectionSocket().close();

						de.setShareData("INFO_CLIENT_" + (1000 + clientId)
								+ "::" + "_");
						return;
					}
					s1.setShareData(new TransferData1(null, connectionSocket,getS2(), s3));
					spanCounter++;
					continue;
				} else if (clientSentence.startsWith("Username")) {
					String username = clientSentence.split(",")[0].split("=")[1];
					String password = clientSentence.split(",")[1].split("=")[1];
					System.out.println("username="+username+" password="+password);
					
					User u = delegate.validateLogin(username, password);
					String userIdStr = "";
					if(u!=null){
						Integer userId = u.getUserId();
						userIdStr = userId+"";
						
						System.out.println("login success");
					}
					else{
						userIdStr = "-1";
						System.out.println("login failure");//System.out.println("**
					}
					
					byte[] typa = ByteBuffer.allocate(4).putInt(3).array();
			        byte[] size = ByteBuffer.allocate(4).putInt(userIdStr.length()).array();
			        outputStream.write(typa);
			        outputStream.write(size);
			        outputStream.write(userIdStr.getBytes());
			        outputStream.flush();
					continue;
					
//**for new entry user client
//					if(u!=null){
//						Integer userId = u.getUserId();
//						String userIdStr = userId+"";
//						String length = userIdStr.length()+"";
//						int siz = length.length();
//						if(siz<8){
//							for(int i=0;i<8-siz;i++){
//								length = "0"+length;
//							}
//						}
//						outToClient.writeBytes(userIdStr+"\n");
//						System.out.println("login success");
//					}
//					else{
//						outToClient.writeBytes("-1\n");
//						System.out.println("login failure");//System.out.println("**
//					}
				} else {
					// System.out.println(clientSentence.replaceAll("\\$",
					// "*&*"));
					String trueSentence = "";
					
					/////////////////////////////////////////////////////////////////////
//					String clientSentenceArray[] = clientSentence.split("/");
//					for(int i=0;i<clientSentenceArray.length-1;i++){
//						trueSentence = trueSentence+Character.toString((char)(Integer.parseInt(clientSentenceArray[i])));
//					}
					/////////////////////////////////////////////////////////////////////
					trueSentence = clientSentence;
					///////////////////////////////////////////////////////////////////
					
					System.out.println("trueSentence = "+trueSentence);
					String items[] = trueSentence.split("\\$");
					for (int index = 0; index < items.length; index++) {
						String item = items[index];
						item = item.replace("{", "");
						item = item.replace("}", "");
						if (item.startsWith("#") == false) {
							String str1 = "";
							String str2 = "";
							str1 = item.split(":")[0].replaceAll("\"", "");
							if(item.split(":").length>1){
								str2 = item.split(":")[1].replaceAll("\"", "");
							}
							DataItem di = new DataItem(str1,str2);
							itemlist.add(di);
						}
						else{
							currentUserId = item.replaceAll("#", "").trim();
						}
					}
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
				String collectorNme = getData(itemlist, "cl");
				if(collectorNme==null||collectorNme.equals("")){
					collectorNme = " ";
				}
				spec.setTempData(collectorNme + "#" + recordNo);
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
					spec.setEndEventDate(DateCreate(getData(itemlist,
							"EndDate") + " 00:00:00"));
				}
				
				if(getData(itemlist, "Next").equals("1")){
					spec.setNext(true);
					specs.add(spec);
				}
				else{
					spec.setNext(false);
					boolean flag1 = false;
					int counter = 0;
					while (true) {
						String c = this.de2.getShareData();//configure.xml
						if (c == null) {
							break;
						} else {
							System.out.println("c="+c);
							counter++;
							if ((!flag1)&&counter>20) {
								java.awt.Toolkit.getDefaultToolkit().beep();
								Thread.sleep(200);
								java.awt.Toolkit.getDefaultToolkit().beep();
								Thread.sleep(200);
								java.awt.Toolkit.getDefaultToolkit().beep();
								Thread.sleep(200);
								java.awt.Toolkit.getDefaultToolkit().beep();
								flag1 = true;
								counter=0;
							}
						}
					}
					//for conveyor end
		
		
					////////////////////////////////////////////////////////////////
					String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
					Process p = Runtime.getRuntime().exec(localDir+"AIAO/UDPSend.exe");
					p.waitFor();
		//			///////////////////////////////////////////////////////////////
					Thread.sleep(4500);
					specs.add(spec);
					s1.setShareData(new TransferData1(specs, connectionSocket,getS2(), s3));
					specs = new ArrayList<Specimen>();
				}
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
