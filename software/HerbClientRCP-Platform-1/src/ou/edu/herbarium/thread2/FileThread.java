package ou.edu.herbarium.thread2;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.Platform;
import org.w3c.dom.Element;

import ou.edu.herbarium.Activator;
import ou.edu.herbarium.image.ImageUtils;
import ou.edu.herbarium.sharedata.ErrorData;
import ou.edu.herbarium.sharedata.ShareData2;
import ou.edu.herbarium.sharedata.ShareData6;
import ou.edu.herbarium.sharedata.ShareData7;
import ou.edu.herbarium.sharedata.ShareData9;
import ou.edu.herbarium.sharedata.TransferData6;
import ou.edu.herbarium.sharedata.TransferData7;
import ou.edu.herbarium.sharedata.TransferData9;
import ou.edu.herbarium.wsclient.DataUtilsDelegate;
import ou.edu.herbarium.wsclient.DataUtilsService;
import ou.edu.herbarium.wsclient.Specimen;
import ou.edu.herbarium.xml.XmlTool;
import ou.edu.herbarium.xml.XmlToolException;

public class FileThread extends Thread {
	ErrorData de;
	ShareData6 s6;
	ShareData7 s7;
	ShareData9 s9;

	public FileThread(ErrorData de, ShareData6 s6, ShareData7 s7, ShareData9 s9) {
		this.de = de;
		this.s6 = s6;
		this.s7 = s7;
		this.s9 = s9;
	}

