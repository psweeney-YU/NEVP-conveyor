package ou.edu.herbarium.thread2;

import java.io.File;
import java.io.IOException;

public class DNGAgent extends Thread{
	String filePath = "";
	
	public DNGAgent(String filePath){
		this.filePath = filePath;
	}
	public void run(){
		try {
			String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
			String splited [] = localDir.split("/");
			String localDir2 = "";
			for(String s : splited){
				System.out.println(s);
				if(s.trim().contains(" ")){
					s = s.replaceAll(" ", "\" \"");
				}
				else localDir2+=(s+"/");
			}
			String command = localDir2+"converter/c.exe -c "+filePath+"\n";
			System.out.println("command="+command);
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			File ff = new File(filePath.replaceAll("cr2", "dng"));
			if(ff.exists()){
				System.out.println("DNG convert successfully");
			}
			else System.out.println("DNG convert Fail");
			Thread.sleep(6000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