	public long copyFile2(String srcFileName, String newFileName) {
		File srcFile = new File(srcFileName);
		long copySizes = 0;
		if (!srcFile.exists()) {
			copySizes = -1;
		} else if (newFileName == null) {
			copySizes = -1;
		} else {
			try {
				FileChannel fcin = new FileInputStream(srcFile).getChannel();
				FileChannel fcout = new FileOutputStream(new File(newFileName))
						.getChannel();
				long size = fcin.size();
				fcin.transferTo(0, fcin.size(), fcout);
				fcin.close();
				fcout.close();
				copySizes = size;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return copySizes;
	}

	public void run() {
		ImageUtils util = new ImageUtils();
		
		while (true) {
			int x = 0,y = 0;
			TransferData6 d6 = s6.getShareData();
			Specimen spec = d6.getSpec();
			String originalFilePath = d6.getOriginalFileName();
			// String scaledFilePath = d6.getThumbnailFileName();
			String scaledFilePath = originalFilePath;
			// copyFile2(originalFilePath+".jpg",scaledFilePath+"_1.jpg");test
			util.rotate(originalFilePath + "full.jpg");
			if(Activator.sysSetting!=null){
				x = Activator.sysSetting.getColorTargetPositionX();//850
				y = Activator.sysSetting.getColorTargetPositionY();//5260
			}
			File file = new File(originalFilePath + "full.jpg");
			
			File configureFile = new File(Platform.getInstallLocation().getURL().getPath()+"/configure.xml");
			XmlTool xml = new XmlTool();
			try {
			    BufferedImage hugeImage = ImageIO.read(file);
				xml.initialize(configureFile);
				Element color = xml.selectElement("system/color");
				for(int i=0;i<color.getChildNodes().getLength();i++){
					if(color.getChildNodes().item(i).getNodeType()==Element.ELEMENT_NODE){
						Element target = (Element)color.getChildNodes().item(i);
						int cordX = Integer.parseInt(target.getAttribute("coordinatex"));
						int cordY = Integer.parseInt(target.getAttribute("coordinatey"));
						int value = hugeImage.getRGB(cordX, cordY);
						Color readcolor = new Color(value, true);
						int r = readcolor.getRed();
						int g = readcolor.getGreen();
						int b = readcolor.getBlue();
						int sr = Integer.parseInt(target.getAttribute("red"));
						int sg = Integer.parseInt(target.getAttribute("green"));
						int sb = Integer.parseInt(target.getAttribute("blue"));
						double er = errorRate(r, g, b, sr, sg, sb);
						String name = target.getAttribute("name");
						if (er > 0.01 && name.equals("red")){
							spec.setImageError(1);
							de.setShareData("ERROR_COLOR_1007:: Red color error with a rate "+ er);
							de.setShareData("ERROR_COLOR_7:: Red color error with a rate "+ er);
						}
						if (er > 0.01 && name.equals("green")){
							spec.setImageError(1);
							de.setShareData("ERROR_COLOR_1008:: Green color error with a rate "+ er);
							de.setShareData("ERROR_COLOR_8:: Green color error with a rate "+ er);
						}
						if (er > 0.01 && name.equals("blue")){
							spec.setImageError(1);
							de.setShareData("ERROR_COLOR_1009:: Blue color error with a rate "+ er);
							de.setShareData("ERROR_COLOR_9:: Blue color error with a rate "+ er);
						}
					}
				}
			}
			catch(XmlToolException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				int red = hugeImage.getRGB(x+150+90, y+30);
//				Color redcolor = new Color(red, true);
//				int r = redcolor.getRed();
//				int g = redcolor.getGreen();
//				int b = redcolor.getBlue();
//				System.out.println("Red" + " " + r + " " + g + " " + b);
//				double er = errorRate(r, g, b, 162, 60, 60);
//				if (er > 0.01){
//					spec.setImageError(1);
//					de.setShareData("ERROR_COLOR_1007:: Red color error with a rate "+ er);
//					de.setShareData("ERROR_COLOR_7:: Red color error with a rate "+ er);
//				}
//
//				int green = hugeImage.getRGB(x+150+45, y+30);
//				Color greencolor = new Color(green, true);
//				r = greencolor.getRed();
//				g = greencolor.getGreen();
//				b = greencolor.getBlue();
//				System.out.println("Green" + " " + r + " " + g + " " + b);
//				double eg = errorRate(r, g, b, 101, 146, 81);
//				if (eg > 0.01){
//					spec.setImageError(1);
//					de.setShareData("ERROR_COLOR_1008:: Green color error with a rate "+ eg);
//					de.setShareData("ERROR_COLOR_8:: Green color error with a rate "+ eg);
//				}
//				int blue = hugeImage.getRGB(x+150, y+30);
//				Color bluecolor = new Color(blue, true);//localDir
//				r = bluecolor.getRed();
//				g = bluecolor.getGreen();
//				b = bluecolor.getBlue();
//				System.out.println("Blue" + " " + r + " " + g + " " + b);
//				double eb = errorRate(r, g, b, 31, 69, 143);
//				if (eb > 0.01){
//					spec.setImageError(1);
//					de.setShareData("ERROR_COLOR_1009:: Blue color error with a rate "+ eb);
//					de.setShareData("ERROR_COLOR_9:: Blue color error with a rate "+ eb);
//				}
//				System.out.println(er + " " + eg + " " + eb);
			util.scale(originalFilePath + "full.jpg", scaledFilePath
					+ "full_2.jpg", 2, false);
			util.scale(originalFilePath + "full_2.jpg", scaledFilePath
					+ "full_4.jpg", 2, false);
			util.scale(originalFilePath + "full_4.jpg", scaledFilePath
					+ "full_8.jpg", 2, false);
			//send feed back to client
//			try{
//				File filee = new File(scaledFilePath+ "full_8.jpg");
//				ObjectOutputStream outputStream = new ObjectOutputStream(d6.getSocket().getOutputStream());
//				FileEvent fileEvent = new FileEvent();
//		        String fileName = filee.getName();
//		        fileEvent.setDestinationDirectory("C:/Users/nevp3/Desktop/temp");
//		        fileEvent.setFilename(fileName);
//		        fileEvent.setSourceDirectory(scaledFilePath);
//		        if (filee.isFile()) {
//		            try {
//		                DataInputStream diStream = new DataInputStream(new FileInputStream(filee));
//		                long len = (int) filee.length();
//		                byte[] fileBytes = new byte[(int) len];
//		                int read = 0;
//		                int numRead = 0;
//		                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read,
//		                        fileBytes.length - read)) >= 0) {
//		                    read = read + numRead;
//		                }
//		                fileEvent.setFileSize(len);
//		                fileEvent.setFileData(fileBytes);
//		                fileEvent.setStatus("Success");
//		            } catch (Exception e) {
//		                e.printStackTrace();
//		                fileEvent.setStatus("Error");
//		            }
//		        } else {
//		            System.out.println("path specified is not pointing to a file");
//		            fileEvent.setStatus("Error");
//		        }
//		      //Now writing the FileEvent object to socket
//		        try {
//		            outputStream.writeObject(fileEvent);
//		            System.out.println("Done...Nexting");
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//			}catch(Exception e){
//				try {
//					FileOutputStream fos = new FileOutputStream(new File("errorLog.log"));
//					PrintStream ps = new PrintStream(fos);
//					e.printStackTrace(ps);
//					
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
				// feedback to client
			d6.getS3().setShareData(originalFilePath + "full.jpg");

			byte[] bytes = getbytes(new File(originalFilePath + "full.jpg"));
			byte[] bytes2 = getbytes(new File(originalFilePath
					+ "full_2.jpg"));
			byte[] bytes3 = getbytes(new File(originalFilePath
					+ "full_4.jpg"));
			byte[] bytes4 = getbytes(new File(originalFilePath
					+ "full_8.jpg"));
		
			
			

			TransferData7 d7 = new TransferData7(d6.getSpec(), bytes,
					bytes2, bytes3, bytes4, originalFilePath);
			TransferData9 d9 = new TransferData9(d6.getSpec(),
					originalFilePath, originalFilePath, "checksum");
			s7.setShareData(d7);
			s9.setShareData(d9);
		}
	}

	public double errorRate(int r, int g, int b, int sr, int sg, int sb) {
		double res = 0.0;
		double real = (Math.abs(r - sr) + Math.abs(g - sg) + Math.abs(b - sb));
		double standard = (255 + 255 + 255);
		res = real / standard;
		return res;
	}

	public int abv(int i) {
		if (i >= 0)
			return i;
		else
			return -i;
	}

	public byte[] getbytes(File file) {
		byte[] bytes = null;
		FileInputStream fin = null;
		FileChannel ch = null;
		try {
			fin = new FileInputStream(file);
			ch = fin.getChannel();
			int size = (int) ch.size();
			MappedByteBuffer buf = ch.map(MapMode.READ_ONLY, 0, size);
			bytes = new byte[size];
			buf.get(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
}
